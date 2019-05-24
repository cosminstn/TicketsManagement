package com.stn.tickets.forms;

import com.stn.tickets.db.dao.models.Artist;
import com.stn.tickets.db.dao.models.Concert;
import com.stn.tickets.db.dao.services.ArtistDAO;
import com.stn.tickets.db.dao.services.ConcertDAO;
import com.stn.tickets.db.dao.services.LocationDAO;
import com.stn.tickets.enums.FormOperatingModes;
import com.stn.tickets.utils.Constants;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.stn.tickets.enums.FormOperatingModes.NEW;
import static com.stn.tickets.enums.FormOperatingModes.UPDATE_DELETE;

public class ConcertForm extends JFrame implements IEntityForm<Concert> {

    private FormOperatingModes mode;
    private JPanel rootPanel;
    private JLabel idLbl;
    private JTextField idField;
    private JTextField nameField;
    private JTextField descField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField locationIdField;
    private JButton saveBtn;
    private JButton deleteBtn;
    private JRadioButton explicitYesRadio;
    private JRadioButton explicitNoRadio;
    private JComboBox mainArtistCombo;
    private JComboBox openingArtistCombo;
    private JCheckBox hasOpeningArtistCheck;

    private ManageConcertsForm parent;

    public ConcertForm(FormOperatingModes mode, ManageConcertsForm parentForm) throws Exception {
        add(rootPanel);

        this.mode = mode;
        this.parent = parentForm;
        updateControls();

        setSize(400, 600);

        //pull artists for comboboxes
        List<Artist> allArtists = ArtistDAO.getInstance().getAllEntities();
        for(Artist artist : allArtists) {
            mainArtistCombo.addItem(artist);
            openingArtistCombo.addItem(artist);
        }

        saveBtn.addActionListener(e -> {
            if (this.mode == NEW) {
                try {
                    Concert con = getEntityFromForm();
                    int conId = ConcertDAO.getInstance().createEntity(con);
                    con.setId(conId);

                    this.mode = UPDATE_DELETE;
                    loadEntityIntoForm(con);
                    JOptionPane.showMessageDialog(null, "Concertul a fost inregistrat!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Concertul nu a putut fi inregistrat! " +
                            "Eroare: " + ex.getMessage());
                }
            }
            else {

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
                setTitle("Adauga un concert nou");
                deleteBtn.setEnabled(false);

            }
            case UPDATE_DELETE: {
                idLbl.setVisible(true);
                idField.setVisible(true);
                setTitle("Modificare concert");
                deleteBtn.setEnabled(true);
            }
        }
    }

    @Override
    public Concert getEntityFromForm() throws Exception {
        Concert con = new Concert();
        con.setName(nameField.getText());
        con.setDescription(descField.getText());
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(Constants.STANDARD_DATE_TIME_FORMAT);
        con.setStartDate(new java.sql.Date(dateTimeFormat.parse(startDateField.getText()).getTime()));
        con.setEndDate(new java.sql.Date(dateTimeFormat.parse(endDateField.getText()).getTime()));
        con.setLocation(LocationDAO.getInstance().getEntity(Integer.parseInt(locationIdField.getText())));
        if (con.getLocation() == null)
            throw new Exception("invalid location");
        con.setExplicit(explicitYesRadio.isSelected());
        con.setMainArtist((Artist) mainArtistCombo.getSelectedItem());
        if (hasOpeningArtistCheck.isSelected())
            con.setOpeningArtist((Artist) openingArtistCombo.getSelectedItem());
        else
            con.setOpeningArtist(null);

        return con;
    }

    @Override
    public void loadEntityIntoForm(Concert entity) throws Exception {
        idField.setText(entity.getId().toString());
        nameField.setText(entity.getName());
        descField.setText(entity.getDescription());

        SimpleDateFormat datetimeFormat = new SimpleDateFormat(Constants.STANDARD_DATE_TIME_FORMAT);
        startDateField.setText(datetimeFormat.format(new java.util.Date(entity.getStartDate().getTime())));
        endDateField.setText(datetimeFormat.format(new java.util.Date(entity.getEndDate().getTime())));
        locationIdField.setText(entity.getLocation().getId().toString());
        mainArtistCombo.setSelectedItem(entity.getMainArtist());
        if (entity.getOpeningArtist() != null) {
            hasOpeningArtistCheck.setSelected(true);
            openingArtistCombo.setSelectedItem(entity.getOpeningArtist());
        }
        else {
            hasOpeningArtistCheck.setSelected(false);
            openingArtistCombo.setSelectedItem(null);
        }
    }
}
