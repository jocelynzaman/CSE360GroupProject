import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


//Opens a FileChooser with a custom filter to only allow for .csv and directories.
//Users can enter file paths, select files, or change directories
//Canceling or hitting X returns "FILE_NOT_OPEN"
//Confirming with OPEN returns the file path as a string unless the path is invalid (wrong type, doesn't exist)
public class Search { 
	public static String search() {
		JButton button_OPEN = new JButton();
		JFileChooser file = new JFileChooser(){
			
			@Override
			//Overridden approveSelection method
    		public void approveSelection() {
        		// test your condition here
				if (Files.exists(Paths.get(this.getSelectedFile().getAbsolutePath())))
					if (this.getSelectedFile().getAbsolutePath().substring(this.getSelectedFile().getAbsolutePath().length() - 4).equals(".csv")) { //checks that the file extension is .csv by taking a substring
						super.approveSelection();
					} else {
						JOptionPane.showMessageDialog(null, "Invalid File Extension. Check spelling.", "File Explorer", JOptionPane.WARNING_MESSAGE);
					}
            		
       			else
            		JOptionPane.showMessageDialog(null, "Could not find designated file, check spelling.", "File Explorer", JOptionPane.WARNING_MESSAGE);                
			}
		};

		//Settings for the File Explorer
		file.setCurrentDirectory(new java.io.File("."));
		file.setDialogTitle("File Explorer");
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);

		file.addChoosableFileFilter(new filter()); //Adds an instance of filter() to file filter which designates the files which the user can see

		file.setAcceptAllFileFilterUsed(false);

		int returnval = file.showOpenDialog(button_OPEN);
		//boolean valid = Files.exists(Paths.get(file.getSelectedFile().getAbsolutePath()));

		if (returnval == JFileChooser.APPROVE_OPTION) { //If a valid file path is provided and the user hits Open
			return file.getSelectedFile().getAbsolutePath();		
		} else {
			return "FILE_NOT_OPEN";
		}
	}
}
