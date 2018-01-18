package hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexioBBDD {
    private static ConnexioBBDD instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/mysql"; //adreça de la base de dades
    private String username = "root";
    private String password = "";
    
    private ConnexioBBDD() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException ex){
            throw (ex);
        }
    }
    
    public static ConnexioBBDD getInstance() throws SQLException {
        if (instance == null){
            instance = new ConnexioBBDD();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
}
