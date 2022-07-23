package Service;

import java.util.*;

public class MultiLevelCache implements IMultiLevelCache{

	private int levels = 1;
	private List<Integer> capacity;
	private Map<Integer, List<Pair>> multiLevelCache;
//	private final int levelStart;
//	private final int levelEnd;


	public MultiLevelCache(int levels, List<Integer> capacity) {
		this.multiLevelCache = new HashMap<>(levels);
		this.levels = levels;
		this.capacity = capacity;
//		this.levelStart=0;
		init();
	}

	private final void init() {
		//Init all the cache at each level
		for (int i = 0; i < levels; i++) {
			multiLevelCache.put(i, new ArrayList<>(capacity.get(i)));
		}

	}


	@Override
	public void write(final int key, final int value)
	{
		writeOnLevel(key,value,0);
	}

	void writeOnLevel(int key, int value, int level){
		int currentLevelSize = multiLevelCache.get(level).size();
		if(currentLevelSize < capacity.get(level)){
			multiLevelCache.get(level).add(0,new Pair(key,value));
		}
		else{
			//remove last used from level 0
			Pair deletedPair = multiLevelCache.get(level).get(currentLevelSize-1);
			multiLevelCache.get(level).remove(currentLevelSize-1);
			//add in level 0
			multiLevelCache.get(level).add(0,new Pair(key,value));
			//add in level 1
			writeOnLevel(deletedPair.key,deletedPair.value,level++);
		}
	}

	@Override
	public void delete(final int key)
	{
		List<Integer> values = new LinkedList<>();
		int level = key.getLevelId();
		Set<Integer> desiredLevels = getDesiredLevels(level);

		for (Integer levelId : desiredLevels) {
			if (multiLevelCache.get(levelId).containsKey(key)) {
				values.add(multiLevelCache.get(levelId).remove(key));
			}
		}

		return values;
	}

	@Override
	public int read(final int key)
	{
		for (Integer levelId : multiLevelCache.keySet()) {
			if (multiLevelCache.get(levelId).containsKey(key)) {
				return multiLevelCache.get(levelId).get(key);
			}
		}

		return -1;
	}
}