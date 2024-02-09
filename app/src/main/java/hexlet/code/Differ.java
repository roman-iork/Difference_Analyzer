package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        //here I convert json to map
        var objectMapper = new ObjectMapper();
//        var differ = new Differ("green", "light");
//        objectMapper.writeValue(new File("/home/iork_ubuntu_user/Java projects/For Hexlet/differ.json"), differ);
        Path src1 = Paths.get(filepath1);
        Path src2 = Paths.get(filepath2);
        Map<String, Object> before = objectMapper.readValue(src1.toFile(), new TypeReference<>() { });
        Map<String, Object> after = objectMapper.readValue(src2.toFile(), new TypeReference<>() { });
        //here I make list of unique keys of two maps/files
        List<String> keysBefore = before.keySet().stream().sorted().toList();
        List<String> keysAfter = after.keySet().stream().sorted().toList();
        Set<String> allKeysSet = new HashSet<>(keysBefore);
        allKeysSet.addAll(keysAfter);
        List<String> sortedAllKeysSet = allKeysSet.stream().sorted().toList();
        //here I'm going to build the final string
        StringBuilder sb = new StringBuilder("{\n");
        for (String key : sortedAllKeysSet) {
            if (before.containsKey(key) && !after.containsKey(key)) {
                sb.append("  - " + key + ": " + before.get(key).toString() + "\n");
            } else if (!before.containsKey(key) && after.containsKey(key)) {
                sb.append("  + " + key + ": " + after.get(key).toString() + "\n");
            } else {
                if (before.get(key).equals(after.get(key))) {
                    sb.append("    " + key + ": " + before.get(key).toString() + "\n");
                } else {
                    sb.append("  - " + key + ": " + before.get(key).toString() + "\n");
                    sb.append("  + " + key + ": " + after.get(key).toString() + "\n");
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }
}