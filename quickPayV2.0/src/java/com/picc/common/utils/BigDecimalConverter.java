package com.picc.common.utils;

import java.math.BigDecimal;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

public class BigDecimalConverter implements Converter {
	
	private int newScale;
	private int roundingMode;
	
	public BigDecimalConverter(int newScale, int roundingMode) {
		this.newScale = newScale;
		this.roundingMode = roundingMode;
	}

	public Object convert(Class type, Object value) {
		String p = (String) value;
		if(p == null || p.trim().length() == 0) {
			return null;
		}
		if(value instanceof BigDecimal) {
			return value;
		}
		try {
			BigDecimal bigDecimal = new BigDecimal(value.toString());
			bigDecimal = bigDecimal.setScale(newScale, roundingMode);
			return bigDecimal;
		}
		catch(Exception e) {
			throw new ConversionException(e);
		}

	}
}
