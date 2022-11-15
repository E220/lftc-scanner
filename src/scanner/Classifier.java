package scanner;

import java.util.*;
import java.util.regex.Pattern;

public class Classifier {
    public enum Class {
        TOKEN,
        IDENTIFIER,
        STRING_CONST,
        NUMBER_CONST,
    }

    @FunctionalInterface
    private interface Condition {
        boolean matches(String string);
    }

    private final Pattern IDENTIFIER = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
    private final Pattern STRING_CONST = Pattern.compile("\".*\"");
    private final Pattern NUMBER_CONST = Pattern.compile("([+-]?([1-9][0-9]*)(\\.[0-9]*[1-9])?|[+-]?0\\.[0-9]*[1-9]|0)");

    private final Map<Class, Condition> conditions;

    public Classifier(Set<String> tokens) {
        this.conditions = Collections.unmodifiableMap(new LinkedHashMap<>(){{
            this.put(Class.TOKEN, tokens::contains);
            this.put(Class.IDENTIFIER, string -> IDENTIFIER.matcher(string).matches());
            this.put(Class.STRING_CONST, string -> STRING_CONST.matcher(string).matches());
            this.put(Class.NUMBER_CONST, string -> NUMBER_CONST.matcher(string).matches());
        }});
    }

    public Class classify(String string) {
        for (final Map.Entry<Class, Condition> entry : this.conditions.entrySet()) {
            if (entry.getValue().matches(string)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
