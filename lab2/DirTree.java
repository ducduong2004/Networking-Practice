package lab2;

import java.io.File;

public class DirTree {
    
    public static void main(String[] args) {
        dirTree("C:\\Users\\admin\\Desktop\\Study\\Môn học\\ltm\\testingZone",0);
    }
        
    public static void dirTree(String folder, int level) {
        File dir = new File(folder);
        if(!dir.exists()) return;

        if(!dir.isDirectory()) return;

        File[] files = dir.listFiles();

        if(files == null) return;

        for(File file : files) {

            
            
            if(file.isDirectory()) {
                for(int i = 0; i < level; i++) System.out.print("|\t");
                System.out.println("+-" + file.getName().toUpperCase() + ": " + getTotalSize(file,0));
                dirTree(file.getAbsolutePath(), level + 1);
            }
            
        }

        

        for(File file : files) {
            
            if(file.isFile()) {
                for(int i = 0; i < level; i++) System.out.print("|\t");
                System.out.println("+-" + file.getName().toLowerCase());
            }
            
        }
        


    }

    public static long getTotalSize(File file, long capacity) {
        
        File[] files = file.listFiles();

        if(files == null) {
            return 0;
        }

        for(File f : files) {
            if(f.isFile()) 
                capacity += f.length();
            if(f.isDirectory())
                capacity += getTotalSize(f.getAbsoluteFile(), capacity);
        }

        return capacity;

    }
}
