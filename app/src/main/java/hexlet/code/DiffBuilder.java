package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class DiffBuilder {
    public static List<Map<String, Object>> buildDiff(Map<String, Object> before, Map<String, Object> after) {
        List<String> keys = sortedAllkeysSet(before, after);
        Map<String, Object> diff = new LinkedHashMap<>();
        List<Map<String, Object>> diffList = new ArrayList<>();
        for (String key : keys) {
            if (before.containsKey(key) && !after.containsKey(key)) {
                diff.put("diffType", "removed");
                diff.put("old", before.get(key));
                diff.put("key", key);
            } else if (!before.containsKey(key) && after.containsKey(key)) {
                diff.put("diffType", "added");
                diff.put("new", after.get(key));
                diff.put("key", key);
            } else if (!(before.get(key) == null) && !(after.get(key) == null)
                    && before.get(key).equals(after.get(key))) {
                diff.put("diffType", "unchanged");
                diff.put("old", before.get(key));
                diff.put("key", key);
            } else if (before.get(key) == null && after.get(key) == null) {
                diff.put("diffType", "unchanged");
                diff.put("old", before.get(key));
                diff.put("key", key);
            } else {
                diff.put("diffType", "changed");
                diff.put("old", before.get(key));
                diff.put("new", after.get(key));
                diff.put("key", key);
            }
            diffList.add(new LinkedHashMap<>(diff));
            diff.clear();
        }
        return diffList;
    }

    private static List<String> sortedAllkeysSet(Map<String, Object> before, Map<String, Object> after) {
        List<String> keysBefore = before.keySet().stream().sorted().toList();
        List<String> keysAfter = after.keySet().stream().sorted().toList();
        Set<String> allKeysSet = new HashSet<>(keysBefore);
        allKeysSet.addAll(keysAfter);
        return allKeysSet.stream().sorted().toList();
    }
}
