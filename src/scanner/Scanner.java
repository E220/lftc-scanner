package scanner;

import container.MemoTrie;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Scanner {
    private final MemoTrie trie;
    private final Classifier classifier;

    public Scanner(Set<String> tokens) {
        this.trie = MemoTrie.build(tokens);
        this.classifier = new Classifier(tokens);
    }

    public void scan(List<String> lines) {
        lines.forEach(line -> {
            List<String> values = this.scan(this.trie, line, 0, new StringBuilder(), false);
            values.forEach(value -> System.out.println(value + " " + classifier.classify(value)));
        });
    }

    private List<String> scan(MemoTrie trie, String line, int index, StringBuilder value, boolean stringMode) {
        final List<String> list = new ArrayList<>();
        if (index == line.length()) {
            list.add(value.toString());
            return list;
        }
        final Character character = line.charAt(index);

        if (stringMode) {
            if (character.equals('\"')) {
                list.add(value.append(character).toString());
                list.addAll(scan(this.trie, line, index + 1, new StringBuilder(), false));
                return list;
            } else {
                return scan(this.trie, line, index + 1, value.append(character), true);
            }
        } else if (character.equals('\"')) {
            return scan(this.trie, line, index + 1, value.append(character), true);
        }

        if (character.equals(' ') && value.length() == 0) {
            return scan(this.trie, line, index + 1, value, false);
        }

        if (trie.contains(character)) {
            return scan(trie.next(character), line, index + 1, value.append(character), false);
        }
        if (trie != this.trie) {
            list.add(value.toString());
            value = new StringBuilder();
            list.addAll(scan(this.trie, line, index, value, false));
            return list;
        }
        list.add(value.append(character).toString());
        list.addAll(scan(this.trie, line, index + 1, new StringBuilder(),false));
        return list;
    }
}
