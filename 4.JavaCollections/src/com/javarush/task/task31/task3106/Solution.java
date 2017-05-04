package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            return;
        }

        // получаем имя выходного файла
        String resultFileName = args[0];

        // добавляем в список имена частей архива
        List<String> parts = new ArrayList<>();
        parts.addAll(Arrays.asList(args).subList(1, args.length));
        Collections.sort(parts);

        try {
            // создаём список входных потоков для каждой части архива
            List<InputStream> inputStreams = new ArrayList<>();
            for (String partFileName : parts) {
                inputStreams.add(new FileInputStream(partFileName));
            }

            FileOutputStream fos = new FileOutputStream(resultFileName);

            ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(Collections.enumeration(inputStreams)));
            if (zipInputStream.getNextEntry() != null)
            {
                copyData(zipInputStream, fos);
                zipInputStream.closeEntry();
            }
            zipInputStream.close();

            fos.close();

            for (InputStream stream : inputStreams) {
                if (stream != null)
                    stream.close();
            }

        } catch (IOException e) {
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
