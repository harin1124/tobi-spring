package com.example.tobispring.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
	새로운 의존관계를 컨테이너가 사용할 설정정보를 이용해 만든 것
	CountingDaoFactory 라는 설정용 클래스
*/
@Configuration
public class CountingDaoFactory {
	@Bean
	public UserDao userDao(){
		return new UserDao(connectionMaker());
	}

	@Bean
	public ConnectionMaker connectionMaker(){
		return new CountingConnectionMaker(realConnectionMaker());
	}

	@Bean
	public ConnectionMaker realConnectionMaker(){
		return new DConnectionMaker();
	}
}
