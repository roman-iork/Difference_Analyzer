package hexlet.code;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String stylish(Map<String, List<Object>> difference) {
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
