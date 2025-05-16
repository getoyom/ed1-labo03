package ed.lab;
import java.util.*;

public class E02AutocompleteSystem {

    HashMap<String, Integer> map = new LinkedHashMap<>();
    Trie trie = new Trie();
    StringBuilder sb = new StringBuilder();
    public E02AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], times[i]);
            trie.insert(sentences[i]);
        }
    }

    public List<String> input(char c) {
        if(c=='#'){
            trie.insert(sb.toString());
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            sb = new StringBuilder();
            return new ArrayList<>(3);
        }
        PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
            if(map.get(a).equals(map.get(b))){
                return b.compareTo(a);
            } else{return map.get(a)-map.get(b);}});
        sb.append(c);
        if(!trie.startsWith(sb.toString())){
            return new ArrayList<>(3);
        }

        for (String key : map.keySet()) {
            if (key.startsWith(sb.toString())) {
                pq.offer(key);
            }
            if(pq.size() > 3) {
                pq.poll();
            }
        }
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(0, pq.poll());
        }
        return result;

    }
}

//Own Trie implementation
class TrieNode {
    public Map<Character, TrieNode> children = new HashMap<>();
    public boolean isLast = false;
    public String word = null;
}

class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.isLast = true;
        current.word = word;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return true;
    }
}

