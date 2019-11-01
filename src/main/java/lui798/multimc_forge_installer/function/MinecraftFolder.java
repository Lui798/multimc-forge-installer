package lui798.multimc_forge_installer.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.File;

public class MinecraftFolder extends Function {

    private String instanceDir;

    @Override
    public String runFunction(String input, JFrame frame) {
        MCFolderChooser fc = new MCFolderChooser(this);

        JOptionPane.showMessageDialog(frame,"Find your MultiMC install and select the folder" +
                "\nof the instance of which want to install forge.", "Select MultiMC instance", JOptionPane.INFORMATION_MESSAGE);
        fc.openChooser();
        File dir = new File(instanceDir + File.separator + ".minecraft");
        if (!dir.exists())
            dir.mkdir();

        return instanceDir;
    }

    public void setInstanceDir(String dir) {
        this.instanceDir = dir;
    }
}

class MCFolderChooser extends JPanel {
    private MinecraftFolder mf;
    private JFileChooser chooser;

    private Logger LOG = LoggerFactory.getLogger(MCFolderChooser.class);

    public MCFolderChooser(MinecraftFolder mf) {
        this.mf = mf;
        initFrame();
    }

    private void initFrame() {
        chooser = new JFileChooser();

        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose instance folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
    }

    public void openChooser() {
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            mf.setInstanceDir(chooser.getSelectedFile().getAbsolutePath());
        }
        else {
            System.exit(0);
        }
    }
}
