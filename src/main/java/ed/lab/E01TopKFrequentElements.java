package ed.lab;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class E01TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        //Hashmap para almacenar la cantidad de apariciones de un número
        HashMap<Integer, Integer> map = new LinkedHashMap<>();
        //Almacenar valores en el HashMap
        for (int num : nums) {
            if(map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        //Cola de prioridades
        Queue<Integer> heap = new PriorityQueue<>((a,b)->map.get(a) - map.get(b));
        // Recorre todas las entradas del mapa y agrega las claves al heap.
        for(var entry: map.entrySet()) {
            heap.offer(entry.getKey());
            //Si el tamaño del heap es mayor a k, eliminar el elemento más pequeño
            if(heap.size() > k) {
                heap.poll();
            }
        }
        //Retornar un resultado de tamaño k
        int[] result = new int[k];
        int index = 0;
        while(!heap.isEmpty()) {
            //Obtener los dos valores más grandes del heap
            result[index] = heap.poll();
            index++;
        }
        return result;
    }
}
