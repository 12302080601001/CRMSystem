package in.ac.adit.pwj.miniproject.crm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class CRMApp extends JFrame {
    private CRMManager crmManager;

    public CRMApp() {
        crmManager = new CRMManager();
        try {
            crmManager.loadData();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }

        // Set title and window properties
        setTitle("Aarish CRM System");
        setSize(500, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Color Scheme
        Color backgroundColor = new Color(44, 62, 80);  // Navy Blue
        Color buttonColor = new Color(52, 152, 219);    // Light Blue
        Color textColor = Color.WHITE;

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(backgroundColor);
        JLabel titleLabel = new JLabel("Aarish CRM System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(textColor);
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 10, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        JButton addIndividualButton = createStyledButton("Add Individual Customer", buttonColor);
        JButton addCorporateButton = createStyledButton("Add Corporate Customer", buttonColor);
        JButton recordSaleButton = createStyledButton("Record Sale", buttonColor);
        JButton generateReportButton = createStyledButton("Generate Report", buttonColor);
        JButton saveButton = createStyledButton("Save Data", buttonColor);
        JButton exitButton = createStyledButton("Exit", Color.RED);

        buttonPanel.add(addIndividualButton);
        buttonPanel.add(addCorporateButton);
        buttonPanel.add(recordSaleButton);
        buttonPanel.add(generateReportButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Footer Panel (Copyright)
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(backgroundColor);
        JLabel copyrightLabel = new JLabel("© AB", SwingConstants.CENTER);
        copyrightLabel.setForeground(textColor);
        footerPanel.add(copyrightLabel);
        add(footerPanel, BorderLayout.SOUTH);

        // Button Actions
        addIndividualButton.addActionListener(e -> addCustomer("Individual"));
        addCorporateButton.addActionListener(e -> addCustomer("Corporate"));
        recordSaleButton.addActionListener(e -> recordSale());
        generateReportButton.addActionListener(e -> JOptionPane.showMessageDialog(this, crmManager.generateReport()));
        saveButton.addActionListener(e -> saveData());
        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    private void addCustomer(String type) {
        String id = JOptionPane.showInputDialog("Enter Customer ID:");
        String name = JOptionPane.showInputDialog("Enter Name:");
        String email = JOptionPane.showInputDialog("Enter Email:");
        String contact = JOptionPane.showInputDialog("Enter Contact:");

        try {
            if ("Individual".equals(type)) {
                crmManager.addCustomer(new Individual(id, name, email, contact));
            } else {
                crmManager.addCustomer(new Corporate(id, name, email, contact));
            }
            JOptionPane.showMessageDialog(this, type + " Customer Added Successfully!");
        } catch (CRMException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void recordSale() {
        String customerId = JOptionPane.showInputDialog("Enter Customer ID:");
        String amountStr = JOptionPane.showInputDialog("Enter Sale Amount:");

        try {
            double amount = Double.parseDouble(amountStr);
            crmManager.recordSale(customerId, amount);
            JOptionPane.showMessageDialog(this, "Sale Recorded Successfully!");
        } catch (CRMException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Amount!");
        }
    }

    private void saveData() {
        try {
            crmManager.saveData();
            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CRMApp::new);
    }
}