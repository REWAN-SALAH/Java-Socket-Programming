package UDP_ADvance2;

import java.net.*;
import java.io.*;
import java.util.*;

public class UnSyncChatServer {

    public static void main(String args[])
            throws IOException, InterruptedException
    {

        // Create DatagramSocket and get ip
        DatagramSocket ss = new DatagramSocket(2023);
        InetAddress ip = InetAddress.getLocalHost();

        System.out.println("Running UnSyncChatServer.java");

        System.out.println("Server is Up....");

        // Create a sender thread
        // with a nested runnable class definition
        Thread ssend;
        ssend = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    Scanner sc = new Scanner(System.in);
                    while (true) {
                        synchronized (this)
                        {
                            byte[] sd = new byte[1000];

                            // scan new message to send
                            sd = sc.nextLine().getBytes();
                            DatagramPacket sp
                                    = new DatagramPacket(
                                    sd,
                                    sd.length,
                                    ip,
                                    2020);

                            // send the new packet
                            ss.send(sp);

                            String msg = new String(sd);
                            System.out.println("Server says: "
                                    + msg);

                            // exit condition
                            if ((msg).equals("bye")) {
                                System.out.println("Server"
                                        + " exiting... ");
                                break;
                            }
                            System.out.println("Waiting for"
                                    + " client response... ");
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("Exception occurred");
                }
            }
        });

        Thread sreceive;
        sreceive = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    while (true) {
                        synchronized (this)
                        {

                            byte[] rd = new byte[1000];

                            // Receive new message
                            DatagramPacket sp1
                                    = new DatagramPacket(
                                    rd,
                                    rd.length);
                            ss.receive(sp1);

                            // Convert byte data to string
                            String msg
                                    = (new String(rd)).trim();
                            System.out.println("Client ("
                                    + sp1.getPort()
                                    + "):"
                                    + " "
                                    + msg);

                            // Exit condition
                            if (msg.equals("bye")) {
                                System.out.println("Client"
                                        + " connection closed.");
                                break;
                            }
                            int result;

                            // Use StringTokenizer to break the
                            // equation into operand and operation
                            StringTokenizer st = new StringTokenizer(msg);

                            int oprnd1 = Integer.parseInt(st.nextToken());
                            String operation = st.nextToken();
                            int oprnd2 = Integer.parseInt(st.nextToken());

                            // Perform the required operation
                            if (operation.equals("+"))
                                result = oprnd1 + oprnd2;

                            else if (operation.equals("-"))
                                result = oprnd1 - oprnd2;

                            else if (operation.equals("*"))
                                result = oprnd1 * oprnd2;

                            else
                                result = oprnd1 / oprnd2;

                            System.out.println("Sending the result...");
                            String res = Integer.toString(result);

                            // Clearing the buffer after every message
                            rd = res.getBytes();

                            // Getting the port of client
                            int port = ss.getPort();

                            sp1 = new DatagramPacket(rd, rd.length, InetAddress.getLocalHost(),
                                    port);
                            ss.send(sp1);
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("Exception occurred");
                }
            }
        });

        ssend.start();
        sreceive.start();

        ssend.join();
        sreceive.join();
    }
}
