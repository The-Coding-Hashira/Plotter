package bg.sofia.tu.iti.math.core.input.token.extractor;

import java.util.Map;

public interface TokenValueExtractor{
    Map<Integer, String> extract(String expression);
}
