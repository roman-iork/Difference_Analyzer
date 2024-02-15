package hexlet.code;

import java.util.List;

import java.util.Map;
import java.util.HashSet;
import java.util.Set;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        //first get parsed to Map source files
        Map<String, Object> before = Parser.parseSource(filepath1);
        Map<String, Object> after = Parser.parseSource(filepath2);
        //here I make list of unique keys of two maps/files
        List<String> keysBefore = before.keySet().stream().sorted().toList();
        List<String> keysAfter = after.keySet().stream().sorted().toList();
        Set<String> allKeysSet = new HashSet<>(keysBefore);
        allKeysSet.addAll(keysAfter);
        List<String> sortedAllKeysSet = allKeysSet.stream().sorted().toList();
        Map<String, List<Object>> difference = DiffBuilder.buildDiff(before, after, sortedAllKeysSet);
        return Formatter.stylish(difference);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}
