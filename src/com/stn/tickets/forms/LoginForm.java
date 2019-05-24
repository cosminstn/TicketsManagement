package com.stn.tickets.forms;

import com.stn.tickets.db.dao.services.UserDAO;
import com.stn.tickets.enums.FormOperatingModes;
import com.stn.tickets.utils.Constants;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    private JPanel rootPanel;
    private JTextField usernameField;
    private JButton loginBtn;
    private JPasswordField passwordField;

    public LoginForm() {
        add(rootPanel);

        setTitle("Login Form");
        setSize(300, 200);

        setResizable(false);
        // Start the form center screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        if (Constants.AUTO_LOGIN) {
            usernameField.setText("user1");
            passwordField.setText("abcd");
        }

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = String.valueOf(passwordField.getPassword());
            try {
                boolean loginSuccess = UserDAO.getInstance().authUser(user, pass);

                if (loginSuccess) {
                    setVisible(false);
//                    ArtistForm artForm = new ArtistForm(FormOperatingModes.NEW);
//                    artForm.setVisible(true);
                    AdminForm adminForm = new AdminForm();
                    adminForm.setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(null, "Invalid account!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Could not perform login! Exception: " + ex.getMessage());
            }

        });


    }

}
