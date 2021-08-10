/**
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 * Dit is een programma om de Breuk klasse te testen door een aantal
 * resultaten te hardcoden en te controleren of de klasse hetzelfde antwoord geeft.
 */

public class TestBreuken
{
    public static void main(String[] args)
    {
        /*
            Dit maakt een aantal breuken voor het testen.
        */
        Breuk breuk1 = new Breuk(3, 5);
        Breuk breuk2 = new Breuk(2);
        Breuk breuk3 = new Breuk(4, -7);
        Breuk breuk4 = new Breuk(0, 1);
        Breuk breuk5 = new Breuk(0, 2);

        /*
            Dit test het maken van een breuk door te kijken of ze vereenvoudigt worden,
            goed initialiren, de min uit een noemer wordt gehaald en een 0 in de noemer 1 wordt.
        */
        System.out.println("Breuk vereenvoudigen       : " + new Breuk(9, 15).equals(new Breuk(breuk1)));
        System.out.println("Breuk met 1 getal opgegeven: " + breuk2.equals(new Breuk(2,1)));
        System.out.println("Kopieeren breuk            : " + new Breuk(breuk1).equals(breuk1));
        System.out.println("min uit noemer halen       : " + breuk3.equals(new Breuk(-4, 7)));
        System.out.println("noemer 0 wordt 1           : " + new Breuk(2, 0).equals(breuk2));
        System.out.println();

        /*
            Dit test de methodes van Breuk door de resultaten te vergelijken met gehardcode antwoorden.
        */
        System.out.println("Optellen        : " + breuk1.telOp(breuk3).equals(new Breuk(1, 35)));
        System.out.println("Aftrekken       : " + breuk1.trekAf(breuk3).equals(new Breuk(41, 35)));
        System.out.println("Vermenigvuldigen: " + breuk1.vermenigvuldig(breuk3).equals(new Breuk(-12, 35)));
        System.out.println("Delen           : " + breuk1.deel(breuk3).equals(new Breuk(-21, 20)));
        System.out.println("Omgekeerde      : " + breuk3.omgekeerde().equals(new Breuk(-7, 4)));
        System.out.println();

        /*
            Dit test of de methodes samen werken. Een getal plus een tweede getal min het tweede
            getal is bijvoorbeeld gelijk aan het eerste getal.
        */
        System.out.println("a + b - b = a  : " + breuk1.telOp(breuk3).trekAf(breuk3).equals(breuk1));
        System.out.println("a * b / b = a  : " + breuk1.vermenigvuldig(breuk3).deel(breuk3).equals(breuk1));
        System.out.println("1 / (1 / a) = a: " + breuk3.omgekeerde().omgekeerde().equals(breuk3));
        System.out.println("(1 / a) * a = 1: " + breuk3.omgekeerde().vermenigvuldig(breuk3).equals(new Breuk(1)));
        System.out.println("0 = 0          : " + breuk4.equals(breuk5));
    }
}