package com.example.tobispring.user.dao;

import com.example.tobispring.user.domain.User;

import java.sql.*;

/*
	UserDao 싱글톤화 하며 발생하는 문제
	- private 생성자를 갖고 있어 상속 불가
		싱글톤 패턴은 생성자를 private로 제한하여, 자신만이 자기 오브젝트를 만들도록 제한한다.
		private 생성자를 가진 클래스는 다른 생성자가 없으면 상속이 불가능하다.
		즉, 객체지향의 장점인 상속과 이를 이용한 다형성을 적용할 수 없다.
	- 테스트에서의 어려움
		싱글톤은 테스트가 어렵거나 테스트 방법에 따라 테스트가 불가능하다.
		싱글톤은 초기화 과정에서 생성자 등을 통해 사용할 오브젝트를 다이내믹하게 주입하기도 힘들어 직접 오브젝트를 만들 수 밖에 없다.
		이럴 때는 테스트용 오브젝트로 대체하기 힘들다.
	- 서버환경에서 싱글톤이 하나만 만들어지느 것 보장 힘듦
		서버에서 클래스 로더를 어떻게 구성하고 있느냐 따라 싱글톤 클래스임에도 하나 이상의 오브젝트가 만들어질 수 있다.
		여러 JVM에 분산되어서 설치가 되는 경우에도 각 독립적으로 오브젝트가 생겨 싱글톤으로서의 가치가 떨어진다.
	- 싱글톤의 사용은 전역 상태를 만들 수 있어 바람직하지 않음
		싱글톤은 사용하는 클라이언트가 정해져 있지 않고, 스태틱 메소드를 이용해 언제든 싱글톤에 쉽게 접근할 수 있어 어디서든 사용될 수 있고
		자연스럽게 전역 상태(global state)로 사용되기 쉽다.
		아무 객체나 자유롭게 접근하고 수정, 공유할 수 있는 전역 상태는 객체지향 프로그래밍에서 권장되지 않는 프로그래밍 모델이다.

	다양한 이유로 싱글톤 디자인 패턴은 위험하다.
	하지만, 스프링은 직접 싱글톤 형태의 오브젝트를 만들고 관리하는 기능을 제공한다.
	이를 싱글톤 레지스트리(singleton registry)라고 한다.
	싱글톤 레지스트리의 장점은 스태틱 메소드와 private 생성자를 사용해야 하는 비정상적인 클래스가 아니라
	평범한 자바 클래스를 싱글톤으로 활용하게 해준다.

	스프링은 IoC 컨테이너이자, 싱글톤 레지스트리이다.
	스프링이 빈을 싱글톤으로 만드는 것은 결국 오브젝트의 생성 방법을 제어하는 IoC 컨테이너로서의 역할이다.

*/
public class UserDao {
	private ConnectionMaker connectionMaker;

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

	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
}