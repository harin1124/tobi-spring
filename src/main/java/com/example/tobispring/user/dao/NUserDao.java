package com.example.tobispring.user.dao;

import java.sql.*;

public class NUserDao extends UserDao{
	public Connection getConnection()throws ClassNotFoundException, SQLException{
		Class.forName("org.mariadb.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/DB명", "아이디", "비밀번호");
		// N사의 DB Connection 생성 코드
		// 어떤 db를 연결할지는 UserDao에서는 관여하지 않고 해당 클래스를 확장해,
		// getConnection 메소드를 상속받아 메소드를 구현한 것이다.
		// DB 연결 방식이나 DB 커넥션을 가져오는 방법이 바뀌면, UserDao는 그대로인 채로 해당 클래스의 메소드만 변경하면 된다.
		return c;
	}
}
