import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Level3 {

    static class Person {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        // Equality is based on id only.
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Person)) return false;

            Person other = (Person) obj;
            return id == other.id;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }

        @Override
        public String toString() {
            return "Person{id=" + id + ", name='" + name + "'}";
        }
    }

    public static void main(String[] args) {

        System.out.println("=== 1. Two keys with same id ===");

        Map<Person, String> employees = new HashMap<>();

        Person p1 = new Person(1, "Ahmed");
        Person p2 = new Person(1, "Ali");

        employees.put(p1, "Employee");
        employees.put(p2, "Manager");

        System.out.println("Map size: " + employees.size());
        System.out.println("Value for p1: " + employees.get(p1));
        System.out.println("Value for p2: " + employees.get(p2));

        // Because p1 and p2 are equal by id, p2 replaces p1's value.

        System.out.println("\n=== 2. Retrieve using a new object ===");

        Person searchPerson = new Person(1, "Anything");

        System.out.println("searchPerson.equals(p1): " + searchPerson.equals(p1));
        System.out.println("Retrieved value: " + employees.get(searchPerson));

        System.out.println("\n=== 3. Modify a key after insertion ===");

        Person key = new Person(10, "Omar");
        employees.put(key, "Employee");

        System.out.println("Before modification:");
        System.out.println("employees.get(key): " + employees.get(key));

        // Changing id changes hashCode().
        key.setId(99);

        System.out.println("\nAfter changing id from 10 to 99:");
        System.out.println("employees.get(key): " + employees.get(key));
        System.out.println("employees.containsKey(key): " + employees.containsKey(key));

    }
}
