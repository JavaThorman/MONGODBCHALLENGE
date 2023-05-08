public class Customer extends Person {
    private String customerNumber;

    public Customer(String name, int age, String address, String customerNumber) {
        super(name, age, address);
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
