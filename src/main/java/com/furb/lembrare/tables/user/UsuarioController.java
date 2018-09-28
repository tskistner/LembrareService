package com.furb.lembrare.tables.user;

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
	public void update(@RequestBody HashMap params) {
		if (params != null && !params.isEmpty()) {
			Optional<Usuario> ret = us.findById(Long.parseLong(params.get("ID_USUARIO").toString()));
			if (ret.isPresent()) {
				Usuario u = ret.get();
				u.setDsCidadeAtual(Utils.toString(params.get("DS_CIDADE_ATUAL")));
				u.setDsCidadeNatal(Utils.toString(params.get("DS_CIDADE_NATAL")));
				u.setDsEmail(Utils.toString(params.get("DS_EMAIL")));
				u.setDsEndereco(Utils.toString(params.get("DS_ENDERECO")));
				u.setDtAtualizacao(new Date(System.currentTimeMillis()));				
				java.util.Date date = Utils.toDate(params.get("DT_NASCIMENTO"));
				if (date != null) {
					u.setDtNascimento(new java.sql.Date(date.getTime()));
				}
				u.setIeSexo(Utils.toString(params.get("IE_SEXO")));
				u.setNmPessoa(Utils.toString(params.get("NM_PESSOA")));
				u.setNrTelefone(Utils.toInt(params.get("NR_TELEFONE")));

				us.update(u);
			}
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("user/gbi/{id}")
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
