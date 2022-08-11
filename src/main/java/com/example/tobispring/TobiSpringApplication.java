package com.example.tobispring;

import com.example.tobispring.user.dao.DaoFactory;
import com.example.tobispring.user.dao.UserDao;
import com.example.tobispring.user.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootApplication
public class TobiSpringApplication {
	public static void main(String[] args)throws Exception{
		//SpringApplication.run(TobiSpringApplication.class, args);

		/*
			직접 생성한 DaoFactory 오브젝트 출력 코드

			해당 팩토리는 실행하면 동일하지 않은 객체로, 매번 새롭게 만들어진다.
			그러므로 출력되는 값이 서로 다르다.
			com.example.tobispring.user.dao.UserDao@36baf30c
			com.example.tobispring.user.dao.UserDao@7a81197d
		*/
		DaoFactory factory = new DaoFactory();
		UserDao dao1 = factory.userDao();
		UserDao dao2 = factory.userDao();

		System.out.println(dao1);
		System.out.println(dao2);

		/*
			스프링의 애플리케이션 컨텍스트에 DaoFactory를 설정정보로 등록
			UserDao 객체를 만드는 작업은 동일하나, 위와 같이 서로 다른 객체가 아닌
			동일 객체로 확인된다.

			스프링은 여러 번 빈을 요청해도 항상 동일한 오브젝트를 돌려준다.
		*/
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

		UserDao dao3 = context.getBean("userDao", UserDao.class);
		UserDao dao4 = context.getBean("userDao", UserDao.class);

		System.out.println(dao3);
		System.out.println(dao4);
	}
}