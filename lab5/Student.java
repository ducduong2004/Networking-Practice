package lab5;

public class Student {
    int id;
    String name;
    int bYear;
    double grade;

    // Constructor
    Student(int id, String name, int bYear, double grade) {
        this.id = id;
        this.name = name;
        this.bYear = bYear;
        this.grade = grade;
    }

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for bYear
    public int getBYear() {
        return bYear;
    }

    public void setBYear(int bYear) {
        this.bYear = bYear;
    }

    // Getter and Setter for grade
    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
