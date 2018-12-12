package com.furb.lembrare;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/chart")
public class ChartService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private ArrayList<String> labels;
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "data")
	public HashMap<String, Object> data(@RequestBody HashMap<String, String> data) {
		HashMap<String, Object> datasource = new HashMap<>(2);
		datasource.put("CHART", getChartDatasource(data));
		datasource.put("REPORTS", allReports(data));
		return datasource;
	}
	
	private HashMap<String, Object> getChartDatasource(HashMap<String, String> data) {
		labels = new ArrayList<>();
		HashMap<String, Object> datasource = new HashMap<>(3);
		
		datasource.put("DATA", buildInfoSerie(data));
		datasource.put("LABEL_Y", getCategoriesName());
		datasource.put("LABEL_X", labels);
		return datasource;
	}
	
	private ArrayList<ArrayList<Integer>> buildInfoSerie(HashMap<String, String> data) {
		ArrayList<ArrayList<Integer>> values = new ArrayList<>();
		values.add(getSerieValues(1, data));
		values.add(getSerieValues(2, data));
		values.add(getSerieValues(3, data));
		values.add(getSerieValues(4, data));
		return values;
	}
	
	private List<Map<String, Object>> getCategoriesName() {
		return jdbcTemplate.queryForList("select nm_categoria from categoria ");
	}
	
	private ArrayList<Integer> getSerieValues(Integer idCategoria, HashMap<String, String> data) {
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableStatement = connection.prepareCall("{ call generate_chart(?, ?, ?, ?) }");
			callableStatement.setInt(1, Utils.getUsuarioAtual().getIdUsuario().intValue());
			callableStatement.setInt(2, idCategoria);
			callableStatement.setDate(3, Utils.toDate(data.get("DT_INICIO")));
			callableStatement.setDate(4, Utils.toDate(data.get("DT_FIM")));
			callableStatement.execute();
			
			jdbcTemplate.execute("commit");
			
			SqlRowSet srs = jdbcTemplate.queryForRowSet("select qt_value, date_format(dt_value, '%d/%b') dt_value from w_chart ");
			connection.close();
			ArrayList<String> l = new ArrayList<>();
			ArrayList<Integer> values = new ArrayList<>();
			while(srs.next()) {
				l.add(srs.getString("DT_VALUE"));
				values.add(srs.getInt("QT_VALUE"));
				
			}
			
			if (labels.isEmpty()) {
				labels.addAll(l);
			}
			
			return values;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*Queries*/
	private List<Map<String,Object>> allReports(HashMap<String,String> data) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" select DATE_FORMAT(dt_atualizacao, '%d/%M/%Y') dt_atualizacao, ds_boletim, ds_pessoa from boletim ")
		   .append(" where  id_usuario = ").append(Utils.getUsuarioAtual().getIdUsuario())
		   .append(" and    dt_atualizacao between STR_TO_DATE('").append(Utils.toString(data.get("DT_INICIO"))).append("','%Y-%m-%d') ")
		   .append(" and    STR_TO_DATE('").append(Utils.toString(data.get("DT_FIM"))).append("','%Y-%m-%d') ");
		return jdbcTemplate.queryForList(sql.toString());
	}

}

