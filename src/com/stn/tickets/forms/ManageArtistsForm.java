package com.stn.tickets.forms;

import com.stn.tickets.db.dao.models.Artist;
import com.stn.tickets.db.dao.services.ArtistDAO;
import com.stn.tickets.enums.FormOperatingModes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ManageArtistsForm extends JFrame {
    private JPanel rootPanel;
    private JButton addBtn;
    private JButton openBtn;
    private JTable mainTable;

    private String[] columnsHeaders = { "ID", "NUME", "DATA NASTERE / FORMARE", "TIP" };
    private DefaultTableModel tableModel;


    public ManageArtistsForm() throws Exception {
        add(rootPanel);
        setTitle("Artisti");
        setSize(400, 400);

        addBtn.addActionListener(e -> {
            ArtistForm artForm = new ArtistForm(FormOperatingModes.NEW, this);
            artForm.setVisible(true);
        });

        openBtn.addActionListener(e -> {
            try {
                // get the selected row
                int id = (Integer) tableModel.getValueAt(mainTable.getSelectedRow(), 0);
                Artist artist = ArtistDAO.getInstance().getEntity(id);
                ArtistForm artForm = new ArtistForm(FormOperatingModes.UPDATE_DELETE, this);
                artForm.loadEntityIntoForm(artist);
                artForm.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Entitatea nu a putut fi incarcata!");
            }
        });

        tableModel = new DefaultTableModel(columnsHeaders, 0);
        mainTable.setModel(tableModel);

        //disable user edits
        mainTable.setDefaultEditor(Object.class, null);

        refreshData();
    }

    public void refreshData() throws Exception {
        //remove all current rows
        tableModel.setRowCount(0);

        List<Artist> data = ArtistDAO.getInstance().getAllEntities();
        for (Artist artist : data) {
            tableModel.addRow(castArtistToRow(artist));
        }
    }

    private Object[] castArtistToRow(Artist art) {
        Object[] row = new Object[columnsHeaders.length];
        int colIndex = 0;
        row[colIndex++] = art.getId();
        row[colIndex++] = art.getName();
        row[colIndex++] = art.getBirthDate().toString();
        row[colIndex]   = art.getType().getName();

        return row;
    }
}
