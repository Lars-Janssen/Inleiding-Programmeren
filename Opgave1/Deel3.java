/**
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 *      Dit programma genereert een willekeurig getal.
 *      Je krijgt drie pogingen om het getal te raden
 *      en het programma vertelt je na elke poging of je
 *      getal te hoog of te laag is.
 *      Als je geen geldig getal invult detecteert het
 *      programma dit en is het spel over.
 */

import java.util.Scanner;
import java.util.Random;

public class Deel3
{

    public static void main(String[] args)
    {
        Random random = new Random();
        Scanner lezer = new Scanner(System.in);
        int pogingen = 3;
        int minimum = 1;
        int maximum = 10;
        int willekeurigGetal = random.nextInt(maximum) + minimum;
        int gekozenGetal;
        boolean verloren = false;

        System.out.print("Geef een getal tussen " + minimum + " en " + maximum + ", ");
        System.out.println("je mag " + pogingen + " keer raden");

        for(int i = 0; i < pogingen; i++)
        {
            if(i == 0)
            {
                System.out.print("Eerste Keer: ");
            }
            else if(i == 1)
            {
                System.out.print("Tweede Keer: ");
            }
            else if(i == 2)
            {
                System.out.print("Derde Keer: ");
            }

            if(!lezer.hasNextInt())
            {
                System.out.println("Dat is geen geheel getal, dan stop ik");
                break;
            }
            gekozenGetal = lezer.nextInt();
            /*
            Bekijkt of het gegeven getal tussen en het minimum en maximum
            is en kijkt vervolgens waar het ligt ten opzichte van het
            willekeurige getal.
            */
            if(gekozenGetal >= minimum && gekozenGetal <= maximum)
            {
                if(gekozenGetal > willekeurigGetal)
                {
                    System.out.println("te groot");
                }
                else if(gekozenGetal < willekeurigGetal)
                {
                    System.out.println("te klein");
                }
                else
                {
                    System.out.println("gewonnen");
                    break;
                }
            }
            else
            {
                System.out.println("Getal was niet tussen " + minimum + " en " +  maximum + ", dan stop ik");
                break;
            }

            if(i == pogingen - 1)
            {
                verloren = true;
            }
        }

        if(verloren)
        {
            System.out.println("verloren, het getal was " + willekeurigGetal);
        }
        lezer.close();
    }
}