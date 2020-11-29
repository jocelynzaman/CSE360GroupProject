import java.io.File;
import javax.swing.filechooser.FileFilter;


//The filter class overrides the fileChooser.FileFilter methods filtering for only directories and .csv file extensions
public class filter extends FileFilter{

	@Override
	public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
    
        String extension = file.getName().substring(file.getName().length() - 4); //checking last 4 bytes of the file path for the extension
        if (extension != null && file.exists()) {
            if (extension.equals(".csv")) {
                    return true;
            } else {
                return false;
            }
        }
    
        return false;
	}

	@Override
	public String getDescription() { //displays to the user acceptable file extensions
		return ".csv";
	}
    
}
