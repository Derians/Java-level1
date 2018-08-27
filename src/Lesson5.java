/**
 * Java level 1, lesson 5
 *
 * 1. Created class "Employee" with fields: (Full name, position,
 * email, phone, salary, age).
 * The class constructor fill these fields when creating the object.
 * Inside the class "Employee" wrote a method that displays information
 * about the object in the console.
 * Created an array of 5 employees and using the cycle to output information
 * only about employees older than 40 years.
 *
 * 2. Inside the class "Employee" wrote a method that override toString method
 *
 * @author Chaykin Ivan
 * @version 26.08.2018
 */

class Lesson5 {

    public static void main(String[] args) {
//      Create and fill array of objects class Employee.
        Employee[] employeeArray = new Employee[5];
        employeeArray[0] = new Employee("Ivanov Ivan", "Head of department",
                "ivivan@mailbox.com", "892312312", 80000, 47);
        employeeArray[1] = new Employee("Petrov Petr", "Engineer",
                "pepetr@mailbox.com", "892312312", 60000, 30);
        employeeArray[2] = new Employee("Sergeev Sergey", "Junior Engineer",
                "sesergey@mailbox.com", "892312312", 30000, 25);
        employeeArray[3] = new Employee("Andreev Andrey", "Deriver",
                "anandrey@mailbox.com", "892312312", 40000, 32);
        employeeArray[4] = new Employee("Viktorov Viktor", "Engineer",
                "viviktor@mailbox.com", "892312312", 60000, 42);

//      Method call that print in console information of object.
        employeeArray[2].print();

/*
 * Cycle that print in console objects where field age more then 40,
 * printing with override toString method.
 */
        for (Employee employee : employeeArray) {
            if (employee.age >= 40)
            System.out.println(employee.toString());
        }
    }
}

// Creating class Employee.
class Employee {

    String fullName;
    String position;
    String email;
    String phone;
    int salary;
    int age;

//  Creating class constructor with fill all fields.
    Employee(String fullName, String position, String email,
                    String phone, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }
//  Method that displays information about the object in the console.
    void print() {
        System.out.println("Full Name: " + fullName +
                ", Position: " + position + ", Email: " + email +
                ", Phone: " + phone + ", Salary: " + salary + ", Age: " + age);
    }
//  Method that override toString method.
    @Override
    public String toString() {
        return "Employee{ \n" +
                "Full Name = " + fullName + "\n" +
                "Position = " + position + "\n" +
                "Email = " + email + "\n" +
                "Phone = " + phone + "\n" +
                "Salary = " + salary + "\n" +
                "Age = " + age + "\n" +
                '}';
    }
}