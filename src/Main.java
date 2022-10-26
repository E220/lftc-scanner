import readers.ProgramReader;
import readers.ReaderException;
import readers.TokenReader;
import scanner.Scanner;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final TokenReader tokenReader = new TokenReader();
        Scanner scanner;
        try {
            tokenReader.read("src/token.in");
            final List<String> tokens = tokenReader.getTokens();
            System.out.println(tokens);
            scanner = new Scanner(tokens);
        } catch (ReaderException e) {
            System.out.println(e.getMessage());
            return;
        }

        final ProgramReader programReader = new ProgramReader();
        try {
            programReader.read("src/p1.lyt");
            List<String> lines = programReader.getLines();
            System.out.println(lines);
            scanner.scan(lines);
        } catch (ReaderException e) {
            System.out.println(e.getMessage());
        }
    }
}