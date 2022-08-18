package com.example.tobispring.user.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoConnectionCountingTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext((CountingDaoFactory.class));
		UserDao dao = context.getBean("userDao", UserDao.class);

		// DAO 사용 코드
		CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class); // DL(의존관계검색)을 사용해 이름을 이용해 어느 빈이든 가져온다.
		System.out.println("Connection counter : "+ccm.getCounter());
	}
}
