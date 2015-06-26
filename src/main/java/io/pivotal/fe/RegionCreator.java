package io.pivotal.fe;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.RegionAttributesFactoryBean;
import org.springframework.stereotype.Component;

import com.gemstone.gemfire.cache.RegionAttributes;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import com.gemstone.gemfire.cache.client.Pool;
import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.FunctionService;


@Component
public class RegionCreator {
	
	@Autowired
	private Pool pool;

	@Resource(name="booksCache")
	private ClientCache cache;
	
    boolean createRegion(String name) {
    	
    	RegionAttributes attributes =  new RegionAttributesFactoryBean().create();
    	
    	Execution fnExec = FunctionService.onServer(pool).withArgs(name);
    	
    	fnExec.execute("createRegion");    	
    	
    	cache.createClientRegionFactory(ClientRegionShortcut.PROXY).create(name);
    	
        return true;
    }

}
