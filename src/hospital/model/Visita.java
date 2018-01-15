package hospital.model;

import java.sql.Date;

public class Visita {

    private int ID;
    private java.sql.Date dataVisita;
    private String motiu;
    private Pacient pacient;
    private Doctor doctor;

    public Visita() {
    }

    public Visita(int ID, Date dataVisita, String motiu, Pacient pacient, Doctor doctor) {
        this.ID = ID;
        this.dataVisita = dataVisita;
        this.motiu = motiu;
        this.pacient = pacient;
        this.doctor = doctor;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(Date dataVisita) {
        this.dataVisita = dataVisita;
    }

    public String getMotiu() {
        return motiu;
    }

    public void setMotiu(String motiu) {
        this.motiu = motiu;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

}
