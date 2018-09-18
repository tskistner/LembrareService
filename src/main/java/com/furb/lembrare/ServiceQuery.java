package com.furb.lembrare;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.Blob;

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
	
	/* ******************************************************************************************************************
	 *                                                   Categories                                                     *
	 ****************************************************************************************************************** */
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
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity  handleFileUpload(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		
		try {
			   /*File image = new File("C:\\test\\longo_prazo.jpg");//file.getOriginalFilename());
			   //file.transferTo(image);
			   final InputStream imageIs = new FileInputStream(image);   
			   LobHandler lobHandler = new DefaultLobHandler(); */
			   
			   jdbcTemplate.update("insert into imagem (id_imagem, qt_tamanho,ds_valores,id_expressao,b_imagem,ds_imagem) "+
						"values (3,0,'',null,"+file.getBytes()+",'teste')");
			   
			   /*jdbcTemplate.update(
			         "insert into imagem (id_imagem, qt_tamanho,ds_valores,id_expressao,b_imagem,ds_imagem) "+
			        		 	" VALUES (?, ?, ?, ?, ?, ?)",
			         new Object[] {
			           1,
			           0,
			           "",
			           null,
			           new SqlLobValue(imageIs, (int)image.length(), lobHandler),
			           "teste"
			         },
			         new int[] {Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.byte, Types.VARCHAR});*/
			   
			   
			  } catch (DataAccessException e) {
			   System.out.println("DataAccessException " + e.getMessage());
			  } catch (FileNotFoundException e) {
			   System.out.println("DataAccessException " + e.getMessage());
			  }

		
		
        /*try {
			byte[] bytes = file.getBytes();
		    //jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		    MapSqlParameterSource parameters = new MapSqlParameterSource();
		    parameters.addValue("blob_field", new SqlLobValue(new ByteArrayInputStream(bytes), bytes.length, new DefaultLobHandler()));
		    jdbcTemplate.update("insert into imagem (id_imagem, qt_tamanho,ds_valores,id_expressao,b_imagem,ds_imagem) "+
		    								"values (1,0,'',null,:blob_field,'teste')", parameters);
		} catch(Exception e) {
		    e.printStackTrace();
		}*/
        //everything was OK, return HTTP OK status (200) to the client
        return ResponseEntity.ok().build();
    }
	
}