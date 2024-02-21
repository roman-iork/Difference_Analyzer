package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    public static LinkedHashMap<String, Object> parseToJvObj(String filepath) throws Exception {
        ObjectMapper mapper;
        //choose mapper
        if (filepath.endsWith(".json")) {
            mapper = new ObjectMapper();
        } else if (filepath.endsWith(".yml")) {
            mapper = new YAMLMapper();
        } else {
            throw new Exception("Check extension");
        }
        Path source = Paths.get(filepath);
        if (Files.size(source) ==  0) {
            return new LinkedHashMap<>();
        }
        return mapper.readValue(source.toFile(), new TypeReference<>() { });
    }
    public static String parseFromJvObj(Map.Entry<String, List<Object>> data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }

    public static String parseFromTextFile(String path) throws Exception {
        Path pathAsObject = Paths.get(path);
        return Files.readString(pathAsObject);
    }
}
