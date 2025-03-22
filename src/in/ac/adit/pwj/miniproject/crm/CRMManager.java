package in.ac.adit.pwj.miniproject.crm;

import java.io.*;
import java.util.*;

public class CRMManager {
    private Map<String, Customer> customers = new HashMap<>();
    private List<String> sales = new ArrayList<>();
    private final Object lock = new Object();  // For Thread Safety

    public void addCustomer(Customer customer) throws CRMException {
        synchronized (lock) {
            if (customers.containsKey(customer.getId())) {
                throw new CRMException("Customer with ID " + customer.getId() + " already exists!");
            }
            customers.put(customer.getId(), customer);
            System.out.println("Customer added successfully.");
        }
    }

    public void recordSale(String customerId, double amount) throws CRMException {
        synchronized (lock) {
            if (!customers.containsKey(customerId)) {
                throw new CRMException("Customer ID not found!");
            }
            sales.add("Customer ID: " + customerId + " - Sale Amount: $" + amount);
            System.out.println("Sale recorded successfully.");
        }
    }

    public void generateReport() {
        synchronized (lock) {
            System.out.println("\n--- Customer Report ---");
            customers.values().forEach(c -> System.out.println(c.getDetails()));

            System.out.println("\n--- Sales Report ---");
            sales.forEach(System.out::println);
        }
    }

    public void saveDataToFile() throws IOException {
        synchronized (lock) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("crm_data.txt"))) {
                writer.write("Customers:\n");
                for (Customer c : customers.values()) {
                    writer.write(c.getDetails());
                    writer.newLine();
                }
                writer.write("\nSales:\n");
                for (String sale : sales) {
                    writer.write(sale);
                    writer.newLine();
                }
            }
            System.out.println("Data saved to crm_data.txt.");
        }
    }
}