package com.furb.lembrare.complementaryInformation;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;


public interface InformacaoComplementarRepo extends CrudRepository<InformacaoComplementar, Long> {
	
	public ArrayList<InformacaoComplementar> findByIdUsuario(Long idUsuario);

}
