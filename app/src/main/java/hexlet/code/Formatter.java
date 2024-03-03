package hexlet.code;

import hexlet.code.formatters.FormatterType;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {

    public static FormatterType getFormatterType(String format) throws Exception {
        switch (format) {
            case "stylish":
                return new Stylish();
            case "plain":
                return new Plain();
            case "json":
                return new Json();
            default:
                throw new Exception("Check format");
        }
    }
}
