package lab7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerThread extends Thread {
    Socket socket;
    DataInputStream netIn;
    DataOutputStream netOut;
    public ServerThread(Socket socket) {
        this.socket = socket;
        try {
            netIn = new DataInputStream(socket.getInputStream());
            netOut = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
    }

    public void run() {
        while(true) {
            
            try {
                
                String line = netIn.readUTF();

                if(line.equalsIgnoreCase("exit")) {
                    break;
                } 

                StringTokenizer stringTokenizer = new StringTokenizer(line, "\t");

                String command = stringTokenizer.nextToken();
                String destination = stringTokenizer.nextToken();
                destination = "C:\\Users\\admin\\Desktop\\Study\\Môn học\\ltm\\testingZone\\copy.pdf";
                
                if(command.equals("upload")) {
                    long size = netIn.readLong();
                    File file = new File(destination);
                    FileOutputStream fos = new FileOutputStream(file);
                    boolean uploadSuccess = dataCopy(netIn, fos, size);
                    if(uploadSuccess) {
                        netOut.writeUTF("Upload success");
                    } else {
                        netOut.writeUTF("Upload failed");
                    }
                }
                


            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean dataCopy(InputStream fis, FileOutputStream fos, long size) throws IOException {
        byte[] buffer = new byte[1024*100];
        int read;
        int remain = (int) size;
        int req;
        while(remain > 0) {
            req = remain < buffer.length ? remain : buffer.length;
            read = fis.read(buffer, 0, req);
            if(read == -1) return false;
            fos.write(buffer, 0, read);
            remain -= read;
        }
        return true;
    }

}
