package com.furb.lembrare.complementaryInformation;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.furb.lembrare.Utils;

@RestController
public class InformacaoComplementarController {
	
	@Autowired
	private InformacaoComplementarService ics;

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "icomp/add")
	public ArrayList<ArrayList<Object>> add(@RequestBody InformacaoComplementar ifc) {
		ics.add(ifc);
		return get(ifc.getIdUsuario());
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "icomp/update")
	public ArrayList<ArrayList<Object>> update(@RequestBody InformacaoComplementar ifc) {
		ics.update(ifc);
		return get(ifc.getIdUsuario());
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "icomp/delete")
	public ArrayList<ArrayList<Object>> delete(@RequestBody HashMap<String,Object> param) {
		ics.delete(Utils.toLong(param.get("ID")));
		return get(Utils.toLong(param.get("idUsuario")));
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/icomp/all")
	public ArrayList<ArrayList<Object>> get(Long idUsuario) {
		ArrayList<ArrayList<Object>> registers = new ArrayList<>();
		for (InformacaoComplementar ic : ics.getAll(idUsuario)) {
			HashMap<String, Object> visual = new HashMap<>(3);
			visual.put("NAME","ai_ds_pessoa");
			visual.put("VALUE", getExpressao(ic.getIdPessoa()));
			visual.put("SHOW", true);
			ArrayList<Object> line = ic.getRegisters();
			line.add(visual);
			registers.add(line);
		}
		return registers;
	}
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String getExpressao(Long idExpressao) {
		return Utils.toString(jdbcTemplate.queryForObject("select ds_expressao from expressao where id_expressao = "+idExpressao, String.class));
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("icomp/parents/{idUsuario}")
	@GetMapping
	public List<Map<String,Object>> getParents() throws Exception {
		return jdbcTemplate.queryForList("select id_expressao VALUE, ds_expressao OPTION from expressao where id_grupo = 2");
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/icomp/allAI/{idUsuario}")
	@GetMapping
	public Map<String,Object> getAditionalInformation(@PathVariable Long idUsuario) throws Exception {
		HashMap<String, Object> ret = new HashMap<>(2);
		ret.put("PARENTS", getParents());
		ret.put("REGISTERS", get(idUsuario));
		return ret;
	}

}
