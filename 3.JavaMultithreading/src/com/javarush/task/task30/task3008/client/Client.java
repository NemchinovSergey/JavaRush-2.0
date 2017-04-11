package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;

/**
 * Created by nemchinov on 11.04.2017.
 */
public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;
    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Enter server ip-address:");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Enter server port:");
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        ConsoleHelper.writeMessage("Enter user name: ");
        return ConsoleHelper.readString();
    }

    protected  boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        }
        catch (IOException e) {
            ConsoleHelper.writeMessage("An error was occurred. Connection is lost.");
            clientConnected = false;
        }
    }

    public class SocketThread extends Thread {

    }
}
