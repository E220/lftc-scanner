package scanner;

import output.ProgramInternalForm;
import output.SymbolTable;

import java.util.*;
import java.util.function.Consumer;
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

    private record InternalClass(Class type, Condition condition, Consumer<String> action) {}

    private final Pattern IDENTIFIER = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
    private final Pattern STRING_CONST = Pattern.compile("\".*\"");
    private final Pattern NUMBER_CONST = Pattern.compile("([+-]?([1-9][0-9]*)(\\.[0-9]*[1-9])?|[+-]?0\\.[0-9]*[1-9]|0)");

    private final List<InternalClass> classes;

    private final ProgramInternalForm pif;
    private final SymbolTable<String> symbolTable;

    public Classifier(Set<String> tokens) {
        pif = new ProgramInternalForm();
        symbolTable = new SymbolTable<>();
        final Consumer<String> idConsumer = string -> pif.addValue(Class.IDENTIFIER.toString(), symbolTable.add(string));
        final Consumer<String> stringConsumer = string -> pif.addValue(Class.STRING_CONST.toString(), symbolTable.add(string));
        final Consumer<String> numberConsumer = string -> pif.addValue(Class.NUMBER_CONST.toString(), symbolTable.add(string));
        this.classes = Arrays.asList(
                new InternalClass(Class.TOKEN, tokens::contains, pif::addToken),
                new InternalClass(Class.IDENTIFIER, string -> IDENTIFIER.matcher(string).matches(), idConsumer),
                new InternalClass(Class.STRING_CONST, string -> STRING_CONST.matcher(string).matches(), stringConsumer),
                new InternalClass(Class.NUMBER_CONST, string -> NUMBER_CONST.matcher(string).matches(), numberConsumer)
        );
    }

    public void classifyAll(List<String> strings) {
        strings.forEach(string -> {
            for (final InternalClass internalClass : this.classes) {
                if (internalClass.condition.matches(string)) {
                    internalClass.action.accept(string);
                    break;
                }
            }
        });
    }
}
