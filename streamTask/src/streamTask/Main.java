package streamTask;


import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;

public class Main {
    List<List<String>> nestedWords = Arrays.asList(
            Arrays.asList("Java", "Stream"),
            Arrays.asList("API", "Lambda"),
            Arrays.asList("FlatMap", "Map")
    );
    static void main(String[] args) {

        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);

        System.out.println("-----------------Basic Stream Operations--------------------");
        List<String> getNames = names.stream().filter(name -> name!=null && name.startsWith(String.valueOf("A")))
                .collect(Collectors.toList());
        System.out.println("Find names starting with a specific letter from a list of strings."+ getNames);

        List<String> getUpperName = names.stream().filter(name ->name!=null).map(name -> name.toUpperCase()).collect(Collectors.toList());
        System.out.println("Convert all strings to uppercase using stream."+ getUpperName);

        List<Integer> evenNumbers = numbers.stream().filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Filter even numbers from a list of integers"+ evenNumbers);

        List<Integer> sortDes = numbers.stream().sorted(
                (n1, n2) -> Integer.compare(n2, n1)
        ).collect(Collectors.toList());
        System.out.println("Sort a list of integers in descending order using streams."+ sortDes);

        List<Integer> distinctNumbers = numbers.stream().distinct().collect(Collectors.toList());
        System.out.println("Distinct numbers from a list of integers"+ distinctNumbers);

        System.out.println("-----------------Intermediate Stream Tasks--------------------");


        List<String> countString = names.stream().filter(name -> name!= null).filter(name -> name.length()>5).collect(Collectors.toList());
        System.out.println("Count the number of strings longer than 5 characters."+ countString);

        Optional<Integer> firstElement = numbers.stream().findFirst();
        System.out.println("First element of a list of integers"+ firstElement);

        boolean checkdivisible = numbers.stream().anyMatch(n -> n%5 == 0);
        System.out.println("Check if any number is divisible by 5 in a list" + " ( "+ checkdivisible + " )");

        Set<Integer> intoSet = numbers.stream().collect(Collectors.toSet());
        System.out.println("Collect elements into a Set instead of a List."+ intoSet);


        System.out.println("**********The list before the skip**********"+ numbers);
        List<Integer> skipFirstThree = numbers.stream().skip(3).toList();
        System.out.println("Skip the first 3 elements and return the rest."+ skipFirstThree );

        System.out.println("-----------------Numeric Streams & Reductions----------------- \n");
        int sumReduce = numbers.stream().reduce(0, Integer::sum);
        System.out.println("Sum the numbers in a list of integers = "+ sumReduce);

        Optional<Integer> Max = numbers.stream().max(Integer::compareTo);
        System.out.println("Max Min using the min number is "+ Max);
        Optional<Integer> Min = numbers.stream().min(Integer::compareTo);
        System.out.println("Max Min using the min number is "+ Min);


        List<Employee> employees = Arrays.asList(
                new Employee("Ali", 30, "HR", 5000),
                new Employee("Mona", 25, "IT", 7000),
                new Employee("Ahmed", 30, "HR", 5500),
                new Employee("Sara", 27, "IT", 7200),
                new Employee("Omar", 40, "Finance", 8000),
                new Employee("Laila", 35, "Finance", 8200)
        );


        //hna 3mlt map 3l4an a5od el age (Integer) wa long 3l4an el count
        Map<Integer, Long> employeesByAge = employees.stream().collect(Collectors.groupingBy(Employee::getAge,
                        Collectors.counting()
                ));

        System.out.println("Employee Age: " + employeesByAge);

        Map<String, Double> salaryByDepa = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        System.out.println("Employees Salary Average: " + salaryByDepa);

        System.out.println("-----------------Advanced Operations-----------------");

        List<Employee> employeeSalarySort = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName))
                .collect(Collectors.toList());
        System.out.println("Employee Salary Sort: " + employeeSalarySort);
//then Comparing 3l4an n3ml sort be el name ba3d el salary

        List<Student> students = Arrays.asList(
                new Student("Ali", "IT", 85),
                new Student("Mona", "CS", 92),
                new Student("Ahmed", "IT", 60),
                new Student("Sara", "CS", 70),
                new Student("Omar", "IS", 45),
                new Student("Laila", "IS", 78)
        );

        Student secondHighestGrade = students.stream().sorted(Comparator.comparing(Student::getGrade).reversed())
                .skip(1).findFirst().orElse(null);
        System.out.println("Find the second highest number in a list. "+ secondHighestGrade);

        List<Integer> findDuplicate = numbers.stream()
                .filter(n -> Collections.frequency(numbers, n) > 1)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Find the duplicate numbers in a list. "+ findDuplicate);

        List<String> cleanNames = names.stream()
                .filter(name -> name != null && !name.isBlank())
                .collect(Collectors.toList());
        System.out.println("Clean Names: " + cleanNames);


        Map<Boolean, List<Student>> passORfailOnGrade = students.stream().collect(Collectors.partitioningBy(student -> student.getGrade()>= 50));
        System.out.println("Passed: " + passORfailOnGrade.get(true));
        System.out.println("Failed: " + passORfailOnGrade.get(false));


    }
}


