import java.io.IOException;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UnixClient {
    public static void main(String[] args) throws IOException {
        String url = args[0];
        System.out.println("Starting copy process..");

        Path socketPath = Paths.get("/path/to/unix/socket");
        SocketChannel socketChannel = SocketChannel.open(UnixDomainSocketAddress.of(socketPath));

        Path filePath = Paths.get(url);

        ByteBuffer buffer = ByteBuffer.wrap(java.nio.file.Files.readAllBytes(filePath));
        System.out.println("starting sending using unix shit");
        long before = System.currentTimeMillis();

        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);
        }
        System.out.println("total time " + (System.currentTimeMillis() - before) / 1000f + " seconds");
        System.out.println("File sent successfully.");
        socketChannel.close();
    }
}
