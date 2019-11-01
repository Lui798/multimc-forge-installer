package lui798.multimc_forge_installer.function;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class CopyLibraries extends Function {
    private String librariesDir;

    private final Logger LOG = LoggerFactory.getLogger(CopyLibraries.class);

    @Override
    public String runFunction(String instanceDir, JFrame frame) {
        LibrariesFolderChooser fc = new LibrariesFolderChooser(this);

        JOptionPane.showMessageDialog(frame,"Find your MultiMC install and select the \"libraries\"" +
                "\nfolder in the root directory.", "Select MultiMC libraries folder", JOptionPane.INFORMATION_MESSAGE);
        fc.openChooser();
        File dir = new File(librariesDir);
        while (!dir.exists()) {
            JOptionPane.showMessageDialog(frame,"Could not find the libraries folder, please select" +
                    "\nthe libraries folder in your MultiMC root directory.", "Folder Error", JOptionPane.ERROR_MESSAGE);
            fc.openChooser();
            dir = new File(librariesDir);
        }

        try {
            File srcDir = new File(instanceDir + File.separator + ".minecraft" + File.separator + "libraries");
            File destDir = new File(dir.getAbsolutePath());
            FileUtils.copyDirectory(srcDir, destDir);
        }
        catch (IOException ex) {
            LOG.error(ExceptionUtils.getStackTrace(ex));
        }

        return instanceDir;
    }

    public void setLibrariesDir(String dir) {
        this.librariesDir = dir;
    }
}

class LibrariesFolderChooser extends JPanel {
    private CopyLibraries cp;
    private JFileChooser chooser;

    private Logger LOG = LoggerFactory.getLogger(MCFolderChooser.class);

    public LibrariesFolderChooser(CopyLibraries cp) {
        this.cp = cp;
        initFrame();
    }

    private void initFrame() {
        chooser = new JFileChooser();

        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose libraries folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
    }

    public void openChooser() {
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            cp.setLibrariesDir(chooser.getSelectedFile().getAbsolutePath());
        }
        else {
            System.exit(0);
        }
    }
}
