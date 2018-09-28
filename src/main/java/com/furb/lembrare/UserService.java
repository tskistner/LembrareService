package com.furb.lembrare;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/userservice")
public class UserService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/parents/{idUsuario}")
	@GetMapping
	public List<Map<String,Object>> getParents(@PathVariable Integer idUsuario) throws Exception {
		return jdbcTemplate.queryForList("select id_expressao VALUE, ds_expressao OPTION from expressao where id_grupo = 2");
	}
	
	@RequestMapping("/getAi/{idUsuario}")
	@GetMapping
	public Map<String,List<Map<String,Object>>> getAditionalInformation(@PathVariable Integer idUsuario) throws Exception {
		HashMap<String, List<Map<String, Object>>> ret = new HashMap<>(2);
		ret.put("PARENTS", getParents(idUsuario));
		ret.put("REGISTERS", jdbcTemplate.queryForList("select * from informacao_complementar where id_usuario = "+idUsuario));
		return ret;
	}

}
