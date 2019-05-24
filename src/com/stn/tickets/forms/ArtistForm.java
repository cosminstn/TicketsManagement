package com.stn.tickets.forms;

import com.stn.tickets.db.dao.models.Artist;
import com.stn.tickets.db.dao.services.ArtistDAO;
import com.stn.tickets.enums.ArtistTypes;
import com.stn.tickets.enums.FormOperatingModes;
import com.stn.tickets.utils.Constants;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.stn.tickets.enums.FormOperatingModes.NEW;
import static com.stn.tickets.enums.FormOperatingModes.UPDATE_DELETE;

public class ArtistForm extends JFrame implements IEntityForm<Artist> {
    private JPanel rootPanel;
    private JTextField nameField;
    private JTextField profilePicUrlField;
    private JTextField birthDateField;
    private JComboBox typeCombo;
    private JTextField idField;
    private JLabel idLbl;
    private JButton saveBtn;
    private JButton deleteBtn;

    private FormOperatingModes mode;
    private ManageArtistsForm parent;


    public ArtistForm(FormOperatingModes mode, ManageArtistsForm parentForm) {
        add(rootPanel);
        setTitle("Adauga un artist nou");

        this.mode = mode;
        this.parent = parentForm;
        updateControls();
        // load the types into the combo
        for (ArtistTypes type : ArtistTypes.values())
            typeCombo.addItem(type);

        saveBtn.addActionListener(e -> {
            if (this.mode == NEW) {
                try {
                    Artist artist = getEntityFromForm();
                    int artistId = ArtistDAO.getInstance().createEntity(artist);
                    this.mode = UPDATE_DELETE;
                    idField.setText(String.valueOf(artistId));
                    parentForm.refreshData();
                    JOptionPane.showMessageDialog(null, "Artistul a fost adaugat in baza de date!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Nu am putut adauga artistul in baza de date! " +
                            "Eroare: " + ex.getMessage());
                }
            }
            else {
                try {
                    Artist artist = getEntityFromForm();
                    boolean success = ArtistDAO.getInstance().updateEntity(artist);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Artistul a fost actualizat!");
                        parentForm.refreshData();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Artistul nu a putut fi actualizat!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Artistul nu a putut fi actualizat! " +
                            "Eroare: " + ex.getMessage());
                }
            }
        });

        deleteBtn.addActionListener(e -> {
            if (this.mode == UPDATE_DELETE) {
                try {
                    int artistId = getEntityFromForm().getId();
                    boolean success = ArtistDAO.getInstance().deleteEntity(artistId);
                    if (success)
                        JOptionPane.showMessageDialog(null, "Inregistrarea a fost stearsa cu success");
                    else
                        throw new Exception("eroare generala");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Nu am putut sterge inregistrarea! Eroare: " +
                            ex.getMessage());
                }

            }
        });
    }

    /**
     * Updates the form controls by it's operating type
     */
    private void updateControls() {
        idField.setEnabled(false);
        switch (mode) {
            case NEW: {
                idLbl.setVisible(false);
                idField.setVisible(false);
                setTitle("Adauga un artist nou");
                deleteBtn.setEnabled(false);

            }
            case UPDATE_DELETE: {
                idLbl.setVisible(true);
                idField.setVisible(true);
                setTitle("Modificare artist");
                deleteBtn.setEnabled(true);
            }
        }
    }

    @Override
    public Artist getEntityFromForm() throws Exception {
        Artist artist = new Artist();
        if (mode == UPDATE_DELETE) {
            try {
                artist.setId(Integer.parseInt(idField.getText()));
            } catch (Exception ex) {}
        }
        artist.setName(nameField.getText());
        SimpleDateFormat format = new SimpleDateFormat(Constants.STANDARD_DATE_FORMAT);
        try {
            artist.setBirthDate(new java.sql.Date(format.parse(birthDateField.getText()).getTime()));
        } catch (ParseException ex) {
            ex.printStackTrace();
            throw new Exception("Formatul datei este invalid!");
        }
        artist.setType((ArtistTypes) typeCombo.getSelectedItem());
        return artist;
    }

    @Override
    public void loadEntityIntoForm(Artist entity) {
        setTitle("Artist: " + entity.getName());

        idField.setText(entity.getId().toString());
        nameField.setText(entity.getName());
        profilePicUrlField.setText(entity.getProfilePicUrl());

        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.STANDARD_DATE_FORMAT);
        birthDateField.setText(dateFormat.format(new Date(entity.getBirthDate().getTime())));
        typeCombo.setSelectedItem(entity.getType());
    }
}
