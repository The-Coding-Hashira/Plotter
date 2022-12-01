package bg.sofia.tu.iti.math.operator.input.extractor;

import bg.sofia.tu.iti.math.core.input.token.extractor.NestedTokenValueExtractor;
import bg.sofia.tu.iti.math.core.input.token.extractor.RegexTokenValueExtractor;
import bg.sofia.tu.iti.math.core.input.token.extractor.TokenValueExtractor;
import bg.sofia.tu.iti.math.operator.notation.OperatorNotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OperatorTokenValueExtractor implements TokenValueExtractor{
    private final NestedTokenValueExtractor extractor;

    public OperatorTokenValueExtractor(){
        List<TokenValueExtractor> extractors = new ArrayList<>();
        extractors.add(new RegexTokenValueExtractor("[" + OperatorNotation.PLUS.getNotation() + "]"));
        extractors.add(new RegexTokenValueExtractor("[" + OperatorNotation.MINUS.getNotation() + "]"));
        extractors.add(new RegexTokenValueExtractor("[" + OperatorNotation.MULTIPLY.getNotation() + "]"));
        extractors.add(new RegexTokenValueExtractor("[" + OperatorNotation.DIVIDE.getNotation() + "]"));
        extractors.add(new RegexTokenValueExtractor("[\\" + OperatorNotation.POWER.getNotation() + "]"));
        extractors.add(new RegexTokenValueExtractor("[" + OperatorNotation.FACTORIAL.getNotation() + "]"));
        extractors.add(new RegexTokenValueExtractor("[" + OperatorNotation.EQUALS.getNotation() + "]"));
        extractor = new NestedTokenValueExtractor(extractors);
    }

    @Override
    public Map<Integer, String> extract(String expression){
        return extractor.extract(expression);
    }
}
