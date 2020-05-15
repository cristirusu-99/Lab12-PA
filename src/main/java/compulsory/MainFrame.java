package compulsory;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JPanel controlPanel;
    JPanel designPanel;

    private MainFrame() {
        designPanel = new DesignPanel();
        controlPanel = new ControlPanel((DesignPanel) designPanel);

        setLayout(new BorderLayout());

        add(controlPanel, BorderLayout.NORTH);
        add(designPanel, BorderLayout.SOUTH);

        this.setSize(1280, 720);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }
}
