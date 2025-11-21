import java.util.*;
public class Employee_database {
    static int id = 1;
    static ArrayList<Employee> employees = new ArrayList<>();
    void addEmployee(String name,int age,String designation){
        Employee employee = new Employee(id, name, age, designation);
        employees.add(employee);
        id++;
        System.out.println("Employee added successfully");
    }
    void showRecord(){
        if(employees.isEmpty()){
            System.out.println("Employee database is empty!!");
            return;
        }
        for(Employee employee: employees){
            employee.showDetail();
        }
    }
    void searchEmployeeById(int id){
        if(employees.isEmpty()){
            System.out.println("Employee database is empty!!");
            return;
        }
        boolean isFound = false;
        for(Employee employee: employees){
            int empId = employee.getId();
            if(empId == id){
                employee.showDetail();
                isFound = true;
                return;
            }
        }
        if(!isFound){
            System.out.println("No data found!");
        }
    }
    void deleteEmployeeById(int id){
        boolean isFound = employees.removeIf(emp -> emp.getId()==id);
        if(isFound){
            System.out.println("Employee record deleted successfully!");
        }
        else{
            System.out.println("No data found!");
        }
    }
    void updateEmployeeById(int id,String field,String input){
        Employee emp = null;
        for(Employee employee: employees){
            int empId = employee.getId();
            if(empId == id){
                emp = employee;
                break;
            }
        }
        if(emp == null){
            System.out.println("No data found!");
            return;
        }
        else{
            switch (field.toLowerCase()) {
                case "name":
                    emp.setName(input);
                    break;
                case "age":
                    int age;
                    try {
                        age = Integer.parseInt(input);
                        emp.setAge(age);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid input for age!!");
                    }
                case "designation":
                    emp.setDesignation(input);
                    break;
            
                default:
                    System.out.println("Invalid field");
                    break;
            }
        }
        System.out.println("Employee data updated!");
    }
    void clearDatabase(){
        employees.clear();
        System.out.println("Employee database is cleared!!");
    }
}
