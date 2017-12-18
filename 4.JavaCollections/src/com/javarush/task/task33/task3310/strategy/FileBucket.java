package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException ignored) {
        }

        path.toFile().deleteOnExit();
    }

    public long getFileSize() {
        long size = 0;
        try {
            size = Files.size(path);
        } catch (IOException ignored) {
        }
        return size;
    }

    public void putEntry(Entry entry) {
        try (OutputStream os = Files.newOutputStream(path)) {
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(entry);
        } catch (IOException ignored) {
        }
    }

    public Entry getEntry() {
        Entry entry = null;

        if (getFileSize() == 0) return null;

        try (InputStream inputStream = Files.newInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(inputStream))
        {
            entry = (Entry) ois.readObject();
        } catch (Exception ignored) {
        }
        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException ignored) {
        }
    }
}
