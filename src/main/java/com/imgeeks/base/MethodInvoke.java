package com.imgeeks.base;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MethodInvoke {
	
	public static Map<String, Object> readClassAttr(Object tb) throws Exception{
		
		Field[] fields=tb.getClass().getDeclaredFields();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for(Field field:fields){
			field.setAccessible(true);  
			if(field.get(tb)!=null&&!"".equals(field.get(tb).toString())){
				paramMap.put(field.getName(), field.get(tb));
			}
		}
		return paramMap;
	}
	
}
