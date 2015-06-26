package io.pivotal.fe;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.RegionAttributesFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.RegionAttributes;
import com.gemstone.gemfire.cache.client.Pool;
import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.FunctionService;

@RestController
public class Controller {
	
	@Autowired
	RegionCreator regionCreator;
	
	@Autowired
	BookRepository repo;

    @RequestMapping("/")
    Book home() {
        return repo.getByIsbn("1234");
    }
    
    @RequestMapping("/create-region")
    boolean createRegion(String name) {
        return regionCreator.createRegion(name);
    }
    
    
	
}
