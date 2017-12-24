package com.example.socket.base1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;

/**
 * socket客户端
 * Created by HF on 2017/12/24.
 */
public class SocketClient {

    public static void main(String[] args) {
        String msg = "嗨！洪玉环";
        try {
            try (Socket socket = new Socket("127.0.0.1",8777)) {
                try (
                        PrintWriter printWriter = new PrintWriter(socket.getOutputStream())
                ){
                    printWriter.println(msg);
                    printWriter.flush();

                    try( BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                        StringBuilder text = new StringBuilder();
                        String line = "";
                        // while ((line = bufferedReader.readLine()) != null) {
                        line = bufferedReader.readLine();
                            text.append(line);
                        // }
                        System.out.println("【收到来自服务端消息】：" + text.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
