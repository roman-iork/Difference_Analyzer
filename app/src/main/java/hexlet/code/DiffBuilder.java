package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiffBuilder {
    public static Map<String, List<Object>> buildDiff(Map<String, Object> before, Map<String, Object> after,
                                                      List<String> keys) {
        Map<String, List<Object>> difference = new LinkedHashMap<>();
        for (String key : keys) {
            List<Object> value = new ArrayList<>();
            if (before.containsKey(key) && !after.containsKey(key)) {
                value.add("removed");
                value.add(before.get(key));
                difference.put(key, value);
            } else if (!before.containsKey(key) && after.containsKey(key)) {
                value.add("added");
                value.add(after.get(key));
                difference.put(key, value);
            } else if (!(before.get(key) == null) && !(after.get(key) == null)
                    && before.get(key).equals(after.get(key))) {
                difference.put(key, List.of("unchanged", before.get(key)));
            } else if (before.get(key) == null && after.get(key) == null) {
                value.add("unchanged");
                value.add(before.get(key));
                difference.put(key, value);
            } else {
                value.add("changed");
                value.add(before.get(key));
                value.add(after.get(key));
                difference.put(key, value);
            }
        }
        return difference;
    }
}
