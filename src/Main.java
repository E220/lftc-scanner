import readers.ProgramReader;
import readers.ReaderException;
import readers.TokenReader;
import scanner.Scanner;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        final TokenReader tokenReader = new TokenReader();
        Scanner scanner;
        try {
            tokenReader.read("src/token.in");
            final Set<String> tokens = tokenReader.getTokens();
            scanner = new Scanner(tokens);
        } catch (ReaderException e) {
            System.out.println(e.getMessage());
            return;
        }

        final ProgramReader programReader = new ProgramReader();
        try {
            programReader.read("src/p1.lyt");
            List<String> lines = programReader.getLines();
            scanner.scan(lines);
        } catch (ReaderException e) {
            System.out.println(e.getMessage());
        }
    }
}