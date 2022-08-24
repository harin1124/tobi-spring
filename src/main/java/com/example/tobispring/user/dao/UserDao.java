package com.example.tobispring.user.dao;

import com.example.tobispring.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;


public class UserDao {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public void add(User user) throws SQLException {
		Connection c = dataSource.getConnection();
	}

	public UserDao(){

	}
}