// Implementation of LFU (Least Frequently Used) cache algorithm
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/*Algorithm Explaination
This Java program implements an LFU (Least Frequently Used) cache using a HashMap and a LinkedHashSet.
 The LFUCache class stores key-value pairs and keeps track of the frequency of each key using a separate HashMap.
 Each time a key is accessed, its frequency is incremented and it is moved to the corresponding LinkedHashSet representing the new frequency.
  The cache has a maximum capacity, and when the capacity is reached, the least frequently used key is evicted from the cache.
   The program includes a main method that creates an LFUCache object, adds and retrieves key-value pairs, and checks that evictions occur as expected.

 */

// Define the LFUCache class
class LFUCache {
    private int capacity; // maximum number of elements that can be stored in the cache
    private Map<Integer, Integer> cache; // the cache data structure
    private Map<Integer, Integer> freq; // keeps track of frequency of each key
    private Map<Integer, LinkedHashSet<Integer>> freqKeys; // keeps track of keys for each frequency level
    private int minFreq; // the minimum frequency level present in the cache
    // Constructor that takes the capacity of the cache as input
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.freq = new HashMap<>();
        this.freqKeys = new HashMap<>();
        this.minFreq = 0;
    }

    // Method to retrieve a value from the cache given a key
    public int get(int key) {
        if (!cache.containsKey(key)) { // check if the key is present in the cache
            return -1; // if not present, return -1
        }
        int value = cache.get(key); // if present, retrieve the value of the key from the cache
        int keyFreq = freq.get(key); // retrieve the frequency of the key
        freq.put(key, keyFreq + 1); // increment the frequency of the key
        freqKeys.get(keyFreq).remove(key); // remove the key from the old frequency level set
        if (keyFreq == minFreq && freqKeys.get(keyFreq).isEmpty()) { // update the minimum frequency level if necessary
            minFreq++;
        }
        freqKeys.computeIfAbsent(keyFreq + 1, k -> new LinkedHashSet<>()).add(key); // add the key to the new frequency level set
        return value; // return the value of the key
    }

    // Method to add a key-value pair to the cache
    public void put(int key, int value) {
        if (capacity <= 0) { // check if the capacity of the cache is zero or negative
            return; // if so, return without adding the key-value pair
        }
        if (cache.containsKey(key)) { // check if the key is already present in the cache
            cache.put(key, value); // if so, update the value of the key in the cache
            get(key); // and increment the frequency of the key
            return;
        }
        if (cache.size() >= capacity) { // check if the cache is already full
            int evict = freqKeys.get(minFreq).iterator().next(); // if so, evict the least frequently used key
            cache.remove(evict); // remove the key-value pair from the cache
            freq.remove(evict); // remove the frequency of the key
            freqKeys.get(minFreq).remove(evict); // remove the key from the set of keys at the minimum frequency level
        }
        cache.put(key, value); // add the key-value pair to the cache
        freq.put(key, 1); // set the frequency of the key to 1
        freqKeys.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key); // add the key to the set of keys at the 1 frequency level
        minFreq = 1; // update the minimum frequency level
    }

    // Main method to test the LFUCache class
    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(5); // create a new LFUCache object with capacity
        // Add key-value pairs to the cache
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        // Retrieve value for key 1, which should be present
        System.out.println(lfuCache.get(1)); // returns 1

// Add another key-value pair to the cache, which should evict key 2
        lfuCache.put(3, 3);

// Retrieve value for key 4, which should not be present
        System.out.println(lfuCache.get(4)); // returns -1 (not found)

// Retrieve value for key 3, which should be present
        System.out.println(lfuCache.get(3)); // returns 3

// Add another key-value pair to the cache, which should evict key 1
        lfuCache.put(4, 4);

// Retrieve value for key 5, which should not be present
        System.out.println(lfuCache.get(5)); // returns -1 (not found)

// Retrieve values for keys 3 and 4, which should be present
        System.out.println(lfuCache.get(3)); // returns 3
        System.out.println(lfuCache.get(4)); // returns 4
    }
}
