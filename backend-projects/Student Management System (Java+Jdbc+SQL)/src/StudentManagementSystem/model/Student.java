package StudentManagementSystem.model;

public class Student {
    private int id;
    private String name;
    private int age;
    private String email;
    private String department;

    public Student() {
    }

    public Student(String name, int age, String email, String department) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.department = department;
    }

    public Student(int id, String name, int age, String email, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString(){
        return "ID: "+id+", Name: "+name+", Age: "+age+", Email: "+email+", Department: "+department;
    }
}
