package StudentManagementSystem.service;

import StudentManagementSystem.dao.StudentDAO;

import StudentManagementSystem.dao.StudentDAOImpl;

import StudentManagementSystem.model.Student;

import java.util.List;

public class StudentService {
    private StudentDAO studentDao;

    public StudentService(){
        studentDao = new StudentDAOImpl();
    }

    public boolean addStudent(Student s){
        return studentDao.addStudent(s);
    }

    public boolean updateStudent(Student s){
        return studentDao.updateStudent(s);
    }

    public boolean deleteStudent(int id){
        return studentDao.deleteStudent(id);
    }

    public Student getStudentById(int id){
        return studentDao.getStudentById(id);
    }

    public List<Student> getAllStudents(){
        return studentDao.getAllStudents();
    }
}
