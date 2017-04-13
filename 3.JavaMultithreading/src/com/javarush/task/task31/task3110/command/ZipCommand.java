package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Paths;

/**
 * Created by nemchinov on 13.04.2017.
 */
public abstract class ZipCommand implements Command {
    public ZipFileManager getZipFileManager() throws Exception {
        ConsoleHelper.writeMessage("Введите полный путь для создаваемого файла-архива: ");
        String zipFileName = ConsoleHelper.readString();
        return new ZipFileManager(Paths.get(zipFileName));
    }
}
