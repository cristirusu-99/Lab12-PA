package compulsory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

public class DesignPanel extends JPanel {
    ControlPanel controlPanel;
    Insets insets;
    List<Component> components;

    public DesignPanel() {
        components = new ArrayList<Component>();
        insets = this.getInsets();

        setLayout(null);
        setSize(300, 300);
        setBackground(Color.WHITE);
        setVisible(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    try {
                        Component component = components.get(components.size() - 1);
                        System.out.println(components.size());
                        component.setBounds(e.getX() + insets.left, e.getY() + insets.top, component.getWidth(), component.getHeight());
                        add(component);
                        revalidate();
                        repaint();
                    } catch (Exception ignored) {
                    }
                    ;
                }
            }


        });
    }

    public void setComponent(Component component) {
        components.add(component);
    }

}
