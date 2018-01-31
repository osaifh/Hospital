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
            //menu principal
            do {
                System.out.print(
                           "Opcio 1: veure dades\n"
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
                        veureTaula();
                        break;
                    case 2:
                        afegirVisita();
                        break;
                    case 3:
                        donarAlta();
                        break;
                    case 4:
                        donarBaixa();
                        break;
                    case 5:
                        afegirPacient();
                        break;
                    case 6:
                        esborrarPacient();
                        break;
                    default:
                        System.out.println("Error: input incorrecte");
                        break;
                }

            } while (userOption != 0);
            System.out.println("Tancant conexió");
            connexio.getConnection().close();
        } catch (SQLException ex) {
            System.out.println("Error de connexió");
        }
    }

    /**
     * Menu en el que es mostren les opcions que permeten especificar quina taula es vol mostrar
     * @throws SQLException error SQL
     */
    private static void veureTaula() throws SQLException {
        int userOption;
        String informacioTaula = "";
        do {
            System.out.print("Quina taula vols veure?\n"
                                    + "1. Departaments\n"
                                    + "2. Doctors\n"
                                    + "3. Pacients\n"
                                    + "4. Visites\n"
                                    + "5. Habitacions\n"
                                    + "6. Edificis\n"
                                    + "0. Cancelar\n");
            try {
                userOption = Integer.parseInt(userInput.nextLine());
            } catch (NumberFormatException ex) {
                userOption = -1;
            }
            switch (userOption) {
                case 1:
                    System.out.println("Informació de la taula Departament:");
                    informacioTaula = controlador.getTaulaDepartament();
                    break;
                case 2:
                    System.out.println("Informació de la taula Doctors:");
                    informacioTaula = controlador.getTaulaDoctors();
                    break;
                case 3:
                    System.out.println("Informació de la taula Pacients:");
                    informacioTaula = controlador.getTaulaPacients();
                    break;
                case 4:
                    System.out.println("Informació de la taula Visites:");
                    informacioTaula = controlador.getTaulaVisita();
                    break;
                case 5:
                    System.out.println("Informacio de la taula habitacions:");
                    informacioTaula = controlador.getTaulaHabitacions();
                    break;
                case 6:
                    System.out.println("Informació de la taula edificis:");
                    informacioTaula = controlador.getTaulaEdificis();
                    break;
                default:
                    System.out.println("Error: input incorrecte");
                    break;
            }
        } while (userOption < 0 || userOption > 6);
        if (userOption != 0){
            System.out.println(informacioTaula);
        }
        else {
            System.out.println("S'ha cancelat l'acció");
        }
    }

    /**
     * acció d'afegir un client a la base de dades
     * @throws SQLException Error SQL
     */
    private static void afegirPacient() throws SQLException {
        try {
            Pacient pacient = new Pacient();
            System.out.println("Introdueix el DNI del pacient");
            pacient.setDNI(userInput.nextLine());
            System.out.println("Introdueix el nom del pacient");
            pacient.setNom(userInput.nextLine());
            System.out.println("Introdueix el primer cognom del pacient");
            pacient.setCognom1(userInput.nextLine());
            System.out.println("Introdueix el segon nom del pacient");
            pacient.setCognom2(userInput.nextLine());
            System.out.println("Introdueix la data de naixement en format dd/mm/yyyy");
            pacient.setDataNaixement(getData(userInput.nextLine()));
            controlador.crearPacient(pacient);
        }
        catch (NumberFormatException ex) {
            System.out.println("Error: format d'entrada incorrecte");
        }
        catch(NullPointerException ex){
            System.out.println("Error: dades invalides");
        }
    }
    
    /**
     * Acció d'afegir una nova visita a la base de dades
     * @throws SQLException Error SQL
     */
    private static void afegirVisita() throws SQLException {
        try {
            Visita visita = new Visita();
            System.out.println("Introdueix el DNI del pacient");
            visita.setPacient(controlador.getPacient(userInput.nextLine()));
            System.out.println("Introdueix el DNI del doctor");
            visita.setDoctor(controlador.getDoctor(userInput.nextLine()));
            System.out.println("Introdueix el motiu de la visita:");
            visita.setMotiu(userInput.nextLine());
            System.out.println("Introdueix la data de la visita en format dd/mm/yyyy");
            visita.setDataVisita(getData(userInput.nextLine()));
            controlador.crearVisita(visita);
        } catch (NumberFormatException ex) {
            System.out.println("Error: format d'entrada incorrecte");
        }
        catch(NullPointerException ex){
            System.out.println("Error: dades invalides");
        }
    }
    
    /**
     * Acció de donar d'alta a un pacient
     * @throws SQLException Error SQL
     */
    private static void donarAlta() throws SQLException {
        System.out.println("Introdueix el DNI del pacient");
        Pacient pacient = controlador.getPacient(userInput.nextLine());
        if (pacient != null){
            if (pacient.getDataAlta() != null){
                System.out.println("Error: aquest pacient ja esta donat d'alta");
            }
            else {
                controlador.altaPacient(pacient);
            }
        }
        else {
            System.out.println("Error: el pacient especificat no existeix");
        }
    }
    
    /**
     * Acció de donar de baixa a un pacient
     * @throws SQLException Error SQL
     */
    private static void donarBaixa() throws SQLException {
        System.out.println("Introdueix el DNI del pacient");
        Pacient pacient = controlador.getPacient(userInput.nextLine());
        if (pacient != null){
            if (pacient.getDataAlta() == null){
                System.out.println("Error: aquest pacient no esta donat d'alta");
            }
            else {
                controlador.baixaPacient(pacient);
            }
        }
        else {
            System.out.println("Error: el pacient especificat no existeix");
        }
    }
    
    /**
     * Acció d'esborrar un pacient de la base de dades
     */
    private static void esborrarPacient() {
        System.out.println("Introdueix el DNI del pacient");
        Pacient pacient = controlador.getPacient(userInput.nextLine());
        if (pacient != null){
            controlador.esborrarPacient(pacient);
        }
        else {
            System.out.println("Error: el pacient especificat no existeix");
        }
    }
    
    /**
     * Parseja una data de un input per convertir-la a un format que SQL pugui interpretar
     * @param input la data en format string
     * @return la data en format java.sql.Date
     */
    private static java.sql.Date getData(String input){
        int day, month, year;
        String[] _date = input.split("/");
        day = Integer.parseInt(_date[0]);
        month = Integer.parseInt(_date[1]);
        year = Integer.parseInt(_date[2]);
        java.sql.Date data = new java.sql.Date(year, month, day);
        return data;
    }

}
