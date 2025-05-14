package ed.lab;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class E01TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new LinkedHashMap<>();

        for (int num : nums) {
            if(map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        Queue<Integer> heap = new PriorityQueue<>((a,b)->map.get(a) - map.get(b));
        for(var entry: map.entrySet()) {
            heap.offer(entry.getKey());

            if(heap.size() > k) {
                heap.poll();
            }
        }

        int[] result = new int[k];
        int index = 0;
        while(!heap.isEmpty()) {
            result[index] = heap.poll();
            index++;
        }
        return result;
    }
}
