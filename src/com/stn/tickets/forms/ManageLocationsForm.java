package com.stn.tickets.forms;

import com.stn.tickets.db.dao.models.Location;
import com.stn.tickets.db.dao.services.LocationDAO;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.text.TabExpander;
import java.util.List;

public class ManageLocationsForm extends JFrame{
    private JPanel rootPanel;
    private JScrollPane scrollPane;

    private String[] columnNames = {"ID Locatie", "Nume", "Tara", "Oras"};

    public ManageLocationsForm() throws Exception {
        add(rootPanel);
        setTitle("Locatii inregistrate");

        setSize(400, 400);

        //configure table
        List<Object[]> locsUnparsed = LocationDAO.getInstance().getAllEntitiesUnparsed();
        Object[][] matrix = new Object[locsUnparsed.size()][4];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = locsUnparsed.get(i);
            System.out.println("parsing");
        }

        JTable mainTable = new JTable(matrix, columnNames);
        mainTable.setEnabled(false);
        scrollPane.setSize(this.getSize());
        scrollPane.add(mainTable);

        add(mainTable);
    }
}
