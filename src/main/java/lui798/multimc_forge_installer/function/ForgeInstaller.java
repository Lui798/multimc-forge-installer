package lui798.multimc_forge_installer.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.*;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.List;

public class ForgeInstaller extends Function {
    private final Logger LOG = LoggerFactory.getLogger(ForgeInstaller.class);

    @Override
    public String runFunction(String instanceDir, JFrame frame) {
        String[] keywords = {"forge", "installer", ".jar"};
        String[] no = {".log"};
        List<File> files = Arrays.asList(FileSystems.getDefault().getPath(".").toFile().listFiles());

        File forgeInstaller = null;

        for (File f : files) {
            if (!containsKeyword(f.getName(), no)) {
                if (containsKeyword(f.getName(), keywords)) {
                    forgeInstaller = f;
                }
            }
        }

        if (forgeInstaller == null) {
            int choice = JOptionPane.showOptionDialog(frame, "Could not find a forge installer in the current directory. " +
                            "Please provide one \nin the same directory as the installer jar, and keep its original file name.", "Error",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"OK"}, "OK");
            if (choice == 0)
                System.exit(0);
        }
        else {
            try {
                JOptionPane.showMessageDialog(frame, "The forge installer will be run now. Select the .minecraft folder" +
                        "\nof your MultiMC instance and install forge as you normally would.", "Forge Installer", JOptionPane.INFORMATION_MESSAGE);

                ProcessBuilder builder = new ProcessBuilder("java", "-jar", forgeInstaller.getAbsolutePath());
                Process proc = builder.start();
                StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERROR");
                StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "OUTPUT");
                errorGobbler.start();
                outputGobbler.start();
                proc.waitFor();
            }
            catch (IOException | InterruptedException ex) {
                LOG.error(ex.toString());
            }
        }

        return instanceDir;
    }

    public boolean containsKeyword(String input, String[] items) {
        boolean result = false;
        for (String item : items) {
            if (input.toLowerCase().contains(item)) {
                result = true;
            }
            else {
                return false;
            }
        }
        return result;
    }
}

class StreamGobbler extends Thread {
    InputStream is;
    String type;

    StreamGobbler(InputStream is, String type) {
        this.is = is;
        this.type = type;
    }

    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
