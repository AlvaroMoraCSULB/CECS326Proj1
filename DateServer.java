import java.net.*;
import java.io.*;
import java.util.Random;

public class DateServer
{
    public static void main(String[] args) {
        String[]quotes = { "The only way to do great work is to love what you do. - Steve Jobs",
                "In three words I can sum up everything I've learned about life: it goes on. - Robert Frost",
                "Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston Churchill",
                "The only thing necessary for the triumph of evil is for good men to do nothing. - Edmund Burke",
                "The best time to plant a tree was 20 years ago. The second best time is now. - Chinese Proverb"};

        try {
            ServerSocket sock = new ServerSocket(6017);

            /* now listen for connections */
            while (true) {
                Socket client = sock.accept();

                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                /* write the Date to the socket */
                Random rand = new Random();
                String quote = quotes[rand.nextInt(quotes.length)];
                pout.println(quote);
                /* close the socket and resume */
                /* listening for connections */
                client.close();
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
