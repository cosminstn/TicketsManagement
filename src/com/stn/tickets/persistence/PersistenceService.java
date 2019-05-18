package com.stn.tickets.persistence;

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

    private static List<AuditAction> actions = loadActions();

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
        auditActions();
    }

    public List<T> loadPersistentList() {
        try {
            File csvFile = new File(Constants.PERSISTENCE_FILES_FOLDER + File.separator +
                    ghostEntity.getPersistenceFileName());

            try (BufferedReader in = new BufferedReader(new FileReader(csvFile))) {
                List<T> list = new ArrayList<>();
                String line;
                while ((line = in.readLine()) != null) {
                    try {
                        list.add(ghostEntity.loadFromCsvLine(line));
                    } catch (Exception ex) {
                        System.out.println("could not process line");
                        ex.printStackTrace();
                    }
                }
                return list;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }



    private static void auditActions() {
        try {
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
            System.out.println("Could not audit actions: ");
            ex.printStackTrace();
        }
    }

    public static List<AuditAction> loadActions() {
        try {
            // Create a ghost entity

            File file = new File(Constants.PERSISTENCE_FILES_FOLDER + File.separator +
                    AuditAction.PERSISTENCE_FILE_NAME);

            try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                List<AuditAction> foundAudit = new ArrayList<>();
                String line;
                int lineCount = 0;
                while ((line = in.readLine()) != null) {
                    try {
                        String[] contents = line.split(",");
                        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.CSV_DATE_TIME_FORMAT);
                        foundAudit.add(new AuditAction(contents[0], dateFormat.parse(contents[1])));
                    } catch (Exception ex) {
                        System.out.println(String.format("Could not process line %d " +
                                "from file %s", lineCount, AuditAction.PERSISTENCE_FILE_NAME));
                        ex.printStackTrace();
                    } finally {
                        lineCount ++;
                    }
                }
                return foundAudit;
            }
        } catch (Exception ex) {
            System.out.println("Could not load " + AuditAction.PERSISTENCE_FILE_NAME);
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
