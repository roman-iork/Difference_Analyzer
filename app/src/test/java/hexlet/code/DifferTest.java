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
                + "  - a: {four=4, milk=false, list=[1, 2, 3]}\n"
                + "    animal: cat\n"
                + "  - apple: green\n"
                + "  + banana: yellow\n"
                + "  - car: true\n"
                + "  + car: false\n"
                + "  + shop: [food, shoes]\n"
                + "    sky: [summer, autumn]\n"
                + "  - time: 12\n"
                + "  + time: null\n"
                + "  + tree: {oak=7, pine=true, birch=[true, 14, spring]}\n"
                + "  - what: null\n"
                + "  + what: true\n"
                + "}";
        String actual = Differ.generate(json(pathBefore), json(pathAfter), "stylish");
        assertEquals(expected, actual);
        actual = Differ.generate(yml(pathBefore), yml(pathAfter), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateEmptyAfter() throws Exception {
        String expected = "{\n"
                + "  - a: {four=4, milk=false, list=[1, 2, 3]}\n"
                + "  - animal: cat\n"
                + "  - apple: green\n"
                + "  - car: true\n"
                + "  - sky: [summer, autumn]\n"
                + "  - time: 12\n"
                + "  - what: null\n"
                + "}";
        String actual = Differ.generate(json(pathBefore), json(pathEmpty), "stylish");
        assertEquals(expected, actual);
        actual = Differ.generate(yml(pathBefore), yml(pathEmpty), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateEmptyBefore() throws Exception {
        String expected = "{\n"
                + "  + animal: cat\n"
                + "  + banana: yellow\n"
                + "  + car: false\n"
                + "  + shop: [food, shoes]\n"
                + "  + sky: [summer, autumn]\n"
                + "  + time: null\n"
                + "  + tree: {oak=7, pine=true, birch=[true, 14, spring]}\n"
                + "  + what: true\n"
                + "}";
        String actual = Differ.generate(json(pathEmpty), json(pathAfter), "stylish");
        assertEquals(expected, actual);
        actual = Differ.generate(yml(pathEmpty), yml(pathAfter), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateEmptyBoth() throws Exception {
        String expected = "{\n"
                + "}";
        String actual = Differ.generate(json(pathEmpty), json(pathEmpty), "stylish");
        assertEquals(expected, actual);
        actual = Differ.generate(yml(pathEmpty), yml(pathEmpty), "stylish");
        assertEquals(expected, actual);
    }

    private static String json(String path) {
        return path + "json";
    }

    private static String yml(String path) {
        return path + "yml";
    }
}
