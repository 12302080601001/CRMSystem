package in.ac.adit.pwj.miniproject.crm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class CRMAppUI extends JFrame {
    private JButton addIndividualButton, addCorporateButton, recordSaleButton, generateReportButton, saveButton, exitButton;
    private CRMManager crmManager;

    public CRMAppUI() throws IOException, CRMException {
        // Initialize CRM Manager
        crmManager = new CRMManager();
        
        // Set frame properties
        setTitle("CRM System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 10, 10));
        
        // Initialize buttons
        addIndividualButton = new JButton("Add Individual Customer");
        addCorporateButton = new JButton("Add Corporate Customer");
        recordSaleButton = new JButton("Record Sale");
        generateReportButton = new JButton("Generate Report");
        saveButton = new JButton("Save Data");
        exitButton = new JButton("Exit");
        
        // Add buttons to the frame
        add(addIndividualButton);
        add(addCorporateButton);
        add(recordSaleButton);
        add(generateReportButton);
        add(saveButton);
        add(exitButton);
        
        // Button actions
        addIndividualButton.addActionListener(e -> addCustomer("Individual"));
        addCorporateButton.addActionListener(e -> addCustomer("Corporate"));
        recordSaleButton.addActionListener(e -> recordSale());
        generateReportButton.addActionListener(e -> generateReport());
        saveButton.addActionListener(e -> saveData());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void addCustomer(String type) {
        String id = JOptionPane.showInputDialog(this, "Enter Customer ID:");
        String name = JOptionPane.showInputDialog(this, "Enter Customer Name:");
        
        if (id != null && name != null) {
            if (type.equals("Individual")) {
                crmManager.addCustomer(new IndividualCustomer(id, name));
            } else {
                crmManager.addCustomer(new CorporateCustomer(id, name));
            }
            JOptionPane.showMessageDialog(this, type + " Customer Added Successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    private void recordSale() {
        String customerId = JOptionPane.showInputDialog(this, "Enter Customer ID for Sale:");
        if (customerId != null) {
            crmManager.recordSale(customerId);
            JOptionPane.showMessageDialog(this, "Sale Recorded Successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    private void generateReport() {
        String report = crmManager.generateReport();
        JTextArea textArea = new JTextArea(report);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        JOptionPane.showMessageDialog(this, scrollPane, "Report", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveData() {
        crmManager.saveData();
        JOptionPane.showMessageDialog(this, "Data Saved Successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                CRMAppUI app = new CRMAppUI();
                app.setVisible(true);
            } catch (IOException | CRMException e) {
                e.printStackTrace();
            }
        });
    }
}