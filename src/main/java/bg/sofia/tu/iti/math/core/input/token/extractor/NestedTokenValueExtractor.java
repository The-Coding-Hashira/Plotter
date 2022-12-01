package bg.sofia.tu.iti.math.core.input.token.extractor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NestedTokenValueExtractor implements TokenValueExtractor{
    private final List<TokenValueExtractor> extractors;

    public NestedTokenValueExtractor(List<TokenValueExtractor> extractors){
        this.extractors = extractors;
    }

    @Override
    public Map<Integer, String> extract(String expression){
        Map<Integer, String> tokenValueIndexes = new HashMap<>();
        for(TokenValueExtractor extractor : extractors){
            tokenValueIndexes.putAll(extractor.extract(expression));
        }
        return tokenValueIndexes;
    }
}
