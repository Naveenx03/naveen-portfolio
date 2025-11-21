package StudentManagementSystem.main;

import StudentManagementSystem.service.StudentService;

import StudentManagementSystem.model.Student;

import java.util.Scanner;

import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        StudentService service = new StudentService();

        while(true){
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Get Student By Id");
            System.out.println("5. List All Student");
            System.out.println("6. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    in.nextLine();
                    String name = in.nextLine();
                    System.out.print("Enter Age: ");
                    int age = in.nextInt();
                    System.out.print("Enter Email: ");
                    String email = in.next();
                    System.out.print("Enter Department: ");
                    in.nextLine();
                    String dept = in.nextLine();
                    Student student = new Student(name,age,email,dept);
                    if(service.addStudent(student)){
                        System.out.println("Student Added Successfully!!");
                    }
                    else{
                        System.out.println("Failed to Add Student!!");
                    }
                    break;
                case 2:
                    System.out.print("Enter Name: ");
                    in.nextLine();
                    String s_name = in.nextLine();
                    System.out.print("Enter Age: ");
                    int s_age = in.nextInt();
                    System.out.print("Enter Email: ");
                    String s_email = in.next();
                    System.out.print("Enter Department: ");
                    in.nextLine();
                    String s_dept = in.nextLine();
                    Student s_student = new Student(s_name,s_age,s_email,s_dept);
                    if(service.addStudent(s_student)){
                        System.out.println("Student Updated Successfully!!");
                    }
                    else{
                        System.out.println("Failed to Update Student!!");
                    }
                    break;
                case 3:
                    System.out.print("Enter Student Id: ");
                    int id = in.nextInt();
                    if(service.deleteStudent(id)){
                        System.out.println("Student record deleted !!");
                    }
                    else System.out.println("Failed to delete student record !!");
                    break;
                case 4:
                    System.out.print("Enter Student Id: ");
                    int s_id = in.nextInt();
                    Student s = service.getStudentById(s_id);
                    if(s != null){
                        System.out.println("+------+-----------------+-------+------------------------+------------------+");
                        System.out.println("| ID   | NAME            | AGE   | EMAIL                  | DEPARTMENT       |");
                        System.out.println("+------+-----------------+-------+------------------------+------------------+");
                        System.out.printf("| %-3s  | %-15s | %-5s | %-22s | %-16s |\n", s.getId(),s.getName(),s.getAge(),s.getEmail(),s.getDepartment());
                        System.out.println("+------+-----------------+-------+------------------------+------------------+");
                    }
                    else System.out.println("Student not found !!");
                    break;
                case 5:
                    List<Student> allStudents = service.getAllStudents();
                    if(allStudents.size()==0){
                        System.out.println("No record found !!");
                        return;
                    }
                    System.out.println("+------+-----------------+-------+------------------------+------------------+");
                    System.out.println("| ID   | NAME            | AGE   | EMAIL                  | DEPARTMENT       |");
                    System.out.println("+------+-----------------+-------+------------------------+------------------+");
                    for(Student std: allStudents){
                        System.out.printf("| %-3s  | %-15s | %-5s | %-22s | %-16s |\n", std.getId(),std.getName(),std.getAge(),std.getEmail(),std.getDepartment());
                    }
                    System.out.println("+------+-----------------+-------+------------------------+------------------+");
                    break;
                case 6:
                    System.out.println("Exiting....");
                    return;
            
                default:
                    System.out.println("Invalid choice, Try again !!");
                    break;
            }
        }
    }
}
