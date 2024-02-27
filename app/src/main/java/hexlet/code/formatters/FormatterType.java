package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public interface FormatterType {
    String format(Map<String, List<Object>> data) throws Exception;
}
