//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;

// Class Singleton pour la connexion à la bdd
public class ConnectionDb {

	private String username = "root";
	private String password = "admin";
	private String url      = "localhost:3306";
	
	private static ConnectionDb connect;
	
	private ConnectionDb() {
		
		// Méthode en commentaire pour tester Singleton sans se connecter.
		
		//		try {
		//			connect = DriverManager.getConnection(url, username, password);
		//		}
		//		catch (SQLException e) {
		//			e.printStackTrace();
		//		}
	}
	
	public static ConnectionDb getInstance() {
		if (connect == null) {
			connect = new ConnectionDb();
		}
		return connect;
	}
	
	// Méthode pour avoir un retour testable dans le main.
	public void getInfos() {
		System.out.println("Username : " + this.username + "\nPassword : " + this.password + "\nUrl : " + this.url);
	}
}
