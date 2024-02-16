package hexlet.code;

import java.util.List;
import java.util.Map;

import hexlet.code.formatters.FormatterType;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        //first parse source files to Maps
        Map<String, Object> before = Parser.parseToJvObj(filepath1);
        Map<String, Object> after = Parser.parseToJvObj(filepath2);
        Map<String, List<Object>> difference = DiffBuilder.buildDiff(before, after);
        FormatterType formatter = Formatter.setFormat(format);
        formatter.setData(difference);
        return formatter.format();
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}
