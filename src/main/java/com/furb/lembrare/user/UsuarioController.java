package com.furb.lembrare.user;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.furb.lembrare.UsuarioAtual;
import com.furb.lembrare.Utils;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService us;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private UsuarioAtual usuarioAtual = new UsuarioAtual();

	@Transactional
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "user/add")
	public void add(@RequestBody Usuario u) {
		u.setDtAtualizacao(new Date(System.currentTimeMillis()));
		u.setDtCriacao(new Date(System.currentTimeMillis()));
		us.add(u);

	}

	@Transactional
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

	@Transactional
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

	@Transactional
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("user/uByID/{id}")
	public Usuario getUserById(@PathVariable Long id) {
		Optional<Usuario> ret = us.findById(id);
		if (ret.isPresent()) {
			return ret.get();
		}
		return null;
	}

	@Transactional
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/AllUsers")
	public ArrayList<Usuario> get() {
		return us.getAll();
	}

	@Transactional
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "user/loggin")
	public Map<String, Object> loggin(@RequestBody HashMap<String, Object> data) {
		try {
			HashMap<String, Object> ret = new HashMap<>();
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call loggin(?, ?, ?, ?, ?)}");
			try {
				callableStatement.setString(1, Utils.toString(data.get("dsCpf")));
				callableStatement.setString(2, Utils.toString(data.get("dsSenha")));
				callableStatement.registerOutParameter(3, Types.INTEGER);
				callableStatement.registerOutParameter(4, Types.VARCHAR);
				callableStatement.registerOutParameter(5, Types.VARCHAR);

				callableStatement.executeUpdate();
			} catch (Exception e) {
				ret.put("DS_ERROR", "<p>Ocorreu um erro, tente novamente!</p>");
				return ret;
			}
			Long idUsuario = callableStatement.getLong(3);
			String nmUsuario = callableStatement.getString(4);
			usuarioAtual.setIdUsuario(idUsuario);
			usuarioAtual.setNmUsuario(nmUsuario);
			Utils.setUsuarioAtual(usuarioAtual);
			ret.put("ID_USER", idUsuario);
			ret.put("NM_USER", nmUsuario);
			ret.put("DS_ERROR", callableStatement.getString(5));
			connection.close();
			return ret;
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("user/getUser")
	public HashMap<String, Object> getUsuario() {
		HashMap<String, Object> ret = new HashMap<>(2);
		ret.put("ID_USER", usuarioAtual.getIdUsuario());
		ret.put("NM_USER", usuarioAtual.getNmUsuario());
		return ret;
	}

	@Transactional
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("user/logout")
	public void logout() {
		usuarioAtual.setIdUsuario(0L);
	}

	@Transactional
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "user/updateUAI")
	public void updateUAI(@RequestBody Usuario u) {
		Optional<Usuario> op = us.findById(u.getIdUsuario());
		if (op.isPresent()) {
			Usuario user = op.get();
			user.setIeFase(u.getIeFase());
			user.setQtTempo(u.getQtTempo());
			user.setDsMedicacao(u.getDsMedicacao());
			user.setIeAntecedentes(u.getIeAntecedentes());
			user.setDsOutrasDoencas(u.getDsOutrasDoencas());
			us.update(user);
		}
	}

}
