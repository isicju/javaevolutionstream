package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.StandardProtocolFamily;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class UnixServer {
    static List<String> lines = new ArrayList<>(100_000_00);

    public static void main(String[] args) throws Exception {
        // Create a Unix domain server
        Path socketPath = Paths.get(args[0]);
        Path newPath = socketPath.resolve(args[1]);

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open(StandardProtocolFamily.UNIX)) {

            serverSocketChannel.bind(UnixDomainSocketAddress.of(newPath));
            System.out.println("Unix Domain Socket Server is running...");

            while (true) {
                try (SocketChannel clientSocket = serverSocketChannel.accept()) {
                    System.out.println("Received request...");
                    handleClient(clientSocket);
                }
            }
        }
    }

    private static void handleClient(SocketChannel clientSocket) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.socket().getInputStream()))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                i++;
                if (i % 100_000 == 0) System.out.println("100_000 still processing.." + i);
                lines.add(line);
            }
        }
    }

    /*private static void handleClient(SocketChannel clientSocket) throws Exception {
        Path filePath = Paths.get("/path/to/your/100M/file");
        FileChannel fileChannel = FileChannel.open(filePath, StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024); // 1MB buffer
        int bytesRead;

        while (fileChannel.read(buffer) > 0) {
            buffer.flip();
            clientSocket.write(buffer);
            buffer.clear();
        }

        fileChannel.close();
        clientSocket.close();
    }*/
}
