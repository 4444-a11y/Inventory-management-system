
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class DBConnection {

	 private static final String URL = "jdbc:mysql://localhost:3306/InventoryManagementSystem";
	    private static final String USER = "root";
	    private static final String PASSWORD = "Obaidullah";
	    
	    public static Connection getConnection() throws SQLException {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            return DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (ClassNotFoundException e) {
	            throw new SQLException("MySQL JDBC Driver not found", e);
	        }
	    }
	    
	    public static void testConnection() {
	        try (Connection conn = getConnection()) {
	            System.out.println("Database connection successful!");
	        } catch (SQLException e) {
	            System.err.println("Connection failed: " + e.getMessage());
	        }
	    }
	
	}
