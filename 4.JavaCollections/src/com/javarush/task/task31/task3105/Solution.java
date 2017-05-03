package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) return;

        Path fileToAdding = Paths.get(args[0]);
        String fileName = fileToAdding.getFileName().toString();
        String zipFilePath = args[1];

        Map<String, byte[]> bufferMap = new HashMap<>();

        // Читаем содержимое архива в память
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                copyData(zipInputStream, byteArrayOutputStream);
                bufferMap.put(zipEntry.getName(), byteArrayOutputStream.toByteArray());

                zipInputStream.closeEntry();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Записываем обратно в архив
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            boolean found = false;

            for (Map.Entry<String, byte[]> entry : bufferMap.entrySet()) {
                // перезаписываем в архиве все файлы с таким же именем (что за дурацкая задача?!)
                String entryFileName = Paths.get(entry.getKey()).getFileName().toString();
                if (fileName.equals(entryFileName)) {
                    zipOutputStream.putNextEntry(new ZipEntry(entry.getKey()));
                    Files.copy(fileToAdding, zipOutputStream);
                    zipOutputStream.closeEntry();
                    found = true;
                }
                else {
                    zipOutputStream.putNextEntry(new ZipEntry(entry.getKey()));
                    zipOutputStream.write(entry.getValue());
                    zipOutputStream.closeEntry();
                }
            }

            if (!found) {
                zipOutputStream.putNextEntry(new ZipEntry("new/" + fileName));
                Files.copy(fileToAdding, zipOutputStream);
                zipOutputStream.closeEntry();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }
}
