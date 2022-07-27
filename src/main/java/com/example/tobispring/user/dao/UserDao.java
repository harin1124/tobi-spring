package com.example.tobispring.user.dao;

import com.example.tobispring.user.domain.User;

import java.sql.*;

public class UserDao {
	/*
	문제
	상속에 대한 문제

	해결
	SimepleConnectionMaker 클래스를 만들어 아예 클래스 단위로 분리하는 것이다.

	하지만
	N사와 D사에 UserDao 클래스만 공급하고 상속을 통해서 db 커넥션 기능을 확장해 사용하는 것이 불가능하다.
	UserDao가 SimpleConnectionMaker 라는 특정 클래스에 종속되어 상속을 사용했을 때, UserDao 코드의 수정이 불가피하기 때문이다.
	결국 UserDao의 소스코드를 함께 제공하지 않고서는 DB 연결 방법을 바꿀 수 없다는 문제로 다시 처음으로 되돌아 온다.

	다시 발생되는 문제

	1.
	makeNewConnection으로 가져왔으나, 만약 D사에서 만든 DB 커넥션 제공 클래스는 openConnection() 메소드 사용하면
	UserDao의 add, get 메소드 커넥션 코드를 모두 수정해주어야 한다.
	2.
	DB 커넥션을 제공하는 클래스가 어떤 것인지를 UserDao가 구체적으로 알고 있어야 한다.
	(UserDao에서 `private SimpleConnectionMaker simpleConnectionMaker;` 이 부분이 결국 UserDao가 DB 커넥션을 제공하는 클래스가 SimpleConnectionMaker임을 알고 있다는 것)
	N사에서 다른 클래스를 구현하려면 UserDao 자체를 다시 수정해야 한다.
	이러한 원인은 UserDao가 바뀔 수 있는 정보(DB 커넥션)인 클래스에 대한 정보를 너무 많이 알고 있는 것이다.
	어떤 클래스인지(SimpleConnectionMaker이라고 명시한 것),해당 커넥션을 가져오는 메소드 이름이 무엇인지(simpleConnectionMaker.makeNewConnection()) 알고 있어야 한다.
	즉, UserDao는 DB커넥션을 가져오는 구체적인 방법에 종속된다.

	해결
	두 개의 클래스가 서로 긴밀하게 연결되어 있지 않도록 중간에 추상적인 느슨한 연결고리를 만들어주는 것
	자바에서의 추상화는 인터페이스를 도입하여 해결할 수 있다.

	*/
	// SimpleConnectionMaker 를 담을 변수를 만든다.
	private SimpleConnectionMaker simpleConnectionMaker;


	// UserDao 객체 생성 시, simpleConnectionMaker 변수에 인스턴스를 담는다.
	public UserDao(){
		simpleConnectionMaker = new SimpleConnectionMaker();
	}

	public void add(User user)throws ClassNotFoundException, SQLException {
		Connection c = simpleConnectionMaker.makeNewConnection();
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
		Connection c = simpleConnectionMaker.makeNewConnection();
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