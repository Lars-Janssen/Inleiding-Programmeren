/**
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 *      Dit programma vraagt om een geheel positief getal
 *      en geeft de som van de even en oneven getallen tot en
 *      met het gegeven getal en het verschil daartussen.
 */

import java.util.Scanner;
import java.lang.Math;

public class Deel2
{
    public static void main(String[] args)
    {
        boolean goedGetal = false;
        int oneven = 0;
        int even = 0;
        int gekozenGetal = 0;
        int maximum = 92680;
        Scanner lezer = new Scanner(System.in);

        System.out.print("Geef een geheel positief getal: ");
        /*
        Bekijk of de gebruiker een positief geheel getal heeft ingevuld.
        */
        if(lezer.hasNextInt() == true)
        {
            gekozenGetal = lezer.nextInt();
            goedGetal = true;
        }
        else
        {
            goedGetal = false;
        }
        lezer.close();
        if(gekozenGetal < 0)
        {
            goedGetal = false;
        }

        if(gekozenGetal > maximum)
        {
            goedGetal = false;
        }
        /*
        Berekent de sommen en het verschil en print ze.
        */
        if(goedGetal == true)
        {
            for (int i = 0; i <= gekozenGetal; i++)
            {
                if (i % 2 == 0)
                {
                    even += i;
                }
                else
                {
                    oneven += i;
                }
            }
            System.out.println("som van alle oneven getallen tot en met " + gekozenGetal + " is " + oneven);
            System.out.println("som van alle   even getallen tot en met " + gekozenGetal + " is " + even);
            System.out.println("verschil tussen twee sommen is " + (oneven - even));
        }
        else if(gekozenGetal > maximum
        )
        {
            System.out.println("Dit getal is te groot");
        }
        else
        {
            System.out.println("Dit is geen geheel positief getal");
        }
    }
}