package io.pivotal.fe.cachemanager;

import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnServer;

@OnServer(pool="mypool")
public interface FunctionExecution {

	@FunctionId("createRegion")
	boolean createRegion(String cacheName);

}

