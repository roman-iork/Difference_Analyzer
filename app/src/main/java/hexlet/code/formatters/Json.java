package hexlet.code.formatters;

import hexlet.code.Parser;

import java.util.List;
import java.util.Map;

public final class Json implements FormatterType {
    public String format(Map<String, List<Object>> difference) throws Exception {
        return Parser.parseFromJvObj(difference);
    }
}
