package UDP_ADvance2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UnSyncChatClient {

    public static void main(String args[])
            throws IOException, InterruptedException {

        // create DatagramSocket and get ip
        DatagramSocket cs = new DatagramSocket(2020);
        InetAddress ip = InetAddress.getLocalHost();

        System.out.println("Running UnSyncChatClient.java");

        System.out.println("Client is Up....");

        // create a sender thread with a nested
        // runnable class definition
        Thread csend;
        csend = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Scanner sc = new Scanner(System.in);

                    while (true) {
                        synchronized (this) {

                            System.out.print(
                                    "Enter the equation in the format:");
                            System.out.println(
                                    "'operand1 operator operand2'");

                            byte[] sd = new byte[1000];

                            // scan new message to send
                            sd = sc.nextLine().getBytes();

                            // create datagram packet
                            // for new message
                            DatagramPacket sp = new DatagramPacket(sd, sd.length, ip,2023 );

                            // send the new packet
                            cs.send(sp);
                            String msg = new String(sd);

                            if (msg.equals("bye")) {
                                System.out.println("client "
                                        + "exiting... ");
                                break;
                            }
                            System.out.println("Waiting for "
                                    + "server response...");
                            System.out.println("Client says: "
                                    + msg);
                            // exit condition

                        }
                    }
                } catch (IOException e) {
                    System.out.println("Exception occurred");
                }
            }
        });

        // create a receiver thread with a nested
        // runnable class definition
        Thread receive;
        receive = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    while (true) {
                        synchronized (this) {

                            byte[] rd = new byte[1000];

                            // receive new message
                            DatagramPacket sp1
                                    = new DatagramPacket(rd,
                                    rd.length);
                            cs.receive(sp1);

                            // convert byte data to string
                            String msg = (new String(rd)).trim();
                            System.out.println(

                                    "Answer = "
                                            + msg);
                            // exit condition
                            if (msg.equals("bye")) {
                                System.out.println("Server"
                                        + " Stopped....");
                                break;
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Exception occurred");
                }
            }
        });

        csend.start();
        receive.start();
        csend.join();
        receive.join();
    }
}
