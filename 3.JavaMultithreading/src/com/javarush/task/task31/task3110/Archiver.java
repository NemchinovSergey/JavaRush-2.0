package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;
import com.javarush.task.task31.task3110.exception.WrongZipFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Created by nemchinov on 13.04.2017.
 */
public class Archiver {

    public static Operation askOperation() throws IOException {
        ConsoleHelper.writeMessage( "Выберите операцию:\n" +
                Operation.CREATE.ordinal()  + " - упаковать файлы в архив\n" +
                Operation.ADD.ordinal()     + " - добавить файл в архив\n" +
                Operation.REMOVE.ordinal()  + " - удалить файл из архива\n" +
                Operation.EXTRACT.ordinal() + " - распаковать архив\n" +
                Operation.CONTENT.ordinal() + " - просмотреть содержимое архива\n" +
                Operation.EXIT.ordinal()    + " – выход");

        int operationNumber = ConsoleHelper.readInt();
        return Operation.values()[operationNumber];
    }

    public static void main(String[] args)
    {
        Operation operation = null;
        while (operation != Operation.EXIT) {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            }
            catch (WrongZipFileException wrongZipFileExc) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            }
            catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }
        }
    }
}
