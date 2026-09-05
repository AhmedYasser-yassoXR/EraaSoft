package streamTask;

import java.util.Arrays;
import java.util.List;

public class Student {



    String name;
    String department;
    double grade;

    // Constructor + Getters
    Student(String name, String department, double grade) {
        this.name = name;
        this.department = department;
        this.grade = grade;
    }
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", grade=" + grade +
                '}';
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getGrade() { return grade; }
}



