package com.example.tobispring.user.test;

import com.example.tobispring.user.dao.ConnectionMaker;
import com.example.tobispring.user.dao.DConnectionMaker;
import com.example.tobispring.user.dao.UserDao;

import java.sql.SQLException;

// UserDao 의 클라이언트
// 해당 클래스는 UserDao와 ConnectionMaker 구현 클래스와의 런타임 오브젝트 의존 관계를 설정하는 책임을 담당한다.
// 즉, 클라이언트에서 인터페이스의 구현체를 해당 Dao를 생성자를 호출할 때 아규먼트로 넣어줌으로써
// 각 회사마다의 ConnectionMaker 구현 클래스를 만들어 자유롭게 사용할 수 있다!! :) 😁
public class UserDaoTest {
	public static void main(String[] args)throws ClassNotFoundException, SQLException {
		ConnectionMaker connectionMaker = new DConnectionMaker();

		UserDao dao = new UserDao(connectionMaker);
	}
}
