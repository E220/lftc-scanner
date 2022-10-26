import symboltable.SymbolTable;

public class Main {
    public static void main(String[] args) {
        final SymbolTable<String> symbolTable = new SymbolTable<>();
        System.out.println(symbolTable.add("var"));
        System.out.println(symbolTable.add("asd"));
        System.out.println(symbolTable.add("foo"));
        System.out.println(symbolTable.add("baz"));

        System.out.println(symbolTable.add("1"));
        System.out.println(symbolTable.add("2"));
        System.out.println(symbolTable.add("3"));
        System.out.println(symbolTable.add("4"));

        for (int i = 0; i < 8; i++)
            System.out.println(symbolTable.getByIndex(i));
    }
}