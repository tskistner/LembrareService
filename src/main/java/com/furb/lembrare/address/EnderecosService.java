package com.furb.lembrare.address;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecosService {
	
	@Autowired
	private EnderecosRepo endRepo;
	
	public void add(Enderecos end) {
		endRepo.save(end);
	}
		
	public void delete(long id) {
		endRepo.deleteById(id);
	}
	
	public void update(Enderecos end) {
		endRepo.save(end);
	}
	
	public ArrayList<Enderecos> getAll(Long idUsuario) {
		ArrayList<Enderecos> res = new ArrayList<>();
		endRepo.findByIdUsuario(idUsuario).forEach(res :: add);
		return res;
	}
	
	public Optional<Enderecos> findById(Long id) {
		return endRepo.findById(id);
	}

}
