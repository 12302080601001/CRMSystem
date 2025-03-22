package in.ac.adit.pwj.miniproject.crm;

import java.io.IOException;
import java.util.Scanner;

public class CRMApp {
    public static void main(String[] args) {
        CRMManager crmManager = new CRMManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nCRM System Menu:");
            System.out.println("1. Add Individual Customer");
            System.out.println("2. Add Corporate Customer");
            System.out.println("3. Record Sale");
            System.out.println("4. Generate Report");
            System.out.println("5. Save Data");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        String id = sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();
                        System.out.print("Enter Personal Phone: ");
                        String phone = sc.nextLine();
                        crmManager.addCustomer(new Individual(id, name, email, phone));
                        break;

                    case 2:
                        System.out.print("Enter ID: ");
                        id = sc.nextLine();
                        System.out.print("Enter Name: ");
                        name = sc.nextLine();
                        System.out.print("Enter Email: ");
                        email = sc.nextLine();
                        System.out.print("Enter Company Name: ");
                        String company = sc.nextLine();
                        crmManager.addCustomer(new Corporate(id, name, email, company));
                        break;

                    case 3:
                        System.out.print("Enter Customer ID: ");
                        id = sc.nextLine();
                        System.out.print("Enter Sale Amount: ");
                        double amount = sc.nextDouble();
                        crmManager.recordSale(id, amount);
                        break;

                    case 4:
                        crmManager.generateReport();
                        break;

                    case 5:
                        crmManager.saveDataToFile();
                        break;

                    case 6:
                        System.out.println("Exiting CRM System. Goodbye!");
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (CRMException | IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}