package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private final String pathBefore = "src/test/resources/before.";
    private final String pathAfter = "src/test/resources/after.";
    private final String pathEmpty = "src/test/resources/empty.";

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
        String actual = Differ.generate(json(pathBefore), json(pathAfter));
        assertEquals(expected, actual);
        actual = Differ.generate(yml(pathBefore), yml(pathAfter));
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
        String actual = Differ.generate(json(pathBefore), json(pathEmpty));
        assertEquals(expected, actual);
        actual = Differ.generate(yml(pathBefore), yml(pathEmpty));
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
        String actual = Differ.generate(json(pathEmpty), json(pathAfter));
        assertEquals(expected, actual);
        actual = Differ.generate(yml(pathEmpty), yml(pathAfter));
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateEmptyBoth() throws Exception {
        String expected = "{\n"
                + "}";
        String actual = Differ.generate(json(pathEmpty), json(pathEmpty));
        assertEquals(expected, actual);
        actual = Differ.generate(yml(pathEmpty), yml(pathEmpty));
        assertEquals(expected, actual);
    }

    private static String json(String path) {
        return path + "json";
    }

    private static String yml(String path) {
        return path + "yml";
    }
}
