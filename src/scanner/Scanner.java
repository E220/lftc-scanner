package scanner;

import container.MemoTrie;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
    private final MemoTrie trie;

    public Scanner(List<String> tokens) {
        this.trie = MemoTrie.build(tokens);
    }

    public void scan(List<String> lines) {
        lines.forEach(line -> {
            List<String> values = this.scan(this.trie, line, 0, new StringBuilder());
            System.out.println(values);
        });
    }

    private List<String> scan(MemoTrie trie, String line, int index, StringBuilder value) {
        final List<String> list = new ArrayList<>();
        if (index == line.length()) {
            list.add(value.toString());
            return list;
        }
        final Character character = line.charAt(index);
        if (trie.contains(character)) {
            return scan(trie.next(character), line, index + 1, value.append(character));
        }
        if (trie != this.trie) {
            list.add(value.toString());
            value = new StringBuilder();
            list.addAll(scan(this.trie, line, index, value));
            return list;
        }
        list.add(value.append(character).toString());
        list.addAll(scan(this.trie, line, index + 1, new StringBuilder()));
        return list;
    }
}
