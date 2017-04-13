package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Created by nemchinov on 13.04.2017.
 */
public class Archiver {
    public static void main(String[] args) {


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
        {
            System.out.println("Enter path to archive: ");
            String archivePath = reader.readLine();

            System.out.println("Enter path to file to zip: ");
            String filePath = reader.readLine();

            ZipFileManager zipFileManager = new ZipFileManager(Paths.get(archivePath));
            zipFileManager.createZip(Paths.get(filePath));

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new ExitCommand().execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
