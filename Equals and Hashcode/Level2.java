import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Level2 {

    static class Person {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {

            return id;

        }

        public String getName() {

            return name;
        }

        // CURRENT equality logic: id only.
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

        System.out.println("=== Duplicate test ===");

        Set<Person> people = new HashSet<>();

        people.add(new Person(1, "Ahmed"));
        people.add(new Person(1, "Ali"));
        people.add(new Person(2, "Osama"));
        people.add(new Person(2, "Mohamed"));

        System.out.println("HashSet size: " + people.size());
        System.out.println(people);

        System.out.println("\n=== 10 Person objects ===");

        Set<Person> tenPeople = new HashSet<>();

        tenPeople.add(new Person(1, "Ahmed"));
        tenPeople.add(new Person(1, "Ali"));       // duplicate by id
        tenPeople.add(new Person(2, "Osama"));
        tenPeople.add(new Person(3, "Mohamed"));
        tenPeople.add(new Person(3, "Hassan"));    // duplicate by id
        tenPeople.add(new Person(4, "Omar"));
        tenPeople.add(new Person(5, "Khaled"));
        tenPeople.add(new Person(5, "Youssef"));  // duplicate by id
        tenPeople.add(new Person(6, "Mina"));
        tenPeople.add(new Person(6, "John"));     // duplicate by id

        System.out.println("Number added: 10");
        System.out.println("Number remaining: " + tenPeople.size());
        System.out.println(tenPeople);

        /*
          Try changing equals/hashCode together:

          1) Equality by id:
               return id == other.id;
               hashCode -> id

          2) Equality by name:
               return Objects.equals(name, other.name);
               hashCode -> Objects.hash(name)

          3) Equality by both:
               return id == other.id && Objects.equals(name, other.name);
               hashCode -> Objects.hash(id, name)

          IMPORTANT:
          Whenever equals() changes, hashCode() must use the same fields.
         */
    }
}
