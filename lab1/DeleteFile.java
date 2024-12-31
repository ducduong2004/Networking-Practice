package lab1;

import java.io.*;

public class DeleteFile {
    
    public static void main(String[] args) {
        String path = "C:\\Users\\admin\\Desktop\\Study\\Môn học\\ltm\\testingZone";
        //deleteFilesOnly(path);
        deleteAll(path);
    }

    //function delete all files and directories. return true if all files are deleted if not return false
    public static boolean deleteAll(String path) {
        File root = new File(path);
        if(root.isDirectory()) {
            File[] files = root.listFiles();
            //check if the file exists if it does not return true
            if(files == null) return true;

            //delete the file
            for(File file : files) {
                deleteAll(file.getAbsolutePath());
            }
        }
        //delete the directory if it is empty after deleting all files
        return root.delete();

    }

    public static boolean deleteFilesOnly(String path) {
        File root = new File(path);
        if(root.isDirectory()) {
            File[] files = root.listFiles();
            //check if the file exists if it does not return true
            if(files == null) return true; 
            //delete the file
            for(File file : files) {
                if(file.isFile()) file.delete();
                else {
                    deleteFilesOnly(file.getAbsolutePath());
                }
            }
        }


        return true;

    }

}
