
import javax.swing.*;


public class InventoryApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		        DBConnection.testConnection();
		        
		        SwingUtilities.invokeLater(() -> {
		            try {
		                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		                MainFrame frame = new MainFrame();
		                frame.setVisible(true);
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        });
		    }

	

}
