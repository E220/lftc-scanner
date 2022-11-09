package readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class TokenReader implements Reader {
    private Set<String> tokens;

    @Override
    public void read(String filename) throws ReaderException {
        tokens = new LinkedHashSet<>();
        try(final Scanner scanner = new Scanner(new File(filename))) {
           while(scanner.hasNextLine()) {
               final String line = scanner.nextLine();
               if (line.contains(" ")) {
                   throw new ReaderException("Line contains space");
               }
               tokens.add(line);
           }
        } catch (FileNotFoundException e) {
            throw new ReaderException("File not Found");
        }
    }

    public Set<String> getTokens() {
        return tokens;
    }


}
