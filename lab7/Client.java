package lab7;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

public class Client {
    
    public static void main(String[] args) throws Exception {
        DataInputStream netIn;
        DataOutputStream netOut;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //
        
        Socket socket = new Socket("127.0.0.1", 2000);
        netIn = new DataInputStream(socket.getInputStream());
        netOut = new DataOutputStream(socket.getOutputStream());

        String line;

        while(true) {
            System.out.println("connected");
            line = br.readLine();
            netOut.writeUTF(line);
            netOut.flush();
            if(line.equalsIgnoreCase("exit")) {
                break;
            }

            StringTokenizer stringTokenizer = new StringTokenizer(line,"\t");
            String command = stringTokenizer.nextToken();
            String source = stringTokenizer.nextToken();
            source = "C:\\Users\\admin\\Desktop\\Study\\Môn học\\ltm\\testingZone\\unpacked.pdf";
            String destination = stringTokenizer.nextToken();


            if(command.equalsIgnoreCase("upload")) {
                File uploadFile = new File(source);

                

                if(!uploadFile.exists()) {
                    System.out.println("file not found");
                    continue;
                }

                netOut.writeLong(uploadFile.length());
                FileInputStream fis = new FileInputStream(uploadFile);
                byte[] buff = new byte[1024*100];
                int size = (int) uploadFile.length();
                int reqByte;
                int readByte;
                int remain = size;

                while(remain > 0) {
                    reqByte = remain < buff.length ? remain : buff.length;
                    readByte = fis.read(buff, 0, reqByte);
                    if(readByte == -1) {
                        fis.close();
                        break;
                    }
                        netOut.write(buff, 0, readByte);
                    remain -= readByte;
                }
                netOut.flush();
                fis.close();

                
                System.out.println(netIn.readUTF());
            }


        }

        socket.close();

    }
}
