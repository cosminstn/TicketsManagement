package com.stn.tickets.forms;

import javax.swing.*;

public class AdminForm extends JFrame {
    private JPanel rootPanel;
    private JButton locsBtn;
    private JButton concertsBtn;
    private JButton artistsBtn;

    public AdminForm() {
        add(rootPanel);
        setTitle("Panou administrare");
        setSize(400, 400);

        locsBtn.addActionListener(e -> {
            try {
                ManageLocationsForm locsForm = new ManageLocationsForm();
                locsForm.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Nu am putut incarca locatiile!");
            }
        });

        artistsBtn.addActionListener(e -> {
            try {
                ManageArtistsForm artistsForm = new ManageArtistsForm();
                artistsForm.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Eroare la incarcarea artistilor!");
            }
        });

        concertsBtn.addActionListener(e -> {
            try {
                ManageConcertsForm concertsForm = new ManageConcertsForm();
                concertsForm.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Nu am putut incarca concertele!");
            }
        });

        //terminates the entire application
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
