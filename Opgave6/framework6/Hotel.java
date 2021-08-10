/**
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 * Deze klasse slaat een hotel op als
 * een VasteGrootteGroep van kamers.
*/

import java.math.*;

@SuppressWarnings("unchecked")
public class Hotel implements HotelInterface
{
    private VasteGrootteGroep kamers;
    private int aantalKamers;
    private static final String[] NAAM_STEDEN =
    {
        "Budapest", "Bucharest", "Chisinau", "Tbilisi", "Munich", "Warsaw",
        "Cluj", "Moscow", "Zagreb", "Prague", "Ljubljana", "California"
    };
    private static final String[] NAAM_TEMPLATES =
    {
    "Grand %s Hotel", "Royal %s Hotel", "Hotel %s", "%s Hotel",
    "Hotel of %s"
    };

    private String hotelNaam = String.format(NAAM_TEMPLATES[(int) (Math.random() * NAAM_TEMPLATES.length)],
                                     NAAM_STEDEN[(int) (Math.random() * NAAM_STEDEN.length)]);

    /*
        Dit maakt een hotel met 10 kamers als de gebruiker
        geen aantal meegeeft.
    */
    Hotel()
    {
        this(new VasteGrootteGroep<>(10));
    }

    /*
        Dit maakt een hotel met een bepaald aantal kamers.
    */
    Hotel(int aantalKamers)
    {
        this(new VasteGrootteGroep<>(aantalKamers));
    }

    /*
        Dit vult het hotel met een kwart eenpersoonskamers,
        een kwart vierpersoonskamers en de rest tweepersoonskamers.
    */
    Hotel(VasteGrootteGroep kamers)
    {
        this.kamers = new VasteGrootteGroep<>(kamers.getCapaciteit());
        aantalKamers = kamers.getCapaciteit();
        int eenKwart = aantalKamers / 4;
        for(int i = 0; i < eenKwart; i++)
        {
            this.kamers.add(new EenPersoonsKamer());
        }

        for(int i = eenKwart; i < aantalKamers - eenKwart; i++)
        {
            this.kamers.add(new TweePersoonsKamer());
        }

        for(int i = aantalKamers - eenKwart; i < aantalKamers; i++)
        {
            this.kamers.add(new VierPersoonsKamer());
        }

    }

    /*
        Dit probeert een groep in te checken door voor elke
        kamer te kijken of hij er in past en te stoppen als dat het
        geval is. Als de groep niet in het hotel past gooit hij een
        GroepPastNietException
    */
    public void checkIn(Groep<Gast> groep) throws GroepPastNietException
    {
        for(int i = 0; i < aantalKamers; i++)
        {
            try
            {
                Kamer huidigeKamer = (Kamer) kamers.get(i);
                huidigeKamer.voegGastenToe(groep);
                break;
            }
            catch(Exception e)
            {}

            if(i == aantalKamers - 1)
            {
                throw new GroepPastNietException();
            }
        }
    }

    /*
        Dit checkt een kamer uit.
    */
    public void checkUit(int kamernummer)
    {
        this.getKamer(kamernummer).maakKamerLeeg();
    }

    /*
        Dit returned de huidige kamer.
    */
    public Kamer getKamer(int nummer)
    {
        Kamer huidigeKamer = (Kamer) kamers.get(nummer);
        return huidigeKamer;
    }

    /*
        Dit returned het aantal kamers in het hotel.
    */
    public int getAantalKamers()
    {
        return aantalKamers;
    }

    /*
        Dit genereert een willekeurige naam voor het hotel
        en returned die.
    */
    @Override
    public String toString()
    {
        return hotelNaam;
    }
}

/*
    Deze klasse gooit een exception als de groep niet in
    het hotel past.
*/
class GroepPastNietException extends Exception
{
    GroepPastNietException()
    {
        super("Deze groep past helaas niet in het hotel.");
    }
}