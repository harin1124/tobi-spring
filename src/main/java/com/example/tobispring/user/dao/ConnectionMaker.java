package com.example.tobispring.user.dao;

import org.mariadb.jdbc.Connection;

import java.sql.SQLException;

public interface ConnectionMaker {
	/*
	해당 인터페이스를 사용할 UserDao 입장에서 바라보면,
	ConnectionMaker 인터페이스 타입의 오브젝트라면 어떤 클래스로 만들어졌든 상관없이
	makeConnection() 메소드를 호출하기만 하면 Connection 타입의 객체를 만들어 돌려줄 것이다.


	*/
	public Connection makeConnection()throws ClassNotFoundException, SQLException;
}