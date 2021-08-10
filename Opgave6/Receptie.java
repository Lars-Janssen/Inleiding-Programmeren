/**
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 * Deze klasse is de receptie van het hotel.
 * De gebruiker kan hierin commando's gebruiken om
 * het hotel te runnen.
*/

import java.util.Scanner;

@SuppressWarnings("unchecked")
public class Receptie implements ReceptieInterface
{
    static private boolean stop = false;
    static private Hotel hotel;
    static private Scanner scanner = new Scanner(System.in);
    static private Scanner getalScanner = new Scanner(System.in);

    /*
        De main leest de input van de gebruiker en voert het uit.
    */
    public static void main(String[] args)
    {
        /*
            Dit kijkt of de gebruiker een aantal kamers heeft
            meegeleverd en maakt een hotel met dat aantal kamers.
            Indien de gebruiker het niet heeft gespecifieerd maakt
            hij een hotel met het standaard aantal kamers.
        */
        if(args.length == 0)
        {
            hotel = new Hotel();
        }
        else
        {
            if(Integer.parseInt(args[0]) > 0)
            {
                hotel = new Hotel(Integer.parseInt(args[0]));
            }
            else
            {
                hotel = new Hotel();
            }
        }

        System.out.println("Welkom bij de mainframe van " + hotel.toString());
        System.out.println("geldige commando's zijn 'checkin', 'checkuit', 'status' en 'stop'");
        /*
            Dit voert de input van de gebruiker uit zolang die het stop
            command niet heeft gebruikt.
        */
        do
        {
            getInput();
        }
        while (stop == false);
    }

    /*
        Dit bekijkt het command van de gebruiker en roept
        de desbetreffende methode aan.
    */
    public static void getInput()
    {
        String command = "";
        System.out.print("Voer een commando in: ");

        /*
            Dit verkrijgt de input van de gebruiker.
        */
        if(scanner.hasNextLine())
        {
            command = scanner.nextLine();
        }

        /*
            Dit voert de input uit.
        */
        if(command.equals("stop"))
        {
            stop = true;
            scanner.close();
            getalScanner.close();
        }
        else if(command.equals("status"))
        {
            status();
        }
        else if(command.equals("checkin"))
        {
            checkIn();
        }
        else if(command.equals("checkuit"))
        {
            checkUit();
        }
    }

    /*
        Deze methode regelt het status command.
    */
    public static void status()
    {
        /*
            Dit print hoeveel mensen er in elke kamer passen
            en de gasten in de kamers. ALs de kamer leeg is
            print hij "leeg".
        */
        for(int i = 0; i < hotel.getAantalKamers(); i++)
        {
            System.out.println("Kamer " + i + " (Maximaal " + hotel.getKamer(i).getGrootte() + " gasten)");
            if(hotel.getKamer(i).kamerIsLeeg())
            {
                System.out.println("Leeg");
            }
            else
            {
                System.out.println(hotel.getKamer(i).getGasten().toString());
            }
        }
    }

    /*
        Deze methode regelt het checkin command.
    */
    public static void checkIn()
    {
        System.out.print("Hoeveel gasten? ");
        int getal;

        /*
            Als de gebruiker een getal heeft meegegeven,
            loopt hij door de kamers en probeert hij dat aantaal mensen
            in te checken. Als dat werkt stopt de loop en als het niet werkt
            gaat hij door. Als de groep in geen enkele kamer past gooit
            hij een GroepPastNietException.
        */
        if(getalScanner.hasNextInt())
        {
            getal = getalScanner.nextInt();
            Groep ingecheckteGroep = new Groep<>();

            for(int i = 0; i < getal; i++)
            {
                ingecheckteGroep.add(new Gast());
            }

            try
            {
                hotel.checkIn(ingecheckteGroep);
            }
            catch(GroepPastNietException e)
            {
                System.out.println("Deze groep past helaas niet in het hotel.");
            }
        }
    }

    /*
        Deze methode regelt het checkuit command
        door de kamer met het meegegeven kamernummer
        leeg te maken.
    */
    public static void checkUit()
    {
        System.out.print("Welk kamernummer? ");
        int getal;

        if(getalScanner.hasNextInt())
        {
            getal = getalScanner.nextInt();
            if(!hotel.getKamer(getal).kamerIsLeeg())
            {
                hotel.checkUit(getal);
            }
        }
    }
}