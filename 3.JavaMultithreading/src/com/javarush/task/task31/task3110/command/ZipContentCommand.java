package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.FileProperties;
import com.javarush.task.task31.task3110.ZipFileManager;
import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.util.List;

/**
 * Created by nemchinov on 13.04.2017.
 */
public class ZipContentCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Просмотр содержимого архива.");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Содержимое архива:");

            List<FileProperties> files = zipFileManager.getFilesList();

            for (FileProperties file : files) {
                ConsoleHelper.writeMessage(file.toString());
            }

            ConsoleHelper.writeMessage("Содержимое архива прочитано.");

        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Вы неверно указали имя файла или директории.");
        }
    }
}
