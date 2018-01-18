package hospital;

import hospital.model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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
        }
        catch (SQLException ex){
            System.out.println("Error en la consulta: " + ex.toString());
        }
    }
    
}
