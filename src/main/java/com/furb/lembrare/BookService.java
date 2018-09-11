package com.furb.lembrare;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {

	@Autowired
	private BookRepo br;
	
	public void add(Book b) {
		br.save(b);
	}
	
	public Book getByName(String name) {
		return br.findOneByName(name);
	}
	
	public void delete(long id) {
		br.deleteById(id);
	}
	
	public void update(Book b) {
		br.save(b);
	}
	
	public ArrayList<Book> getAll() {
		ArrayList<Book> res = new ArrayList<Book>();
		br.findAll().forEach(res :: add);
		return res;
	}
	
}
