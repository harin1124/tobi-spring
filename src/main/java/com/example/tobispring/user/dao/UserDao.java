package com.example.tobispring.user.dao;

import com.example.tobispring.user.domain.User;

import java.sql.*;

public class UserDao {
	/*
	문제
	- 여러 메소드에서 동일한 작업

	해결
	- 중복 코드의 메소드 추출 => 중복 코드 분리 => Connection 분리해 중복 제거 (getConnection 메소드 생성)
	*/
	public void add(User user)throws ClassNotFoundException, SQLException {
		Connection c = getConnection();
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
		Connection c = getConnection();
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

	private Connection getConnection()throws ClassNotFoundException, SQLException{
		Class.forName("org.mariadb.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/DB명", "아이디", "비밀번호");
		return c;
	}
}