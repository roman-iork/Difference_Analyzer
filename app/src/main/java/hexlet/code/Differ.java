package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import hexlet.code.formatters.FormatterType;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        //first parse source files to Maps
        Map<String, Object> before = Parser.parseToJvObj(getFileAndExtension(filepath1), "file");
        Map<String, Object> after = Parser.parseToJvObj(getFileAndExtension(filepath2), "file");
        Map<String, List<Object>> difference = DiffBuilder.buildDiff(before, after);
        //choose formatter type
        FormatterType formatter = Formatter.getFormatterType(format);
        //return required format representation
        return formatter.format(difference);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static List<Object> getFileAndExtension(String filepath) {
        Path source = Paths.get(filepath);
        String extensionType = "";
        if (filepath.endsWith(".json")) {
            extensionType = "json_extension";
        } else if (filepath.endsWith(".yml")) {
            extensionType = "yml_extension";
        }
        return List.of(source.toFile(), extensionType);
    }
}
