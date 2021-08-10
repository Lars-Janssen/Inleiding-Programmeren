/**
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 *      Dit programma vraagt om een natuurlijk getal n
 *      en print vervolgens de eerste n Lucas-getallen.
 *      De Lucas reeks begint met 2,1 en telt vervolgens steeds
 *      de twee vorige termen bij elkaar op om de nieuwe term te maken.
 *      Term drie zou bijvoorbeeld 2+1=3 zijn.
 *      Als de gebruiker een te groot of een te klein getal invult
 *      zal dit voor een foutmelding zorgen.
 *      Er is volgt ook een foutmelding als de gebruiker geen getal invult.
 */

 import java.util.Scanner;

 public class Deel4
 {
    public static void main(String[] args)
    {
        int tijdelijkeGetal;
        int gekozenAantal = 0;
        int eersteGetal = 2;
        int tweedeGetal = 1;
        int nieuweGetal = 2;
        int oudeGetal = 1;
        int maximum = 45;
        boolean isGetal = false;
        Scanner lezer = new Scanner(System.in);

        System.out.print("Geef een natuurlijk getal: ");
        if(lezer.hasNextInt())
        {
            gekozenAantal = lezer.nextInt();
            isGetal = true;
        }
        lezer.close();
        /*
        Bekijkt of de Lucas-getallen berekent moeten worden
        en berekent en print ze vervolgens.
        */
        if(gekozenAantal > 2 && gekozenAantal <= maximum)
        {
            System.out.println("De eerste " + gekozenAantal + " Lucas-getallen:");

            for(int i = 0; i < gekozenAantal; i++)
            {
                if(i == 0)
                {
                    System.out.print(eersteGetal + " ");
                    oudeGetal = eersteGetal;
                }
                else if(i == 1)
                {
                    System.out.print(tweedeGetal + " ");
                    nieuweGetal = tweedeGetal;
                }
                else
                {
                    tijdelijkeGetal = nieuweGetal;
                    nieuweGetal = nieuweGetal + oudeGetal;
                    oudeGetal = tijdelijkeGetal;
                    System.out.print(nieuweGetal + " ");
                }
            }

        }
        /*
        Bekijkt alle andere gevallen.
        */
        else if(gekozenAantal > maximum)
        {
            System.out.println("Getal te groot, fout");
        }
        else if(isGetal == false)
        {
            System.out.println("Geen getal, fout");
        }
        else if(gekozenAantal < 0)
        {
            System.out.println("Getal negatief, fout");
        }
        else if(gekozenAantal == 1)
        {
            System.out.println("Het eerste Lucas-getal is:");
            System.out.println(eersteGetal);
        }
        else if(gekozenAantal == 2)
        {
            System.out.println("Het tweede Lucas-getal is: ");
            System.out.println(tweedeGetal);
        }
        else if(gekozenAantal == 0)
        {
            System.out.println("Getal 0, fout");
        }
        System.out.println();
    }
}