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
            } catch (IOException e) {
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

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (String name : connectionMap.keySet()) {
                if (!name.equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, name));
                }
            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message handshake = connection.receive();

                if (handshake.getType() == MessageType.USER_NAME) {
                    String userName = handshake.getData();
                    if (!userName.isEmpty() && !connectionMap.containsKey(userName)) {
                        Server.connectionMap.put(userName, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return userName;
                    }
                }
            }
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
