package com.austin.tcs.poc.ehcache;

import java.util.Collection;
import java.util.HashSet;

import net.sf.ehcache.CacheEntry;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.CacheEntryFactory;
import net.sf.ehcache.constructs.blocking.SelfPopulatingCache;
import net.sf.ehcache.writer.CacheWriter;
import net.sf.ehcache.writer.writebehind.operations.SingleOperationType;

import com.austin.tcs.wurfl.Device;

public class WurflCacheAccessHelper<K, V> {
	private final Ehcache cache;

	public WurflCacheAccessHelper(Ehcache ehcache) {
		ehcache.registerCacheWriter(new WurflCacheWriter());

		this.cache = new SelfPopulatingCache(ehcache, new WurflCacheEntryFactory());
	}

	@SuppressWarnings("unchecked")
	public V read(K key) {
		return (V) cache.get(key).getObjectValue();
	}

	public void write(K key, V value) {
		cache.put(new Element(key, value));
		// TODO: store <key, vale> into DB
	}

	public long getCacheSize() {
		return cache.getSize();
	}

	/**
	 * Implement the CacheEntryFactory that allows the cache to provide the
	 * read-through strategy
	 */
	private class WurflCacheEntryFactory implements CacheEntryFactory {
		@Override
		public Object createEntry(Object key) throws Exception {
			return new Device("fake", "fake", "fake");//null; // TODO: readDataFromDataStore(key);
		}
	}

	/**
	 * Implement the CacheWriter interface which allows the cache to provide the
	 * write-through or write-behind strategy.
	 */
	private class WurflCacheWriter implements CacheWriter {

		@Override
		public CacheWriter clone(Ehcache cache)
				throws CloneNotSupportedException {

			throw new CloneNotSupportedException();
		}

		@Override
		public void delete(CacheEntry entry) throws CacheException {
			// TODO Auto-generated method stub
			cache.remove(entry.getKey());
			System.out.println(String.format("To delete <%s, %s> from DB",
					entry.getElement().getObjectKey(), entry.getElement()
					.getObjectValue()));
		}

		@Override
		public void deleteAll(Collection<CacheEntry> entries)
				throws CacheException {
			Collection<Object> keys = new HashSet<Object>();
			for (CacheEntry entry : entries) {
				keys.add(entry.getKey());
			}

			cache.removeAll(keys);
		}

		@Override
		public void dispose() throws CacheException {
			// TODO Auto-generated method stub

		}

		@Override
		public void init() {
			// TODO: to initialise Writer
			System.out.println("init called in Writer");
		}

		@Override
		public void throwAway(Element element,
				SingleOperationType operationType, RuntimeException e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void write(Element element) throws CacheException {
			// TODO Auto-generated method stub
			System.out.println(String.format("'write called to store <%s, %s>",
					element.getObjectKey(), element.getObjectValue()));
		}

		@Override
		public void writeAll(Collection<Element> elements)
				throws CacheException {
			// TODO Auto-generated method stub
			for (Element e : elements) {
				write(e);
			}

		}

	}

}
