package bg.sofia.tu.iti.math.core.input.token.extractor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTokenValueExtractor implements TokenValueExtractor{
    private final Pattern              pattern;
    private final Map<Integer, String> tokenValueIndexes;

    public RegexTokenValueExtractor(String regex){
        pattern           = Pattern.compile(regex);
        tokenValueIndexes = new HashMap<>();
    }

    @Override
    public Map<Integer, String> extract(String expression){
        Matcher matcher = pattern.matcher(expression);
        while(matcher.find()){
            tokenValueIndexes.put(matcher.start(), matcher.group());
        }
        return tokenValueIndexes;
    }
}
