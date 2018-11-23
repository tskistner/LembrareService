package com.furb.lembrare.report;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furb.lembrare.address.Enderecos;

@Service
public class BoletimService {
	
	@Autowired
	private BoletimRepo br;
	
	public void add(Boletim u) {
		br.save(u);
	}
		
	public void delete(long id) {
		br.deleteById(id);
	}
	
	public void update(Boletim u) {
		br.save(u);
	}
	
	public ArrayList<Boletim> getAll(long idUsuario) {
		ArrayList<Boletim> res = new ArrayList<>();
		br.findByIdUsuario(idUsuario).forEach(res :: add);
		return res;
	}
	
	public Optional<Boletim> findById(Long id) {
		return br.findById(id);
	}

}
