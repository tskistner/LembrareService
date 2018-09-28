package com.furb.lembrare.tables.user;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepo ur;
	
	public void add(Usuario u) {
		ur.save(u);
	}
		
	public void delete(long id) {
		ur.deleteById(id);
	}
	
	public void update(Usuario u) {
		ur.save(u);
	}
	
	public ArrayList<Usuario> getAll() {
		ArrayList<Usuario> res = new ArrayList<Usuario>();
		ur.findAll().forEach(res :: add);
		return res;
	}
	
	public Optional<Usuario> findById(Long id) {
		return ur.findById(id);
	}

}
