package project_chat_simple_TCP;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
// Server
public class server
{
    public server() throws IOException
    {
        System.out.println("Server");
        // Enter Port Number
        int port_number = 2823;
        ServerSocket server_socket = new ServerSocket(port_number);
        System.out.println("Port "+port_number+" is now open ^_^");
        // OPEN the Connection
        Socket socket=server_socket.accept();
        // Print client IP address
        System.out.println("Client "+socket.getInetAddress()+" has connected successfully ^_~");
        // Buffers :
         // Read(input)
         BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         // Write(output)
         PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
        // Send welcome to the client
        out_socket.println("Welcome !");
        // Put message that come from client in variable
        String message =in_socket.readLine();
        // Display client's message in the console
        System.out.println("Client says : "+ message);
        // Close the Connection
        socket.close();
        System.out.println("Socket is closed on Server.");
    }
    // Main
    public static void main(String[] args)
    {
      try
      {
        new server();
      }
      catch (Exception e)
      {
      e.printStackTrace();
      }
    }
}
