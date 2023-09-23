import java.io.*;
import java.net.*;
import java.util.Scanner;



public class EchoClient {
    public static void main(String[] args) {
        try {
            // Connect to the server on localhost and port 12345
            Socket sock = new Socket("localhost", 12345);

            // Create input and output streams for the server sock
            InputStream inputStream = sock.getInputStream();
            OutputStream outputStream = sock.getOutputStream();
            Scanner scan = new Scanner(System.in); // scanner object for user input
            while (true) {
                System.out.print("Enter any message or ('1' to quit): ");
                String userInput = scan.nextLine(); // turn userinput into a string

                if ("1".equalsIgnoreCase(userInput)) {
                   break; // Exit the while loop if the user enters '1'
                }

                // Send data to the server
                outputStream.write(userInput.getBytes());
                outputStream.flush(); // clear the stream

                // Receive and print the response from the server
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                String response = new String(buffer, 0, bytesRead);
                System.out.println("Received from server: " + response);
            }
            sock.close(); // close socket
            scan.close();
        } catch (IOException ioe) {
           System.err.println(ioe);
        }
    }
}