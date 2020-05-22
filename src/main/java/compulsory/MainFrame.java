package compulsory;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JPanel controlPanel;
    JPanel designPanel;

    private MainFrame() {
        designPanel = new DesignPanel();
        controlPanel = new ControlPanel((DesignPanel) designPanel);

        this.setSize(new Dimension(1280, 720));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.add(controlPanel, BorderLayout.NORTH);
        this.add(designPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }
}
