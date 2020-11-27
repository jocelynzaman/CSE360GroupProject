import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Search {
	public static String search() {
		JButton button_OPEN = new JButton();
		JFileChooser file = new JFileChooser(){
			
			@Override
    		public void approveSelection() {
        		// test your condition here
				if (Files.exists(Paths.get(this.getSelectedFile().getAbsolutePath())))
					if (this.getSelectedFile().getAbsolutePath().substring(this.getSelectedFile().getAbsolutePath().length() - 4).equals(".csv")) {
						super.approveSelection();
					} else {
						JOptionPane.showMessageDialog(null, "Invalid File Extension. Check spelling.", "File Explorer", JOptionPane.WARNING_MESSAGE);
					}
            		
       			else
            		JOptionPane.showMessageDialog(null, "Could not find designated file, check spelling.", "File Explorer", JOptionPane.WARNING_MESSAGE);                
			}
		};
		file.setCurrentDirectory(new java.io.File("."));
		file.setDialogTitle("File Explorer");
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);

		file.addChoosableFileFilter(new filter());

		file.setAcceptAllFileFilterUsed(false);

		int returnval = file.showOpenDialog(button_OPEN);
		//boolean valid = Files.exists(Paths.get(file.getSelectedFile().getAbsolutePath()));

		if (returnval == JFileChooser.APPROVE_OPTION) {
			return file.getSelectedFile().getAbsolutePath();		
		} else {
			return "FILE_NOT_OPEN";
		}
	}
}
