/**
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 * Deze klasse extend groep en zorgt ervoor dat
 * de groep een vaste grootte heeft.
*/

import java.util.*;

public class VasteGrootteGroep<T> extends Groep<T>
{
    /*
        Dit maakt een groep met vaste grootte i door
        de constructor van groep te gebruiken.
    */
    VasteGrootteGroep(int lengte)
    {
        super(lengte);
    }

    /*
        Dit override de add methode van groep en zorgt ervoor
        dat er alleen een nieuw element toegevoegd kan worden
        als hij niet vol zit. Als hij zol zit en je wil iets
        toevoegen gooit hij een GroepVolException.
    */
    @Override
    public void add(T nieuw)
    {
        if(super.getPlek() < super.getCapaciteit())
        {
            super.add(nieuw);
        }
        else
        {
            throw new GroepVolException();
        }
    }
}

/*
    Dit gooit de GroepVolException.
*/
class GroepVolException extends RuntimeException
{
    GroepVolException()
    {
        super("De groep is vol.");
    }
}