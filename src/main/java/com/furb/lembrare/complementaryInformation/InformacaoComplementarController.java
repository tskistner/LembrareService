package com.furb.lembrare.complementaryInformation;


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
public class InformacaoComplementarController {
	
	@Autowired
	private InformacaoComplementarService ics;

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "icomp/add")
	public void add(@RequestBody InformacaoComplementar ifc) {
		ics.add(ifc);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "icomp/update")
	public void update(@RequestBody InformacaoComplementar ifc) {
		ics.update(ifc);
		/*if (params != null && !params.isEmpty()) {
			Optional<InformacaoComplementar> ret = ics.findById(Long.parseLong(params.get("ID_INFO_COMP").toString()));
			if (ret.isPresent()) {
				InformacaoComplementar ic = ret.get();
				ic.setIdPessoa(Utils.toLong(params.get("ID_PESSOA")));
				ic.setNmPessoa(Utils.toString(params.get("NM_PESSOA")));
				java.util.Date date = Utils.toDate(params.get("DT_NASCIMENTO"));
				if (date != null) {
					ic.setDtNascimento(new java.sql.Date(date.getTime()));
				}

				ics.update(ic);
			}
		}*/
	}

}
