package com.furb.lembrare;

import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book, Long>{
	
	public Book findOneByName(String name);

}
