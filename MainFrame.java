
import javax.swing.*;

public class MainFrame extends JFrame {

	 public MainFrame() {
	        setTitle("Inventory Management System");
	        setSize(1000, 700);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        
	        JTabbedPane tabbedPane = new JTabbedPane();
	        tabbedPane.addTab("Products", new ProductPanel());
	        tabbedPane.addTab("Suppliers", new SupplierPanel());
	        
	        add(tabbedPane);
	    }
	
}
