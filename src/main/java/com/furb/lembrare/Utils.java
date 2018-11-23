package com.furb.lembrare;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class Utils {
	
	private static UsuarioAtual usuarioAtual;
	
	public static void setUsuarioAtual(UsuarioAtual ua) {
		usuarioAtual = ua;
	}
	
	public static UsuarioAtual getUsuarioAtual() {
		return usuarioAtual;
	}

	public static String toString(Object value) {
		if (value == null) {
			return "";
		} else {
			return value.toString();
		}
	}

	public static Long toLong(Object value) {
		if (value == null) {
			return 0L;
		} else {
			return Long.parseLong(value.toString());
		}
	}

	public static Integer toInt(Object value) {
		if (value == null) {
			return 0;
		} else {
			return Integer.parseInt(value.toString());
		}
	}

	public static Date toDate(Object valor) {
		try {
			if (valor != null) {
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = sdf1.parse(valor.toString());
				return new java.sql.Date(date.getTime());
			} else {
				return null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
