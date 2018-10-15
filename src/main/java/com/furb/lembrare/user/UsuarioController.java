package com.furb.lembrare.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.furb.lembrare.Utils;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService us;

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "user/add")
	public void add(@RequestBody Usuario u) {
		us.add(u);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "user/update")
	public void update(@RequestBody Usuario u) {
		Optional<Usuario> op = us.findById(u.getIdUsuario());
		if (op.isPresent()) {
			Usuario uActual = op.get();
			uActual.copyAll(u);
			uActual.setDtAtualizacao(new Date(System.currentTimeMillis()));
			us.update(uActual);
		}
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "user/upwd")
	public void updatePassword(@RequestBody HashMap<String, Object> params) {
		if (params != null && !params.isEmpty()) {
			Optional<Usuario> op = us.findById(Utils.toLong(params.get("idUsuario")));
			if (op.isPresent()) {
				Usuario u = op.get();
				u.setDsSenha(Utils.toString(params.get("dsSenha")));
				u.setIeUsarSenha(Utils.toString(params.get("ieUsarSenha")));
				us.update(u);
			}
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("user/uByID/{id}")
	public Usuario getUserById(@PathVariable Long id) {
		Optional<Usuario> ret = us.findById(id);
		if (ret.isPresent()) {
			return ret.get();
		}
		return null;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/AllUsers")
	public ArrayList<Usuario> get() {
		return us.getAll();
	}

}
