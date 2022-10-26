package scanner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Scanner {
    private final Set<String> tokens;

    public Scanner(List<String> tokens) {
        this.tokens = new HashSet<>(tokens);
    }

    public void scan(List<String> lines) {
        lines.forEach(this::scan);
    }

    private void scan(String line) {
        System.out.println(line);
    }
}
