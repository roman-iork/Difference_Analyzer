package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private final String PATH_BEFORE = "src/test/resources/before.";
    private final String PATH_AFTER = "src/test/resources/after.";
    private final String PATH_EMPTY = "src/test/resources/empty.";

    @Test
    public void testGenerateNormal() throws Exception {
        String expected = "{\n"
                + "    animal: cat\n"
                + "  - apple: green\n"
                + "  + banana: yellow\n"
                + "    car: true\n"
                + "  - time: 12\n"
                + "  + time: 15\n"
                + "}";
        String actual = Differ.generate(json(PATH_BEFORE), json(PATH_AFTER));
        assertEquals(expected, actual);
        actual = Differ.generate(yml(PATH_BEFORE), yml(PATH_AFTER));
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateEmptyAfter() throws Exception {
        String expected = "{\n"
                + "  - animal: cat\n"
                + "  - apple: green\n"
                + "  - car: true\n"
                + "  - time: 12\n"
                + "}";
        String actual = Differ.generate(json(PATH_BEFORE), json(PATH_EMPTY));
        assertEquals(expected, actual);
        actual = Differ.generate(yml(PATH_BEFORE), yml(PATH_EMPTY));
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateEmptyBefore() throws Exception {
        String expected = "{\n"
                + "  + animal: cat\n"
                + "  + banana: yellow\n"
                + "  + car: true\n"
                + "  + time: 15\n"
                + "}";
        String actual = Differ.generate(json(PATH_EMPTY), json(PATH_AFTER));
        assertEquals(expected, actual);
        actual = Differ.generate(yml(PATH_EMPTY), yml(PATH_AFTER));
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateEmptyBoth() throws Exception {
        String expected = "{\n"
                + "}";
        String actual = Differ.generate(json(PATH_EMPTY), json(PATH_EMPTY));
        assertEquals(expected, actual);
        actual = Differ.generate(yml(PATH_EMPTY), yml(PATH_EMPTY));
        assertEquals(expected, actual);
    }

    private static String json(String path) {
        return path + "json";
    }

    private static String yml(String path) {
        return path + "yml";
    }
}
