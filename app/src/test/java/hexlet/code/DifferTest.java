package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private final String pathBefore = "src/test/resources/before.";
    private final String pathAfter = "src/test/resources/after.";
    private final String pathEmpty = "src/test/resources/empty.";

    @Test
    public void testGenerateNormal() throws Exception {
        String expected1 = "{\n"
                + "  - a: {four=4, milk=false, list=[1, 2, 3]}\n"
                + "    animal: cat\n"
                + "  - apple: green\n"
                + "  + banana: yellow\n"
                + "  - car: true\n"
                + "  + car: false\n"
                + "  + shop: [food, shoes]\n"
                + "    sky: [summer, autumn]\n"
                + "  - time: 12.4\n"
                + "  + time: null\n"
                + "  + tree: {oak=7, pine=true, birch=[true, 14, spring]}\n"
                + "  - what: null\n"
                + "  + what: true\n"
                + "}";
        String expected2 = "Property 'a' was removed\n"
                + "Property 'apple' was removed\n"
                + "Property 'banana' was added with value: 'yellow'\n"
                + "Property 'car' was updated. From true to false\n"
                + "Property 'shop' was added with value: [complex value]\n"
                + "Property 'time' was updated. From 12.4 to null\n"
                + "Property 'tree' was added with value: [complex value]\n"
                + "Property 'what' was updated. From null to true";
        String expected3 = "{\n"
                + "  \"a\":[\"removed\",{\"four\":4,\"milk\":false,\"list\":[1,2,3]}],\n"
                + "  \"animal\":[\"unchanged\",\"cat\"],\n"
                + "  \"apple\":[\"removed\",\"green\"],\n"
                + "  \"banana\":[\"added\",\"yellow\"],\n"
                + "  \"car\":[\"changed\",true,false],\n"
                + "  \"shop\":[\"added\",[\"food\",\"shoes\"]],\n"
                + "  \"sky\":[\"unchanged\",[\"summer\",\"autumn\"]],\n"
                + "  \"time\":[\"changed\",12.4,null],\n"
                + "  \"tree\":[\"added\",{\"oak\":7,\"pine\":true,\"birch\":[true,14,\"spring\"]}],\n"
                + "  \"what\":[\"changed\",null,true]\n"
                + "}";
        String actual = Differ.generate(json(pathBefore), json(pathAfter), "stylish");
        assertEquals(expected1, actual);
        actual = Differ.generate(yml(pathBefore), yml(pathAfter), "stylish");
        assertEquals(expected1, actual);
        actual = Differ.generate(json(pathBefore), json(pathAfter), "plain");
        assertEquals(expected2, actual);
        actual = Differ.generate(yml(pathBefore), yml(pathAfter), "plain");
        assertEquals(expected2, actual);
        actual = Differ.generate(json(pathBefore), json(pathAfter), "json");
        assertEquals(expected3, actual);
        actual = Differ.generate(yml(pathBefore), yml(pathAfter), "json");
        assertEquals(expected3, actual);
    }

    @Test
    public void testGenerateEmptyAfter() throws Exception {
        String expected1 = "{\n"
                + "  - a: {four=4, milk=false, list=[1, 2, 3]}\n"
                + "  - animal: cat\n"
                + "  - apple: green\n"
                + "  - car: true\n"
                + "  - sky: [summer, autumn]\n"
                + "  - time: 12.4\n"
                + "  - what: null\n"
                + "}";
        String expected2 = "Property 'a' was removed\n"
                + "Property 'animal' was removed\n"
                + "Property 'apple' was removed\n"
                + "Property 'car' was removed\n"
                + "Property 'sky' was removed\n"
                + "Property 'time' was removed\n"
                + "Property 'what' was removed";
        String expected3 = "{\n"
                + "  \"a\":[\"removed\",{\"four\":4,\"milk\":false,\"list\":[1,2,3]}],\n"
                + "  \"animal\":[\"removed\",\"cat\"],\n"
                + "  \"apple\":[\"removed\",\"green\"],\n"
                + "  \"car\":[\"removed\",true],\n"
                + "  \"sky\":[\"removed\",[\"summer\",\"autumn\"]],\n"
                + "  \"time\":[\"removed\",12.4],\n"
                + "  \"what\":[\"removed\",null]\n"
                + "}";
        String actual = Differ.generate(json(pathBefore), json(pathEmpty), "stylish");
        assertEquals(expected1, actual);
        actual = Differ.generate(yml(pathBefore), yml(pathEmpty), "stylish");
        assertEquals(expected1, actual);
        actual = Differ.generate(json(pathBefore), json(pathEmpty), "plain");
        assertEquals(expected2, actual);
        actual = Differ.generate(yml(pathBefore), yml(pathEmpty), "plain");
        assertEquals(expected2, actual);
        actual = Differ.generate(json(pathBefore), json(pathEmpty), "json");
        assertEquals(expected3, actual);
        actual = Differ.generate(yml(pathBefore), yml(pathEmpty), "json");
        assertEquals(expected3, actual);
    }

    @Test
    public void testGenerateEmptyBefore() throws Exception {
        String expected1 = "{\n"
                + "  + animal: cat\n"
                + "  + banana: yellow\n"
                + "  + car: false\n"
                + "  + shop: [food, shoes]\n"
                + "  + sky: [summer, autumn]\n"
                + "  + time: null\n"
                + "  + tree: {oak=7, pine=true, birch=[true, 14, spring]}\n"
                + "  + what: true\n"
                + "}";
        String expected2 = "Property 'animal' was added with value: 'cat'\n"
                + "Property 'banana' was added with value: 'yellow'\n"
                + "Property 'car' was added with value: false\n"
                + "Property 'shop' was added with value: [complex value]\n"
                + "Property 'sky' was added with value: [complex value]\n"
                + "Property 'time' was added with value: null\n"
                + "Property 'tree' was added with value: [complex value]\n"
                + "Property 'what' was added with value: true";
        String expected3 = "{\n"
                + "  \"animal\":[\"added\",\"cat\"],\n"
                + "  \"banana\":[\"added\",\"yellow\"],\n"
                + "  \"car\":[\"added\",false],\n"
                + "  \"shop\":[\"added\",[\"food\",\"shoes\"]],\n"
                + "  \"sky\":[\"added\",[\"summer\",\"autumn\"]],\n"
                + "  \"time\":[\"added\",null],\n"
                + "  \"tree\":[\"added\",{\"oak\":7,\"pine\":true,\"birch\":[true,14,\"spring\"]}],\n"
                + "  \"what\":[\"added\",true]\n"
                + "}";
        String actual = Differ.generate(json(pathEmpty), json(pathAfter), "stylish");
        assertEquals(expected1, actual);
        actual = Differ.generate(yml(pathEmpty), yml(pathAfter), "stylish");
        assertEquals(expected1, actual);
        actual = Differ.generate(json(pathEmpty), json(pathAfter), "plain");
        assertEquals(expected2, actual);
        actual = Differ.generate(yml(pathEmpty), yml(pathAfter), "plain");
        assertEquals(expected2, actual);
        actual = Differ.generate(json(pathEmpty), json(pathAfter), "json");
        assertEquals(expected3, actual);
        actual = Differ.generate(yml(pathEmpty), yml(pathAfter), "json");
        assertEquals(expected3, actual);
    }

    @Test
    public void testGenerateEmptyBoth() throws Exception {
        String expected1 = "{\n"
                + "}";
        String expected2 = "";
        String actual = Differ.generate(json(pathEmpty), json(pathEmpty), "stylish");
        assertEquals(expected1, actual);
        actual = Differ.generate(yml(pathEmpty), yml(pathEmpty), "stylish");
        assertEquals(expected1, actual);
        actual = Differ.generate(json(pathEmpty), json(pathEmpty), "plain");
        assertEquals(expected2, actual);
        actual = Differ.generate(yml(pathEmpty), yml(pathEmpty), "plain");
        assertEquals(expected2, actual);
        actual = Differ.generate(json(pathEmpty), json(pathEmpty), "json");
        assertEquals(expected1, actual);
        actual = Differ.generate(yml(pathEmpty), yml(pathEmpty), "json");
        assertEquals(expected1, actual);
    }

    private static String json(String path) {
        return path + "json";
    }

    private static String yml(String path) {
        return path + "yml";
    }
}
