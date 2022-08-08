package com.example.tobispring.user.test;

import com.example.tobispring.user.dao.ConnectionMaker;
import com.example.tobispring.user.dao.DConnectionMaker;
import com.example.tobispring.user.dao.DaoFactory;
import com.example.tobispring.user.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoTest {
	public static void main(String[] args)throws ClassNotFoundException, SQLException {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		//UserDao dao = new DaoFactory().userDao();
		// getBean 메소드의 첫 번째 파라미터는 두 번째 파라미터 클래스 내의 메소드명 문자열
		// 두 번째 파라미터는 리턴 타입 클래스이다.
		UserDao dao = context.getBean("userDao", UserDao.class);
	}
}
