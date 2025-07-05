
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;


public class SupplierPanel extends JPanel {

	 private JTable supplierTable;
	    private DefaultTableModel tableModel;
	    private JTextField nameField, contactField, phoneField, emailField;
	    private JTextArea addressArea;
	    
	    public SupplierPanel() {
	        initializeUI();
	        loadInitialData();
	        setupEventListeners();
	    }
	    
	    private void initializeUI() {
	        setLayout(new BorderLayout());
	        
	        
	        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Contact", "Phone", "Email"}, 0);
	        supplierTable = new JTable(tableModel);
	        JScrollPane scrollPane = new JScrollPane(supplierTable);
	        
	      
	        nameField = new JTextField(20);
	        contactField = new JTextField(20);
	        phoneField = new JTextField(15);
	        emailField = new JTextField(20);
	        addressArea = new JTextArea(3, 20);
	        JScrollPane addressScroll = new JScrollPane(addressArea);
	        
	        
	        JButton addBtn = new JButton("Add");
	        JButton updateBtn = new JButton("Update");
	        JButton deleteBtn = new JButton("Delete");
	        JButton clearBtn = new JButton("Clear");
	        
	       
	        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
	        formPanel.add(new JLabel("Supplier Name:"));
	        formPanel.add(nameField);
	        formPanel.add(new JLabel("Contact Person:"));
	        formPanel.add(contactField);
	        formPanel.add(new JLabel("Phone:"));
	        formPanel.add(phoneField);
	        formPanel.add(new JLabel("Email:"));
	        formPanel.add(emailField);
	        formPanel.add(new JLabel("Address:"));
	        formPanel.add(addressScroll);
	        
	    
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.add(addBtn);
	        buttonPanel.add(updateBtn);
	        buttonPanel.add(deleteBtn);
	        buttonPanel.add(clearBtn);
	        
	    
	        add(scrollPane, BorderLayout.CENTER);
	        
	        JPanel southPanel = new JPanel(new BorderLayout());
	        southPanel.add(formPanel, BorderLayout.NORTH);
	        southPanel.add(buttonPanel, BorderLayout.SOUTH);
	        add(southPanel, BorderLayout.SOUTH);
	    }
	    
	    private void loadInitialData() {
	        loadSuppliers();
	    }
	    
	    private void setupEventListeners() {
	        
	    }
	    
	   
	    private void loadSuppliers() {
	        try (Connection conn = DBConnection.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery("SELECT * FROM Suppliers")) {
	            
	            tableModel.setRowCount(0);
	            while (rs.next()) {
	                tableModel.addRow(new Object[]{
	                    rs.getInt("SupplierID"),
	                    rs.getString("SupplierName"),
	                    rs.getString("ContactName"),
	                    rs.getString("Phone"),
	                    rs.getString("Email")
	                });
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(this, "Error loading suppliers: " + e.getMessage());
	        }
	    }
	 }
