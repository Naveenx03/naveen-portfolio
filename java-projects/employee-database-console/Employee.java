public class Employee {
    private int id;
    private String name;
    private int age;
    private String designation;

    public Employee(int id, String name, int age, String designation) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.designation = designation;
    }

    void showDetail() {
        System.out.println("Id: " + id + " Name: " + name + " Age: " + age + " Designation: " + designation);
    }

    int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}