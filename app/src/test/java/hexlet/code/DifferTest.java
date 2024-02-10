package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private static String path1 = "src/test/resources/before1.json";
    private static String path2 = "src/test/resources/after1.json";
    private static String pathEmpty = "src/test/resources/empty.json";

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
        String actual = Differ.generate(path1, path2);
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
        String actual = Differ.generate(path1, pathEmpty);
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
        String actual = Differ.generate(pathEmpty, path2);
        assertEquals(expected, actual);
    }
}
