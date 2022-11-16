package scanner;

import container.MemoTrie;

import java.util.*;

public class Scanner {
    private final MemoTrie trie;
    private final Classifier classifier;

    public Scanner(Set<String> tokens) {
        this.trie = MemoTrie.build(tokens);
        this.classifier = new Classifier(tokens);
    }

    public void scan(List<String> lines) {
        lines.forEach(line -> {
            List<String> values = this.scan(this.trie, line, 0, new StringBuilder(), false, true);
            this.classifier.classifyAll(values);
        });
    }

    private List<String> scan(MemoTrie trie, String line, int index, StringBuilder value, boolean stringMode, boolean tokenMode) {
        final List<String> list = new ArrayList<>();
        if (index == line.length()) {
            if (!value.isEmpty()) {
                list.addAll(separateExceptions(value.toString()));
            }
            return list;
        }
        final Character character = line.charAt(index);

        if (stringMode) {
            if (character.equals('\"')) {
                list.addAll(separateExceptions(value.append(character).toString()));
                list.addAll(scan(this.trie, line, index + 1, new StringBuilder(), false, true));
                return list;
            } else {
                return scan(this.trie, line, index + 1, value.append(character), true, false);
            }
        } else if (character.equals('\"')) {
            return scan(this.trie, line, index + 1, value.append(character), true, false);
        }

        if (character.equals(' ')) {
            if (!value.isEmpty()) {
                list.addAll(separateExceptions(value.toString()));
                list.addAll(scan(this.trie, line, index + 1, new StringBuilder(), false, true));
                return list;
            } else {
                return scan(this.trie, line, index + 1, new StringBuilder(), false, true);
            }
        }

        if (tokenMode) {
            if (trie.contains(character)) {
                return scan(trie.next(character), line, index + 1, value.append(character), false, true);
            } else {
                return scan(this.trie, line, index, value, false, false);
            }
        } else {
            return scan(this.trie, line, index + 1, value.append(character), false, false);
        }
    }

    private final static Set<Character> exceptions = new HashSet<>(Arrays.asList('.', ',', '[', ']', '(', ')'));

    private List<String> separateExceptions(String string) {
        final List<String> list = new ArrayList<>();
        final List<Integer> splits = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            if (exceptions.contains(string.charAt(i))) {
                splits.add(i);
            }
        }
        if (splits.isEmpty()) {
            return Collections.singletonList(string);
        }
        int index = 0;
        for (final int split : splits) {
            final String first = string.substring(index, split);
            if (!first.isEmpty()) {
                list.add(first);
            }
            final String second = string.substring(split, split + 1);
            list.add(second);
            index = split + 1;
        }
        final String last = string.substring(index);
        if (!last.isEmpty()) {
            list.add(last);
        }
        return list;
    }
}
