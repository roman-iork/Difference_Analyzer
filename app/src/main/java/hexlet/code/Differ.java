package hexlet.code;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import hexlet.code.formatters.FormatterType;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> before = Parser.parseToJvObj(Map.of("source", getFile(filepath1),
                "extension", getExtension(filepath1)),
                "file");
        Map<String, Object> after = Parser.parseToJvObj(Map.of("source", getFile(filepath2),
                "extension", getExtension(filepath2)),
                "file");
        List<Map<String, Object>> difference = DiffBuilder.buildDiff(before, after);
        FormatterType formatter = Formatter.getFormatterType(format);
        return formatter.format(difference);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static File getFile(String filepath) {
        Path source = Paths.get(filepath);
        return source.toFile();
    }

    private static String getExtension(String filepath) {
        String[] splitPath = filepath.split("\\.");
        int length = splitPath.length;
        return splitPath[length - 1];
    }
}
