package com.example.tobispring.user.dao;

public class DaoFactory {
	/*
		문제
		다양한 DAO가 만들어지더라도 동일 DB를 사용하므로(동일 커넥션메이커) new DConnectionMaker() 가 반복되고 있다.

		해결
		초난감 DAO의 코드에서 getConnection 메소드를 따로 만들어 DB 연결 기능을 분리해내는 메소드를 만드는 방법을 사용한다.
	*/
	public UserDao userDao(){
		return new UserDao(new DConnectionMaker());
	}

	public AccountDao accountDao(){
		return new AccountDao(new DConnectionMaker());
	}

	public MessageDao messageDao(){
		return new MessageDao(new DConnectionMaker());
	}
}
