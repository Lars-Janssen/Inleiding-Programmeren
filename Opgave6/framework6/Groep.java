/**
 *
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 * Deze klasse slaat een groep van type T op.
*/

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class Groep<T> implements GroepInterface<T>, Iterable<T>
{
    /*
        Dit maakt een array om de elementen van de groep in
        op te slaan en nog wat andere nuttige variabelen.
    */
    private Object groepArray[];
    private int capiciteit;
    private int plek = 0;
    private int origineleCapiciteit;

    /*
        Dit maakt een groep met 1 element erin.
        (Ik weet niet zeker of dit bedoeld werd in de opdracht.)
    */
    Groep(Object element)
    {
        this(1);
        groepArray[0] = element;
        plek++;
    }

    /*
        Dit maakt een groep met een bepaalde lengte.
    */
    Groep(int lengte)
    {
        groepArray = new Object[lengte];
        capiciteit = groepArray.length;
        origineleCapiciteit = lengte;
    }

    /*
        Dit maakt een lege groep met lengte 1.
    */
    Groep()
    {
        this(1);
    }

    /*
        Dit returned de plek in de array van de groep.
    */
    public int getPlek()
    {
        return plek;
    }

    /*
        Dit returned het object op plek i in de array.
    */
    public T get(int index)
    {
        return (T) groepArray[index];
    }

    /*
        Dit returned de capiciteit van de groep.
    */
    public int getCapaciteit()
    {
        return capiciteit;
    }

    /*
        Dit returned de groep.
    */
    public Object[] getGroepArray()
    {
        return groepArray;
    }

    /*
        Dit voegt een object van type T toe aan de groep.
        ALs de array te klein is verdubbelt hij in lengte.
    */
    public void add(T nieuw)
    {
        if(plek == groepArray.length)
        {
            maakGroter();
        }
        groepArray[plek] = nieuw;
        plek++;
    }

    /*
        Dit maakt de array groter door een nieuwe array met de
        dubbele lengte te maken en daar alle elementen in te zetten.
        Daarna maakt hij de originele array deze nieuwe array.
    */
    public void maakGroter()
    {
        Object tijdelijkeArray[] = new Object[groepArray.length * 2];

        for(int i = 0; i < groepArray.length; i++)
        {
            tijdelijkeArray[i] = groepArray[i];
        }

        groepArray = tijdelijkeArray;
        capiciteit = groepArray.length;
    }

    /*
        Dit kijkt of alle elementen in de groep null zijn.
    */
    public boolean isLeeg()
    {

        for(int i = 0; i < this.getCapaciteit(); i++)
        {
            if(this.get(i) != null)
            {
                return false;
            }
        }

        return true;
    }

    /*
        Dit maakt de groep leeg door een nieuwe groep met de
        originele capiciteit te maken.
    */
    public void maakLeeg()
    {
        Object[] tijdelijkeArray = new Object[origineleCapiciteit];
        groepArray = tijdelijkeArray;
    }

    /*
        Dit returned de groep zodt hij geprint kan worden.
    */
    @Override
    public String toString()
    {
        String groepPrint = "";
        /*
            Dit loopt door alle elementen in de groep die niet nul zijn
            en voegt deze toe aan de te printen String. Als het volgende
            element niet nul is en het element niet het laatste element is
            voegt hij er "\n" aan toe.
        */
        for(int i = 0; i < plek; i++)
        {
            groepPrint += this.get(i);

            if(i + 1 != plek)
            {
                groepPrint += "\n";
            }
        }
        return groepPrint;
    }

    /*
        Dit returend een nieuwe iterator die door de groep itereert.
    */
    public Iterator<T> iterator()
    {
        return this .new GroepIterator();
    }

    /*
        Dit itereert door de groep.
    */
    private class GroepIterator implements Iterator<T>
    {
        int teller = 0;

        public T next()
        {
            return get(teller++);
        }
        public boolean hasNext()
        {
            return teller < groepArray.length;
        }
    }
}