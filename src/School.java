import java.util.ArrayList;
import java.util.List;

/**
 * Created by pooja.sharma on 30/10/17.
 */

interface StudentInterface {
    boolean test(Student s);
}


public class School {

   // volatile int  var1 = 10;
    public static List<Student> getTopStudent(Iterable<Student> students, Criteria stuIntf) {
        List<Student> topStudents = new ArrayList<Student>();
        for(Student i: students){
            if(stuIntf.test(i)) {
                topStudents.add(i);
            }
        }

        return topStudents;
    }

    public static <E> Criteria<E> inverse(Criteria<E> crit) {
        return (e)-> !crit.test(e);
    }


    public static <E> Criteria<E> AndCritera(Criteria<E> crit1, Criteria<E> crit2) {
        return (e)-> crit1.test(e) && crit2.test(e);
    }

    public static <E> Criteria<E> OrCritera(Criteria<E> crit1, Criteria<E> crit2) {
        return (e)-> crit1.test(e) || crit2.test(e);
    }

    public static void showAll(List<Student> students) {
        for(Student s:students) {
            s.printOutPut();
        }

    }
//    public static void main(String[] ars) {
//
//        List<Student> students = new ArrayList<Student>();
//        students.add(new Student("Student1", 66, "Math", "Physics"));
//        students.add(new Student("Student2", 99, "Math", "Physics", "Subject1", "Subject1"));
//        students.add(new Student("Student3", 100, "Math", "Physics", "Random"));
//        students.add(new Student("Student4", 67, "Math", "Physics", "Subject1"));
//        students.add(new Student("Student5", 68, "Math", "Physics"));
//
//        students.sort(Student.getComparator());
//
//        students.get(0).printOutPut();
//        students.get(1).printOutPut();
//        students.get(2).printOutPut();
//        students.get(3).printOutPut();
//
//
//        //Return student more than thresold
//        System.out.println("Top students:");
//        //List<Student> topStudents = getTopStudent(students, 68);
//
//        Criteria<Student> criteria1 = (s) -> s.getStudentGrade()>68; //More than 68 grades
//        Criteria<Student> criteria2 = (s) -> s.getSubjectCount()>3; //More than 3 subjects
//
//        List<Student> topStudents1 = getTopStudent(students, AndCritera(criteria1, criteria2));
//        List<Student> topStudents2 = getTopStudent(students, OrCritera(criteria1, criteria2));
//
//        System.out.println("And students:");
//        showAll(topStudents1);
//
//        System.out.println("Or students:");
//        showAll(topStudents2);
//    }
}
