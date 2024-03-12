package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class Parser {
    public static LinkedHashMap<String, Object> parseToJvObj(Map<String, Object> data,
                                                             String dataType) throws Exception {
        if (dataType.equals("file")) {
            File source = (File) data.get("source");
            String extension = (String) data.get("extension");
            ObjectMapper mapper = getFileMapper(extension);
            return source.length() == 0 ? new LinkedHashMap<>() : mapper.readValue(source, new TypeReference<>() { });
        } else {
            throw new Exception("Check dataType");
        }
    }

    private static ObjectMapper getFileMapper(String extension) throws Exception {
        ObjectMapper mapper;
        if (extension.equals("json")) {
            mapper = new ObjectMapper();
        } else if (extension.equals("yml")) {
            mapper = new YAMLMapper();
        } else {
            throw new Exception("Check extension");
        }
        return mapper;
    }
}
