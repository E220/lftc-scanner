package container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MemoTrie {

    private final MemoTrie parent;
    private final Character value;
    private final Map<Character, MemoTrie> children = new HashMap<>();
    private MemoTrie(MemoTrie parent, Character value) {
        this.parent = parent;
        this.value = value;
    }

    public static MemoTrie build(List<String> strings) {
        final MemoTrie trie = new MemoTrie(null, null);
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

    public StringBuilder getString() {
        if (Objects.isNull(parent)) {
            return new StringBuilder();
        } else {
            return parent.getString().append(value);
        }
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
        children.put(character, new MemoTrie(this, character));
        children.get(character).add(string, index + 1);
    }
}
