package multithread;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//Client
public class client
{
   public client() throws Exception
   {
       System.out.println("Client");
       // Enter Port Number
       int port_number = 2820;
       // Enter Local Host
       String localhost="127.0.0.1";
       // Start Connection
       Socket socket = new Socket(localhost,port_number);
       System.out.println(" Successful connection to the server ");
       // Buffers :
         // Read(input)
         BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         // Write(output)
         PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
           Scanner keyboard = new Scanner(System.in);
           String message = in_socket.readLine();
       System.out.println(" server says : "+ message);
        message = keyboard.nextLine();
        out_socket.println(message);
       socket.close();
       System.out.println("Socket is closed on Client.");
   }
   // Main
    public static void main(String[] args)
    {
        try
        {
            new client();
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }
}
