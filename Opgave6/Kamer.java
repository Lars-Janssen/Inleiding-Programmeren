/*
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 * Deze abstracte klasse slaat een kamer op door een groep
 * met de gasten bij te houden.
*/

@SuppressWarnings("unchecked")
abstract class Kamer implements KamerInterface
{
    private final VasteGrootteGroep gasten;

    /*
        Dit maakt een kamer van een bepaalde grootte
        door een VasteGrootteGroep met die lengte te maken.
    */
    Kamer(int grootte)
    {
        gasten = new VasteGrootteGroep(grootte);
    }

    /*
        Dit voegt gasten toe aan de kamer als die groep
        past en de kamer leeg is, anders gooit het exceptions.
    */
    public void voegGastenToe(Groep g) throws Exception
    {
        /*
            Dit kijkt of de kamer leeg is en gooit anders een exception.
        */
        if(gasten.isLeeg())
        {
            /*
                Dit kijkt of de gasten in de kamer passen
                en gooit anders een exception.
            */
            if(g.getCapaciteit() <= gasten.getCapaciteit())
            {
                /*
                    Dit voegt de gasten aan de kamer toe.
                */
                for(int i = 0; i < g.getCapaciteit(); i++)
                {
                    gasten.add(g.get(i));
                }
            }
            else
            {
                throw new KamerTeKleinException();
            }
        }
        else
        {
            throw new KamerAlBezetException();
        }
    }

    /*
        Dit maakt de kamer leeg.
    */
    public void maakKamerLeeg()
    {
        gasten.maakLeeg();
    }

    /*
        Dit kijkt of een kamer leeg is.
    */
    public boolean kamerIsLeeg()
    {
        return gasten.isLeeg();
    }

    /*
        Dit returned de gasten in de kamer.
    */
    public VasteGrootteGroep<Gast> getGasten()
    {
        VasteGrootteGroep<Gast> gastReturn = new VasteGrootteGroep<>(gasten.getCapaciteit());
        /*
            Alle plekken die niet leeg zijn worden teoegevoegd aan de
            gastReturn groep.
        */
        for(int i = 0; i < gasten.getPlek(); i++) {
            gastReturn.add((Gast) gasten.get(i));;
        }
        return gastReturn;
    }

    /*
        Dit returned de grootte van een kamer.
    */
    public int getGrootte()
    {
        return gasten.getCapaciteit();
    }
}

/*
    Deze klasse maakt een eenpersoonskamer
    door een kamer met grootte 1 te maken.
*/
class EenPersoonsKamer extends Kamer
{
    EenPersoonsKamer()
    {
        super(1);
    }
}

/*
    Deze klasse maakt een tweepersoonskamer
    door een kamer met grootte twee te maken.
*/
class TweePersoonsKamer extends Kamer
{
    TweePersoonsKamer()
    {
        super(2);
    }
}

/*
    Deze klasse maakt een vierpersoonskamer
    door een kamer met grootte vier te maken.
*/
class VierPersoonsKamer extends Kamer
{
    VierPersoonsKamer()
    {
        super(4);
    }
}

/*
    Deze klasse gooit een exception als de kamer te klein is.
*/
class KamerTeKleinException extends Exception
{
    KamerTeKleinException()
    {
        super("De kamer is te klein.");
    }
}

/*
    Deze klasse gooit een exception als de kamer niet leeg is.
*/
class KamerAlBezetException extends Exception
{
    KamerAlBezetException()
    {
        super("De kamer is al bezet.");
    }
}