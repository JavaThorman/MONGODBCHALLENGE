public class Main {

            public static void main(String[] args) {

                // Skapa ett antal Person-objekt
                Person person1 = new Person("Alice", 25, "123 Main St");
                Person person2 = new Person("Bob", 35, "456 Elm St");
                Person person3 = new Person("Charlie", 45, "789 Oak St");

                // Skapa ett antal Kund-objekt
                Customer customer1 = new Customer("Dave", 30, "234 Pine St", "234 Pine St");
                Customer customer2 = new Customer("Eve", 40, "567 Walnut St", "C002");

                // Skapa ett antal Anställd-objekt
                   Employee employee1 = new Employee("Frank", 50, "678 Maple St", "E001");
                     Employee employee2 = new Employee("Grace", 60, "901 Cedar St", "E002");

                // Skapa en instans av MongoDBFasad
                MongoDBFasad fasad = new MongoDBFasad("mongodb://localhost:27017", "Users");




                // Spara Kund-objekten i databasen
                fasad.createCustomer(customer1);
                fasad.createCustomer(customer2);

                // Spara Anställd-objekten i databasen
                fasad.createEmployee(employee1);
                fasad.createEmployee(employee2);



            }
}