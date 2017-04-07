package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nemchinov on 06.04.2017.
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();
    public static void sendBroadcastMessage(Message message) {
        for (Connection connection : connectionMap.values()) {
            try {
                connection.send(message);
            }
            catch (IOException e) {
                ConsoleHelper.writeMessage("Error send message");
            }
        }
    }


    public static void main(String[] args) {
        //ConsoleHelper.writeMessage("Enter server socket number:");
        int serverPort = ConsoleHelper.readInt();

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            ConsoleHelper.writeMessage(String.format("Server started at %d port", serverSocket.getLocalPort()));

            while (!Thread.currentThread().isInterrupted()) {
                Socket client = serverSocket.accept();
                Handler handler = new Handler(client);
                handler.start();
            }

        } catch (IOException e) {
            ConsoleHelper.writeMessage(e.getMessage());
        }

    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {


                    sleep(10);
                }
            } catch (InterruptedException ioException) {
            }
        }
    }
}
