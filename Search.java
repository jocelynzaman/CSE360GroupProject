import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Search{
	public static boolean search() {
		JButton button_OPEN = new JButton();
		JFileChooser file = new JFileChooser();
		file.setCurrentDirectory(new java.io.File("."));
		file.setDialogTitle("File Explorer");
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (file.showOpenDialog(button_OPEN) == JFileChooser.APPROVE_OPTION) {
			//include code here;
			return true;
		} else {
			//include code here;
			return false;
		}
		
	}
}
