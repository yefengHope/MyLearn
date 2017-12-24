package com.example.socket.base1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket服务端
 * Created by HF on 2017/12/24.
 */
public class SocketServer {



    public static void main(String[] args) {


        try {
            ServerSocket serverSocket = new ServerSocket(8777);

            try (Socket socket = serverSocket.accept()) {
                try (
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                ) {

                    String line = "";
                    StringBuffer text = new StringBuffer();
                    // while ((line = bufferedReader.readLine()) != null && !"exit".equalsIgnoreCase(line)) {
                    line = bufferedReader.readLine();
                        text.append(line);
                        text.append("\n");
                    // }
                    System.out.println("【服务端收到消息】：" + text.toString());
                    try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream());) {
                        printWriter.println(text.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
