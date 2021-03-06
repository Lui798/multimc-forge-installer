package lui798.multimc_forge_installer;

import lui798.multimc_forge_installer.function.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Installer {
    private static JFrame frame = new JFrame("MultiMC Forge Installer for 1.13+");
    private static final List<Function> functions = new ArrayList<>();

    public static void main(String s[]) {
        initFrame();
        initFunctions();
        runFunctions();

        int choice = JOptionPane.showOptionDialog(frame,"Done!" + "\nRestart your MultiMC and launch your instance, Forge should"
                        + "\nbe installed. You can use this installer multiple times.", "Installation Finished",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"  OK  "}, "  OK  ");
        if (choice == 0)
            System.exit(0);
    }

    private static void initFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) { }

        frame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
        frame.setSize(new Dimension(400, 300));
        frame.setVisible(true);
    }

    private static void initFunctions() {
        functions.add(new MinecraftFolder());
        functions.add(new LauncherProfileJson());
        functions.add(new ForgeInstaller());
        functions.add(new ForgePatch());
        functions.add(new CopyLibraries());
        functions.add(new CleanupInstance());
    }

    private static void runFunctions() {
        String buffer = "";
        for (Function func : functions) {
            buffer = func.runFunction(buffer, frame);
            System.out.println(buffer);
        }
    }
}
