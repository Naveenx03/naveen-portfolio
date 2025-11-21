import java.util.Scanner;
public class Main_menu {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Employee_database employeeDb = new Employee_database();
        while (true) {
            System.out.println("\n======= Employee Database =======");
            System.out.println("1. Add Employee");
            System.out.println("2. Show All Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Clear Database");
            System.out.println("7. Exit");
            System.out.println("================================");
            int option;
            System.out.print("Choose option: ");
            option = in.nextInt();
            in.nextLine();
            switch (option) {
                case 1:
                    System.out.print("Enter Employee Name: ");
                    String name = in.nextLine();
                    System.out.print("Enter Age: ");
                    int age = -1;
                    try {
                        age = in.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid input for age!!");
                        in.nextLine();
                        break;
                    }         
                    in.nextLine();
                    System.out.print("Enter Designation: ");
                    String designation = in.nextLine();
                    employeeDb.addEmployee(name, age, designation);
                    break;
                case 2:
                    employeeDb.showRecord();
                    break;
                case 3:
                    System.out.print("Enter Employee ID: ");
                    int id = in.nextInt();
                    in.nextLine();
                    employeeDb.searchEmployeeById(id);
                    break;
                case 4:
                    System.out.print("Enter Employee ID: ");
                    int emp_Id = in.nextInt();
                    in.nextLine();
                    System.out.print("Enter Field to Update: ");
                    String field = in.next();
                    in.nextLine();
                    System.out.print("Enter Data: ");
                    String input = in.nextLine();
                    employeeDb.updateEmployeeById(emp_Id, field, input);
                    break;
                case 5:
                    System.out.print("Enter Employee ID: ");
                    int emp_id = in.nextInt();
                    in.nextLine();
                    employeeDb.deleteEmployeeById(emp_id);
                    break;
                case 6:
                    employeeDb.clearDatabase();
                    break;
                case 7:
                    System.out.println("Exiting!!");
                    in.close();
                    return;
            
                default:
                    System.out.println("Invalid option! Try again.");
                    break;
            }
        }
    }
}