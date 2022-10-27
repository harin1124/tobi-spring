package com.example.tobispring;

import com.example.tobispring.user.dao.UserDao;
import com.example.tobispring.user.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

// import static org.junit.Assert.assertThat;
// 해당 클래스는 junit 4의 클래스이며, junit 5는 다음과 같은 클래스 사용
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserDaoTest {
	private static UserDao dao;
	private static User user1;
	private static User user2;
	private static User user3;


	@BeforeAll
	static void setUp(){
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		dao = context.getBean("userDao", UserDao.class);

		user1 = new User("thor", "토르", "thorpw");
		user2 = new User("jane", "제인", "janepw");
		user3 = new User("ironman", "아이언맨", "ironmanpw");
	}

	@Test
	public void addAndGet() throws SQLException {
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);

		dao.add(user1);
		dao.add(user2);
		assertEquals(dao.getCount(), 2);

		User userget1 = dao.get(user1.getId());
		assertEquals(userget1.getName(), user1.getName());
		assertEquals(userget1.getPassword(), user1.getPassword());

		User userget2 = dao.get(user2.getId());
		assertEquals(userget2.getName(), user2.getName());
		assertEquals(userget2.getPassword(), user2.getPassword());
	}

	@Test
	public void count() throws SQLException {
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);

		dao.add(user1);
		assertEquals(dao.getCount(), 1);

		dao.add(user2);
		assertEquals(dao.getCount(), 2);

		dao.add(user3);
		assertEquals(dao.getCount(), 3);
	}

	@Test()
	public void getUserFailure() throws SQLException {
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);

		assertThrows(EmptyResultDataAccessException.class, () -> {
			dao.get("unknown_id");
		});
	}
}