package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Sender {
    public Sender() throws Exception{
        DatagramSocket socket= new DatagramSocket();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter your message " );
        String message = keyboard.nextLine();
        byte[] buffer = message.getBytes();
        int port_number=2020;
        DatagramPacket packet= new DatagramPacket(buffer, buffer.length, InetAddress.getByName("127.0.0.1"), port_number );
    socket.send(packet);
        System.out.println("sent"+message);
        buffer =new byte[1500];
        packet=new DatagramPacket(buffer,buffer.length);
        socket.receive(packet);
        message =new String(buffer).trim();
        System.out.println("Received"+ message);
    }
    public static void main(String[] args) {
        try {
            new Sender();
        }catch (Exception e){
             e.printStackTrace();
        }
    }
}
