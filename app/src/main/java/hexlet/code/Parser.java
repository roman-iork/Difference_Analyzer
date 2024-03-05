package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class Parser {
    public static LinkedHashMap<String, Object> parseToJvObj(Map<String, Object> data,
                                                             String dataType) throws Exception {
        ObjectMapper mapper;
        if (dataType.equals("file")) {
            File source = (File) data.get("source");
            mapper = (ObjectMapper) data.get("mapper");
            return source.length() == 0 ? new LinkedHashMap<>() : mapper.readValue(source, new TypeReference<>() { });
        } else {
            throw new Exception("Check dataType");
        }
    }
}
