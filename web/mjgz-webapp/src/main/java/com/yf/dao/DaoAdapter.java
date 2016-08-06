package com.yf.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.annotation.*;
public class DaoAdapter extends JdbcDaoSupport {

	@Resource(name = "yfJdbcTemplate")
	private JdbcTemplate yfJdbcTemplate;

	@PostConstruct
	public void initJdbcTemplate() {
		super.setJdbcTemplate(yfJdbcTemplate);
	}
	
//	public void getType(){
//		Type t = getClass().getGenericSuperclass();    
//        ParameterizedType p = (ParameterizedType) t ;    
//        Class<E> c = (Class<E>) p.getActualTypeArguments()[0];  
//        System.out.println(c.getName()); 
//		
//	}
}
