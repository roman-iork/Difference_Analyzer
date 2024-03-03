package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.util.LinkedHashMap;

public class Parser {
    public static LinkedHashMap<String, Object> parseToJvObj(Source source, String objectType) throws Exception {
        ObjectMapper mapper;
        //make sure that source is file
        if (!objectType.equals("file")) {
            throw new Exception("Supply with file source");
        }
        //use file source as standard object type
        File file = source.getFileSource();
        String extension = source.getFileExtension();
        //choose mapper
        mapper = getMapper(extension);
        //in case file is empty
        if (file.length() ==  0) {
            return new LinkedHashMap<>();
        }
        return mapper.readValue(file, new TypeReference<>() { });
    }

    private static ObjectMapper getMapper(String extensionTye) throws Exception {
        ObjectMapper mapper;
        if (extensionTye.equals("json")) {
            mapper = new ObjectMapper();
        } else if (extensionTye.equals("yml")) {
            mapper = new YAMLMapper();
        } else {
            throw new Exception("Check extension");
        }
        return mapper;
    }
}
