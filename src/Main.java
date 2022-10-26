import readers.ProgramReader;
import readers.ReaderException;
import readers.TokenReader;

public class Main {
    public static void main(String[] args) {
        final TokenReader tokenReader = new TokenReader();
        try {
            tokenReader.read("src/token.in");
            System.out.println(tokenReader.getTokens());
        } catch (ReaderException e) {
            System.out.println(e.getMessage());
        }

        final ProgramReader programReader = new ProgramReader();
        try {
            programReader.read("src/p1.lyt");
            System.out.println(programReader.getLines());
        } catch (ReaderException e) {
            System.out.println(e.getMessage());
        }
    }
}