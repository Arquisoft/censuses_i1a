package es.uniovi.asw.dbupdate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class to connect to the database, reports any incident regarding the connection
 * 
 * @author UO237212
 */
public class ConnectionManager {
/**_________________________________________________________________________**/
/* 	Hsqldb
*/	
	private static final String DRIVER = "org.hsqldb.jdbcDriver";
	private static String URL = "jdbc:hsqldb:file:src/main/resources/db/db";
	private static final String USER = "sa";
	private static final String PASS = "";
/**_________________________________________________________________________**/	
	static {
		try {
			Class.forName( DRIVER );
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Driver not found in classpath", e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}

	public static Connection getConnection(String url) throws SQLException {
		return DriverManager.getConnection(url, USER, PASS);
	}
	
	public static void close(ResultSet rs, Statement st, Connection c) {
		close(rs);
		close(st);
		close(c);
	}

	public static void close(ResultSet rs, Statement st) {
		close(rs);
		close(st);
	}

	protected static void close(ResultSet rs) {
		if (rs != null) try { rs.close(); } catch(SQLException e) {};
	}

	public static void close(Statement st) {
		if (st != null ) try { st.close(); } catch(SQLException e) {};
	}

	public static void close(Connection c) {
		if (c != null) try { c.close(); } catch(SQLException e) {};
	}

	public static Connection createThreadConnection() throws SQLException {
		Connection con = getConnection();
		con.setAutoCommit( false );
		threadConnection.set(con);
		return con;
	}

	private static ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();

	public static Connection getCurrentConnection() {
		return threadConnection.get();
	}

	/*
	 * For test purposes
	 */
	public static void setURL(String uRL) {
		URL = uRL;
	}

}