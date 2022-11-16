package output;

import java.util.LinkedList;
import java.util.List;

public class ProgramInternalForm {
    public record Entry(String token, Integer symbol) {
    }

    private final List<Entry> list = new LinkedList<>();

    public void addToken(String token) {
        list.add(new Entry(token, -1));
    }

    public void addValue(String type, int symbol) {
        list.add(new Entry(type, symbol));
    }

    public Iterable<Entry> getList() {
        return list;
    }
}
