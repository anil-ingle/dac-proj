package com.dac.onlineparking.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	// get Connection obj from jdbc con pool
	public static Connection getConnection() throws SQLException, NamingException {
		Connection con = null;
		InitialContext ic = null;
		DataSource ds = null;
		if (con == null) {
			// create InitialCintext object
			ic = new InitialContext();
			// get DataSource object ref from Jndi Registry
			ds = (DataSource) ic.lookup("java:/comp/env/DsJndi");
			// get Connection obj from jdbc con pool
			con = ds.getConnection();
		}
		return con;
	}// getConnection()

	public void closeMyConnection(Connection con) throws SQLException {
		con.close();
	}
}