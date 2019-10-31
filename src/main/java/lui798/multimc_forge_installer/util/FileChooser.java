package lui798.multimc_forge_installer.util;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public abstract class FileChooser extends JPanel implements ActionListener {
    private JFileChooser chooser;
    private String title;
    private JButton chooseButton;

    public FileChooser() {
        chooser = new JFileChooser();
        chooseButton = new JButton("Choose Instance");
        chooseButton.addActionListener(this);
        add(chooseButton);
    }

    public abstract void actionPerformed(ActionEvent e);

    public Dimension getPreferredSize(){
        return new Dimension(400, 300);
    }

    public JFileChooser getFileChooser() {
        return chooser;
    }
}
