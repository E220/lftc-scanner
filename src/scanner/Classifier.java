package scanner;

import java.util.Set;
import java.util.regex.Pattern;

public class Classifier {
    public enum Class {
        TOKEN,
        IDENTIFIER,
        STRING_CONST,
        NUMBER_CONST,
    }

    private final Pattern IDENTIFIER = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
    private final Pattern STRING_CONST = Pattern.compile("\".*\"");
    private final Pattern NUMBER_CONST = Pattern.compile("([+-]?([1-9][0-9]*)(\\.[0-9]*[1-9])?|[+-]?0\\.[0-9]*[1-9]|0)");
    private final Set<String> tokens;

    public Classifier(Set<String> tokens) {
        this.tokens = tokens;
    }

    public Class classify(String string) {
        if (tokens.contains(string)) {
            return Class.TOKEN;
        }
        if (IDENTIFIER.matcher(string).matches()) {
            return Class.IDENTIFIER;
        }
        if (STRING_CONST.matcher(string).matches()) {
            return Class.STRING_CONST;
        }
        if (NUMBER_CONST.matcher(string).matches()) {
            return Class.NUMBER_CONST;
        }
        return null;
    }
}
