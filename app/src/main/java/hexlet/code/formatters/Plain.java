package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Plain implements FormatterType {

    public String format(Map<String, List<Object>> difference) {
        StringBuilder sb = new StringBuilder();
        for (String key : difference.keySet()) {
            List<Object> value = difference.get(key);
            if (value.get(0).equals("removed")) {
                sb.append("Property '" + key + "' was removed\n");
            } else if (value.get(0).equals("added")) {
                sb.append("Property '" + key + "' was added with value: " + convertedValue(value.get(1)) + "\n");
            } else if (value.get(0).equals("unchanged")) {
                continue;
            } else {
                sb.append("Property '" + key + "' was updated. From " + convertedValue(value.get(1)));
                sb.append(" to " + convertedValue(value.get(2)) + "\n");
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
