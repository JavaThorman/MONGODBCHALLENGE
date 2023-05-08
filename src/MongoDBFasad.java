
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.*;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;

public class MongoDBFasad {

    MongoClient client; // MongoClient är en instans av MongoDB-klienten som används för att ansluta till en databas.
    MongoDatabase db; // MongoDatabase är instansen av en databas som används för att kommunicera med databasen.
    MongoCollection<Document> collection; // En MongoCollection innehåller dokument som kan vara av olika typer.

    String connString = "mongodb://localhost:27017"; // MongoDB-anslutningssträngen som definierar var databasen är värd.
    String collectionName = "Customer"; // Namnet på den samling (dvs. tabell) som innehåller alla .
    String databaseName = "Users";

    private final MongoCollection<Document> customerCollection;
    private final MongoCollection<Document> employeeCollection;

    public MongoDBFasad(String connectionString, String databaseName) {
        this.connString = connString;
        this.collectionName = collectionName;
        this.databaseName = databaseName;
        customerCollection = db.getCollection("customers");
        employeeCollection = db.getCollection("employees");
    }

    // CRUD operations for Customer
    public void createCustomer(Customer customer) {
        Document document = new Document()
                .append("name", customer.getName())
                .append("age", customer.getAge())
                .append("address", customer.getAddress())
                .append("customerNumber", customer.getCustomerNumber());
        customerCollection.insertOne(document);
    }

    public Customer readCustomer(String customerNumber) {
        Document document = customerCollection.find(eq("customerNumber", customerNumber)).first();
        if (document != null) {
            String name = document.getString("name");
            int age = document.getInteger("age");
            String address = document.getString("address");
            Customer customer = new Customer(name, age, address, customerNumber);
            return customer;
        }
        return null;
    }

    public void updateCustomer(Customer customer) {
        customerCollection.updateOne(eq("customerNumber", customer.getCustomerNumber()),
                new Document("$set", new Document()
                        .append("name", customer.getName())
                        .append("age", customer.getAge())
                        .append("address", customer.getAddress())
                        .append("customerNumber", customer.getCustomerNumber())));
    }

    public void deleteCustomer(String customerNumber) {
        customerCollection.deleteOne(eq("customerNumber", customerNumber));
    }
    // CRUD operations for Employee
    public void createEmployee(Employee employee) {
        Document document = new Document()
                .append("name", employee.getName())
                .append("age", employee.getAge())
                .append("address", employee.getAddress())
                .append("employeeNumber", employee.getEmployeeNumber());
        employeeCollection.insertOne(document);
    }

    public Employee readEmployee(String employeeNumber) {
        Document document = employeeCollection.find(eq("employeeNumber", employeeNumber)).first();
        if (document != null) {
            String name = document.getString("name");
            int age = document.getInteger("age");
            String address = document.getString("address");
            Employee employee = new Employee(name, age, address, employeeNumber);
            return employee;
        }
        return null;
    }

    public void updateEmployee(Employee employee) {
        employeeCollection.updateOne(eq("employeeNumber", employee.getEmployeeNumber()),
                new Document("$set", new Document()
                        .append("name", employee.getName())
                        .append("age", employee.getAge())
                        .append("address", employee.getAddress())
                        .append("employeeNumber", employee.getEmployeeNumber())));
    }

    public void deleteEmployee(String employeeNumber) {
        employeeCollection.deleteOne(eq("employeeNumber", employeeNumber));
    }

    public static void main(String[] args) {
        // Test CRUD operations for Person, Customer and Employee
        MongoDBFasad fasad = new MongoDBFasad("mongodb://localhost:27017", "mydatabase");

        // Create and save new Person
        Person person = new Person("Alice", 25, "123 Main St");
        fasad.createCustomer((Customer) person);
        fasad.createEmployee((Employee) person);

        // Read and update Customer
        Customer customer = fasad.readCustomer("C001");
        customer.setName("Carol");
        fasad.updateCustomer(customer);

        // Read and update Employee
        Employee employee = fasad.readEmployee("E001");
        employee.setAddress("456 Elm St");
        fasad.updateEmployee(employee);

        // Delete Customer and Employee
        fasad.deleteCustomer("C001");
        fasad.deleteEmployee("E001");
    }
}


