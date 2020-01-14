package com.fakturowanko.db;

import java.io.IOException;

public class BackupClass {
    private static final String command = "mysqldump -u root -p fakturowanie > C:/Users/Eryk/Desktop/BACKUP1/data-dump.sql";

    public static void makeBackupGreatAgain() {
        try {
            System.out.println("Making backup...");
            Process process = Runtime.getRuntime().exec(command);
            int processComplete = process.waitFor();
            if(processComplete == 0){
                System.out.println("success");
            } else {
                System.out.println(":(");
            }
        } catch(IOException | InterruptedException ex) {
            System.out.println("Backup failed");
            ex.printStackTrace();
        }
    }
}
