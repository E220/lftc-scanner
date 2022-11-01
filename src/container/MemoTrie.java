package container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoTrie {

    private final Map<Character, MemoTrie> children = new HashMap<>();
    private MemoTrie() {
    }

    public static MemoTrie build(List<String> strings) {
        final MemoTrie trie = new MemoTrie();
        for (final String string : strings) {
            trie.add(string, 0);
        }
        return trie;
    }

    public boolean contains(Character character) {
        return children.containsKey(character);
    }

    public MemoTrie next(Character character) {
        return children.get(character);
    }

    private void add(String string, int index) {
        if (index == string.length()) {
            return;
        }
        final Character character = string.charAt(index);
        if (children.containsKey(character)) {
            children.get(character).add(string, index + 1);
            return;
        }
        children.put(character, new MemoTrie());
        children.get(character).add(string, index + 1);
    }
}
