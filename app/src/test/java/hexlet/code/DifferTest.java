package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private final String sourceBefore = "src/test/resources/fixtures/before.";
    private final String sourceAfter = "src/test/resources/fixtures/after.";
    private final String sourceEmpty = "src/test/resources/fixtures/empty.";
    private final String pathBlank = "src/test/resources/fixtures/expected/";

    @Test
    public void testGenerateStylishJson() throws Exception {
        String expected1 = parseFromTextFile(txt(stylishStyle(pathBlank + "bothFilled")));
        String actual = Differ.generate(json(sourceBefore), json(sourceAfter), "stylish");
        assertEquals(expected1, actual);
        String expected2 = parseFromTextFile(txt(stylishStyle(pathBlank + "emptyAfter")));
        actual = Differ.generate(json(sourceBefore), json(sourceEmpty), "stylish");
        assertEquals(expected2, actual);
        String expected3 = parseFromTextFile(txt(stylishStyle(pathBlank + "emptyBefore")));
        actual = Differ.generate(json(sourceEmpty), json(sourceAfter), "stylish");
        assertEquals(expected3, actual);
        String expected4 = "{\n}";
        actual = Differ.generate(json(sourceEmpty), json(sourceEmpty), "stylish");
        assertEquals(expected4, actual);
    }
    @Test
    public void testGenerateStylishYml() throws Exception {
        String expected1 = parseFromTextFile(txt(stylishStyle(pathBlank + "bothFilled")));
        String actual = Differ.generate(yml(sourceBefore), yml(sourceAfter), "stylish");
        assertEquals(expected1, actual);
        String expected2 = parseFromTextFile(txt(stylishStyle(pathBlank + "emptyAfter")));
        actual = Differ.generate(yml(sourceBefore), yml(sourceEmpty), "stylish");
        assertEquals(expected2, actual);
        String expected3 = parseFromTextFile(txt(stylishStyle(pathBlank + "emptyBefore")));
        actual = Differ.generate(yml(sourceEmpty), yml(sourceAfter), "stylish");
        assertEquals(expected3, actual);
        String expected4 = "{\n}";
        actual = Differ.generate(yml(sourceEmpty), yml(sourceEmpty), "stylish");
        assertEquals(expected4, actual);
    }
    @Test
    public void testGeneratePlainJson() throws Exception {
        String expected1 = parseFromTextFile(txt(plainStyle(pathBlank + "bothFilled")));
        String actual = Differ.generate(json(sourceBefore), json(sourceAfter), "plain");
        assertEquals(expected1, actual);
        String expected2 = parseFromTextFile(txt(plainStyle(pathBlank + "emptyAfter")));
        actual = Differ.generate(json(sourceBefore), json(sourceEmpty), "plain");
        assertEquals(expected2, actual);
        String expected3 = parseFromTextFile(txt(plainStyle(pathBlank + "emptyBefore")));
        actual = Differ.generate(json(sourceEmpty), json(sourceAfter), "plain");
        assertEquals(expected3, actual);
        String expected4 = "";
        actual = Differ.generate(json(sourceEmpty), json(sourceEmpty), "plain");
        assertEquals(expected4, actual);
    }
    @Test
    public void testGeneratePlainYml() throws Exception {
        String expected1 = parseFromTextFile(txt(plainStyle(pathBlank + "bothFilled")));
        String actual = Differ.generate(yml(sourceBefore), yml(sourceAfter), "plain");
        assertEquals(expected1, actual);
        String expected2 = parseFromTextFile(txt(plainStyle(pathBlank + "emptyAfter")));
        actual = Differ.generate(yml(sourceBefore), yml(sourceEmpty), "plain");
        assertEquals(expected2, actual);
        String expected3 = parseFromTextFile(txt(plainStyle(pathBlank + "emptyBefore")));
        actual = Differ.generate(yml(sourceEmpty), yml(sourceAfter), "plain");
        assertEquals(expected3, actual);
        String expected4 = "";
        actual = Differ.generate(yml(sourceEmpty), yml(sourceEmpty), "plain");
        assertEquals(expected4, actual);
    }
    @Test
    public void testGenerateJsonJson() throws Exception {
        String expected1 = parseFromTextFile(json(jsonStyle(pathBlank + "bothFilled")));
        String actual = Differ.generate(json(sourceBefore), json(sourceAfter), "json");
        assertEquals(expected1, actual);
        String expected2 = parseFromTextFile(json(jsonStyle(pathBlank + "emptyAfter")));
        actual = Differ.generate(json(sourceBefore), json(sourceEmpty), "json");
        assertEquals(expected2, actual);
        String expected3 = parseFromTextFile(json(jsonStyle(pathBlank + "emptyBefore")));
        actual = Differ.generate(json(sourceEmpty), json(sourceAfter), "json");
        assertEquals(expected3, actual);
        String expected4 = "[]";
        actual = Differ.generate(json(sourceEmpty), json(sourceEmpty), "json");
        assertEquals(expected4, actual);
    }
    @Test
    public void testGenerateJsonYml() throws Exception {
        String expected1 = parseFromTextFile(json(jsonStyle(pathBlank + "bothFilled")));
        String actual = Differ.generate(yml(sourceBefore), yml(sourceAfter), "json");
        assertEquals(expected1, actual);
        String expected2 = parseFromTextFile(json(jsonStyle(pathBlank + "emptyAfter")));
        actual = Differ.generate(yml(sourceBefore), yml(sourceEmpty), "json");
        assertEquals(expected2, actual);
        String expected3 = parseFromTextFile(json(jsonStyle(pathBlank + "emptyBefore")));
        actual = Differ.generate(yml(sourceEmpty), yml(sourceAfter), "json");
        assertEquals(expected3, actual);
        String expected4 = "[]";
        actual = Differ.generate(yml(sourceEmpty), yml(sourceEmpty), "json");
        assertEquals(expected4, actual);
    }
    @Test
    public void testGenerateNoFormatJson() throws Exception {
        String expected1 = parseFromTextFile(txt(stylishStyle(pathBlank + "bothFilled")));
        String actual = Differ.generate(json(sourceBefore), json(sourceAfter));
        assertEquals(expected1, actual);
        String expected2 = parseFromTextFile(txt(stylishStyle(pathBlank + "emptyAfter")));
        actual = Differ.generate(json(sourceBefore), json(sourceEmpty));
        assertEquals(expected2, actual);
        String expected3 = parseFromTextFile(txt(stylishStyle(pathBlank + "emptyBefore")));
        actual = Differ.generate(json(sourceEmpty), json(sourceAfter));
        assertEquals(expected3, actual);
        String expected4 = "{\n}";
        actual = Differ.generate(json(sourceEmpty), json(sourceEmpty));
        assertEquals(expected4, actual);
    }
    @Test
    public void testGenerateNoFormatYml() throws Exception {
        String expected1 = parseFromTextFile(txt(stylishStyle(pathBlank + "bothFilled")));
        String actual = Differ.generate(yml(sourceBefore), yml(sourceAfter));
        assertEquals(expected1, actual);
        String expected2 = parseFromTextFile(txt(stylishStyle(pathBlank + "emptyAfter")));
        actual = Differ.generate(yml(sourceBefore), yml(sourceEmpty));
        assertEquals(expected2, actual);
        String expected3 = parseFromTextFile(txt(stylishStyle(pathBlank + "emptyBefore")));
        actual = Differ.generate(yml(sourceEmpty), yml(sourceAfter));
        assertEquals(expected3, actual);
        String expected4 = "{\n}";
        actual = Differ.generate(yml(sourceEmpty), yml(sourceEmpty));
        assertEquals(expected4, actual);
    }

    private static String json(String pathBlank) {
        return pathBlank + "json";
    }
    private static String yml(String pathBlank) {
        return pathBlank + "yml";
    }
    private static String txt(String pathBlank) {
        return pathBlank + "txt";
    }
    private static String stylishStyle(String pathBlank) {
        return pathBlank + "Stylish.";
    }
    private static String plainStyle(String pathBlank) {
        return pathBlank + "Plain.";
    }
    private static String jsonStyle(String pathBlank) {
        return pathBlank + "Json.";
    }

    public static String parseFromTextFile(String path) throws Exception {
        Path pathAsObject = Paths.get(path);
        return Files.readString(pathAsObject);
    }
}
