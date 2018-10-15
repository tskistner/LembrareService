package com.furb.lembrare;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/service")
public class ServiceQuery {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/all")
	@GetMapping
	public List<Map<String, Object>> getUsersa() throws SQLException {
		String sql = "select nm_usuario from usuario";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		return rows;		
	}

}