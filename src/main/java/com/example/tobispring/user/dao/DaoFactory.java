package com.example.tobispring.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration 스프링이 빈 팩토리를 위한 오브젝트 설정을 담당하는 클래스라고 인식하도록 하는 어노테이션
@Configuration
public class DaoFactory {
	/*
		스프링 프레임워크의 빈 팩토리 or 애플리케이션 컨텍스트가 IoC 방식의 기능 제공 시, 사용할 설정정보
		XML과 같은 스프링 전용 설정정보라고 보아도 됌
	*/

	// @Bean 오브젝트를 만들어주는 메소드
	@Bean
	public UserDao userDao(){
		return new UserDao(connectionMaker());
	}

	@Bean
	public ConnectionMaker connectionMaker(){
		return new DConnectionMaker();
	}
}