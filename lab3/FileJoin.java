package lab3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileJoin {
    
    public static void joinFile(String path) throws IOException {
        String dest = path.substring(0,path.lastIndexOf("."));
        FileOutputStream fos = new FileOutputStream(dest);
        int fileNum = 0;

        while(true) {
            String source = dest + generateExt(fileNum++);
            File file = new File(source);

            if(!file.exists()) break;

            FileInputStream in = new FileInputStream(file);

            Datacopy(in, fos, (int)file.length());
            in.close();
            
        }

        fos.close();
        
    }

    public static void Datacopy(FileInputStream fis, FileOutputStream fos, int size) throws IOException {
        byte[] buff = new byte[100*1024];
        int remain = size;
        int reqByte;
        int readByte;
        while(remain > 0) {
            reqByte = remain < buff.length ? remain : buff.length;
            readByte = fis.read(buff, 0, reqByte);
            if(readByte == -1 ) return; // da het du lieu
            fos.write(buff, 0, readByte);
            remain -= readByte;
        }
    }

    public static String generateExt(int fileNum) throws IOException {
        if(fileNum < 10 ) return ". 00" + fileNum;
        if( fileNum < 100 ) return ". 0" + fileNum;
        else return ". " + fileNum;
    }

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\admin\\Desktop\\Study\\Môn học\\ltm\\testingZone\\joinme\\Nguyễn Thị Vân Anh-22051275.pdf.002";
        joinFile(path);
    }

}
