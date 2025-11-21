package HospitalManagementSystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class HospitalManagementSystem {

    private static final String url = "jdbc:mysql://localhost:3306/hospital"; // your DB name here
    private static final String user = "root";  // your MySQL username
    private static final String password = "Naveen#1"; // your MySQL password

    public static void main(String[] args) {
        try {
            // Load the MySQL JDBC driver (optional in new versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection conn = DriverManager.getConnection(url, user, password);
            Scanner in = new Scanner(System.in);
            Patient patient = new Patient(conn, in);
            Doctor doctor = new Doctor(conn);
            while(true){
                System.out.println("HOSPITAL MANAGAMENT SYSTEM");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patient");
                System.out.println("3. View Doctor");
                System.out.println("4. Book Appointement");
                System.out.println("5. Exit");
                System.out.println("Enter your choice: ");
                int choice = in.nextInt();
                switch (choice) {
                    case 1:
                        patient.addPatients();
                        System.out.println();
                        break;
                    case 2:
                        patient.viewPatients();
                        System.out.println();
                        break;
                    case 3:
                        doctor.viewDoctors();
                        System.out.println();
                        break;
                    case 4:
                        bookAppointment(patient, doctor, conn, in);
                        System.out.println();
                        break;
                    case 5:
                        System.out.println("Thank you, Have a nice day!!");
                        conn.close();
                        return;
                    default:
                        System.out.println("Enter valid choice!!!");
                        System.out.println();
                        break;
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }

    static void bookAppointment(Patient patient, Doctor doctor, Connection conn, Scanner in){
        System.out.println("Enter Patient Id: ");
        int patient_id = in.nextInt();
        System.out.println("Enter Doctor Id: ");
        int doctor_id = in.nextInt();
        System.out.println("Enter Appointment Date (yyyy-mm-dd): ");
        String date = in.next();
        if(checkDoctorAvailability(conn, doctor_id, date)){
            String query = "INSERT INTO appointments(doctor_id, patient_id, appointment_date) VALUES(?,?,?)";
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1,patient_id);
                preparedStatement.setInt(2,doctor_id);
                preparedStatement.setString(3,date);
                int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected>0){
                    System.out.println("Appointment Booked Successfully!!!");
                }
                else{
                    System.out.println("Failed To Book Appointment!!!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Patient or Doctor is not available!!");
        }
    }

    static boolean checkDoctorAvailability(Connection conn, int doctor_id, String date){
        String query = "SELECT * FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, doctor_id);
            preparedStatement.setString(2, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
