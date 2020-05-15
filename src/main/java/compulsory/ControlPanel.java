package compulsory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ControlPanel extends JPanel {

    JTextField textComponentName;
    JTextField textWidth;
    JTextField textHeight;
    JTextField textText;

    JButton newComponent;

    DesignPanel designPanel;

    JPanel rootPanel;
    JPanel details;


    public ControlPanel(DesignPanel designPanel) {
        this.designPanel = designPanel;

        newComponent = new JButton("New Shape");

        textComponentName = new JTextField(10);
        textWidth = new JTextField(10);
        textHeight = new JTextField(10);
        textText = new JTextField(15);

        details = new JPanel();
        rootPanel = new JPanel();

        rootPanel.setVisible(true);
        details.setVisible(true);

        this.setVisible(true);


        details.setLayout(new FlowLayout());
        details.add(new JLabel("WIDTH"));
        details.add(textWidth);
        details.add(new JLabel("HEIGHT"));
        details.add(textHeight);
        details.add(new JLabel("Text"));
        details.add(textText);
        rootPanel.setLayout(new FlowLayout());


        textComponentName.addActionListener(this::actionPerformed);
        newComponent.addActionListener(this::actionPerformed);


        setLayout(new FlowLayout());

        textHeight.setSize(100, 30);
        textWidth.setSize(100, 30);

        rootPanel.add(details, BorderLayout.NORTH);
        rootPanel.add(new JLabel("CLASS NAME"));
        rootPanel.add(textComponentName);

        rootPanel.add(newComponent, BorderLayout.CENTER);
        add(rootPanel);

    }

    public void actionPerformed(ActionEvent actionEvent) {
        String text = textComponentName.getText();
        System.out.println(text);
        try {
            Class clazz = Class.forName("javax.swing." + text);
            Component newComponent;
            newComponent = (Component) clazz.getConstructor().newInstance();
            System.out.println("Loaded component class: " + newComponent.getClass());
            int x = Integer.parseInt(textWidth.getText());
            int y = Integer.parseInt(textHeight.getText());
            x = Integer.max(x, 100);
            y = Integer.max(y, 30);
            try {
                Method method = clazz.getMethod("setText", String.class);
                System.out.println("Loaded method: " + method.getName());
                method.invoke(newComponent,textText.getText());
            } catch (Exception ignored) {
                System.out.println(ignored);
            }


            newComponent.setSize(x, y);

            designPanel.setComponent(newComponent);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ignored) {

        }
    }
}