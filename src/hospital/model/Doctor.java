package hospital.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor extends Persona {
    private String posicio;
    private java.sql.Date dataInici;
    private Departament departament;

    public Doctor() {
    }
    
    public Doctor(String posicio, Date dataInici, Departament departament) {
        this.posicio = posicio;
        this.dataInici = dataInici;
        this.departament = departament;
    }
    
    public Doctor(ResultSet rs) throws SQLException {
        try {
            posicio = (rs.getString("posicio"));
            dataInici = (rs.getDate("dataInici"));
        } catch (SQLException ex) {
            throw (ex);
        }
    }
    
    public void setDoctorData(ResultSet rs) throws SQLException {
        try {
            posicio = (rs.getString("posicio"));
            dataInici = (rs.getDate("dataInici"));
        } catch (SQLException ex) {
            throw (ex);
        }
    }
    
    public String getPosicio() {
        return posicio;
    }

    public void setPosicio(String posicio) {
        this.posicio = posicio;
    }

    public Date getDataInici() {
        return dataInici;
    }

    public void setDataInici(Date dataInici) {
        this.dataInici = dataInici;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }

    @Override
    public String toString() {
        return "Doctor{"  + super.toString() + "posicio=" + posicio + ", dataInici=" + dataInici + ", departament=" + departament + '}';
    }
    
}
