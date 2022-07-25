package com.example.tobispring.user.dao;

import com.example.tobispring.user.domain.User;

import java.sql.*;

public class UserDao {
	/*
	
	UserDao의 관심
	- DB와 연결을 위한 커넥션을 어떻게 가져올까 => DB 연결과 관련된 관심
	- 파라미터로 넘어온 사용자 정보를 Statement에 바인딩하고, Statement에 담긴 SQL을 DB 통해 실행 => Statement 관련 관심
	- 작업이 끝나ㄴ면 사용한 리소스인 Statement, Connection 오브젝트를 닫아 공유 리소스 반환 => 리소스 반환 관심
	
	+ 공유 리소스를 반환하지 않는 일이 없도록 주의해야 하는 코드

	문제
	- 여러 메소드에서 동일한 작업

	해결
	- 중복 코드의 메소드 추출 => 중복 코드 분리 => Connection 분리해 중복 제거
	
	*/
	public void add(User user)throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/DB명", "아이디", "비밀번호");
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
		Class.forName("org.mariadb.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/DB명", "아이디", "비밀번호");
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