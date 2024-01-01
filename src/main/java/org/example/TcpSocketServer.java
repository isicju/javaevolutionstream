package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSocketServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
            System.out.println("TCP Socket Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        long totalBytesRead = 0;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            totalBytesRead += bytesRead;
        }

        System.out.println("Received " + totalBytesRead + " bytes from the client.");

        clientSocket.close();
    }
}
