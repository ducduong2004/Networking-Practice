package lab5;

import java.io.IOException;
import java.io.RandomAccessFile;

public class StudentRAF {
    
    public static final int HEADER_SIZE = 4; // 4 bytes
    public int count = 0; // number of students
    public RandomAccessFile raf;

    public StudentRAF () throws IOException {
        String path = "C:\\Users\\admin\\Desktop\\Study\\Môn học\\ltm\\testingZone\\Student\\StudentData.txt";
        
        raf = new RandomAccessFile(path, "rw");
        if(raf.length() > 0)
        {        
            raf.seek(0);
            count = raf.readInt();
        }
        
    }

    public void addStudent(Student st) {
        try {
            raf.seek(0);
            raf.writeInt(++count);
            raf.seek(HEADER_SIZE);
            raf.writeLong(getSize(st));
            raf.seek(raf.length());
            
            int id = st.getId();
            String name = st.getName();
            int bYear = st.getBYear();
            double grade = st.getGrade();

            raf.writeInt(id);
            raf.writeUTF(name);
            raf.writeInt(bYear);
            raf.writeDouble(grade);

        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    public long getSize(Student st) {
        return (long) (4 + 4 + 8 + st.getName().getBytes().length);
    }

    public Student getStudent(int id) {
        // TO DO
        return null;
    }

    public void updateStudent(Student st) {
        // TO DO
    }

    public Student findById(int id) {
        // TODO
        return null;
    }


    public static void main(String[] args) throws Exception {
        StudentRAF student = new StudentRAF();
        Student st1 = new Student(1, "John Doe", 1995, 8.5);
        student.addStudent(st1);

    }
}
