package lab4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class UnpackFile {
    
    public static void unPack(String packedFile, String extractFile, String destFile) {
        File file = new File(packedFile);
        
        if(!file.exists()) return;
        if(!file.isFile()) return;
        

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
        int count = raf.readInt(); // đọc số lượng file
        System.out.println(count);
        for(int i = 0; i < count; i++) {
            long pos = raf.readLong();
            long size = raf.readLong();
            String nameString = raf.readUTF();
            
            System.out.println("File: " + nameString + ", Position: " + pos + ", Size: " + size);

            if(nameString.equals(extractFile)) {
                raf.seek(pos);

                FileOutputStream fos = new FileOutputStream(destFile);
                
                byte[] buff = new byte[100*1024];
                int readByte;
                int reqBytes;
                int remain = (int) size;
                while(remain > 0) {
                    reqBytes = remain < buff.length ? remain : buff.length;
                    readByte = raf.read(buff, 0, reqBytes);
                    if(readByte==-1) break;
                    fos.write(buff, 0, readByte);
                    remain -= readByte;
                }
                fos.close();
                break; // File founded!
            }
        }
        raf.close();
            


        } catch (IOException e) {
            e.printStackTrace();
        }
        
    
    
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\admin\\Desktop\\Study\\Môn học\\ltm\\testingZone\\packed.abc";
        unPack(path,"cc.pdf", "C:\\Users\\admin\\Desktop\\Study\\Môn học\\ltm\\testingZone\\unpacked.pdf");
    }

}
