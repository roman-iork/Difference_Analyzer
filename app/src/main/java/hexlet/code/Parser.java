package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseSource(String filepath) throws Exception {
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
}
