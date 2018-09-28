package com.furb.lembrare.complementaryInformation;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformacaoComplementarService {
	
	@Autowired
	private InformacaoComplementarRepo icr;
	
	public void add(InformacaoComplementar u) {
		icr.save(u);
	}
		
	public void delete(long id) {
		icr.deleteById(id);
	}
	
	public void update(InformacaoComplementar u) {
		icr.save(u);
	}
	
	public ArrayList<InformacaoComplementar> getAll() {
		ArrayList<InformacaoComplementar> res = new ArrayList<InformacaoComplementar>();
		icr.findAll().forEach(res :: add);
		return res;
	}
	
	public Optional<InformacaoComplementar> findById(Long id) {
		return icr.findById(id);
	}

}
