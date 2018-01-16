package hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hospital {

    public static void main(String[] args) {
        try {
            System.out.println("Realitzant conexió amb la base de dades");
            ConexioBBDD conexio = ConexioBBDD.getInstance();
            System.out.println("Conexio realitzada");
            try {
                PreparedStatement stat = conexio.getConnection().prepareStatement("SELECT * FROM hospital.doctor");
                 ResultSet rs = stat.executeQuery();
                 while (rs.next()){
                     System.out.println("posicio: " + rs.getString("posicio"));
                 }
            }
            catch (SQLException ex){
                System.out.println("Error en la consulta: " + ex.toString());
            }
            conexio.getConnection().close();
        } catch (SQLException ex) {
            System.out.println("Conexió fallida");
        }
    }
}
