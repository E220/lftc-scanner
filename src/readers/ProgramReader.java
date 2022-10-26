package readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramReader implements Reader {
    private List<String> lines;
    @Override
    public void read(String filename) throws ReaderException {
        lines = new ArrayList<>();
        try(final Scanner scanner = new Scanner(new File(filename))) {
            while(scanner.hasNextLine()) {
                final String line = scanner.nextLine().trim();
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new ReaderException("File not Found");
        }
    }

    public List<String> getLines() {
        return lines;
    }
}
