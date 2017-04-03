package com.javarush.task.task31.task3111;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName;
    private String partOfContent;
    private int minSize = -1;
    private int maxSize = -1;
    private List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if (partOfName != null && !file.getFileName().toString().contains(partOfName)) {
            return FileVisitResult.CONTINUE;
        }

        if (minSize >= 0 && !(Files.size(file) >= minSize)){
            return FileVisitResult.CONTINUE;
        }

        if (maxSize >= 0 && !(Files.size(file) <= maxSize)){
            return FileVisitResult.CONTINUE;
        }

        if (partOfContent != null) {
            final String data = new String(Files.readAllBytes(file));
            if (!data.contains(partOfContent)){
                return FileVisitResult.CONTINUE;
            }
        }

        foundFiles.add(file);
        
        return FileVisitResult.CONTINUE;
    }

    private boolean checkContent(Path file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (line.contains(partOfContent)) {
                    return true;
                }
            }
        } catch (IOException e) {

        }
        return false;
    }


    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}
