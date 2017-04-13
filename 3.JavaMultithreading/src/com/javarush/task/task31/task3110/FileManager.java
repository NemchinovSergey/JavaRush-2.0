package com.javarush.task.task31.task3110;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nemchinov on 13.04.2017.
 */
public class FileManager {
    private Path rootPath;
    private List<Path> fileList;

    public FileManager(Path rootPath) throws IOException {
        this.rootPath = rootPath;
        this.fileList = new ArrayList<>();
        collectFileList(rootPath);
    }

    public List<Path> getFileList() {
        return fileList;
    }

    private void collectFileList(Path path) throws IOException {
        if (Files.isRegularFile(path)) {
            fileList.add(rootPath.relativize(path));
        }
        else if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                for (Path entry: directoryStream) {
                    collectFileList(entry);
                }
            }
            catch (DirectoryIteratorException ex) {
                // I/O error encounted during the iteration, the cause is an IOException
                throw ex.getCause();
            }
        }
    }
}
