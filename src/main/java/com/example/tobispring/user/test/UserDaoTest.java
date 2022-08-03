package com.example.tobispring.user.test;

import com.example.tobispring.user.dao.ConnectionMaker;
import com.example.tobispring.user.dao.DConnectionMaker;
import com.example.tobispring.user.dao.DaoFactory;
import com.example.tobispring.user.dao.UserDao;

import java.sql.SQLException;

/*
	문제
	해당 클래스는 UserDao의 기능이 잘 동작하는지를 테스트하려고 만든 것이다.
	하지만 ConnectionMaker 구현 클래스를 사용할지 결정하는 기능을 담당하고 있다.
	즉, 이는 완벽한 관심사 분리에 다시 문제가 생긴 것이다.

	해결
	UserDao와 ConnectionMaker 구현 클래스의 오브젝트를 만드는 것과,
	만들어진 두 개의 오브젝트가 연결되어 사용될 수 있도록 관계를 맺어주는 것이다.
	팩토리 객체를 사용해, 객체를 생성하는 쪽과, 객체를 사용하는 쪽의 역할과 책임을 말끔히 분리한다.

	결과
	각 자신의 책임에만 충실하도록 역할에 따라 분리하는 작업 완료
*/
public class UserDaoTest {
	public static void main(String[] args)throws ClassNotFoundException, SQLException {
		UserDao dao = new DaoFactory().userDao();
	}
}
