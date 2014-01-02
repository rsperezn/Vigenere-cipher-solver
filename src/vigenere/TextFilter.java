package vigenere;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

public class TextFilter extends FileFilter {

	@Override
	public boolean accept(File arg0) {
		if (arg0.isDirectory()) {
            return true;
        }
 
        String extension = Utils.getExtension(arg0);
        if (extension != null) {
            if (extension.equals(Utils.txt)){
                    return true;
            } else {
                return false;
            }
        }
        return false;
	}

	@Override
	public String getDescription() {
		return "Just Text Files (.txt)";	
	}
}