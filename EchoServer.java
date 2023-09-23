import java.io.*;
import java.net.*;
public class EchoServer {
    public static void main(String[] args) {
        try {
            // Create a server socket on port 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server listening on port 12345...");

            while (true) {
                // Wait for a client to connect
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                // returns the address to which the socket is connected

                // Create a new thread to handle the client
                Thread clientThread = new ClientThread(clientSocket);
                clientThread.start();
            }
        } catch (IOException ioe) {
          System.err.println(ioe);
        }
    }
}
class ClientThread extends Thread {
    private final Socket clientSocket;

    public ClientThread(Socket sock) { // init method
        this.clientSocket = sock;
    }
    public void run() { // run method is used to perform action for client thread
        try {
            // Create input and output streams for the client socket
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            byte[] buffer = new byte[1024]; // buffer
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // Read data from the client
                String clientmsg = new String(buffer, 0, bytesRead);
                System.out.println("Received from client: " + clientmsg);
                // Replace  string "client" with "server" in the client's message
                String modifymsg = clientmsg.replace("client", "server");

                // Send the modified message back to the client
                outputStream.write(modifymsg.getBytes());
                outputStream.flush(); // Flush() clears the stream of elements


                }


            // Client closed the connection
            System.out.println("Client disconnected: " + clientSocket.getInetAddress());
            clientSocket.close();
        } catch (IOException ioe) {
           System.err.println(ioe);
        }
    }
}





