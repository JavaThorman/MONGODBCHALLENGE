public class Employee extends Person {
    private String employeeNumber;

    public Employee(String name, int age, String address, String employeeNumber) {
        super(name, age, address);
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
