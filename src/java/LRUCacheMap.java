package net.elastica.discoveryutil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * An LRU based cache implementation
 * 
 * @author Saif Asif
 *
 */
public class LRUCacheMap<K,V> {

	// Need to decide on the load factor. Better to load it from global_configs
	private static final float hashTableLoadFactor = 0.75f;

	private LinkedHashMap<K,V> cacheMap;
	private int cacheSize;

	/**
	 * Constructor to create a new LRU based CacheMap
	 * 
	 * @param cacheSize the maximum number of entries that will be kept in this cache.
	 */
	public LRUCacheMap(int cacheSize) {
		this.cacheSize = cacheSize;
		int hashTableCapacity = (int)Math.ceil(cacheSize / hashTableLoadFactor) + 1;
		cacheMap = new LinkedHashMap<K,V>(hashTableCapacity, hashTableLoadFactor, true) {
			private static final long serialVersionUID = -162707789837069743L;

			@Override
			protected boolean removeEldestEntry (Map.Entry<K,V> eldest) {
				/*
				 *  We want to remove the eldest entry iff the size of our cache map increase the cacheSize limit.
				 *  Otherwise just silently keep on adding the entries to the map
				 */
				return size() > LRUCacheMap.this.cacheSize; 
			}
		}; 
	}

	/**
	 * Retrieves an entry from the cache. The retrieved entry will become the MRU (most recently used) entry.
	 * 
	 * @param key The key entry to fetch
	 * @return value associated to this key, null otherwise
	 */
	public synchronized V get (K key) {
		return cacheMap.get(key);
	}

	/**
	 * Add the specified key/value pair to the cache map. Thread safe method to avoid threading issues.
	 * The added entry becomes the MRU (Most recently used entry) for this cache map. If the size of the cache map increases
	 * the {@link LRUCache#cacheSize}, then the LRU (Least recently used) entry is removed.
	 * 
	 * @param key the key with which the specified value is to be associated.
	 * @param value a value to be associated with the specified key.
	 */
	public synchronized void put (K key, V value) {
		cacheMap.put(key, value);
	}

	/**
	 * 
	 * TODO do we need this ?
	 * @return a <code>Collection</code> with a copy of the contents of th cache map
	 */
	public synchronized Collection<Map.Entry<K,V>> getAll() {
		return new ArrayList<Map.Entry<K,V>>(cacheMap.entrySet()); 
	}

	/**
	 * Just a convenient method at the moment for the purpose of testing
	 * Print the current cache content in the increasing order of MRU entry
	 * @deprecated
	 */
	public void printValues(){
		for(Object key : cacheMap.keySet()){
			System.out.print(String.valueOf(key));
		}
		System.out.println();
	}

	public static void main(String[] args) {
		LRUCacheMap<String,String> c = new LRUCacheMap<String, String>(3);
		c.put ("1", "one");                           // 1
		c.put ("2", "two");                           // 2 1
		c.put ("3", "three");                         // 3 2 1
		c.printValues();
		c.get("1");
		c.printValues();
		//		c.put ("4", "four");                          // 4 3 2
		//		c.printValues();
		//		c.put ("5", "five");                          // 5 2 4
		//		c.printValues();
		//		c.put ("4", "second four");                   // 4 5 2
		//		c.printValues();

	}

}
