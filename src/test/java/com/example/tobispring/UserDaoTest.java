package com.example.tobispring;

import com.example.tobispring.user.dao.UserDao;
import com.example.tobispring.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
// import static org.junit.Assert.assertThat;
// 해당 클래스는 junit 4의 클래스이며, junit 5는 다음과 같은 클래스 사용
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoTest {
	@Test
	public void addAndGet() throws SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("user");
		user.setName("myName");
		user.setPassword("myPassword");

		dao.add(user);

		System.out.println(user.getId() + " 등록 성공");

		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());

		System.out.println(user2.getId()+" 조회 성공");
	}
}