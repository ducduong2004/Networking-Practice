package lab2;

import java.io.*;

public class CMD {

    String command;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    final File defaultDirectory = new File("C:\\Users\\admin\\Desktop\\Study\\Môn học\\ltm\\testingZone");
    File currentDirectory;
    boolean isrunning = true;
    

    public CMD() {
        currentDirectory = defaultDirectory;
        run();
    }

    public static void main(String[] args) {
        new CMD();
    }

    public void run() {
        while(isrunning) {
            showPromt();
            input();
            response();
        }
    }

    public void showPromt() {
        System.out.print(currentDirectory + " > :");
    }

    public void input() {
        try {
            command = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void response() {
        command = command.toLowerCase();
        String params[] = command.split(" ");
        switch (params[0]) {
            case "exit":{
                isrunning = false;
                break;
            }
                
            case "cd": 
                changeDirectory(params);
                break;

            case "dir":
                listFiles();
                break;

            case "delete":
                delete(params[1]);
                break;


            default:
                System.out.println("command not supported");
                break;
        }
    }

    public void changeDirectory(String[] params) {
        if(params.length == 1) currentDirectory = defaultDirectory; 
        else if(params[1].equals("..")){
            File newDir = new File(currentDirectory.getParent());;
            if(newDir.exists()) {
                currentDirectory = newDir;
                return;
            }
        } else {
            File newDir = new File(currentDirectory,params[1]);
            if(newDir.exists() && newDir.isDirectory()) {
                currentDirectory = newDir;
                return;
            }
        }
        System.out.println("Directory not found");
    }

    public void listFiles() {
        File file = new File(currentDirectory.getAbsolutePath());
        File[] files = file.listFiles();
        if(files == null) {
            return;
        }
        for (File f : files) {
            System.out.println(f.getName());
        }
    }

    public void delete(String params) {
        File file = new File(currentDirectory,params);
        System.out.println(file.getAbsolutePath());
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            if(files == null) return;

            for(File f : files) {
                delete(f.getAbsolutePath());
            }

        }

        file.delete();

    }

}