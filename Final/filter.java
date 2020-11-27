import java.io.File;
import javax.swing.filechooser.FileFilter;

public class filter extends FileFilter{

	@Override
	public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
    
        String extension = file.getName().substring(file.getName().length() - 4);
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
	public String getDescription() {
		return ".csv";
	}
    
}
