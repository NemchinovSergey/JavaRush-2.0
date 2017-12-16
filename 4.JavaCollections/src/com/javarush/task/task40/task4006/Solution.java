package com.javarush.task.task40.task4006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            Socket socket = new Socket(url.getHost(), url.getDefaultPort());
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            out.println(String.format("GET %s HTTP/1.1", url.getFile()));
            out.println("Host: " + url.getHost());
            out.println("User-Agent: Mozilla/5.0");
            //out.println("Accept: text/html");
            //out.println("Accept-Language: ru-RU,ru;");
            //out.println("Accept-Encoding: gzip, deflate");
            //out.println("Connection: close");
            out.println();
            out.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
            bufferedReader.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}