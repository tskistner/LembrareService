package com.furb.lembrare.report;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.furb.lembrare.Utils;

@RestController
public class BoletimController {

	@Autowired
	private BoletimService bs;

	@Transactional
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "report/add")
	public ArrayList<ArrayList<Object>> add(@RequestBody Boletim b) {
		b.setDtAtualizacao(new Timestamp(System.currentTimeMillis()));
		bs.add(b);
		return get();
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/report/all")
	public ArrayList<ArrayList<Object>> get() {
		ArrayList<ArrayList<Object>> registers = new ArrayList<>();
		for (Boletim b : bs.getAll(Utils.getUsuarioAtual().getIdUsuario())) {
			registers.add(b.getRegisters());
		}
		return registers;
	}

	@Transactional
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "report/update")
	public ArrayList<ArrayList<Object>> update(@RequestBody Boletim b) {
		b.setDtAtualizacao(new Timestamp(System.currentTimeMillis()));
		bs.update(b);
		return get();
	}

	@Transactional
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "report/delete")
	public ArrayList<ArrayList<Object>> delete(@RequestBody HashMap<String, Object> data) {
		bs.delete(Utils.toLong(data.get("ID_BOLETIM")));
		return get();
	}

}
