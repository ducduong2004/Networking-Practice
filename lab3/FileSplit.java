package lab3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class FileSplit {

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\admin\\Desktop\\Study\\Môn học\\ltm\\testingZone\\spliter\\Nguyễn Thị Vân Anh-22051275.pdf";
        split(path, 100*1024);
        
    }

    
    public static void split(String source, int pSize) throws IOException {
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos;
        int fileNum = 0;
        boolean hasMoreData = false;
        do {
            String dest = generateExtension(source, fileNum++);
            fos = new FileOutputStream(dest);
            
            hasMoreData = transfer(fis,fos,pSize);

            fos.close();

        } while (hasMoreData);

        

    }

    public static boolean transfer(FileInputStream fis, FileOutputStream fos, int pSize ) throws IOException {
        byte[] buff = new byte[100*1024];
        int remain = pSize;
        int readByte;
        int reqByte;
        while(remain > 0) {
            reqByte = remain < buff.length ? remain : buff.length;

            readByte = fis.read(buff, 0, reqByte);
            if(readByte == -1) {
                return false;
            }

            fos.write(buff,0,readByte);

            remain -= readByte;

        }


        return true;
    }

    private static String generateExtension(String path, int index) {
        if(index < 10) return path + ". 00" + index;
        if(index < 100) return path + ". 0" + index;
        return path + ". " + index;
    }

}
