package com.example.tobispring.user.dao;

import com.example.tobispring.user.domain.User;

import java.sql.*;

public abstract class UserDao {
	/*

	문제
	DB커넥션을 판매하고 UserDao 소스를 공개하고 싶지 않은 채로,
	고객(N사, D사)이 스스로 원하는 DB 커넥션 생성 방식을 적용해가며 사용하는 방법은?

	해결
	기존에 같은 클래스에서 다른 메소드로 분리된 DB 커넥션 연결이라는 관심을
	상속을 통해 서브클래스로 분리

	해결은 했으나 상속을 사용한 단점이 있다.
	자바는 다중상속을 허용하지 않는다. 또한, 커넥션 객체를 가져오는 방법을 분리하기 위하여 상속구조로 만들면, 다른 목적으로 UserDao에 상속 적용이 힘들다.
	상속을 통해 관심이 다른 기능을 분리하고, 필요에 따라 다양하게 수정되도록 확장성을 부여했지만 상속관계는 두 가지 다른 관심사에 대해 긴밀한 결헙을 허용한다.
	서브클래스는 슈퍼클래스의 기능을 직접 사용할 수 있다. (NuserDao 에서 UserDao의 메소드 사용 가능)
	즉, 슈퍼클래스의 내부의 변경이 발생할 때 해당 슈퍼클래스를 사용한 서브클래스도 함께 수정하거나 다시 개발해야 할 수 있다.
	반대로 이러한 변화에 불편을 주지 않기 위해서 슈퍼클래스가 더 이상 변화하지 않도록 제약을 가해야 할 수도 있다.

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

	/*
	Connection 오브젝트를 만들어내는 것은 NUserDao의 관심사항으로 이동되었다.
	*/
	public abstract Connection getConnection()throws ClassNotFoundException, SQLException;
}