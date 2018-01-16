package hospital.model;

import java.sql.Date;

public abstract class Persona {
    private int personaID;
    private String DNI, nom, cognom1, cognom2;
    private java.sql.Date dataNaixement;

    public Persona() {
    }

    public Persona(int personaID, String DNI, String nom, String cognom1, String cognom2, Date dataNaixement) {
        this.personaID = personaID;
        this.DNI = DNI;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.dataNaixement = dataNaixement;
    }

    public int getPersonaID() {
        return personaID;
    }

    public void setPersonaID(int personaID) {
        this.personaID = personaID;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom1() {
        return cognom1;
    }

    public void setCognom1(String cognom1) {
        this.cognom1 = cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        this.cognom2 = cognom2;
    }

    public Date getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(Date dataNaixement) {
        this.dataNaixement = dataNaixement;
    }
    
}
