package hospital.model;

public class Habitacio {
    private int ID;
    private boolean lliure;
    private Edifici edifici;
    private Pacient pacient;

    public Habitacio() {
    }

    public Habitacio(int ID, boolean lliure, Edifici edifici, Pacient pacient) {
        this.ID = ID;
        this.lliure = lliure;
        this.edifici = edifici;
        this.pacient = pacient;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isLliure() {
        return lliure;
    }

    public void setLliure(boolean lliure) {
        this.lliure = lliure;
    }

    public Edifici getEdifici() {
        return edifici;
    }

    public void setEdifici(Edifici edifici) {
        this.edifici = edifici;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }
    
    
}
