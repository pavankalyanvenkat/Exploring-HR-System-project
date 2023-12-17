import java.util.ArrayList;

public class ExploringHRSystemproject {
    public interface User {
        String getUsername();
        String getPassword();
        boolean login(String username, String password);
    }

    public class Employee implements User {
        private String firstName;
        private String lastName;
        private int registration;
        private int age;
        private int daysWorked;
        private int vacationDaysTaken;
        private double salary;
        private int yearsWorked;

        public Employee(String firstName, String lastName, int registration, int age, int daysWorked, int vacationDaysTaken, double salary, int yearsWorked) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.registration = registration;
            this.age = age;
            this.daysWorked = daysWorked;
            this.vacationDaysTaken = vacationDaysTaken;
            this.salary = salary;
            this.yearsWorked = yearsWorked;
        }

        public int timeToRetirement() {
            return Math.min(60 - age, 40 - yearsWorked);
        }

        public int vacationTimeLeft() {
            return (int) ((daysWorked / 360.0) * (30 - vacationDaysTaken)); // Corrected integer division to double division
        }

        public int calculateBonus() {
            return (int) (2.2 * salary);
        }

        @Override
        public String getUsername() {
            return firstName + lastName + registration;
        }

        @Override
        public String getPassword() {
            return "password"; // TODO: Implement secure password hashing and storage
        }

        @Override
        public boolean login(String username, String password) {
            return username.equals(getUsername()) && password.equals(getPassword());
        }
    }

    public class SalesRep extends Employee {
        private double salesMade;

        public SalesRep(String firstName, String lastName, int registration, int age, int daysWorked, int vacationDaysTaken, double salary, int yearsWorked, double salesMade) {
            super(firstName, lastName, registration, age, daysWorked, vacationDaysTaken, salary, yearsWorked);
            this.salesMade = salesMade;
        }

        public int calculateCommission() {
            return (int) (0.1 * salesMade);
        }
    }

    public class SalesManager extends Employee {
        private ArrayList<SalesRep> team;

        public SalesManager(String firstName, String lastName, int registration, int age, int daysWorked, int vacationDaysTaken, double salary, int yearsWorked) {
            super(firstName, lastName, registration, age, daysWorked, vacationDaysTaken, salary, yearsWorked);
            this.team = new ArrayList<>();
        }

        public void addToTeam(SalesRep salesRep) {
            team.add(salesRep);
        }

        public int calculateCommission() {
            int totalSales = 0;
            for (SalesRep rep : team) {
                totalSales += rep.calculateCommission();
            }
            return (int) (0.03 * totalSales);
        }
    }

    public static class Main {
        public static void main(String[] args) {
            ExploringHRSystemproject projectInstance = new ExploringHRSystemproject();

            SalesRep rep1 = projectInstance.new SalesRep("John", "Doe", 1234, 30, 180, 10, 50000, 5, 200000);
            SalesManager manager = projectInstance.new SalesManager("Jane", "Smith", 5678, 35, 200, 5, 70000, 10);
            manager.addToTeam(rep1);

            System.out.println("John's time to retirement: " + rep1.timeToRetirement());
            System.out.println("John's vacation time left: " + rep1.vacationTimeLeft());
            System.out.println("John's bonus: " + rep1.calculateBonus());
            System.out.println("John's commission: " + rep1.calculateCommission());

            System.out.println("Jane's time to retirement: " + manager.timeToRetirement());
            System.out.println("Jane's vacation time left: " + manager.vacationTimeLeft());
            System.out.println("Jane's bonus: " + manager.calculateBonus());
            System.out.println("Team commission: " + manager.calculateCommission());
        }
    }
}
