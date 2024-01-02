package org.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        String fileLocation = args[0];
        try (Socket socket = new Socket("localhost", 7777)) {
            Path filePath = Paths.get(fileLocation);

            try (OutputStream outputStream = socket.getOutputStream()) {
                java.nio.file.Files.copy(filePath, outputStream);
            }

            System.out.println("File sent successfully.");
        }
    }
}
