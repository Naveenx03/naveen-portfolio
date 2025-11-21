package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner in;
    
    public Patient(Connection connection, Scanner in){
        this.connection = connection;
        this.in = in;
    }

    public void addPatients(){
        System.out.print("Enter Patient's name: ");
        in.nextLine();
        String name = in.nextLine();
        System.out.print("Enter Patient's age: ");
        int age = in.nextInt();
        System.out.print("Enter Patient's gender: ");
        String gender = in.next();

        try {
            String query = "INSERT INTO patients(name, age, gender) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected>0){
                System.out.println("Patient Added Successfully!");
            }
            else{
                System.out.println("Failed To Add Patient!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewPatients(){
        String query = "SELECT * FROM patients";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+------------+-----------------+--------+--------+");
            System.out.println("| PATIENT ID | NAME            | AGE    | GENDER |");
            System.out.println("+------------+-----------------+--------+--------+");
                                                                                   
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-10s | %-15s | %-6s | %-6s |\n", id, name, age, gender);
                System.out.println("+------------+-----------------+--------+--------+");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id){
        String query = "SELECT * FROM patients WHERE id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
