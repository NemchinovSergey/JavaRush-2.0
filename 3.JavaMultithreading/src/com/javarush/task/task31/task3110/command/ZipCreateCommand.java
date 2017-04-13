package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;
import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by nemchinov on 13.04.2017.
 */
public class ZipCreateCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Создание архива.");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Введите полный путь файла или директории для архивирования: ");
            Path path = Paths.get(ConsoleHelper.readString());
            zipFileManager.createZip(path);

            ConsoleHelper.writeMessage("Архив создан.");
        }
        catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Вы неверно указали имя файла или директории.");
        }
    }


}
