import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingInt;

public class Main {

    public static void main(String[] args) {
        List<Employee> employees = readFromFile("file");
        System.out.format("Maximum salary: %d.\nMinimum salary: %d.\nAvg salary: %f.%nEmployees on each job: %s.\nEmployees count by first surname character: %s.",
                maxSalary(employees), minSalary(employees), averageSalary(employees), jobCount(employees).toString(), abc(employees).toString());
    }

    public static List<Employee> readFromFile(String file){
        try {
            return Files.lines(Paths.get(file))
                    .map(a -> a.split(",\\s*"))
                    .map(a -> new Employee(a[0], a[1], Integer.parseInt(a[2])))
                    .collect(Collectors.toList());
        }
        catch (IOException error){
            System.out.println(error);
        }
        return null;
    }

    public static int maxSalary(List<Employee> list) {
        return list.stream().mapToInt(Employee::getSalary).max().getAsInt();
    }
    public static int minSalary(List<Employee> list) {
        return list.stream().mapToInt(Employee::getSalary).min().getAsInt();
    }
    public static double averageSalary(List<Employee> list) {
        return list.stream().mapToInt(Employee::getSalary).average().getAsDouble();
    }
    public static Map<String, Integer> jobCount(List<Employee> list) {
        return list.stream().map(Employee::getJob).collect(Collectors.groupingBy(Function.identity(), summingInt(a -> 1)));
    }
    public static Map<Character, Integer> abc(List<Employee> list) {
        return list.stream().map(Employee::getSurname).map(a -> a.charAt(0)).collect(Collectors.groupingBy(Function.identity(), summingInt(a -> 1)));
    }

    static class Employee{
        private String surname, job;
        private Integer salary;
        Employee(String surname, String job, Integer salary){
            this.surname = surname; this.job = job; this.salary = salary;
        }
        public String getSurname(){
            return surname;
        }
        public String getJob(){
            return job;
        }
        public Integer getSalary(){
            return salary;
        }
    }
}
