package com.austin.tcs.poc.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class WurflCacheManager {
	private CacheManager cacheManager = CacheManager.create();
	
	public WurflCacheManager() {
		Cache cache = new Cache("TCS", 0, true, true, 300, 300);

		cacheManager.addCache(cache);
	}

	public Cache getCache(String cacheName) {
		return cacheManager.getCache(cacheName);
	}

}
