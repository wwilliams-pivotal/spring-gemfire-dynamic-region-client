package io.pivotal.fe.cachemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.gemfire.GemfireSystemException;
import org.springframework.data.gemfire.support.GemfireCache;
import org.springframework.data.gemfire.support.GemfireCacheManager;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import com.gemstone.gemfire.cache.client.Pool;

public class GemFireClientCacheManager extends GemfireCacheManager {

	@Autowired
	ClientCache booksCache;

	@Autowired
	Pool mypool;

	@Autowired
	FunctionExecution functionExecution;

	private boolean createRemoteRegions = false; 

	@Override
	public Cache getCache(String cacheName) throws GemfireSystemException{
		Cache cache = super.getCache(cacheName);

		if(cache == null && createRemoteRegions) {
			
			boolean remoteRegionCreated = functionExecution.createRegion(cacheName);
			if(remoteRegionCreated){
				
				Region<?, ?> region = booksCache.createClientRegionFactory(ClientRegionShortcut.PROXY).create(cacheName);
				cache = new GemfireCache(region);
				addCache(cache);
			}else {
				throw new GemfireSystemException(new RuntimeException("Could not create remote region"));
			}
		}
		
		return cache;
	}

	public boolean isCreateRemoteRegions() {
		return createRemoteRegions;
	}

	public void setCreateRemoteRegions(boolean createRemoteRegions) {
		this.createRemoteRegions = createRemoteRegions;
	}	

}
