package multithread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class server_thread implements Runnable{
    private Socket socket;
    private server_main serverMain;
    public server_thread(Socket socket,server_main serverMain){
        this.socket=socket;
        this.serverMain=serverMain;
    }

    @Override
    public void run(){
        try {
             int client_number  = serverMain.getClientnumber();
            System.out.println("Client"+ client_number+ " has connected ");
            // Buffers :
            // Read(input)
            BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Write(output)
            PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            // Send welcome to the client
            out_socket.println("Welcome !  You are client "+client_number+". What is your name ?");
            // Put message that come from client in variable
            String message;
            message = in_socket.readLine();
            // Display client's message in the console
            System.out.println("Client says : "+ message);
            // Close the Connection
            socket.close();
            System.out.println("Client "+ client_number+"has disconnected.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
