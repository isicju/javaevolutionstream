import java.io.IOException;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class UnixDomainSocketClient {

    public static void main(String[] args) {
        try {
            Path socketPath = Paths.get("/path/to/unix/socket");
            SocketChannel socketChannel = SocketChannel.open(UnixDomainSocketAddress.of(socketPath));

            Path filePath = Paths.get("/path/to/large/file.txt");

            ByteBuffer buffer = ByteBuffer.wrap(java.nio.file.Files.readAllBytes(filePath));

            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }

            System.out.println("File sent successfully.");

            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}