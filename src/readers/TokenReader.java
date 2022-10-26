package readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TokenReader {
    private List<String> tokens;

    public void read(String filename) throws ReaderException {
        tokens = new ArrayList<>();
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

    public List<String> getTokens() {
        return tokens;
    }


}
