package hospital;

import hospital.model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    private ConnexioBBDD connexio;
    
    public Controller(ConnexioBBDD connexio) {
        this.connexio = connexio;
    }
    
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
    
    public Doctor getDoctor(int doctorID){
        Doctor doctor;
        try {
            PreparedStatement stat = connexio.getConnection().prepareStatement("SELECT * FROM hospital.persona WHERE (personaID = (?))");
            stat.setInt(1, doctorID);
            ResultSet rs = stat.executeQuery();
            if (rs.first()){
                doctor = new Doctor();
                doctor.setPersonaData(rs);
                doctor.setPersonaID(doctorID);
                PreparedStatement stat2 = connexio.getConnection().prepareStatement("SELECT * FROM hospital.doctor WHERE (doctorID = (?))");
                stat2.setInt(1, doctorID);
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
    
    public void crearVisita(Visita visita){
        try {
            //INSERT INTO Visita(visitaID, dataVisita, motiu, doctorID, pacientID) VALUES (1,'2018-01-05',"Visita rutinaria",2,5);
            PreparedStatement stat = connexio.getConnection().prepareStatement("INSERT INTO hospital.visita(visitaID, dataVisita, motiu, doctorID, pacientID) VALUES (DEFAULT,?,?,?,?)");
            //stat.setString(1, "DEFAULT");
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
