package lab4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PackFile {
    
    public static void pack(String folder, String packedFile) throws IOException {

        File file = new File(folder);

        if(!file.exists()) return;
        if(!file.isDirectory()) return;

        File[] files = file.listFiles();
        if(files == null) return;

        File dest = new File(packedFile);
        RandomAccessFile raf = new RandomAccessFile(dest, "rw");
        long[] pos = new long[files.length];
        int count = 0;

        for(File f : files) {
            if(f.isFile())
                count++;
          
        }

        raf.writeInt(count); // write the count of files here
        
        for(int i=0; i<files.length; i++){
            File f = files[i];
            if(!f.isFile()) continue; // skip
            pos[i] = raf.getFilePointer(); // later will use to write the size here
            raf.writeLong(0);
            raf.writeLong(f.length());
            raf.writeUTF(f.getName());
        }

        for(int i=0; i<files.length; i++) {
            File f = files[i];

            if(!f.isFile()) continue;

            FileInputStream fis = new FileInputStream(f);
            
            byte[] buff = new byte[100*1024];
            int remain = (int) f.length();

            long position = raf.getFilePointer();
            raf.seek(pos[i]);
            raf.writeLong(position);
            raf.seek(raf.length());
            System.out.println(f.getName() + ": " + position);

            while(remain > 0){
                int reqByte =(int) remain < buff.length ? remain : buff.length;
                int readByte = fis.read(buff, 0, reqByte);
                if(readByte == -1) break;
                raf.write(buff, 0, readByte);
                remain -= readByte;
            }
            fis.close();

        }

        raf.close();

    }


    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\admin\\Desktop\\Study\\Môn học\\ltm\\testingZone\\packunpack";
        pack(path, "C:\\Users\\admin\\Desktop\\Study\\Môn học\\ltm\\testingZone\\packed.abc");
    }


}
