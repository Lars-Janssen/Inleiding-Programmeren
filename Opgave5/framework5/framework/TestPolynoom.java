import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.io.File;
import java.util.*;

public class TestPolynoom {

    /*
        Dit test de toString functie.
    */
    @Test
    public void toStringTest()
    {
        assertEquals("ToString test", "2.0 x^8 + 3.0 x^3" ,new Polynoom(2, 8, 3, 3).toString());
    }


    /*
        Deze test mislukt met opzet om te kijken of JUnit werkt.
    */
    @Test
    public void testTest()
    {
        assertEquals("Zijn 1 en 2 hetzelfde?", 1, 2);
    }


    /*
        Dit test de equals functie.
    */
    @Test
    public void equalsTest()
    {
        assertTrue("Equals test", new Polynoom(2, 3, 5, 2, 1, 0).equals(new Polynoom(1, 3, 1, 3, 4, 2, 1, 2, 1, 0)));
    }


    /*
        De onderstaande tests testen de constructors. Het is niet nodig om de constructor voor een
        Arraylist te testen, omdat deze al getest wordt met de Polynoom
        en Bestand constructors.
    */
    @Test
    public void legeConstructorTest()
    {
        assertEquals("Lege Constructor" , new Polynoom().toString(), "0");
    }

    @Test
    public void bestandConstructorsTest()
    {
        assertEquals("Bestand Constructor", new Polynoom(new File ("invoer/polynoom1.txt")).toString(), "x + 2.0");
    }

    @Test
    public void doubleConstructorsTest()
    {
        assertEquals("double Constructor", new Polynoom(1, 5 , 4.5 , 5, 7.5 , 3).toString(), "5.5 x^5 + 7.5 x^3");
    }

    @Test
    public void polynoomConstructorsTest()
    {
        assertEquals("Polynoom Constructor", new Polynoom(new Polynoom(1, 5, 6, 7)).toString(), "6.0 x^7 + x^5");
    }

    @Test
    public void arrayListConstructorTest()
    {
        ArrayList<Paar> testList = new ArrayList<Paar>();
        testList.add(new Paar(2, 4));
        testList.add(new Paar(1,3));
        testList.add(new Paar(2, 3));

        Polynoom arrayListTest = new Polynoom(testList);
        assertEquals("ArrayList Constructor", arrayListTest.toString(), "2.0 x^4 + 3.0 x^3");
    }



    Polynoom testPol1 = new Polynoom(new File("invoer/polynoom4.txt"));
    Polynoom testPol2 = new Polynoom(new File("invoer/polynoom6.txt"));

    Polynoom testTelOp = testPol1.telOp(testPol2);
    Polynoom testTrekAf = testPol1.trekAf(testPol2);
    Polynoom testVermenigvuldig = testPol1.vermenigvuldig(testPol2);
    Polynoom testDifferentieer = testPol1.differentieer();
    Polynoom testIntegreer = testPol1.integreer(1);

    /*
        De onserstaande tests testen de methoden van Polynoom door de uitkomsten
        van de hierbovenstande variabelen te vergelijken met de goede antwoorden.
    */
    @Test
    public void telOpTest()
    {
        assertEquals("telOp", testTelOp.toString(), "x^3 + x^2");
    }

    @Test
    public void trekAfTest()
    {
        assertEquals("trekAf", testTrekAf.toString(), "x^3 + x^2 - 2.0 x + 2.0");
    }

    @Test
    public void vermenigvuldigTest()
    {
        assertEquals("vermenigvuldig", testVermenigvuldig.toString(), "x^4 - 2.0 x^2 + 2.0 x - 1.0");
    }

    @Test
    public void differentieerTest()
    {
        assertEquals("differentieer", testDifferentieer.toString(), "3.0 x^2 + 2.0 x - 1.0");
    }

    @Test
    public void integreerTest()
    {
        assertEquals("integreer", testIntegreer.toString(), "0.25 x^4 + 0.3333333333333333 x^3 - 0.5 x^2 + x + 1.0");
    }


    /*
        Dit voert de tests uit en print of er foutmeldingen zijn en zo ja,
        welke. Ook pprint het het aantal uitgevoerde tests
        en de tijd die daar voor nodig was.
    */
    public static void main(String[] args)
    {
        System.out.println("Opgave 5 Tester\n---------------");

        Result result = JUnitCore.runClasses(TestPolynoom.class);

        if (result.wasSuccessful())
        {
            System.out.println("Hoera, alle tests zijn geslaagd!");
        }
        else
        {
            if (result.getFailureCount() > 1)
            {
                System.out.println("Er zijn " + result.getFailureCount() + " fouten opgetreden:");
            }
            else
            {
                System.out.println("Er is 1 fout opgetreden:");
            }

            for (Failure failure : result.getFailures())
            {
                System.out.println(failure.toString());
            }
        }

        System.out.println("\nAantal tests uitgevoerd: " + result.getRunCount());
        System.out.println("Het uitvoeren van de tests duurde " + (result.getRunTime() / 1000.0) + " seconden.");
    }
}
