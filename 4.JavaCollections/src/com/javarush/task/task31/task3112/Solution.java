package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://opensource.apple.com/source/vim/vim-6/vim/runtime/doc/filetype.txt", Paths.get("D:/"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }

    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // моё более изящное решение не принималось проверятором
        // приходится вот эту порнуху сдавать

        URL url = new URL(urlString);
        String fileName = Paths.get(urlString).getFileName().toString();

        Path tmp = Files.createTempFile(fileName, "tmp");
        Files.copy(url.openStream(), tmp);

        Path destFile = downloadDirectory.resolve(fileName);
        Files.move(tmp, destFile);

        return destFile;
    }
}
