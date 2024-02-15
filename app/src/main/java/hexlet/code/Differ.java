package hexlet.code;

import java.util.List;
import java.util.Map;

import hexlet.code.formatters.FormatterType;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        //first get parsed to Map source files
        Map<String, Object> before = Parser.parseSource(filepath1);
        Map<String, Object> after = Parser.parseSource(filepath2);
        Map<String, List<Object>> difference = DiffBuilder.buildDiff(before, after);
        FormatterType formatter = Formatter.setFormat(format);
        formatter.setData(difference);
        return formatter.format();
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}
