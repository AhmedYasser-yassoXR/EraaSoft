import java.util.Objects;

public class Level1 {

    // BEFORE overriding equals/hashCode:
    // Person uses Object.equals(), so two different objects are NOT equal
    // even if they have the same id and name.

        class Person {
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

        // AFTER overriding equals():
        // Two Person objects are equal when their id is equal.
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Person)) return false;

            Person other = (Person) obj;
            return id == other.id;
        }

        // hashCode MUST agree with equals().
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

        Person p1 = new Person(1, "Ahmed");
        Person p2 = new Person(1, "Ali");
        Person p3 = new Person(2, "Osama");
        Person p4 = new Person(1, "Mohamed");

        System.out.println("=== Different objects, same id ===");
        System.out.println("p1 == p2: " + (p1 == p2));
        System.out.println("p1.equals(p2): " + p1.equals(p2));
        System.out.println("p1.equals(p3): " + p1.equals(p3));
        System.out.println("p1.equals(p4): " + p1.equals(p4));

        System.out.println("\n=== Multiple Persons with same id ===");
        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        System.out.println("p4: " + p4);

        System.out.println("\n=== hashCode ===");
        System.out.println("p1.hashCode(): " + p1.hashCode());
        System.out.println("p2.hashCode(): " + p2.hashCode());
        System.out.println("p4.hashCode(): " + p4.hashCode());

        /*
        ---------------------------------------------------------------------------
          Think:
          lw 3mlt override equals() wa m3mltesh hashcode() kda el objects hatt5er fe el hashcode bta3ha
         */
    }
}
