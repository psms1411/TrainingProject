import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

/**
 * Created by pooja.sharma on 30/10/17.
 */
public final class Student {
    private String name;
    private int grade;
    List<String> courses = new ArrayList<String>();
    //int noOfCourses = 0;
//    Student(String name, int grade, String... courses) {
////        if(name==null) {
////            System.out.println("Name should not be null");
////            return;
////        }
//        this.name = name;
//        this.grade = grade;
//        this.courses = Arrays.asList(courses);
//        //this.courses.add(courses);
//        //this.courses = new ArrayList<>();
//    }

    public static Student ofNameGradeCourses(String name, int averageGrade, String... courses) {
        Student self = new Student();
        self.name = name;
        self.grade = averageGrade;
        self.courses = Arrays.asList(courses);
        return self;
    }

    public int getStudentGrade() {
        return this.grade;
    }

    public void printOutPut() {
        System.out.println("Name: "+this.name+" Grade: "+this.grade+"%" + this.courses);

    }

    public List<String> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public int getSubjectCount() {
        return this.courses.size();
    }

    private static StudentComparator studentComparatorVar = new StudentComparator();

    public static StudentComparator getComparator() {
        return studentComparatorVar;
    }

     private static class StudentComparator implements Comparator<Student> {
         @Override
         public int compare(Student o1, Student o2) {
             return o1.getStudentGrade()-o2.getStudentGrade();
         }
     }

   public static StudentInterface getTopStudent(int threshold) {
       return (s) -> s.getStudentGrade()>threshold;   //***It maps to interface exactly the same way***/
   }


}
