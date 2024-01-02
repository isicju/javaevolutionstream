package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
public class TcpSocketServer {
//    public static void main(String[] args) throws IOException {
//        ServerSocket serverSocket = new ServerSocket(7777);
//        System.out.println("TCP Socket Server is running...");
//
//        while (true) {
//            try(Socket clientSocket = serverSocket.accept()){
//                try (InputStream inputStream = clientSocket.getInputStream()) {
//                    long totalBytesRead = inputStream.transferTo(System.out);
//                    System.out.println("Received " + totalBytesRead + " bytes from the client.");
//                }
//            }
//
//        }
//
//
//    }
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);
        System.out.println("TCP Socket Server is running...");

        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 InputStream inputStream = clientSocket.getInputStream()) {

                List<Byte> receivedBytes = new ArrayList<>();
                int byteRead;
                while ((byteRead = inputStream.read()) != -1) receivedBytes.add((byte) byteRead);

                System.out.println("Received " + receivedBytes.size() + " bytes from the client.");

                // Process the receivedBytes list as needed
//                byte[] byteArray = new byte[receivedBytes.size()];
//                for (int i = 0; i < receivedBytes.size(); i++) byteArray[i] = receivedBytes.get(i);

                // Process the byteArray as needed
            }
        }
    }
//    private static void handleClient(Socket clientSocket) throws IOException {
//        InputStream inputStream = clientSocket.getInputStream();
//        byte[] buffer = new byte[1024];
//        int bytesRead;
//        long totalBytesRead = 0;
//
//        while ((bytesRead = inputStream.read(buffer)) != -1) {
//            totalBytesRead += bytesRead;
//        }
//
//        System.out.println("Received " + totalBytesRead + " bytes from the client.");
//
//        clientSocket.close();
//    }
}
