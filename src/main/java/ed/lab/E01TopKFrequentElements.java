package ed.lab;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class E01TopKFrequentElements {
    HashMap<Integer, Integer> map = new LinkedHashMap<>();
    public int[] topKFrequent(int[] nums, int k) {
        add(nums);
        Queue<Integer> heap = new PriorityQueue<>((a,b)->{return map.get(b) - map.get(a);});
        for(var entry: map.entrySet()) {
            heap.offer(entry.getKey());

            if(heap.size() > k) {
                heap.poll();
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k && !heap.isEmpty(); i++) {
            result[i] = heap.poll();
        }
        return result;
    }

    private void add(int[] nums) {
        for (int num : nums) {
            if(map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
    }
}
