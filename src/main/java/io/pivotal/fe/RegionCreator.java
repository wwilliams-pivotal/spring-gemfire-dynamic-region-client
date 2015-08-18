package io.pivotal.fe;

import javax.annotation.Resource;

import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import com.gemstone.gemfire.cache.client.Pool;
import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.FunctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegionCreator {

  @Autowired
  private Pool pool;

  @Resource(name = "booksCache")
  private ClientCache cache;

  boolean createRegion(String name) {
    Execution function = FunctionService.onServer(pool).withArgs(name);

    function.execute("createRegion");

    cache.createClientRegionFactory(ClientRegionShortcut.PROXY).create(name);

    return true;
  }

}
