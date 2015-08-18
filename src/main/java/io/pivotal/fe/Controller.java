package io.pivotal.fe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
