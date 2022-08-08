package com.example.tobispring.user.test;

import com.example.tobispring.user.dao.ConnectionMaker;
import com.example.tobispring.user.dao.DConnectionMaker;
import com.example.tobispring.user.dao.DaoFactory;
import com.example.tobispring.user.dao.UserDao;

import java.sql.SQLException;

public class UserDaoTest {
	public static void main(String[] args)throws ClassNotFoundException, SQLException {
		UserDao dao = new DaoFactory().userDao();
	}
}
