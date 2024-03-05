package hexlet.code;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.formatters.FormatterType;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> before = Parser.parseToJvObj(Map.of("source", getFile(filepath1),
                "mapper", getFileMapper(filepath1)),
                "file");
        Map<String, Object> after = Parser.parseToJvObj(Map.of("source", getFile(filepath2),
                "mapper", getFileMapper(filepath2)),
                "file");
        List<Map<String, Object>> difference = DiffBuilder.buildDiff(before, after);
        FormatterType formatter = Formatter.getFormatterType(format);
        return formatter.format(difference);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static File getFile(String filepath) {
        Path source = Paths.get(filepath);
        return source.toFile();
    }

    private static String getExtension(String filepath) throws Exception {
        if (filepath.endsWith(".json")) {
            return  "json";
        } else if (filepath.endsWith(".yml")) {
            return  "yml";
        } else {
            throw new Exception("Check file");
        }
    }

    private static ObjectMapper getFileMapper(String filepath) throws Exception {
        ObjectMapper mapper;
        if (getExtension(filepath).equals("json")) {
            mapper = new ObjectMapper();
        } else if (getExtension(filepath).equals("yml")) {
            mapper = new YAMLMapper();
        } else {
            throw new Exception("Check extension");
        }
        return mapper;
    }
}
