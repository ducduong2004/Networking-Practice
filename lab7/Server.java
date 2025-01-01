package lab7;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(2000);
    while (true) {
        Socket socket = serverSocket.accept();
        ServerThread serverThread = new ServerThread(socket);
        serverThread.start();
    }

    }    
}
