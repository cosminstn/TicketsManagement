package com.stn.tickets.forms;

import javax.swing.*;
import java.awt.*;

public class UserTypeSelectorForm extends JFrame {
    private JButton consumerBtn;
    private JButton adminBtn;
    private JLabel optionLbl;
    private JPanel rootPanel;

    public UserTypeSelectorForm() {
        add(rootPanel);

        setTitle("Tickets Management Platform");
        setSize(300, 200);
        setResizable(false);
        // Start the form center screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setFont(new Font("Segoe UI", Font.BOLD, 16));
        consumerBtn.addActionListener(e -> {
            setVisible(false);

            ConsumerForm consForm = new ConsumerForm();
            consForm.setVisible(true);
        });

        adminBtn.addActionListener(e -> {
            setVisible(false);

            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        });
    }

}
