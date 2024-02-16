package hexlet.code;

import hexlet.code.formatters.FormatterType;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {

    public static FormatterType setFormat(String format) {
        if (format.equals("plain")) {
            return new Plain();
        } else if (format.equals("json")) {
            return new Json();
        }
        return new Stylish();
    }
}
