package hospital;

import hospital.model.Doctor;
import hospital.model.Pacient;
import hospital.model.Visita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hospital {

    static Scanner userInput;
    static Controller controlador;

    public static void main(String[] args) {
        userInput = new Scanner(System.in);

        try {
            System.out.println("Realitzant conexió amb la base de dades");
            ConnexioBBDD connexio = ConnexioBBDD.getInstance();
            System.out.println("Conexio realitzada");
            controlador = new Controller(connexio);
            int userOption;
            do {
                System.out.print("Opcio 1: veure dades\n"
                        + "Opcio 2: afegir cita\n"
                        + "Opcio 3: alta pacient\n"
                        + "Opcio 4: baixa pacient\n"
                        + "Opcio 5: afegir nou pacient\n"
                        + "Opcio 6: esborrar pacient\n"
                );
                try {
                    userOption = Integer.parseInt(userInput.nextLine());
                } catch (NumberFormatException ex) {
                    userOption = -1;
                }
                switch (userOption) {
                    case 1:
                        String something = controlador.getTaulaVisita();
                        System.out.println(something);
                        break;
                    case 2:
                        //afegir cita
                        String s = controlador.getTaulaPacients();
                        System.out.println(s);
                        s = controlador.getTaulaDoctors();
                        System.out.println(s);
                        afegirVisita();
                        break;
                    case 3:
                        //alta pacient
                        break;
                    case 4:
                        //baixa pacient
                        break;
                    case 5:
                        //afegir nou pacient
                        break;
                    case 6:
                        //esborrar pacient
                        break;
                    default:
                        System.out.println("Error: input incorrecte");
                        break;
                }

            } while (userOption != 0);
            /*
            try {
                PreparedStatement stat = connexio.getConnection().prepareStatement("SELECT * FROM hospital.doctor");
                 ResultSet rs = stat.executeQuery();
                 while (rs.next()){
                     System.out.println("posicio: " + rs.getString("posicio"));
                 }
            }
            catch (SQLException ex){
                System.out.println("Error en la consulta: " + ex.toString());
            }*/

            System.out.println("Tancant conexió");
            connexio.getConnection().close();
        } catch (SQLException ex) {
            System.out.println("Error de connexió");
        }
    }

    

    public static void afegirVisita() throws SQLException {
        Visita visita = new Visita();
        System.out.println("Introdueix el DNI del pacient");
        visita.setPacient(controlador.getPacient(userInput.nextLine()));
        System.out.println("Introdueix el DNI del doctor");
        visita.setDoctor(controlador.getDoctor(userInput.nextLine()));
        System.out.println("Introdueix el motiu de la visita:");
        visita.setMotiu(userInput.nextLine());
        System.out.println("Introdueix la data de la visita en format dd/mm/yyyy");
        try {
            String date = userInput.nextLine();
            int day, month, year;
            String[] _date = date.split("/");
            day = Integer.parseInt(_date[0]);
            month = Integer.parseInt(_date[1]);
            year = Integer.parseInt(_date[2]);
            java.sql.Date data = new java.sql.Date(year, month, day);
            visita.setDataVisita(data);
            controlador.crearVisita(visita);
        } catch (NumberFormatException ex) {
            System.out.println("Error: format incorrecte");
        }
    }
}
