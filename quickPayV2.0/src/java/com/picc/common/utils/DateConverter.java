package com.picc.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;



public class DateConverter implements Converter {

    public Object convert(Class type, Object value) {
        String p = (String)value;
        if(p== null || p.trim().length()==0){
            return null;
        }
        if(value instanceof Date) {
            return value;
        }
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.parse(p.trim());
        }
        catch(Exception e){
           
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
				return df.parse(p.trim());
			}
			catch(ParseException e1) {
				e1.printStackTrace();
				return null;
			}
            
        }
        
    }
    
//    public Object convert1(Class type, Object value) {
//        if(value == null) {
//            return null;
//        }
//        
//        if(value instanceof Date) {
//            return value;
//        }
//        
//        if(value instanceof Long) {
//            Long longValue = (Long) value;
//            return new Date(longValue.longValue());
//        }
//        
//            try {
//                return ExcelUtils.SIMPLE_DATE_FORMATER.parse(value.toString());
//            } catch (Exception e) {
//                throw new ConversionException(e);
//            }
//        }

}