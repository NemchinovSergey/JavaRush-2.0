package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;
import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;
import com.javarush.task.task31.task3110.exception.WrongZipFileException;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by nemchinov on 13.04.2017.
 */
public class ZipExtractCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Распаковка архива.");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Введите полный путь директории куда распаковывать:");
            Path sourcePath = Paths.get(ConsoleHelper.readString());
            zipFileManager.extractAll(sourcePath);

            ConsoleHelper.writeMessage("Архив распакован.");

        }
        catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Вы неверно указали имя файла или директории.");
        }
        catch (WrongZipFileException e) {
            ConsoleHelper.writeMessage("Не верно указано имя zip-архива.");
        }
    }
}
