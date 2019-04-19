package com.stn.tickets.persistence;

import com.stn.tickets.models.Consumer;
import com.stn.tickets.utils.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersistenceService<T extends PersistentEntity> {

    private T ghostEntity;

    public PersistenceService(T ghostEntity) {
        this.ghostEntity = ghostEntity;
    }

    private static List<AuditAction> actions = new ArrayList<>();

    public void persistList(List<T> persistentList) throws Exception {
        if (persistentList == null || persistentList.size() == 0)
            throw new Exception("Nothing to persist!");

        File csvFolder = new File(Constants.PERSISTENCE_FILES_FOLDER);
        csvFolder.mkdirs();

        File csvFile = new File(Constants.PERSISTENCE_FILES_FOLDER + File.separator +
                ghostEntity.getPersistenceFileName());

//        if (!csvFile.mkdirs())
//            throw new Exception("Could not create the file: " + ghostEntity.getPersistenceFileName());
//
//        if (csvFile.exists())
//            csvFile.delete();

        try (PrintWriter writer = new PrintWriter(csvFile)) {
            for (T line : persistentList) {
                writer.println(line.toCsvLine());
                actions.add(new AuditAction("write_" + ghostEntity.getPersistenceFileName(),
                        new Date()));
            }

        }
    }

    public List<T> loadPersistentList() {
        //TODO
        return null;
    }

    private static void auditActions(String actionName, Date timestamp) {
        try {
            if (actionName == null || timestamp == null)
                return;

            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.CSV_DATE_TIME_FORMAT);

            File csvFolder = new File(Constants.PERSISTENCE_FILES_FOLDER);
            csvFolder.mkdirs();

            File auditCsvFile = new File(Constants.PERSISTENCE_FILES_FOLDER + File.separator
                    + "audit.csv");
            try (PrintWriter writer = new PrintWriter(auditCsvFile)) {
                for (AuditAction action : actions) {
                    writer.println(action.toCsvLine());
                }
            }

        } catch (Exception ex) {
            System.out.println("Could not audit action: " + actionName);
        }
    }

    public static void loadActions() {
        try {
            // Create a ghost entity

            File file = new File(Constants.PERSISTENCE_FILES_FOLDER + File.separator +
                    AuditAction.PERSISTENCE_FILE_NAME);

            try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                String line;
                int lineCount = 0;
                actions = new ArrayList<>();
                while ((line = in.readLine()) != null) {
                    try {
                        String[] contents = line.split(",");
                        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.CSV_DATE_TIME_FORMAT);
                        actions.add(new AuditAction(contents[0], dateFormat.parse(contents[1])));
                    } catch (Exception ex) {
                        System.out.println(String.format("Could not process line %d " +
                                "from file %s", lineCount, AuditAction.PERSISTENCE_FILE_NAME));
                        ex.printStackTrace();
                    } finally {
                        lineCount ++;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Could not load " + AuditAction.PERSISTENCE_FILE_NAME);
            ex.printStackTrace();
        }
    }
}
