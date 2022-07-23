package Service;

import java.util.*;

public class MultiLevelCache {

	public int levels;
	public List<Integer> capacity;
	public Map<Integer, List<Pair>> multiLevelCache;


	public MultiLevelCache(int levels, List<Integer> capacity) {
		this.levels = levels;
		this.capacity = capacity;
		init();
	}

	private final void init() {
		this.multiLevelCache = new HashMap<>(levels);
		//Init all the cache at each level
		for (int i = 0; i < levels; i++) {
			multiLevelCache.put(i, new ArrayList<>(capacity.get(i)));
		}

	}

	public void write(final int key, final int value)
	{
		writeOnLevel(key,value,0);
	}

	void writeOnLevel(int key, int value, int level){
		int currentLevelSize = multiLevelCache.get(level).size();
		if(currentLevelSize < capacity.get(level)){
			multiLevelCache.get(level).add(0,new Pair(key,value));
			System.out.println("Key : "+key+" Value : "+value+" added to level : "+level);
			return;
		}
		else{
			//remove last used from level 0
			Pair deletedPair = multiLevelCache.get(level).get(currentLevelSize-1);
			multiLevelCache.get(level).remove(currentLevelSize-1);
			//add in level 0
			multiLevelCache.get(level).add(0,new Pair(key,value));
			System.out.println("Key : "+key+" Value : "+value+" added to level : "+level);
			//add in level 1
			writeOnLevel(deletedPair.key,deletedPair.value,++level);
		}
	}

	public void delete(final int key)
	{
		int level=0;
		for (List<Pair> currentListPairs : multiLevelCache.values()){
			for(Pair p: currentListPairs){
				if(key==p.key){
					int keyToDelete = p.key;
					multiLevelCache.get(level).remove(p);
					System.out.println("Key : "+p.key+" Value : "+p.value+" deleted from level : "+level);
					return;
				}
			}
			++level;
		}
	}

	public int read(final int key)
	{
		int level=0;
		for (List<Pair> currentListPairs : multiLevelCache.values()){
			for(Pair p: currentListPairs){
				if(key==p.key){
					System.out.println("Key : "+p.key+" Value : "+p.value+" is read on level : "+level);
					return p.value;
				}
			}
			level++;
		}
		return -1;
	}
}