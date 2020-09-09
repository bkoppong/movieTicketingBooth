package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	private static final String URL = "jdbc:derby:Example;create=true";

	private Connection connection;
	private PreparedStatement selectAllExample;

	public DatabaseConnection() {
		try {
			System.out.println("Connecting to database URL: " + URL);
			connection = DriverManager.getConnection(URL);

			resetExampleDatabase();

			System.out.println("Creating Example prepareStatement");
			selectAllExample = connection.prepareStatement("Select * From example");

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	void resetExampleDatabase() {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			System.out.println("Creating Table - This will throw an exception if the table is already created.");
			stmt.execute("CREATE TABLE example (" + "id INTEGER PRIMARY KEY," + "name VARCHAR(255))");
			System.out.println("adding values into example table");
			stmt.executeUpdate("INSERT INTO example VALUES (1, 'Example Data')");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException sqlExeption) {
			sqlExeption.printStackTrace();
		}
	}
}

/**import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    String driver;
    String dbName;
    String connectionURL;

    String username;
    String password;

    public DatabaseConnection() {
        driver = "com.mysql.jdbc.Driver";
        connectionURL = "jdbc:mysql://localhost/";
        dbName = "movieDatabase";
        username = "root";
        password = "";
    }

    public Connection getConnection() throws Exception {

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(connectionURL + dbName, username, password);

        return connection;
    }

    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        try {
            Connection conn = db.getConnection();
            System.out.println("Database successfully connected!");
            conn.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
**/