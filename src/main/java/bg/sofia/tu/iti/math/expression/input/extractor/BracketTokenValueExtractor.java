package bg.sofia.tu.iti.math.expression.input.extractor;

import bg.sofia.tu.iti.math.core.input.token.extractor.NestedTokenValueExtractor;
import bg.sofia.tu.iti.math.core.input.token.extractor.RegexTokenValueExtractor;
import bg.sofia.tu.iti.math.core.input.token.extractor.TokenValueExtractor;
import bg.sofia.tu.iti.math.expression.input.token.type.BracketType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BracketTokenValueExtractor implements TokenValueExtractor{
    private final NestedTokenValueExtractor extractor;

    public BracketTokenValueExtractor(){
        List<TokenValueExtractor> extractors = new ArrayList<>();
        extractors.add(new RegexTokenValueExtractor("[" + BracketType.OPEN_BRACKET.getNotation() + "]"));
        extractors.add(new RegexTokenValueExtractor("[" + BracketType.CLOSE_BRACKET.getNotation() + "]"));
        extractor = new NestedTokenValueExtractor(extractors);
    }

    @Override
    public Map<Integer, String> extract(String expression){
        return extractor.extract(expression);
    }
}
