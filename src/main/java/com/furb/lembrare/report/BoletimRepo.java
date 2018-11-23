package com.furb.lembrare.report;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface BoletimRepo extends CrudRepository<Boletim, Long> {
	
	public ArrayList<Boletim> findByIdUsuario(Long idUsuario);

}
