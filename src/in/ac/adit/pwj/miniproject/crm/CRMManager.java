package in.ac.adit.pwj.miniproject.crm;

import java.io.*;
import java.util.*;

public class CRMManager {
    private Map<String, Customer> customers = new HashMap<>();
    private static final String FILE_PATH = "in/ac/adit/pwj/miniproject/crm/crm_data.txt";

    public synchronized void addCustomer(Customer customer) throws CRMException {
        if (customers.containsKey(customer.getId())) {
            throw new CRMException("Customer ID already exists!");
        }
        customers.put(customer.getId(), customer);
    }

    public synchronized void recordSale(String customerId, double amount) throws CRMException {
        if (!customers.containsKey(customerId)) {
            throw new CRMException("Customer not found!");
        }
        System.out.println("Sale recorded for ID: " + customerId + " Amount: " + amount);
    }

    public synchronized String generateReport() {
        StringBuilder report = new StringBuilder("Customer Report:\n");
        for (Customer c : customers.values()) {
            report.append(c).append("\n");
        }
        return report.toString();
    }

    public synchronized void saveData() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Customer c : customers.values()) {
                writer.write(c.toString());
                writer.newLine();
            }
        }
    }

    public synchronized void loadData() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) continue;

                String type = parts[0];
                String id = parts[1];
                String name = parts[2];
                String email = parts[3];
                String contact = parts[4];

                if (type.equals("I")) {
                    customers.put(id, new Individual(id, name, email, contact));
                } else if (type.equals("C")) {
                    if (parts.length < 6) continue;
                    String companyName = parts[5];
                    customers.put(id, new Corporate(id, name, email, contact, companyName));
                }
            }
        }
    }
}
