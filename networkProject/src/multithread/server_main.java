package multithread;
import java.net.ServerSocket;
import java.net.Socket;

public class server_main {
    public server_main() throws Exception{
        System.out.println("Server");
        // Enter Port Number
        int port_number = 2820;
        ServerSocket server_socket = new ServerSocket(port_number);
        System.out.println("Port "+port_number+" is now open ^_^");
        // Loop : waiting connection
        while (true){
            // OPEN the Connection
            Socket socket=server_socket.accept();
            server_thread st = new server_thread(socket,this);
            Thread thread = new Thread(st);
            thread.start();
        }
    }
    private int clientnumber =1;
    public int getClientnumber(){
        return clientnumber++;
    }
    public static void main(String[] args) {
        try {
            new server_main();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
