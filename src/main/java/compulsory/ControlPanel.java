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

    JTextArea errorText;

    JButton newComponent;

    DesignPanel designPanel;

    JPanel rootPanel;
    JPanel details;
    JPanel errorLog;

    public ControlPanel(DesignPanel designPanel) {
        this.designPanel = designPanel;

        newComponent = new JButton("Get Component!");

        textComponentName = new JTextField(10);
        textWidth = new JTextField(10);
        textHeight = new JTextField(10);
        textText = new JTextField(15);

        errorText = new JTextArea("Error Log:");

        details = new JPanel();
        rootPanel = new JPanel();
        errorLog = new JPanel();

        rootPanel.setVisible(true);
        details.setVisible(true);
        errorLog.setVisible(true);

        this.setVisible(true);

        details.setLayout(new FlowLayout());
        details.add(new JLabel("WIDTH"));
        details.add(textWidth);
        details.add(new JLabel("HEIGHT"));
        details.add(textHeight);
        details.add(new JLabel("TEXT"));
        details.add(textText);
        rootPanel.setLayout(new FlowLayout());

        newComponent.addActionListener(this::actionPerformed);

        setLayout(new BorderLayout());

        textHeight.setSize(100, 30);
        textWidth.setSize(100, 30);

        rootPanel.add(details, BorderLayout.NORTH);
        rootPanel.add(new JLabel("CLASS NAME"));
        rootPanel.add(textComponentName);

        rootPanel.add(newComponent, BorderLayout.CENTER);

        errorLog.add(errorText);

        add(rootPanel, BorderLayout.NORTH);
        add(errorLog, BorderLayout.SOUTH);

    }

    public void actionPerformed(ActionEvent actionEvent) {
        String text = textComponentName.getText();
        System.out.println(text);
        try {
            Class clazz = Class.forName("javax.swing." + text);
            Component newComponent;
            newComponent = (Component) clazz.getConstructor().newInstance();
            System.out.println("Loaded component class: " + newComponent.getClass());
            int width;
            int height;
            width = Integer.max(Integer.max(Integer.parseInt(textWidth.getText()), 30),
                    textText.getText().length() * 7);
            height = Integer.max(Integer.parseInt(textHeight.getText()), 30);
            try {
                Method method = clazz.getMethod("setText", String.class);
                System.out.println("Loaded method: " + method.getName());
                method.invoke(newComponent, textText.getText());
            } catch (NoSuchMethodException | NumberFormatException exception) {
                System.out.println(exception);
                errorText.setText("Error Log: " + exception);
            }


            newComponent.setSize(width, height);

            designPanel.setComponent(newComponent);

            errorText.setText("Error Log: All components loaded successfully!");
            textText.setText("");
            textWidth.setText("");
            textHeight.setText("");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                NoSuchMethodException | InvocationTargetException | NumberFormatException reflectiveOperationException) {
            System.out.println(reflectiveOperationException);
            errorText.setText("Error Log: " + reflectiveOperationException);
        }
    }
}