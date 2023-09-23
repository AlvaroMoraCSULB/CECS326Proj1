import java.io.*;
import java.net.*;
public class QuoteClient {
        public static void main(String[] args) {
            try {
                Socket sock = new Socket("localhost", 6017);

                BufferedReader input = new BufferedReader(new InputStreamReader(sock.getInputStream()));

                String quote;
                while ((quote = input.readLine()) != null) {
                    System.out.println("Quote of the Day: " + quote);
                    break; // break is used so only one quote is displayed
                }

                sock.close();
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
    }

