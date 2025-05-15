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
    PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
        if(map.get(a)==map.get(b)){
            return b.compareTo(a);
        } else{return map.get(a)-map.get(b);}});

    public List<String> input(char c) {
        sb.append(c);
        if(!trie.startsWith(sb.toString())){
            return new ArrayList<>();
        }
        if(c=='#'){
            trie.insert(sb.toString());
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            return new ArrayList<>();
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
        for (int i = 0; i < 3; i++) {
            result.add(pq.poll());
        }
        return result;
    }
}

//Own Trie implementation
class Trie {
    private Node root;
    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node current = root;
        for(char c:word.toCharArray()){
            if(current.children[c-'a']==null){
                current.children[c-'a'] = new Node();
            }
            current = current.children[c-'a'];
        }
        current.isLast=true;
    }

    public boolean startsWith(String prefix) {
        Node current = root;
        for(char c:prefix.toCharArray()){
            if(current.children[c-'a']==null)
                return false;

            current=current.children[c-'a'];
        }
        return true;
    }

    private static class Node{
        public Node[] children;
        public boolean isLast;
        public Node(){
            children = new Node[26];
            isLast=false;
        }
    }
}
