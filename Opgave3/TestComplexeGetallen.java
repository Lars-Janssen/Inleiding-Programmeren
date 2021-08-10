/**
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 * Dit is een programma om de ComplexGetal klasse te testen door een aantal
 * resultaten te hardcoden en te controleren of de klasse hetzelfde antwoord geeft.
 */
public class TestComplexeGetallen
{
    public static void main(String[] args)
    {
        /*
            Dit maakt een aantal complexe getallen om te testen
        */
        ComplexGetal getal1 = new ComplexGetal(2, -4, -6, 8);
        ComplexGetal getal2 = new ComplexGetal(new Breuk(3, -4), new Breuk(7, 8));
        ComplexGetal getal3 = new ComplexGetal(new Breuk(1, 1), new Breuk(0));
        ComplexGetal getal4 = new ComplexGetal(new Breuk(0, 0), new Breuk(0, 1));
        ComplexGetal getal5 = new ComplexGetal(new Breuk(0, 2), new Breuk(0, 3));

        /*
            Dit test of de complexe getallen goed geinitialiseerd worden.
        */
        System.out.println("-1/2 -3/4i = " + getal1);
        System.out.println("-3/4 + 7/8i = " + getal2);
        System.out.println();

        /*
            Dit test de methodes van ComplexGetal door de resultaten te vergelijken met gehardcode antwoorden.
        */
        System.out.print("Optellen        : ");
        System.out.println(getal1.telOp(getal2).equals(new ComplexGetal(new Breuk(-5, 4), new Breuk(1, 8))));
        System.out.print("Aftrekken       : ");
        System.out.println(getal1.trekAf(getal2).equals(new ComplexGetal(new Breuk(1, 4), new Breuk(-13, 8))));
        System.out.print("Vermenigvuldigen: ");
        System.out.println(getal1.vermenigvuldig(getal2).equals(new ComplexGetal(new Breuk(33, 32), new Breuk(1, 8))));
        System.out.print("Delen           : ");
        System.out.println(getal1.deel(getal2).equals(new ComplexGetal(new Breuk(-18, 85), new Breuk(64, 85))));
        System.out.print("Omgekeerde      : ");
        System.out.println(getal2.omgekeerde().equals(new ComplexGetal(new Breuk(-48, 85), new Breuk(-56, 85))));
        System.out.println();

        /*
            Dit test of de methodes samen werken. Een getal plus een tweede getal min het tweede
            getal is bijvoorbeeld gelijk aan het eerste getal.
        */
        System.out.print("a + b - b = a  : ");
        System.out.println(getal1.telOp(getal2).trekAf(getal2).equals(getal1));
        System.out.print("a * b / b = a  : ");
        System.out.println(getal1.vermenigvuldig(getal2).deel(getal2).equals(getal1));
        System.out.print("1 / (1 / a) = a: ");
        System.out.println(getal1.omgekeerde().omgekeerde().equals(getal1));
        System.out.print("(1 / a) * a = 1: ");
        System.out.println(getal1.omgekeerde().vermenigvuldig(getal1).equals(getal3));
        System.out.print("0 = 0          : ");
        System.out.println(getal4.equals(getal5));
        System.out.println();
        System.out.println("(a * b / b = a geldt niet als geldt: b = 0)");
        System.out.println("((1 / a) * a = 1 geldt niet als geldt: a = 0)");
    }
}