import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collector;
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
        Student student11 = new Student(11, "Nikhil",24,76);
        Student student12 = new Student(12, "Rajan",23,54);



        List<Student> students = new ArrayList<>();
        students.addAll(Arrays.asList(student1, student2, student3, student4, student5, student6, student7, student8, student9, student10, student11, student12));
    
        Solution solution = new Solution();

        // List<Student> studentsBasedOnGrade = solution.findStudentsByGrade(students, 62);
        // studentsBasedOnGrade.forEach(System.out::println);

        // Map<String, Integer> nameGradeMap = solution.convertToNameGradeMap(students);
        // for(Entry<String, Integer> entry: nameGradeMap.entrySet()){
        //     System.out.println(entry.getKey()+", "+entry.getValue());
        // } 

        // Map<String, List<Student>> result = solution.groupStudentsInGradeGroup(students);
        // for(Entry<String, List<Student>> entry: result.entrySet()){
        //     System.out.println(entry.getKey());
        //     entry.getValue().forEach(System.out::println);
        // }

        // Student kThHighestGradeStudent = solution.findKthHighestGradeStudent(students,2);
        // System.out.println(kThHighestGradeStudent);

        // Map<Boolean, List<Student>> passFailStudents = solution.partitionStudentsBasedOnPassFail(students, 60);
        // for(Entry<Boolean, List<Student>> entry: passFailStudents.entrySet()){
        //     System.out.println(entry.getKey());
        //     entry.getValue().forEach(System.out::println);
        // }

        // IntSummaryStatistics gradeSummary = solution.getGradeSummary(students);
        // System.out.println(gradeSummary);

        // String lowestGradeStudentName = solution.studentNameWithLowestGrade(students);
        // System.out.println(lowestGradeStudentName);

        // List<List<Student>> superList = new ArrayList<>();
        // superList.add(students);
        // List<Student> flattenedList = solution.flattenList(superList);
        // flattenedList.forEach(System.out::println);
        

        // String studentNameCsv = solution.convertStudentListToNameCSV(students);
        // System.out.println(studentNameCsv);

        List<Student> uniqeStudents = solution.removeDuplicateBasedOnName(students);
        uniqeStudents.forEach(System.out::println);
    }
}

class Solution{
    List<Student> findStudentsByGrade(List<Student> students, int gradeEqualOrHigher){
       return students.stream().filter(student->student.getGrade()>=gradeEqualOrHigher).collect(Collectors.toList());
    }

    // convert list of students to map of name-grade: what happens when two students have same name
    Map<String, Integer> convertToNameGradeMap(List<Student> students){
        return students.stream().collect(Collectors.toMap(student->student.getName(), student->student.getGrade()));
    }

    // count students in each grade group: 0-5-, 51-60,61-70,71-80,81-90,91-100: UNSOLVED
    // SEEN SOLUTION
    Map<String, List<Student>> groupStudentsInGradeGroup(List<Student> students){
        return students.stream()
                       .collect(Collectors.groupingBy(student -> {
                           int grade = student.getGrade();
                           if (grade <= 59) return "0-59";
                           else if (grade <= 69) return "60-69";
                           else if (grade <= 79) return "70-79";
                           else if (grade <= 89) return "80-89";
                           else return "90-100";
                       }));
    }

    // FIND Kth HIGHEST GRADE
    Student findKthHighestGradeStudent(List<Student> students, int k){
        // approach get top k. and then return last
        return students.stream().sorted((st1, st2)->st2.getGrade()-st1.getGrade()).limit(k).toList().get(k-1);
    }

    // partition students based on pass/fail.
    Map<Boolean, List<Student>> partitionStudentsBasedOnPassFail(List<Student> students, int passGrade){
        return students.stream().collect(Collectors.partitioningBy(student->student.getGrade()>=passGrade));
    }

    // grades summary:
    IntSummaryStatistics getGradeSummary(List<Student> students){
        // counts students and also finds sum, average, min, max of grades
        return students.stream().collect(Collectors.summarizingInt(student->student.getGrade()));
    }

    // name of student with lowest grade
    String studentNameWithLowestGrade(List<Student> students){
        return students.stream().min((st1, st2)->st1.getGrade()-st2.getGrade()).get().getName();
        // for multple lowest grade students:
            // get min grade by converting the student stream to int(grade) stream suing mapToInt and then applying min.
            // now, filter based on min grade
    }


    // Flatten a List of Student Lists: HAVE MULTIPLE STUDENT LIST OR JUST USE THE SINGLE ONE INSIDE A LIST
    List<Student> flattenList(List<List<Student>> studentListList){
        return studentListList.stream()
            .flatMap(List::stream).toList();
    }


    String convertStudentListToNameCSV(List<Student> students){
        return students.stream().map(st->st.getName()).collect(Collectors.joining(", "));
    }

    // remove duplicate entry based on name. keep the first object.
    // SEEN SOLUTION
    List<Student> removeDuplicateBasedOnName(List<Student> students){
        Set<String> seenNames = new LinkedHashSet<>();
            return students.stream()
                           .filter(student -> seenNames.add(student.getName()))
                           .collect(Collectors.toList());
    }
}