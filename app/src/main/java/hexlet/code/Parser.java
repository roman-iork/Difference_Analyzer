package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseToJvObj(String content, String dataFormat) throws Exception {
        ObjectMapper mapper = getFileMapper(dataFormat);
        return content.isEmpty() ? new HashMap<>() : mapper.readValue(content, new TypeReference<>() { });
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
