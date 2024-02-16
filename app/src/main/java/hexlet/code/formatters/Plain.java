package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Plain implements FormatterType {

    private Map<String, List<Object>> difference;
    public String format() {
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

    String convertedValue(Object value) {

        if (value == null) {
            return null;
        } else if (value.getClass().getSuperclass().toString().endsWith("Number")) {
            return value.toString();
        } else if (value.getClass().isInstance("str")) {
            return "'" + value + "'";
        } else if (value.getClass().isInstance(true)) {
            return value.toString();
        }
        return "[complex value]";
    }

    public void setData(Map<String, List<Object>> data) {
        difference = data;
    }
}
