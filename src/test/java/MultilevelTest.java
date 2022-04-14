import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MultilevelTest {
    private Multilevel createMultilevel() {
        Multilevel multilevel = new Multilevel();

        Person p1 = new Person("Jan Carlos", "Colombia", 18);
        Person p2 = new Person("Pedro", "Brazil", 20);
        Person p3 = new Person("Juan", "Ecuador", 50);
        Person p5 = new Person("Steven", "Argentina", 32);
        Person p6 = new Person("Santiago", "Colombia", 20);

        multilevel.insertReferrer(null, p1);
        multilevel.insertReferrer(p1, p2);
        multilevel.insertReferrer(p1, p3);
        multilevel.insertReferrer(p2, new Person("Samuel", "Peru", 30));
        multilevel.insertReferrer(p2, p5);
        multilevel.insertReferrer(p5, new Person("Sebastian", "Bolivia", 23));
        multilevel.insertReferrer(p5, new Person("Sergio", "Puerto Rico", 30));

        multilevel.insertReferrer(p3, p6);
        multilevel.insertReferrer(p6, new Person("Sandra", "Venezuela", 32));

        return multilevel;
    }

    @org.junit.jupiter.api.Test
    void insertReferrer() {
        System.out.println("insertReferrer");
        Multilevel multilevel = new Multilevel();

        Person p1 = new Person("Jan Carlos", "Colombia", 18);
        Person p2 = new Person("Pedro", "Brazil", 20);

        multilevel.insertReferrer(null, p1);

        assertTrue(multilevel.insertReferrer(p1, p2));

        multilevel.insertReferrer(p1, p2);

        assertFalse(multilevel.insertReferrer(p1, p2));
        assertEquals(multilevel.meanAge(), 19.3, 0.04);
    }

    @org.junit.jupiter.api.Test
    void listPersonsByCountry() {
        System.out.println("listPersonsByCountry");
        String country = "Colombia";
        ArrayList<Person> personsByCountry = this.createMultilevel().listPersonsByCountry(country);

        ArrayList<Person> persons = new ArrayList<>();

        persons.add(new Person("Jan Carlos", "Colombia", 18));
        persons.add(new Person("Santiago", "Colombia", 20));

        assertEquals(personsByCountry, persons);
    }

    @org.junit.jupiter.api.Test
    void meanAge() {
        System.out.println("meanAge");
        assertEquals(this.createMultilevel().meanAge(), 28.3, 0.04);
    }

    @org.junit.jupiter.api.Test
    void searchByName() {
        System.out.println("searchByName");
        String name = "Samuel";
        Person p1 = new Person(name, "Peru", 30);
        Person person = this.createMultilevel().searchByName(name).get(0);

        assertEquals(p1, person);
    }

    @org.junit.jupiter.api.Test
    void calculateSalaryByName() {
        System.out.println("calculateSalaryByName");
        String name = "Jan Carlos";
        int salary = this.createMultilevel().calculateSalaryByName(name).get(0);

        assertEquals(salary, 800);
    }

    @org.junit.jupiter.api.Test
    void getNumPersonsWithoutReferrers() {
        System.out.println("getNumPersonsWithoutReferrers");
        int numPersonsWithoutReferrers = this.createMultilevel().getNumPersonsWithoutReferrers();

        assertEquals(numPersonsWithoutReferrers, 4);
    }
}