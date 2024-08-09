import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class Student implements Comparable<Student>{
    private int id;
    private String name;
    private int age;
    private int grade;
    public Student(){

    }
    public Student(int id, String name, int age, int grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public int getGrade() {
        return grade;
    }

    @Override
    public int compareTo(Student obj){
        return this.getAge()-obj.getAge();
    }

    @Override
    public String toString() {
        return "Student [id="+id + " name=" + name + ", age=" + age + ", grade=" + grade + "]";
    }
}

public class Main{
    public static void main(String args[]){
        Student student1 = new Student(1, "Shubham",23,60);
        Student student2 = new Student(2, "Shivam",21,62);
        Student student3 = new Student(3, "Anil",24,52);
        Student student4 = new Student(4, "Raushan",22,54);
        Student student5 = new Student(5, "Nikhil",23,68);
        Student student6 = new Student(6, "Rajan",26,61);
        Student student7 = new Student(7, "Amar",22,82);
        Student student8 = new Student(8, "Naveen",25,58);
        Student student9 = new Student(9, "Madan",26,72);
        Student student10 = new Student(10, "Tarun",23,54);


        List<Student> students = new ArrayList<>();
        students.addAll(Arrays.asList(student1, student2, student3, student4, student5, student6, student7, student8, student9, student10));
    
        Solution solution = new Solution();

        // List<Student> sortedBasedOnAge = solution.sortBasedOnAge(students);
        // sortedBasedOnAge.forEach(student->System.out.println(student));

        // List<Student> filteredStudentsBasedOnGrade = solution.filterStudentsByGrade(students,60);
        // filteredStudentsBasedOnGrade.forEach(student->System.out.println(student));

        // List<String> topStudentNames = solution.topNamesBasedOnGrade(students,3);
        // topStudentNames.forEach(studentName->System.out.println(studentName));

        // List<String> topStudentNames = solution.topNamesBasedOnGradeSeenSolution(students,3);
        // topStudentNames.forEach(studentName->System.out.println(studentName));

        // Student oldest = solution.oldestStudent(students);
        // System.out.println(oldest);

        // Student oldest = solution.oldestStudenSeenSolutiont(students);
        // System.out.println(oldest);

        // List<Student> oldest = solution.multipleOldestStudenSeenSolutiont(students);
        // oldest.forEach(System.out::println);

        // Map<Object, List<Student>> groupedByAge = solution.groupByAge(students);
        // for( Entry<Object, List<Student>> entry: groupedByAge.entrySet()){
        //     System.out.println(entry.getKey());
        //     entry.getValue().forEach(student->System.out.println(student));
        // }

        double averageGrade = solution.findAverageGradeSeenSolution(students);
        System.out.println("The average student grade is: "+averageGrade);

        // boolean hasAnyStudentFailed = solution.hasAnyStudentFailed(students, 55);
        // System.out.println(hasAnyStudentFailed);

        // Map<Integer, Student> studentsMappedWithTheirId = solution.collectIntoMap(students);
        // for(Entry<Integer, Student> entry: studentsMappedWithTheirId.entrySet()){
        //     System.out.println("id: "+entry.getKey()+", student: "+entry.getValue());
        // }

        // String youngestStudentName = solution.findYoungestStudentName(students);
        // System.out.println(youngestStudentName);

        // String allNamesJoined = solution.joinNames(students);
        // System.out.println(allNamesJoined);
        
    }
}

class Solution{
    List<Student> sortBasedOnAge(List<Student> students){
        // 1. LAMBDA DEFINED COMPARATOR WITH STREAM SORTED
        // return students.stream()
        //     .sorted((st1, st2)->st1.getAge()-st2.getAge())
        //     .collect(Collectors.toList());

        // 2. LAMDA DEFINED COMPARATOR WITH COLLECTIONS SORT
        // Collections.sort(students, Collections.reverseOrder((st1, st2)->st1.getAge()-st2.getAge()));
        // return students;

        Collections.sort(students, (st1, st2)->st1.getAge()-st2.getAge());
        return students;

        // 3. YOU CAN HAVE A SEPERATE COMPARATOR CLASS THEN INSTANCE AND USE IN ABOVE TWO METHODS.

        // 4. OR IMPLEMENT COMPARABLE IN STUDENTS CLASS, THEN NO NEED OF COMPARATOR, JUST COLLECTIONS.SORT
            // we have implemented comparable just for this

        // Collections.sort(students, Collections.reverseOrder()); // sorting in reverese order of the actual comparator, so decreasing.
        // return students;
    }


    List<Student> filterStudentsByGrade(List<Student> students, int filterFrade){
        // 1. STREAM FILTER
        return students.stream()
        .filter(student->student.getGrade()>=filterFrade).toList();
    }

    List<String> topNamesBasedOnGrade(List<Student> students ,int topperCount){
        // approach: sort decreasing. then get first k student names. LOOK FOR BETTER SOLUTION
        List<Student> sorted = students.stream().sorted((st1, st2)->st2.getGrade()-st1.getGrade()).toList();
        List<String> names = new ArrayList<>();
        // now return top 3
        for(int i=0;i<3;i++) names.add(sorted.get(i).getName());
        return names;
    }

    Student oldestStudent(List<Student> students){
        // approach sort decreasing. return first: LOOK FOR BETTER SOLUTIONS. ALSO WHAT IF MULTIPLE OLDEST
        List<Student> sorted = students.stream().sorted(Collections.reverseOrder()).toList();
        return sorted.get(0);
    }

    Map<Object, List<Student>> groupByAge(List<Student> students){
        return students.stream().collect(Collectors.groupingBy(student->student.getAge()));
    }

    double findAverageGradeSeenSolution(List<Student> students){
        return students.stream().collect(Collectors.averagingInt(student->student.getGrade()));
    }

    boolean hasAnyStudentFailed(List<Student> students, int failGrade){
        return students.stream().anyMatch(student->student.getGrade()<=failGrade);
    }

    Map<Integer, Student> collectIntoMap(List<Student> students){
        return students.stream().collect(Collectors.toMap(student->student.getId(), student->student));
    }

    String findYoungestStudentName(List<Student> students){
        // approach: sort by age increasing. return first student name
        // LOOK FOR BETTER SOLUTION. WHAT IF MULTIPLE YOUNGEST
        return students.stream().sorted().toList() // using student class ordering
            .get(0).getName();

    }

    String joinNames(List<Student> students){
        return students.stream().map(student->student.getName()).collect(Collectors.joining(","));
    }


    // =============== SEEN SOLUTIONS

        // 1. TOP K NAMES BASED ON GRADE. USING LIMIT

        List<String> topNamesBasedOnGradeSeenSolution(List<Student> students ,int topperCount){
           return students.stream().sorted((st1, st2)->st2.getGrade()-st1.getGrade())
                    .limit(topperCount)
                    .map(student->student.getName())
                    .toList();
        }




        // 2. OLDEST STUDENT(S)

        Student oldestStudenSeenSolutiont(List<Student> students){
            // using max method on stream: note max returns optional.
            return students.stream().max((st1, st2)->st1.getAge()-st2.getAge()).get();
        }

        // WITH LITTLE MODIFICATION WE CAN GET LIST OF ALL OLDEST STUDENTS, IF THERE ARE MULTIPLE.
        List<Student> multipleOldestStudenSeenSolutiont(List<Student> students){
            // first find max age
                // basic approach: since we cant have stream of int, we need an intstream or we can form a stream of age as integer and then get max age
            // int max = students.stream().map(student->(Integer)student.getAge()).toList()
            //     .stream().max((a,b)->a-b).get();

                // now the easy way. get intstream directly using mapToInt
            OptionalInt maxOptional = students.stream().mapToInt(student->student.getAge()).max();
            if(maxOptional.isPresent()){
                int max = maxOptional.getAsInt();
                return students.stream().filter(student->student.getAge()==max).toList();
            } else{
                System.out.println("could not get max age");
                return null;
            }           
        }
}