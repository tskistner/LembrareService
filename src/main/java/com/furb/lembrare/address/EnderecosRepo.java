package com.furb.lembrare.address;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface EnderecosRepo extends CrudRepository<Enderecos, Long> {

	public ArrayList<Enderecos> findByIdUsuario(Long idUsuario);
	
}
