package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    public static LinkedHashMap<String, Object> parseToJvObj(List<Object> source, String objectType) throws Exception {
        ObjectMapper mapper;
        //make sure that source is file
        if (!objectType.equals("file")) {
            throw new Exception("Supply with file source");
        }
        //use file source as standard object type
        File file = (File) source.get(0);
        String extension = (String) source.get(1);
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
        if (extensionTye.equals("json_extension")) {
            mapper = new ObjectMapper();
        } else if (extensionTye.equals("yml_extension")) {
            mapper = new YAMLMapper();
        } else {
            throw new Exception("Check extension");
        }
        return mapper;
    }

    public static String parseFromJvObj(Map<String, List<Object>> data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }

    public static String parseFromTextFile(String path) throws Exception {
        Path pathAsObject = Paths.get(path);
        return Files.readString(pathAsObject);
    }
}
