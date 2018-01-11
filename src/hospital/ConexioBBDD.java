package hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexioBBDD {
    private static ConexioBBDD instance;
    private Connection connection;
    private String url = "";//adreça de la base de dades
    private String username = "root";
    private String password = "";
    
    private ConexioBBDD() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException ex){
            throw (ex);
        }
    }
    
    public static ConexioBBDD getInstance() throws SQLException {
        if (instance == null){
            instance = new ConexioBBDD();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
}
