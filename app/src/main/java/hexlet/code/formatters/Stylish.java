package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Stylish implements FormatterType {

    public String format(List<Map<String, Object>> difference) {
        StringBuilder sb = new StringBuilder("{\n");
        for (Map<String, Object> element : difference) {
            if (element.get("diffType").equals("removed")) {
                sb.append("  - " + element.get("key") + ": " + element.get("old") + "\n");
            } else if (element.get("diffType").equals("added")) {
                sb.append("  + " + element.get("key") + ": " + element.get("new") + "\n");
            } else if (element.get("diffType").equals("unchanged")) {
                sb.append("    " + element.get("key") + ": " + element.get("old") + "\n");
            } else {
                sb.append("  - " + element.get("key") + ": " + element.get("old") + "\n");
                sb.append("  + " + element.get("key") + ": " + element.get("new") + "\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
