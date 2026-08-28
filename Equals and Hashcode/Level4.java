import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Objects;

public class Level4 {

    // ================= PRODUCT =================

    static class Product {
        private String code;
        private double price;

        public Product(String code, double price) {
            this.code = code;
            this.price = price;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Product)) return false;

            Product other = (Product) obj;
            return Objects.equals(code, other.code);
        }

        @Override
        public int hashCode() {
            return Objects.hash(code);
        }

        @Override
        public String toString() {
            return "Product{code='" + code + "', price=" + price + "}";
        }
    }

    // ================= STUDENT =================

    static class Student {
        private int id;
        private String email;

        public Student(int id, String email) {
            this.id = id;
            this.email = email;
        }

        @Override
        public String toString() {
            return "Student{id=" + id + ", email='" + email + "'}";
        }

        // CASE 1: equality by ID
        static class EqualityById extends Student {
            public EqualityById(int id, String email) {
                super(id, email);
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (!(obj instanceof EqualityById)) return false;

                EqualityById other = (EqualityById) obj;
                return this.id == other.id;
            }

            @Override
            public int hashCode() {
                return Integer.hashCode(id);
            }
        }

        // CASE 2: equality by email
        static class EqualityByEmail extends Student {
            public EqualityByEmail(int id, String email) {
                super(id, email);
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (!(obj instanceof EqualityByEmail)) return false;

                EqualityByEmail other = (EqualityByEmail) obj;
                return Objects.equals(this.email, other.email);
            }

            @Override
            public int hashCode() {
                return Objects.hash(email);
            }
        }
    }

    // ================= CAR =================

    static class Car {
        private String plateNumber;
        private String color;

        public Car(String plateNumber, String color) {
            this.plateNumber = plateNumber;
            this.color = color;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Car)) return false;

            Car other = (Car) obj;
            return Objects.equals(plateNumber, other.plateNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(plateNumber);
        }

        @Override
        public String toString() {
            return "Car{plateNumber='" + plateNumber + "', color='" + color + "'}";
        }
    }

    public static void main(String[] args) {

        // =========================================================
        // 1. PRODUCT + HASHSET
        // =========================================================

        System.out.println("=== PRODUCT ===");

        Set<Product> products = new HashSet<>();

        products.add(new Product("P100", 100.0));
        products.add(new Product("P100", 150.0));
        products.add(new Product("P200", 200.0));

        System.out.println("Products in HashSet: " + products.size());
        System.out.println(products);

        // P100 appears once because equality is based on code only.

        // =========================================================
        // 2. STUDENT - EQUALITY BY ID
        // =========================================================

        System.out.println("\n=== STUDENT: EQUALITY BY ID ===");

        Set<Student.EqualityById> studentsById = new HashSet<>();

        studentsById.add(new Student.EqualityById(1, "ahmed@gmail.com"));
        studentsById.add(new Student.EqualityById(1, "ali@gmail.com"));
        studentsById.add(new Student.EqualityById(2, "ali@gmail.com"));

        System.out.println("HashSet size: " + studentsById.size());
        System.out.println(studentsById);

        // The two students with id=1 are considered equal.

        // =========================================================
        // 3. STUDENT - EQUALITY BY EMAIL
        // =========================================================

        System.out.println("\n=== STUDENT: EQUALITY BY EMAIL ===");

        Set<Student.EqualityByEmail> studentsByEmail = new HashSet<>();

        studentsByEmail.add(new Student.EqualityByEmail(1, "ahmed@gmail.com"));
        studentsByEmail.add(new Student.EqualityByEmail(2, "ahmed@gmail.com"));
        studentsByEmail.add(new Student.EqualityByEmail(3, "ali@gmail.com"));

        System.out.println("HashSet size: " + studentsByEmail.size());
        System.out.println(studentsByEmail);

        // The two students with the same email are considered equal.

        // =========================================================
        // 4. CAR + HASHMAP
        // =========================================================

        System.out.println("\n=== CAR + HASHMAP ===");

        Map<Car, String> carOwners = new HashMap<>();

        Car car1 = new Car("ABC-123", "Black");
        Car car2 = new Car("ABC-123", "Red");

        carOwners.put(car1, "Ahmed");
        carOwners.put(car2, "Ali");

        System.out.println("Map size: " + carOwners.size());
        System.out.println("Owner of car1: " + carOwners.get(car1));
        System.out.println("Owner of new same-plate car: "
                + carOwners.get(new Car("ABC-123", "White")));

        /*
         * Real-world idea:
         *
         * The plate number identifies the car.
         * Therefore color should NOT affect equality (wa aked mesh han3rf el car with the color).
         *
         * car1 and car2 have the same plate number, so they represent
         * the same logical key and the second put() replaces the value.
         */
    }
}
