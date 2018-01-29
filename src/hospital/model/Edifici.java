package hospital.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Edifici {
    private int ID;
    private String nom;

    public Edifici() {
    }

    public Edifici(int ID, String nom) {
        this.ID = ID;
        this.nom = nom;
    }

    public Edifici (ResultSet rs) throws SQLException{
        ID = rs.getInt("edificiID");
        nom = rs.getString("nom");
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Edifici{" + "ID=" + ID + ", nom=" + nom + '}';
    }
    
}
