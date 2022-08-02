package com.example.tobispring.user.dao;

import com.example.tobispring.user.domain.User;

import java.sql.*;

public class UserDao {
	/*
	문제
	UserDao 에 DConnection을 알고 있는 것=직접적인 관계가 있는 것=포함되는 것

	해결
	생성자에 파라미터를 두어 전달받으면 해결된다.
	즉, 생성자 파라미터를 이용하며, 파라미터의 타입을 인터페이스로 선언하면, DConnection 이든, NConnection이든 무엇이든 가능하다. (=몰라도 된다)
	UserDao의 모든 코드는 ConnectionMaker 인터페이스 외에는 어떤 클래스와도 관계를 가져서는 안 된다.

	*/

	private ConnectionMaker connectionMaker;

	// ConnectionMaker 구현 클래스는 UserDao의 클라이언트에게 넘기도록 구현되었다.
	public UserDao(ConnectionMaker connectionMaker){
		this.connectionMaker = connectionMaker;
	}

	public void add(User user)throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();

		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values (?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		// 실행
		ps.execute();

		ps.close();
		c.close();
	}

	public User get(String id)throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		PreparedStatement ps = c.prepareStatement("select * from USERS where id = ?");
		ps.setString(1, id);

		// 실행
		ResultSet rs = ps.executeQuery();
		rs.next();

		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));

		rs.close();
		ps.close();
		c.close();

		return user;
	}
}