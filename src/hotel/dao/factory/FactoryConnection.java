package hotel.dao.factory;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class FactoryConnection {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "My@123456%sql";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/hotel_alura?useTimezone=true&serverTimezone=UTC";
	
	public static Connection createPoolConnection() throws SQLException, ClassNotFoundException {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl(DATABASE_URL);
		cpds.setUser(USERNAME);
		cpds.setPassword(PASSWORD);	
		return cpds.getConnection();
	}
}
	
	

