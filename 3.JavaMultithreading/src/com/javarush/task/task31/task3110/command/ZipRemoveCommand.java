package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;
import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by nemchinov on 13.04.2017.
 */
public class ZipRemoveCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Удаление файлов из архива");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Введите мя файла для удаления:");
            String fileName = ConsoleHelper.readString();
            zipFileManager.removeFile(Paths.get(fileName));

            ConsoleHelper.writeMessage("Файл удалён");

        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Неверный путь для распаковки.");
        }
    }
}
