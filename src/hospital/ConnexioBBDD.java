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
    
    /**
     * Retorna la instancia de connexió amb la base de dades
     * @return la instancia de connexió amb la base de dades
     * @throws SQLException Error SQL
     */
    public static ConnexioBBDD getInstance() throws SQLException {
        if (instance == null){
            instance = new ConnexioBBDD();
        }
        return instance;
    }
    
    /**
     * retorna la connexió a la base de dades
     * @return la connexió a la base de dades
     */
    public Connection getConnection(){
        return connection;
    }
}
