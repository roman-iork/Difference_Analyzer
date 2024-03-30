package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import hexlet.code.formatters.FormatterType;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> before = Parser.parseToJvObj(readFile(filepath1), getExtension(filepath1));
        Map<String, Object> after = Parser.parseToJvObj(readFile(filepath2), getExtension(filepath2));
        List<Map<String, Object>> difference = DiffBuilder.buildDiff(before, after);
        FormatterType formatter = Formatter.getFormatterType(format);
        return formatter.format(difference);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static String getExtension(String filepath) {
        String[] splitPath = filepath.split("\\.");
        int length = splitPath.length;
        return splitPath[length - 1];
    }

    private static String readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        return Files.readString(path);
    }
}
