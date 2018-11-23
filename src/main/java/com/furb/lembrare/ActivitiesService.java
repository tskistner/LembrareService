package com.furb.lembrare;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/activitiesservice")
public class ActivitiesService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	@RequestMapping("/allCategories")
	@GetMapping
	public List<Map<String, Object>> getCategories() {
		String sql = "select * from categoria";
		return jdbcTemplate.queryForList(sql);
	}

	@Transactional
	@RequestMapping("/getLevel")
	@GetMapping
	public Integer getLevel(Integer idUsuario, Integer idCategoria) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select qt_nivel ").append(" from categoria_nivel ").append(" where id_usuario = ")
				.append(idUsuario).append(" and id_categoria = ").append(idCategoria);
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}

	@Transactional
	private Integer getRandomExercise(Integer idUsuario, Integer idCategoria, Integer idExercicio) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select id_exercicio ").append(" from categoria_exercicio ").append(" where id_categoria = ")
				.append(idCategoria).append(" and id_exercicio <> ").append(idExercicio)
				.append(" order by rand() limit 1 ");
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "updateLevel")
	@CrossOrigin(origins = "http://localhost:3000")
	public void updateLevel(@RequestBody HashMap<String, Object> data) {
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call atualizar_nivel_usuario(?, ?, ?, ?)}");
			callableStatement.setInt(1, Utils.toInt(data.get("idUsuario")));
			callableStatement.setInt(2, Utils.toInt(data.get("idCategoria")));
			callableStatement.setInt(3, Utils.toInt(data.get("idExercicio")));
			callableStatement.setString(4, Utils.toString(data.get("ieOpcao")));
			callableStatement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "getExercise")
	public HashMap<String, Object> getExercice(@RequestBody HashMap<String, Object> data) {
		try {
			Integer idUsuario = Utils.toInt(data.get("idUsuario"));
			Integer idExercicio = getRandomExercise(idUsuario, Utils.toInt(data.get("idCategoria")),
					Utils.toInt(data.get("idExercicio")));

			//idExercicio = 7;
			HashMap<String, Object> ret = new HashMap<>(5);
			ret.put("EXERCISE", idExercicio);
			if (idExercicio == 4 || idExercicio == 5 || idExercicio == 6) {
				ret.putAll(getImageSound(Utils.toInt(data.get("idExercicio"))));
			} else if (idExercicio != 9 && idExercicio != 8) {
				Connection connection = jdbcTemplate.getDataSource().getConnection();
				CallableStatement callableStatement = connection.prepareCall("{call generate_exercise(?, ?, ?, ?)}");
				callableStatement.setInt(1, idUsuario);
				callableStatement.setInt(2, idExercicio);
				callableStatement.registerOutParameter(3, Types.VARCHAR);
				callableStatement.registerOutParameter(4, Types.VARCHAR);
				callableStatement.executeUpdate();
				ret.put("DESCRIPTION", callableStatement.getString(3));
				ret.put("ANSWER", callableStatement.getString(4));
				connection.close();
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	private HashMap<String, Object> getImageSound(int idExercicio) {
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call generate_image(?, ?, ?, ?, ?)}");
			callableStatement.setInt(1, idExercicio);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.registerOutParameter(4, Types.BLOB);
			callableStatement.registerOutParameter(5, Types.BLOB);
			callableStatement.executeUpdate();
			HashMap<String, Object> ret = new HashMap<>(4);
			ret.put("DESCRIPTION", callableStatement.getString(2));
			ret.put("ANSWER", callableStatement.getString(3));
			ret.put("B_IMAGEM", getImage(callableStatement.getBytes(4)));
			ret.put("B_SOUND", getImage(callableStatement.getBytes(5)));
			connection.close();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping("/imageExcercise/{idExercicio}")
	@GetMapping
	public Map<String, Object> getImageExcercise(@PathVariable Integer idExercicio) throws SQLException, IOException {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select a.b_imagem, a.ds_valores, a.ds_imagem, c.ds_expressao ")
				.append(" from imagem a left join expressao c on a.id_expressao = c.id_expressao, exercicio_imagem b ")
				.append(" where a.id_imagem = b.id_imagem ").append(" and b.id_exercicio = ").append(idExercicio)
				.append(" order by rand() limit 1 ");
		Map<String, Object> retMap = jdbcTemplate.queryForMap(sql.toString());
		retMap.put("B_IMAGEM", getImage((byte[]) retMap.get("B_IMAGEM")));
		return retMap;
	}

	private String getImage(byte[] image) {
		if (image != null) {
			String encodeImage = null;
			encodeImage = Base64.getEncoder().withoutPadding().encodeToString(image);
			return encodeImage;
		}
		return null;
	}

}
