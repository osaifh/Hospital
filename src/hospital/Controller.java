package hospital;

import hospital.model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    //la connexió a la base de dades sobre el que es realitzen totes les queries i consultes
    private ConnexioBBDD connexio;
    
    public Controller(ConnexioBBDD connexio) {
        this.connexio = connexio;
    }
    
    /**
     * Retorna el contingut de la taula de doctors en format string
     * @return el contingut de la taula de doctors en format string
     */
    public String getTaulaDoctors(){
       StringBuilder resultat = new StringBuilder();
       try {
            PreparedStatement stat = connexio.getConnection().prepareStatement("SELECT * FROM hospital.doctor");
            ResultSet rs = stat.executeQuery();
            while (rs.next()){
                Doctor doctor = new Doctor(rs);
                int id = rs.getInt("DoctorID");
                PreparedStatement stat2 = connexio.getConnection().prepareStatement("SELECT * FROM hospital.persona WHERE (personaID = (?))");
                stat2.setInt(1, id);
                ResultSet rs2 = stat2.executeQuery();
                if (rs2.first()){
                    doctor.setPersonaData(rs2);
                }
                resultat.append(doctor.toString());
                resultat.append("\n");
            }
        }
        catch (SQLException ex){
            System.out.println("Error en la consulta: " + ex.toString());
        }
       return (resultat.toString());
    } 
    
    /**
     * Retorna el contingut de la taula de pacients en format string
     * @return el contingut de la taula de pacients en format string
     */
    public String getTaulaPacients(){
       StringBuilder resultat = new StringBuilder();
       try {
            PreparedStatement stat = connexio.getConnection().prepareStatement("SELECT * FROM hospital.pacient");
            ResultSet rs = stat.executeQuery();
            while (rs.next()){
                Pacient pacient = new Pacient(rs);
                int id = rs.getInt("PacientID");
                PreparedStatement stat2 = connexio.getConnection().prepareStatement("SELECT * FROM hospital.persona WHERE (personaID = (?))");
                stat2.setInt(1, id);
                ResultSet rs2 = stat2.executeQuery();
                if (rs2.first()){
                    pacient.setPersonaData(rs2);
                }
                resultat.append(pacient.toString());
                resultat.append("\n");
            }
        }
        catch (SQLException ex){
            System.out.println("Error en la consulta: " + ex.toString());
        }
       return (resultat.toString());
    }
    
    /**
     * Retorna el contingut de la taula de visites en format string
     * @return el contingut de la taula de visites en format string
     */
    public String getTaulaVisita(){
        StringBuilder resultat = new StringBuilder();
        try {
            PreparedStatement stat = connexio.getConnection().prepareStatement("SELECT * FROM hospital.visita");
            ResultSet rs = stat.executeQuery();
            while (rs.next()){
                Visita visita = new Visita(rs);
                visita.setDoctor(getDoctor(rs.getInt("doctorID")));
                visita.setPacient(getPacient(rs.getInt("pacientID")));
                resultat.append(visita.toString());
                resultat.append("\n");
            }
        }
        catch (SQLException ex){
            System.out.println("Error en la consulta: " + ex.toString());
        }
        return resultat.toString();
    }
    
    /**
     * Retorna el contingut de la taula de departaments en format string
     * @return el contingut de la taula de departaments en format string
     */
    public String getTaulaDepartament(){
        StringBuilder resultat = new StringBuilder();
        try {
            PreparedStatement stat = connexio.getConnection().prepareStatement("SELECT * FROM hospital.departament");
            ResultSet rs = stat.executeQuery();
            while (rs.next()){
                Departament departament = new Departament(rs);
                resultat.append(departament.toString());
                resultat.append("\n");
            }
        }
        catch (SQLException ex){
            System.out.println("Error en la consulta: " + ex.toString());
        }
        return resultat.toString();
    }
    
    /**
     * Retorna el contingut de la taula de departaments en format string
     * @return el contingut de la taula de departaments en format string
     */
    public String getTaulaHabitacions(){
        StringBuilder resultat = new StringBuilder();
        try {
            PreparedStatement stat = connexio.getConnection().prepareCall("SELECT * FROM hospital.habitacio");
            ResultSet rs = stat.executeQuery();
            while (rs.next()){
                Habitacio habitacio = new Habitacio(rs);
                resultat.append(habitacio.toString());
                resultat.append("\n");
            }
        }
        catch (SQLException ex){
            System.out.println("Error en la consulta: " + ex.toString());
        }
        return resultat.toString();
    }
    
    /**
     * Retorna el contingut de la taula d'edificis en format string
     * @return el contingut de la taula de d'edificis en format string
     */
    public String getTaulaEdificis(){
        StringBuilder resultat = new StringBuilder();
        try {
            PreparedStatement stat = connexio.getConnection().prepareCall("SELECT * FROM hospital.edifici");
            ResultSet rs = stat.executeQuery();
            while (rs.next()){
                Edifici edifici = new Edifici(rs);
                resultat.append(edifici.toString());
                resultat.append("\n");
            }
        }
        catch (SQLException ex){
            System.out.println("Error en la consulta: " + ex.toString());
        }
        return resultat.toString();
    }
    
    /**
     * Retorna un objecte pacient amb totes les seves dades en base al seu DNI
     * @param DNI el DNI del pacient que volem obtenir
     * @return el pacient corresponent al DNI d'entrada
     */
    public Pacient getPacient(String DNI){
        Pacient pacient;
        try {
            PreparedStatement stat = connexio.getConnection().prepareStatement("SELECT * FROM hospital.persona WHERE (DNI = (?))");
            stat.setString(1, DNI);
            ResultSet rs = stat.executeQuery();
            if (rs.first()){
                pacient = new Pacient();
                pacient.setPersonaData(rs);
                int id = rs.getInt("PersonaID");
                pacient.setPersonaID(id);
                PreparedStatement stat2 = connexio.getConnection().prepareStatement("SELECT * FROM hospital.pacient WHERE (pacientID = (?))");
                stat2.setInt(1, id);
                ResultSet rs2 = stat2.executeQuery();
                if (rs2.first()){
                    pacient.setPacientData(rs2);
                }
                return pacient;
            }
        }
        catch (SQLException ex){
            System.out.println("Error en la consulta: " + ex.toString());
        }
        return null;
    }
    
    /**
     * Retorna un objecte doctor amb totes les seves dades en base al seu DNI
     * @param DNI el DNI del doctor que volem obtenir
     * @return el doctor corresponent al DNI d'entrada
     */
    public Doctor getDoctor(String DNI){
        Doctor doctor;
        try {
            PreparedStatement stat = connexio.getConnection().prepareStatement("SELECT * FROM hospital.persona WHERE (DNI = (?))");
            stat.setString(1, DNI);
            ResultSet rs = stat.executeQuery();
            if (rs.first()){
                doctor = new Doctor();
                doctor.setPersonaData(rs);
                int id = rs.getInt("PersonaID");
                doctor.setPersonaID(id);
                PreparedStatement stat2 = connexio.getConnection().prepareStatement("SELECT * FROM hospital.doctor WHERE (doctorID = (?))");
                stat2.setInt(1, id);
                ResultSet rs2 = stat2.executeQuery();
                if (rs2.first()){
                    doctor.setDoctorData(rs2);
                }
                return doctor;
            }
        }
        catch (SQLException ex){
            System.out.println("Error en la consulta: " + ex.toString());
        }
        return null;
    }
    
     /**
     * Retorna un objecte pacient amb totes les seves dades en base a la seva ID
     * @param personaID la ID del pacient que volem obtenir
     * @return el pacient corresponent a la ID d'entrada
     */
    public Pacient getPacient(int personaID){
        Pacient pacient;
        try {
            PreparedStatement stat = connexio.getConnection().prepareStatement("SELECT * FROM hospital.persona WHERE (personaID = (?))");
            stat.setInt(1, personaID);
            ResultSet rs = stat.executeQuery();
            if (rs.first()){
                pacient = new Pacient();
                pacient.setPersonaData(rs);
                pacient.setPersonaID(personaID);
                PreparedStatement stat2 = connexio.getConnection().prepareStatement("SELECT * FROM hospital.pacient WHERE (pacientID = (?))");
                stat2.setInt(1, personaID);
                ResultSet rs2 = stat2.executeQuery();
                if (rs2.first()){
                    pacient.setPacientData(rs2);
                }
                return pacient;
            }
        }
        catch (SQLException ex){
            System.out.println("Error en la consulta: " + ex.toString());
        }
        return null;
    }
    
     /**
     * Retorna un objecte pacient amb totes les seves dades en base a la seva ID
     * @param personaID la ID del doctor que volem obtenir
     * @return el doctor corresponent a la ID d'entrada
     */
    public Doctor getDoctor(int personaID){
        Doctor doctor;
        try {
            PreparedStatement stat = connexio.getConnection().prepareStatement("SELECT * FROM hospital.persona WHERE (personaID = (?))");
            stat.setInt(1, personaID);
            ResultSet rs = stat.executeQuery();
            if (rs.first()){
                doctor = new Doctor();
                doctor.setPersonaData(rs);
                doctor.setPersonaID(personaID);
                PreparedStatement stat2 = connexio.getConnection().prepareStatement("SELECT * FROM hospital.doctor WHERE (doctorID = (?))");
                stat2.setInt(1, personaID);
                ResultSet rs2 = stat2.executeQuery();
                if (rs2.first()){
                    doctor.setDoctorData(rs2);
                }
                return doctor;
            }
        }
        catch (SQLException ex){
            System.out.println("Error en la consulta: " + ex.toString());
        }
        return null;
    }
    
    /**
     * Retorna el departament corresponent a una ID
     * @param departamentID la ID del departament que volem obtenir
     * @return el departament corresponent a la ID d'entrada
     */
    public Departament getDepartament(int departamentID){
        Departament departament = null;
        try {
            PreparedStatement stat = connexio.getConnection().prepareStatement("SELECT * FROM hospital.departament WHERE (departamentID = (?))");
            stat.setInt(1, departamentID);
            ResultSet rs = stat.executeQuery();
            if (rs.first()){
                departament = new Departament(rs);
            }
        }
        catch (SQLException ex){
            System.out.println("Error en la consulta: " + ex.toString());
        }
        return departament;
    }
    
     /**
     * Retorna l'edifici corresponent a una ID
     * @param edificiID la ID de l'edifici que volem obtenir
     * @return l'edifici corresponent a la ID d'entrada
     */
    public Edifici getEdifici(int edificiID){
        Edifici edifici = null;
        try {
            PreparedStatement stat = connexio.getConnection().prepareStatement("SELECT * FROM hospital.edifici WHERE (hospitalID = (?)");
            stat.setInt(1, edificiID);
            ResultSet rs = stat.executeQuery();
            if (rs.first()){
                edifici = new Edifici(rs);
            }
        }
        catch (SQLException ex){
            System.out.println("Error en la consulta: " + ex.toString());
        }
        return edifici;
    }
    
    /**
     * Crear un pacient a la base de dades
     * @param pacient l'objecte pacient local que volem crear
     */
    public void crearPacient(Pacient pacient){
        try {
            PreparedStatement stat = connexio.getConnection().prepareCall("INSERT INTO hospital.persona(personaID,DNI,dataNaixement,nom,cognom1,cognom2) VALUES (DEFAULT,?,?,?,?,?)");
            stat.setString(1, pacient.getDNI());
            stat.setDate(2, pacient.getDataNaixement());
            stat.setString(3, pacient.getNom());
            stat.setString(4, pacient.getCognom1());
            stat.setString(5, pacient.getCognom2());
            stat.execute();
            PreparedStatement stat2 = connexio.getConnection().prepareStatement("SELECT personaID FROM hospital.persona WHERE (DNI = (?))");
            stat2.setString(1, pacient.getDNI());
            ResultSet rs = stat2.executeQuery();
            if (rs.first()){
                int id = rs.getInt("personaID");
                PreparedStatement stat3 = connexio.getConnection().prepareCall("Insert INTO hospital.pacient(pacientID, dataAlta, dataBaixa) VALUES (?,NULL,NULL)");
                stat3.setInt(1, id);
                stat3.execute();
                System.out.println("S'ha creat el pacient correctament");
            }
        }
        catch (SQLException ex){
            System.out.println("Error en la creació de nou pacient: " + ex.toString());
        }
    }
    
    /**
     * Crear una visita a la base de dades
     * @param visita l'objecte visita local que volem crear
     */
    public void crearVisita(Visita visita){
        try {
            PreparedStatement stat = connexio.getConnection().prepareStatement("INSERT INTO hospital.visita(visitaID, dataVisita, motiu, doctorID, pacientID) VALUES (DEFAULT,?,?,?,?)");
            stat.setDate(1, visita.getDataVisita());
            stat.setString(2, visita.getMotiu());
            stat.setInt(3, visita.getDoctor().getPersonaID());
            stat.setInt(4, visita.getPacient().getPersonaID());
            stat.execute();
            System.out.println("S'ha creat la nova visita correctament");
        }
        catch (SQLException ex){
            System.out.println("Error en creació de nova visita: " + ex.toString());
        }
    }
    
    /**
     * Dona d'alta a un pacient
     * @param pacient el pacient que volem donar d'alta
     */
    public void altaPacient(Pacient pacient){
        try {
            PreparedStatement stat = connexio.getConnection().prepareStatement("UPDATE hospital.pacient SET dataAlta = NOW(), dataBaixa = NULL WHERE (pacientID = (?))");
            stat.setInt(1, pacient.getPersonaID());
            stat.execute();
            System.out.println("S'ha donat d'alta el pacient correctament");
        }
        catch (SQLException ex){
            System.out.println("Error al donar d'alta el pacient: " + ex.toString());
        }
    }
    
    /**
     * Dona de baixa a un pacient
     * @param pacient el pacient que volem donar de baixa
     */
    public void baixaPacient(Pacient pacient){
        try {
            PreparedStatement stat = connexio.getConnection().prepareStatement("UPDATE hospital.pacient SET dataBaixa = NOW() WHERE (pacientID = (?))");
            stat.setInt(1, pacient.getPersonaID());
            stat.execute();
            System.out.println("S'ha donat de baixa el pacient correctament");
        }
        catch (SQLException ex){
            System.out.println("Error al donar de baixa el pacient: " + ex.toString());
        }
    }
    
    /**
     * Esborra un pacient de la base de dades
     * @param pacient el pacient que volem esborrar
     */
    public void esborrarPacient(Pacient pacient){
        try {
            PreparedStatement stat = connexio.getConnection().prepareStatement("DELETE FROM hospital.pacient WHERE (pacientID = (?))");
            stat.setInt(1, pacient.getPersonaID());
            stat.execute();
            System.out.println("S'ha esborrat el pacient correctament");
        }
        catch (SQLException ex){
            System.out.println("Error al donar de baixa el pacient: " + ex.toString());
        }
    }
}
