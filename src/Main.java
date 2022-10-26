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
    }
}