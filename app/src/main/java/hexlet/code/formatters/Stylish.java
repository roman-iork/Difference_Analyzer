package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Stylish implements FormatterType {

    public String format(Map<String, List<Object>> difference) {
        StringBuilder sb = new StringBuilder("{\n");
        for (String key : difference.keySet()) {
            List<Object> value = difference.get(key);
            if (value.get(0).equals("removed")) {
                sb.append("  - " + key + ": " + value.get(1) + "\n");
            } else if (value.get(0).equals("added")) {
                sb.append("  + " + key + ": " + value.get(1) + "\n");
            } else if (value.get(0).equals("unchanged")) {
                sb.append("    " + key + ": " + value.get(1) + "\n");
            } else {
                sb.append("  - " + key + ": " + value.get(1) + "\n");
                sb.append("  + " + key + ": " + value.get(2) + "\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
