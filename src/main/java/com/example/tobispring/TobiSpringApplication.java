package com.example.tobispring;

import com.example.tobispring.user.dao.UserDao;
import com.example.tobispring.user.domain.User;

//@SpringBootApplication
public class TobiSpringApplication {
	public static void main(String[] args)throws Exception{
		//SpringApplication.run(TobiSpringApplication.class, args);

		UserDao dao = new UserDao();

		// 유저 정보
		User testUser = new User();
		testUser.setId("mcu_thor");
		testUser.setName("thor");
		testUser.setPassword("good");

		dao.add(testUser);

		System.out.println(testUser.getId()+"등록 성공");
	}
}