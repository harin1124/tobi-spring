package com.example.tobispring.user.dao;

import org.mariadb.jdbc.Connection;

import java.sql.SQLException;

/*
	ConnectionMaker의 호출 횟수를 알고 싶을 때 만들 수 잇는 클래스 형태다.
	내부에서 직접 DB 커넥셔을 만들지 않고, DAO가 DB 커넥션을 가져올 때마다
	호출하는 makeConnection() 매소드에서 DB 연결 횟수 카운터를 증가시킨다.
*/
public class CountingConnectionMaker implements ConnectionMaker {
	int counter = 0;
	private final ConnectionMaker realConnectionMaker;
	
	public CountingConnectionMaker(ConnectionMaker realConnectionMaker){
		this.realConnectionMaker = realConnectionMaker;
	}

	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		this.counter++;
		return realConnectionMaker.makeConnection();
	}

	public int getCounter(){
		return this.counter;
	}
}
