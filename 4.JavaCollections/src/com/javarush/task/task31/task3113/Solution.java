package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String dirName = console.readLine();

        try {
            Path path = Paths.get(dirName);
            if (Files.isDirectory(path)) {
                DirectoryExplorer directoryExplorer = new DirectoryExplorer();
                directoryExplorer.walkFileTree(path);
                System.out.println(String.format("Всего папок - %d", directoryExplorer.getDirCount()-1));
                System.out.println(String.format("Всего файлов - %d", directoryExplorer.getFilesCount()));
                System.out.println(String.format("Общий размер - %d", directoryExplorer.getTotalSize()));
            } else {
                throw new IllegalArgumentException();
            }
        }
        catch (Exception e) {
            System.out.println(String.format("%s - не папка", dirName));
        }
    }

    private static class DirectoryExplorer extends SimpleFileVisitor<Path> {
        private int filesCount;
        private int dirCount;
        private long totalSize;

        public long walkFileTree(Path path) throws IOException {
            Files.walkFileTree(path, this);
            return totalSize;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            filesCount++;
            totalSize += Files.size(file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            dirCount++;
            return FileVisitResult.CONTINUE;
        }

        public int getFilesCount() {
            return filesCount;
        }

        public int getDirCount() {
            return dirCount;
        }

        public long getTotalSize() {
            return totalSize;
        }
    }
}
