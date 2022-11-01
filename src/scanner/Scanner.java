package scanner;

import container.MemoTrie;

import java.util.List;

public class Scanner {
    private final MemoTrie trie;

    public Scanner(List<String> tokens) {
        this.trie = MemoTrie.build(tokens);
    }

    public void scan(List<String> lines) {
        lines.forEach(this::scan);
    }

    private void scan(String line) {
        MemoTrie trie = this.trie;
        final char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char character = chars[i];
            if (trie.contains(character)) {
                trie = trie.next(character);
            } else if (trie == this.trie) {
                System.out.println(character);
            } else {
                System.out.println(trie.getString().toString());
                trie = this.trie;
                i--;
            }
        }
        System.out.println(trie.getString().toString());
    }
}
