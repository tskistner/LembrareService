package com.furb.lembrare;

import java.util.ArrayList;
import java.util.HashMap;

public interface TableInterface {
	
	public ArrayList<Object> getRegisters();
	
	default HashMap<String, Object> getLine(String key, Object value, Boolean ieShow) {
		HashMap<String,Object> register = new HashMap<>(3);
		register.put("NAME", key);
		register.put("VALUE", value);
		register.put("SHOW", ieShow);
		return register;
	}

}
