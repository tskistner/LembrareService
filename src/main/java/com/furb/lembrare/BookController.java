package com.furb.lembrare;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@Autowired
	private BookService bs;

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "/book/add")
	public void add(@RequestBody Book b) {
		bs.add(b);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("book/bn/{name}")
	public Book get(@PathVariable String name) {
		return bs.getByName(name);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/Allbook")
	public ArrayList<Book> get() {
		return bs.getAll();
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.DELETE, value = "/book/del/{id}")
	public void delete(@PathVariable long id) {
		bs.delete(id);
	}
}
