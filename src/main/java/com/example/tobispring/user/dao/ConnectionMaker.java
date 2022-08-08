package com.example.tobispring.user.dao;

import org.mariadb.jdbc.Connection;

import java.sql.SQLException;

public interface ConnectionMaker {
	public Connection makeConnection()throws ClassNotFoundException, SQLException;
}