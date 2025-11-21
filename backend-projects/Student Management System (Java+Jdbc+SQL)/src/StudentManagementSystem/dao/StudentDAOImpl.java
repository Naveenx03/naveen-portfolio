package StudentManagementSystem.dao;

import java.util.List;

import StudentManagementSystem.model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {

    private static final String url = "jdbc:mysql://localhost:3306/studentdb"; // your DB name here
    private static final String user = "root";  // your MySQL username
    private static final String password = "Naveen#1"; // your MySQL password
    private static Connection connection;

    public StudentDAOImpl(){
        try {
            // Load the MySQL JDBC driver (optional in new versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean addStudent(Student s) {
        boolean useId = s.getId()>0;
        String query;
        if(useId) query = "INSERT INTO students(id,name,age,email,department) VALUES(?,?,?,?,?)";
        else query = "INSERT INTO students(name,age,email,department) VALUES(?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            if(useId){
                preparedStatement.setInt(1, s.getId());
                preparedStatement.setString(2, s.getName());
                preparedStatement.setInt(3, s.getAge());
                preparedStatement.setString(4, s.getEmail());
                preparedStatement.setString(5, s.getDepartment());
            }
            else{
                preparedStatement.setString(1, s.getName());
                preparedStatement.setInt(2, s.getAge());
                preparedStatement.setString(3, s.getEmail());
                preparedStatement.setString(4, s.getDepartment());
            }
            

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected>0;
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected>0;
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Student> getAllStudents() {
        String query = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                    students.add(
                    new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("name"), 
                    resultSet.getInt("age"),
                    resultSet.getString("email"),
                    resultSet.getString("department")
                    )
                    );
                }
                return students;
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Student getStudentById(int id) {
        String query = "SELECT * FROM students WHERE id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("name"), 
                    resultSet.getInt("age"),
                    resultSet.getString("email"),
                    resultSet.getString("department")
                    );
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public boolean updateStudent(Student s) {
        String query = "UPDATE students SET name=?, age=?, email=?, department=? WHERE id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, s.getName());
            preparedStatement.setInt(2, s.getAge());
            preparedStatement.setString(3, s.getEmail());
            preparedStatement.setString(4, s.getDepartment());
            preparedStatement.setInt(5, s.getId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

            // System.out.println("+------+-----------------+-------+------------------------+------------------+");
            // System.out.println("| ID   | NAME            | AGE   | EMAIL                  | DEPARTMENT       | ");
            // System.out.println("+------+-----------------+-------+------------------------+------------------+");
                                                                                                               
            // while(resultSet.next()){
            //     System.out.printf("| %-3s | %-15s | %-5s | %-22s | %-16s",
            //     resultSet.getInt("id"), 
            //     resultSet.getString("name"),
            //     resultSet.getInt("age"),
            //     resultSet.getString("email"),
            //     resultSet.getString("department")
            //     );
            // }