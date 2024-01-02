package org.example;

import java.io.IOException;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UnixServer {
    public static void main(String[] args) throws IOException {
        Path socketPath = Paths.get("/path/to/unix/socket");

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(UnixDomainSocketAddress.of(socketPath));
            System.out.println("Unix Domain Socket Server is running...");

            while (true) {
                try (SocketChannel clientSocket = serverSocketChannel.accept()) {
                    handleClient(clientSocket);
                }
            }
        }
    }

    private static void handleClient(SocketChannel clientSocket) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = clientSocket.read(buffer);

        while (bytesRead != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();
            bytesRead = clientSocket.read(buffer);
        }
    }

}