package com.example.tobispring.user.dao;

import com.example.tobispring.user.domain.User;

import java.sql.*;

public class UserDao {
	/*
	인터페이스는 어떤 일을 하겠다는 기능만 정의해놓은 것이다. 즉, 구현 방법은 없다.
	구현 방법은 인터페이스를 구현한(인스턴스화 시킨) 클래스들이 알아서 결정할 일이다.

	해결
	D사와 N사가 DB 접속용 클래스를 다시 만들어도 UserDao의 코드를 뜯어 고칠 일은 없다.

	또 다른 문제

	*/

	// 인터페이스를 통해 오브젝트에 접근하여 구체적인 클래스 정보를 알 필요가 없다.
	private ConnectionMaker connectionMaker;


	public UserDao(){
		// 하지만 여전히 DConnection 클래스의 생성자를 호출하여 오브젝트를 생성하는 코드가 남아있다.
		connectionMaker = new DConnectionMaker(); // 클래스 명칭이 나온다. 위험!
	}

	public void add(User user)throws ClassNotFoundException, SQLException {
		// 인터페이스에 정의된 메소드를 사용하여, 클래스가 바뀌어도 메소드 이름은 변경되지 않는다.
		// (인터페이스 구현은 동일 메소드 명으로 하므로)
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