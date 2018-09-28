package com.furb.lembrare;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
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
@RequestMapping("/activitiesservice")
public class ActivitiesService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/categories")
	@GetMapping
	public List<Map<String, Object>> getCategories() {
		String sql = "select * from categoria";
		return jdbcTemplate.queryForList(sql);
	}
	
	@RequestMapping("/getLevel")
	@GetMapping
	public Integer getLevel(Integer idUsuario, Integer idCategoria) {
		String sql = "select qt_nivel from categoria_nivel where id_usuario = "+idUsuario+" and id_categoria = "+idCategoria;
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	@RequestMapping("/randomExerciseByLevel/{idUsuario}/{idCategoria}")
	@GetMapping
	public HashMap<String,Object> getRandomExerciseByLevel(@PathVariable Integer idUsuario, @PathVariable Integer idCategoria) {
		Integer level = getLevel(idUsuario, idCategoria);
		String sql = "select id_exercicio from categoria_exercicio where id_categoria = "+idCategoria+" order by RAND() LIMIT 1;";
		Integer idExercicio = jdbcTemplate.queryForObject(sql, Integer.class);
		HashMap<String,Object> ret = new HashMap<>(2);
		ret.put("LEVEL", level);
		ret.put("EXERCICIO", idExercicio);
		return ret;
	}
	
	@RequestMapping("/imageExcercise/{idExercicio}")
	@GetMapping
	public Map<String,Object> getImageExcercise(@PathVariable Integer idExercicio) throws SQLException, IOException {
		List<Map<String,Object>> retList = jdbcTemplate.queryForList(
				"select b.b_imagem, b.ds_valores, b.ds_imagem from exercicio_imagem a, imagem b where a.id_exercicio = "
						+ idExercicio + " and a.id_imagem = b.id_imagem order by rand() limit 1");
		Map<String, Object> retMap = (Map<String,Object>) retList.get(0);
		retMap.put("B_IMAGEM", getImage((byte[]) retMap.get("B_IMAGEM")));
		return retMap;
	}
	
	private String getImage(byte[] image) {
		String encodeImage = null;
		encodeImage = Base64.getEncoder()
			    .withoutPadding()
			    .encodeToString(image);
		return encodeImage; 
	}
	
	@RequestMapping("/updateLevel/{parametros}")
	@GetMapping
	public void updateLevel(@PathVariable HashMap<String,Object> parametros) throws Exception {
		StringBuilder sb = new StringBuilder("");
		sb.append(" call atualizar_nivel_usuario(")
			.append(parametros.get("ID_USUARIO")).append(",")
			.append(parametros.get("ID_CATEGORIA")).append(",")
			.append(parametros.get("ID_EXERCICIO")).append(",")
			.append(parametros.get("IE_OPCAO")).append(")");
		jdbcTemplate.execute(sb.toString());
	}

}
