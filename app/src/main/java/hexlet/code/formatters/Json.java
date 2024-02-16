package hexlet.code.formatters;

import hexlet.code.Parser;

import java.util.List;
import java.util.Map;

public class Json implements FormatterType {

    private Map<String, List<Object>> difference;
    public String format() throws Exception {
        StringBuilder sb = new StringBuilder("{\n");
        for (Map.Entry<String, List<Object>> entrySet : difference.entrySet()) {
            String line = Parser.parseFromJvObj(entrySet);
            int lastIndex = line.length() - 1;
            String formattedLine = line.substring(1, lastIndex);
            sb.append("  " + formattedLine + ",\n");
        }
        String resultString = sb.toString();
        int lastCommaIndex = resultString.lastIndexOf(",");
        if (lastCommaIndex < 0) {
            lastCommaIndex = 1;
        }
        return resultString.substring(0, lastCommaIndex).concat("\n}");
    }

    public void setData(Map<String, List<Object>> data) {
        difference = data;
    }
}
