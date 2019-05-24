package com.stn.tickets.forms;

import com.stn.tickets.db.dao.models.Concert;
import com.stn.tickets.db.dao.services.ConcertDAO;
import com.stn.tickets.enums.FormOperatingModes;
import com.stn.tickets.utils.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class ManageConcertsForm extends JFrame {

    private JPanel rootPanel;
    private JTable mainTable;
    private JButton addBtn;
    private JButton openBtn;

    private String[] columnsHeaders = {
            "ID", "NUME", "DESCRIERE", "START", "FINAL", "LOCATIE", "EXPLICIT", "ARTIST PRINCIPAL", "ARTIST_DESCHIDERE"
    };

    private DefaultTableModel tableModel;
    public ManageConcertsForm() throws Exception {
        add(rootPanel);

        setTitle("Concerte");
        setSize(600, 400);

        addBtn.addActionListener(e -> {
            try {
                ConcertForm concertForm = new ConcertForm(FormOperatingModes.NEW, this);
                concertForm.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Eroare generala! Eroare: " + ex.getMessage());
            }
        });

        // configure the table headers
        tableModel = new DefaultTableModel(columnsHeaders, 0);

        loadConcerts();
        mainTable.setModel(tableModel);
    }

    private void loadConcerts() throws Exception {
        List<Concert> cons = ConcertDAO.getInstance().getAllEntities();

        for (Concert con : cons) {
            Object[] arr = new Object[9];

            int colIndex = 0;
            arr[colIndex++] = con.getId();
            arr[colIndex++] = con.getName();
            arr[colIndex++] = con.getDescription();
            SimpleDateFormat datetimeFormat = new SimpleDateFormat(Constants.STANDARD_DATE_TIME_FORMAT);
            arr[colIndex++] = datetimeFormat.format(con.getStartDate());
            try { arr[colIndex] = datetimeFormat.format(con.getEndDate()); } catch (Exception ignored) {} finally {colIndex++;}
            arr[colIndex++] = con.getLocation().getName();
            arr[colIndex++] = con.isExplicit() ? "DA" : "NU";
            arr[colIndex++] = con.getMainArtist().getName();
            try { arr[colIndex] = con.getOpeningArtist().getName(); } catch (Exception ignored) {}
            tableModel.addRow(arr);
        }
    }
}
