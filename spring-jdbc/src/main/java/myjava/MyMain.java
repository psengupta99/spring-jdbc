package myjava;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import myjava.dao.HibernateDaoImpl;
import myjava.dao.MyDaoSupport;
import myjava.dao.MyJdbcDaoimpl;
import myjava.model.Circle;

public class MyMain {
	
	public static void main (String[] args) {
	
	//List<Circle> cir = null;
	//try {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		//MyDaoSupport jdbcDao = context.getBean("myDaoSupport",MyDaoSupport.class);
		HibernateDaoImpl jdbcDao = context.getBean("hibernateDao",HibernateDaoImpl.class);
		//List<Circle> cir = jdbcDao.getCircle();
		Circle cir = jdbcDao.getCircleByID(3);
		System.out.println(cir);
	//	cir = new MyJdbcDaoimpl().getCircle(2);
	/*	jdbcDao.addCircle(5, "Fifth Circle");
		jdbcDao.addCircle(6, "Sixth Circle");*/
		/*
		String name = jdbcDao.getCircleName(5);
		int id = jdbcDao.getCircleId(name);
	
	
		
	
	
	System.out.println(id);
	System.out.println(name);*/
		
	//Circle	cir = jdbcDao.getCircleForId(1);
		
	//	System.out.println(cir);
		//jdbcDao.createTable();
	
	}

}
