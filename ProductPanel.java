
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;


public class ProductPanel extends JPanel{

	 private JTable productTable;
	    private DefaultTableModel tableModel;
	    private JTextField nameField, priceField, stockField;
	    private JComboBox<String> categoryCombo, supplierCombo;
	    
	    public ProductPanel() {
	        initializeUI();
	        loadInitialData();
	        setupEventListeners();
	    }
	    
	    private void initializeUI() {
	        setLayout(new BorderLayout());
	        
	        
	        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Category", "Supplier", "Price", "Stock"}, 0);
	        productTable = new JTable(tableModel);
	        JScrollPane scrollPane = new JScrollPane(productTable);
	        
	      
	        nameField = new JTextField(20);
	        priceField = new JTextField(10);
	        stockField = new JTextField(10);
	        categoryCombo = new JComboBox<>();
	        supplierCombo = new JComboBox<>();
	        
	        
	        JButton addBtn = new JButton("Add");
	        JButton updateBtn = new JButton("Update");
	        JButton deleteBtn = new JButton("Delete");
	        JButton clearBtn = new JButton("Clear");
	        
	       
	        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
	        formPanel.add(new JLabel("Name:"));
	        formPanel.add(nameField);
	        formPanel.add(new JLabel("Category:"));
	        formPanel.add(categoryCombo);
	        formPanel.add(new JLabel("Supplier:"));
	        formPanel.add(supplierCombo);
	        formPanel.add(new JLabel("Price:"));
	        formPanel.add(priceField);
	        formPanel.add(new JLabel("Stock:"));
	        formPanel.add(stockField);
	        
	      
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
	        loadProducts();
	    }
	    
	    private void setupEventListeners() {
	    } 
	    
	 
	    private void loadProducts() {
	        try (Connection conn = DBConnection.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(
	                 "SELECT p.ProductID, p.ProductName, c.CategoryName, s.SupplierName, p.UnitPrice, p.QuantityInStock " +
	                 "FROM Products p " +
	                 "LEFT JOIN Categories c ON p.CategoryID = c.CategoryID " +
	                 "LEFT JOIN Suppliers s ON p.SupplierID = s.SupplierID")) {
	            
	            tableModel.setRowCount(0);
	            while (rs.next()) {
	                tableModel.addRow(new Object[]{
	                    rs.getInt("ProductID"),
	                    rs.getString("ProductName"),
	                    rs.getString("CategoryName"),
	                    rs.getString("SupplierName"),
	                    rs.getDouble("UnitPrice"),
	                    rs.getInt("QuantityInStock")
	                });
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage());
	        }
	    }
	    
	  }
