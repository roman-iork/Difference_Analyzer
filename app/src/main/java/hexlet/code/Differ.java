package hexlet.code;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import hexlet.code.formatters.FormatterType;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        //Create Source objects
        Source sourceFile1 = new Source();
        sourceFile1.setFileSource(getFile(filepath1));
        sourceFile1.setFileExtension(getExtension(filepath1));
        Source sourceFile2 = new Source();
        sourceFile2.setFileSource(getFile(filepath2));
        sourceFile2.setFileExtension(getExtension(filepath2));
        //first parse source files to Maps
        Map<String, Object> before = Parser.parseToJvObj(sourceFile1, "file");
        Map<String, Object> after = Parser.parseToJvObj(sourceFile2, "file");
        Map<String, List<Object>> difference = DiffBuilder.buildDiff(before, after);
        //choose formatter type
        FormatterType formatter = Formatter.getFormatterType(format);
        //return required format representation
        return formatter.format(difference);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static File getFile(String filepath) {
        Path source = Paths.get(filepath);
        return source.toFile();
    }

    private static String getExtension(String filepath) throws Exception {
        String extensionType = "";
        if (filepath.endsWith(".json")) {
            return  "json";
        } else if (filepath.endsWith(".yml")) {
            return  "yml";
        } else {
            throw new Exception("Check file");
        }
    }
}
