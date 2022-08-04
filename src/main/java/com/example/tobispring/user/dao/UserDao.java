package com.example.tobispring.user.dao;

import com.example.tobispring.user.domain.User;

import java.sql.*;

public class UserDao {
	/*
		UserDao는 제어의 역전이 적용되어 있다.
		이전에는 ConnectionMaker의 구현 클래스를 결정하고 오브젝트를 만드는 제어권이 있었다.
		하지만 현재 DaoFacotory 에게 있다.
		자신이 사용할 오브젝트(ConnectionMaker)도 DaoFactory가 공급해주는 것을 수동적으로 사용해야 하는 입장이다.
		현재 UserDao는 능동적이 아닌 수동적인 존재가 되었다.
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