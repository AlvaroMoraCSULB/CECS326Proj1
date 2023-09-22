import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "example.com";
        int port = 6017;

        try {
            Socket socket = new Socket(serverAddress, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            char[] buffer = new char[4096];
            int bytesRead;
            StringBuilder quoteBuilder = new StringBuilder();

            while ((bytesRead = reader.read(buffer)) != -1) {
                quoteBuilder.append(buffer, 0, bytesRead);
            }

            String quote = quoteBuilder.toString();
            System.out.println(quote);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
