package thosakwe.java2dart;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Utils {
    private static Pattern BRACKETS = Pattern.compile(".+\\[]$");

    public static String convertToCamelCase(String str) {
        StringBuilder buf = new StringBuilder();
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];

            if (ch == ' ' | ch == '-') {
                buf.append('_');
            } else if (Character.isUpperCase(ch)) {
                buf.append(Character.toLowerCase(ch));
            } else {
                buf.append(ch);
            }
        }

        return buf.toString();
    }

    public static String convertToDartType(String type) {
        Matcher matcher = BRACKETS.matcher(type);

        if (!matcher.matches()) {
            return type;
        } else {
            String innerType = type.replaceFirst("\\[]$", "");
            return String.format("List<%s>", convertToDartType(innerType));
        }
    }
}
