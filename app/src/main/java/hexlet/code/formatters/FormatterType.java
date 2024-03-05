package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public interface FormatterType {
    String format(List<Map<String, Object>> data) throws Exception;
}
