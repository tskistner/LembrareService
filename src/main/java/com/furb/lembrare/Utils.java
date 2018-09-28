package com.furb.lembrare;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

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
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				return sdf1.parse(valor.toString());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
