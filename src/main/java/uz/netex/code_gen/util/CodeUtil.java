package uz.netex.code_gen.util;

public class CodeUtil {

    public static String removeComments(String text) {
        StringBuilder newStr = new StringBuilder();
        for (String line : text.split("\n")) {
            if (line.trim().equals("")) continue;
            if (line.trim().startsWith("//")) continue;
            int i = line.indexOf("//");
            if (i > -1) {
                line = line.substring(0, i);
            }
            newStr.append(line).append("\n");
        }

        StringBuilder textBuilder = new StringBuilder();
        for (int i = 0; i < newStr.length(); i++) {
            if (i + 2 < newStr.length() &&
                    newStr.substring(i, i + 2).equals("/*") &&
                    newStr.charAt(i + 2) != '*') {
                i += 2;
                while (i + 1 < newStr.length() &&
                        !newStr.substring(i, i + 2).equals("*/")) {
                    i++;
                }
                i += 2;
            }
            if (i < newStr.length())
                textBuilder.append(newStr.charAt(i));
        }
        text = textBuilder.toString();
        return text;
    }

    public static String remove2Probels(String content) {
        content = content.trim();
        while (content.contains("  ")) {
            content = content.replaceAll(" {2}", " ");
        }
        return content;
    }

    public static int findCloseBkt(String str, int openIndex) {
        try {
            char openBkt = str.charAt(openIndex);
            char closeBkt;
            switch (openBkt) {
                case '{':
                    closeBkt = '}';
                    break;
                case '(':
                    closeBkt = ')';
                    break;
                case '[':
                    closeBkt = ']';
                    break;
                default:
                    return -1;
            }
            int v = 1;
            int cursor = openIndex + 1;
            while (cursor < str.length()) {
                if (str.charAt(cursor) == openBkt)
                    v++;
                else if (str.charAt(cursor) == closeBkt)
                    v--;
                if (v == 0)
                    return cursor;
                cursor++;
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    public static String extractJavadoc(String text) {
        if (text.startsWith("/**")) {
            int endOfComment = text.indexOf("*/", 3) + 2;
            return endOfComment == 1
                    ? text
                    : text.substring(0, endOfComment);
        }
        return "";
    }

}
