package StudentManagementSystem.dao;
import StudentManagementSystem.model.Student;
import java.util.List;
public interface StudentDAO {
    
    boolean addStudent(Student s);
    boolean updateStudent(Student s);
    boolean deleteStudent(int id);
    Student getStudentById(int id);
    List<Student> getAllStudents();

}
