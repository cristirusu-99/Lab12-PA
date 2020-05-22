package compulsory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

public class DesignPanel extends JPanel {
    Insets insets;
    List<Component> components;

    public DesignPanel() {
        System.out.println("Created!");
        components = new ArrayList<>();
        insets = this.getInsets();

        setLayout(null);
        setPreferredSize(new Dimension(1280, 600)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder());
        setBackground(Color.WHITE);
        setVisible(true);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Checked! 1");
                if (e.getButton() == MouseEvent.BUTTON1) {
                    try {
                        Component component = components.get(components.size() - 1);
                        component.setBounds(e.getX() + insets.left, e.getY() + insets.top, component.getWidth(), component.getHeight());
                        add(component);
                        revalidate();
                        repaint();
                    } catch (Exception ignored) {
                    }
                }
            }
        });
    }

    public void setComponent(Component component) {
        components.add(component);
    }

}
