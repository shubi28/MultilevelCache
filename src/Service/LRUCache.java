package Service;

import java.io.Serializable;
import java.util.*;

public class LRUCache<Integer> extends LinkedHashMap<Integer, Integer> implements Serializable
{


	//Since this is lru cache, so if this cache get filed (over capacity), it will kick least recently used item.
	private int capacity;


	public LRUCache(int capacity) {
		// Call constructor of LinkedHashMap with accessOrder set to true to
		// achieve LRU Cache behavior
		super(capacity, 1.0f, true);
		this.capacity = capacity;
	}

	// Remove the eldest element whenever size of cache exceeds the capacity
	@Override
	protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
		return (this.size() > capacity);
	}


}