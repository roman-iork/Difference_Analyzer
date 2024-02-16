package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public interface FormatterType {
    String format() throws Exception;

    void setData(Map<String, List<Object>> data);
}
