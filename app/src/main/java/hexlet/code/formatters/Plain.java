package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Plain implements FormatterType {

    public String format(List<Map<String, Object>> difference) {
        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> element : difference) {
            if (element.get("diffType").equals("removed")) {
                sb.append("Property '" + element.get("key") + "' was removed\n");
            } else if (element.get("diffType").equals("added")) {
                sb.append("Property '" + element.get("key")
                        + "' was added with value: " + convertedValue(element.get("new")) + "\n");
            } else if (element.get("diffType").equals("unchanged")) {
                continue;
            } else {
                sb.append("Property '" + element.get("key")
                        + "' was updated. From " + convertedValue(element.get("old")));
                sb.append(" to " + convertedValue(element.get("new")) + "\n");
            }
        }
        return sb.toString().trim();
    }

    private String convertedValue(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Number) {
            return value.toString();
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Boolean) {
            return value.toString();
        }
        return "[complex value]";
    }
}
