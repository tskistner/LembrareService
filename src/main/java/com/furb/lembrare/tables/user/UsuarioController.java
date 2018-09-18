package com.furb.lembrare.tables.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService us;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "/user/add")
	public void add(@RequestBody Usuario u) {
		us.add(u);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/AllUsers")
	public ArrayList<Usuario> get() {
		return us.getAll();
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.DELETE, value = "/user/del/{id}")
	public void delete(@PathVariable long id) {
		us.delete(id);
	}

}
