import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SuperIterable<E> implements Iterable<E> {

    private Iterable<E> self;

    private SuperIterable(Iterable<E> target) {
        self = target;
    }

    public static <E> SuperIterable<E> of(Iterable<E> target) {
        return new SuperIterable<>(target);
    }

    public SuperIterable<E> filter(Predicate<E> pred) {
        List<E> rv = new ArrayList<>();
        self.forEach(e -> {
            if (pred.test(e)) {
                rv.add(e);
            }
        });
        return new SuperIterable<>(rv);
    }

   public SuperIterable<E> distinct() {
       List<E> listDistinctInts = new ArrayList<>();
       self.forEach(e-> {
           if(!listDistinctInts.contains(e)){
               listDistinctInts.add(e);
           }
       }
       );

       return new SuperIterable<>(listDistinctInts);
   }

    public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
        List<F> rv = new ArrayList<>();
        self.forEach(e -> op.apply(e).forEach(f -> rv.add(f)));
        return new SuperIterable<>(rv);
    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }

    public static void main(String[] args) throws IOException {
        SuperIterable<Student> school = SuperIterable.of(Arrays.asList(
                Student.ofNameGradeCourses("Fred", 68, "Maths", "Physics"),
                Student.ofNameGradeCourses("Jim", 59, "Art", "History", "Journalism"),
                Student.ofNameGradeCourses("Sheila", 88, "Maths", "Physics",
                        "Astrophysics", "Quantum Physics"),
                Student.ofNameGradeCourses("Mary", 72, "Electronics")
        ));

        school.forEach(s-> System.out.println("> " + s));
        System.out.println("----------------------------------");
        school
                .filter(s -> s.getStudentGrade() > 60)
                .flatMap(s -> SuperIterable.of(s.getCourses()))
                .forEach(s-> System.out.println("> " + s));



        school.forEach(s-> SuperIterable.of(s.getCourses())
                .distinct()
                .forEach(p-> System.out.println("> " + p)));
        //school.distinct(sc.asList("Maths", "X1", "X2", "X1", "X3"));


        String fileName = "pg42671.txt";
        Stream<String> lines= Files.lines(Paths.get(fileName));

        Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" +")));

        Map<String, Long> map = words.collect(Collectors.groupingBy(s -> (s.toString()), Collectors.counting()));

//        Comparator<Map.Entry> countComparator =
//                (o1, o2) ->  (o2.getValue().compareTo(o1.getValue());

        map.entrySet().stream()
                .sorted((o1, o2)-> o2.getValue().compareTo(o1.getValue()))
                .limit(25)
                .forEach(e -> System.out.println(e.getKey() + " === " + e.getValue()));
       // words.forEach((s)-> System.out.println(s));
        //lines.flatMap();


    }
}