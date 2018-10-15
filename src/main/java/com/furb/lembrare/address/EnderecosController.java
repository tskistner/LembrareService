package com.furb.lembrare.address;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.furb.lembrare.Utils;

@RestController
public class EnderecosController {
	
	@Autowired
	private EnderecosService endS;

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "address/add")
	public ArrayList<ArrayList<Object>> add(@RequestBody Enderecos end) {
		endS.add(end);
		return get(end.getIdUsuario());
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "address/update")
	public ArrayList<ArrayList<Object>> update(@RequestBody Enderecos end) {
		endS.update(end);
		return get(end.getIdUsuario());
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "address/delete")
	public ArrayList<ArrayList<Object>> delete(@RequestBody HashMap<String,Object> param) {
		endS.delete(Utils.toLong(param.get("ID")));
		return get(Utils.toLong(param.get("idUsuario")));
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/address/all/{idUsuario}")
	public ArrayList<ArrayList<Object>> get(@PathVariable Long idUsuario) {
		ArrayList<ArrayList<Object>> registers = new ArrayList<>();
		for (Enderecos end : endS.getAll(idUsuario)) {
			registers.add(end.getRegisters());
		}
		return registers;
	}
}
