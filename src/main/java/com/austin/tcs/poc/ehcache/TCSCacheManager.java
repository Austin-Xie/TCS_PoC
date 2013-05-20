package com.austin.tcs.poc.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class TCSCacheManager {
	private CacheManager cacheManager = CacheManager.create();
	
	public TCSCacheManager() {
		Cache cache = new Cache("TCS", 0, true, true, 300, 300);
//		Cache(String name,
//	             int maxElementsInMemory,
//	             boolean overflowToDisk,
//	             boolean eternal,
//	             long timeToLiveSeconds,
//	             long timeToIdleSeconds)
	             
		cacheManager.addCache(cache);
	}
	
	public void put(String name, Object value) {
		Cache tcsCache = cacheManager.getCache("TCS");
		
		tcsCache.put(new Element(name, value));

	}
	
	public Object get(String name) {
		Cache tcsCache = cacheManager.getCache("TCS");
		
		return tcsCache.get(name).getObjectValue();

	}
	
	public long getCacheSize() {
		return cacheManager.getCache("TCS").getMemoryStoreSize();
	}

}
