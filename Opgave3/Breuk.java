/**
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 * Deze klasse slaat een breuk op door de teller en noemer op te slaan als int.
 * Verder vereenvoudigt deze klasse elke breuk (2/4 wordt bijvoorbeeld 1/2).
 * Ook geeft deze klasse een aantal methodes om breuken op te tellen,
 * af te trekken, te vermenigvuldigen, te delen en om de omgekeerde van
 * een breuk te geven.
 * Daarnaast is er nog een public method toString om te zorgen dat de breuken
 * goed geprint worden en een methode equals om te kijken of twee breuken hetzelfde
 * zijn.
 */


public class Breuk
{
    int teller;
    int noemer;

    /*
        Dit is en constructor om een breuk te maken met twee ints als input.
        Het vermenigvuldigt de teller en noemer met -1 als de noemer negatief is
        en vereenvoudigt de breuk vervolgens met het algoritme van Euclides.
        Daarnaast verandert hij de noemer in 1 als die 0 is.
    */
    Breuk(int teller, int noemer)
    {
        if(noemer < 0)
        {
            teller *= -1;
            noemer *= -1;
            this.teller = teller;
            this.noemer = noemer;
        }
        else if(noemer == 0)
        {
            this.teller = teller;
            this.noemer++;
        }
        else
        {
            this.teller = teller;
            this.noemer = noemer;
        }

        int ggd = euclidesAlgoritme(this.teller, this.noemer);
        this.teller = this.teller / ggd;
        this.noemer = this.noemer / ggd;
    }

    /*
        Dit is een constructor om een breuk te maken met een teller als input en
        noemer 1. Dit doet hij door het door te te sturen naar de vorige constructor
    */
    Breuk(int teller)
    {
        this(teller, 1);
    }

    /*
        Dit doet hetzelfde als de constructor hierboven, maar dan is er geen input
        en geeft hij teller 0 en noemer 1 mee.
    */
    Breuk()
    {
        this(0, 1);
    }

    /*
        Dit kopieert de breuk in de input door de teller en noemer van de breuk
        door te sturen naar de bovenste constructor
    */
    Breuk(Breuk breuk)
    {
        this(breuk.teller, breuk.noemer);
    }

    /*
        Deze methode maakt een nieuwe breuk aan die beide breuken bij elkaar
        opgeteld is. Daarna verandert hij deze in een kopie van zichzelf om
        de breuk te vereenvoudigen en returned hij hem.
    */
    Breuk telOp(Breuk b)
    {
        Breuk opgeteldeBreuk = new Breuk();
        opgeteldeBreuk.teller = (teller * b.noemer) + (noemer * b.teller);
        opgeteldeBreuk.noemer = (noemer * b.noemer);
        opgeteldeBreuk = new Breuk(opgeteldeBreuk);
        return opgeteldeBreuk;
    }

    /*
        Deze methode doet hetzelfde als het commentaar bij telOp beschrijft
        maar dan met aftrekken.
    */
    Breuk trekAf(Breuk b)
    {
        Breuk afgeteldeBreuk = new Breuk();
        afgeteldeBreuk.teller = (teller * b.noemer) - (noemer * b.teller);
        afgeteldeBreuk.noemer = (noemer * b.noemer);
        afgeteldeBreuk = new Breuk(afgeteldeBreuk);
        return afgeteldeBreuk;
    }

    /*
        Deze methode doet hetzelfde als het commentaar bij telOp beschrijft
        maar dan met vermenigvuldigen.
    */
    Breuk vermenigvuldig(Breuk b)
    {
        Breuk vermenigvuldigdeBreuk = new Breuk();
        vermenigvuldigdeBreuk.teller = (teller * b.teller);
        vermenigvuldigdeBreuk.noemer = (noemer * b.noemer);
        vermenigvuldigdeBreuk = new Breuk(vermenigvuldigdeBreuk);
        return vermenigvuldigdeBreuk;
    }

    /*
        Deze methode draait de tweede breuk om en vermenigvuldigt ze vervolgens.
        Verder doet hij hetzelfde als wat het commentaar bij vermenigvuldig beschrijft.
    */
    Breuk deel(Breuk b)
    {
        Breuk gedeeldeBreuk = new Breuk();
        b = b.omgekeerde();
        gedeeldeBreuk = vermenigvuldig(b);
        gedeeldeBreuk = new Breuk(gedeeldeBreuk);
        return gedeeldeBreuk;

    }

    /*
        Deze methode draait de breuk door een nieuwe breuk te maken
        waarbij de teller en noemer van het orgineel zijn omgedraaid.
    */
    Breuk omgekeerde()
    {
        Breuk omgekeerdeBreuk = new Breuk();
        omgekeerdeBreuk.teller = this.noemer;
        omgekeerdeBreuk.noemer = this.teller;
        omgekeerdeBreuk = new Breuk(omgekeerdeBreuk);
        return omgekeerdeBreuk;
    }

    /*
        Deze methode vervangt de standaard toString methode en zorgt
        ervoor dat de breuken goed gepprint worden.
    */
    public String toString()
    {
        /*
            Als de teller 0 is of de noemer 1 is wordt alleen de teller geprint
            en als de breuk een geheel getal representeerd wordt dit getal geprint.
        */
        if(teller == 0 || noemer == 1)
        {
            return(this.teller + "");
        }

        if(teller != noemer)
        {
            return (this.teller + "/" + this.noemer);
        }
        else
        {
            return (this.teller / this.teller + "");
        }
    }

    /*
        Deze methode kijkt of 2 breuken hetzelfde zijn.
    */
    boolean equals(Breuk b)
    {
       return this.teller == b.teller && this.noemer == b.noemer;
    }

    /*
        Deze methode vindt de grootste gemeenschappelijke deler van de teller
        en de noemer door middel van het algoritme van Euclides.
        Eerst wordt de teller positief gemaakt, daarna wordt steeds de noemer
        van de teller afgetrokken als de teller groter is dan de noemer
        of de teller van de noemer afgetrokken als de noemer groter is.
        Dit gaat door totdat de teller deelbaar is door de noemer.
        In dit geval is de overgebleven noemer de grootste gemeenschappelijke
        deller en deze wordt gereturned.
    */
    int euclidesAlgoritme(int a, int b)
    {
        if(a < 0)
        {
            a = -a;
        }

        while(a % b != 0)
        {
            if(a > b)
            {
                a -= b;
            }
            else
            {
                b -= a;
            }
        }
        return b;
    }
}