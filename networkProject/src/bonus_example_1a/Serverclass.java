package bonus_example_1a;

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;
public class Serverclass
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket myserverSocket = new ServerSocket(5056);
        // getting client request
        while (true)
        // running infinite loop
        {
            Socket mynewSocket = null;

            try
            {
                // mynewSocket object to receive incoming client requests
                mynewSocket = myserverSocket.accept();

                System.out.println("A new connection identified : " + mynewSocket);
                // obtaining input and out streams
                DataInputStream ournewDataInputstream = new DataInputStream(mynewSocket.getInputStream());
                DataOutputStream ournewDataOutputstream = new DataOutputStream(mynewSocket.getOutputStream());

                System.out.println("Thread assigned");

                Thread myThread = new ClientHandler(mynewSocket, ournewDataInputstream, ournewDataOutputstream);
                // starting
                myThread.start();

            }
            catch (Exception e){
                mynewSocket.close();
                e.printStackTrace();
            }
        }
    }
}