package project_chat_simple_TCP;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
//Client
public class client
{
   public client() throws Exception
   {
       System.out.println("Client");
       // Enter Port Number
       int port_number = 2823;
       // Enter Local Host
       String localhost="localhost";
       // Start Connection
       Socket socket = new Socket(localhost,port_number);
       System.out.println(" Successful connection to the server ");
       // Buffers :
         // Read(input)
         BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         // Write(output)
         PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
       // Put message that come from Server in variable
       String message = in_socket.readLine();
       // Display server's message in the console
       System.out.println("Server says : "+ message);
       // Send Thanks to the Server
       out_socket.println("Thanks ! ");
       // Close the Connection
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
