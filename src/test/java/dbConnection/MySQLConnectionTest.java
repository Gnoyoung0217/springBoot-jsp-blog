package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

public class MySQLConnectionTest {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/myapp?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "sleepyjoe";
	
	@Test
	public void testConnection() throws Exception {
		Class.forName(DRIVER);
		
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
