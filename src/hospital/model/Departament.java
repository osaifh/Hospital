package hospital.model;

public class Departament {
    private int ID;
    private String nom;
    private Edifici edifici;

    public Departament(){}

    public Departament(int ID, String nom, Edifici edifici) {
        this.ID = ID;
        this.nom = nom;
        this.edifici = edifici;
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

    public Edifici getEdifici() {
        return edifici;
    }

    public void setEdifici(Edifici edifici) {
        this.edifici = edifici;
    }
    
    
}
