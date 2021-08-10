import java.util.*;
import java.lang.*;
import java.io.IOException;
import java.io.File;

class Polynoom implements PolynoomInterface
{
    /*
        Dit is de arraylist waar de termen van het polynoom
        in worden opgeslagen.
    */
    private ArrayList<Paar> termen;

    /*
        Dit maakt een polynoom zonder termen aan.
    */
    Polynoom()
    {
        termen = new ArrayList<Paar>();
    }

    /*
        Dit leest een bestand uit en voegt de paren
        toe als termen.
    */
    Polynoom(File bestand)
    {
        this(leesPolynoom(bestand));
    }

    /*
        Dit leest een rij doubles uit en voegt de paren
        toe als termen.
    */
    /*
        Dit maakt van twee opeenvolgende doubles een paar,
        en voegt deze als term toe aan het polynoom.
        Als de lengte van de rij getallen oneven is geeft hij
        een Exception.
        Daarna sorteert em vereenvoudigt hij deze.
    */
    Polynoom(double... lijst)
    {
        this(leesDoubles(lijst));
    }

    /*
        Dit maakt een polynoom van een arraylist
        en sorteert en vereenvoudigd die.
    */
    Polynoom(ArrayList<Paar> termen)
    {
        this.termen = termen;
        Collections.sort(this.termen);
        this.termen = this.vereenvoudig();
    }

    /*
        Dit kopieert een polynoom.
    */
    Polynoom(Polynoom p)
    {
        this(p.termen);
    }

    /*
        Dit telt 2 polynomen op door een polynoom te maken
        met de termen van beide polynomen en dit polynoom
        te vereenvoudigen.
    */
    public Polynoom telOp(Polynoom ander)
    {
        Polynoom opgeteld = new Polynoom();
        opgeteld.termen.addAll(this.termen);
        opgeteld.termen.addAll(ander.termen);
        opgeteld = new Polynoom(opgeteld);
        return opgeteld;
    }

    /*
        Dit trek 2 polynomen van elkaar of door een polynoom te maken
        met de termen van het eerste polynoom en daar de termen van
        het tweede polynoom, keer -1, bij op te tellen.
        Daarna wordt het vereenvoudigd.
    */
    public Polynoom trekAf(Polynoom ander)
    {
        Polynoom afgetrokken = new Polynoom();
        afgetrokken = this.telOp(ander.vermenigvuldig(new Polynoom(-1, 0)));
        afgetrokken = new Polynoom(afgetrokken);
        return afgetrokken;
    }

    /*
        Dit vermenigvuldigt 2 polynomen door alle termen
        met elkaar te vermenigvuldigen.
        Daarna wordt het vereenvoudigd.
    */
    public Polynoom vermenigvuldig(Polynoom ander)
    {
        Polynoom vermenigvuldigd = new Polynoom();
        for(int i = 0; i < this.termen.size(); i++)
        {
            for(int y = 0; y < ander.termen.size(); y++)
            {
                vermenigvuldigd.termen.add(this.termen.get(i).vermenigvuldig(ander.termen.get(y)));
            }
        }
        vermenigvuldigd = new Polynoom(vermenigvuldigd);
        return vermenigvuldigd;
    }

    /*
        Dit differentieert een polynoom door elk paar
        te differentieeren.
        Daarna wordt het vereenvoudigd.
    */
    public Polynoom differentieer()
    {
        Polynoom gedifferentieerd = new Polynoom();
        for (Paar p : this.termen)
        {
            gedifferentieerd.termen.add(p.differentieer());
        }
        gedifferentieerd = new Polynoom(gedifferentieerd);
        return gedifferentieerd;
    }

    /*
        Dit integreert een polynoom door elk paar te integreren.
        Daarna wordt het vereenvoudigd.
    */
    public Polynoom integreer(double c) {
        Polynoom geintegreerd = new Polynoom();
        for (Paar p : this.termen)
        {
            geintegreerd.termen.add(p.integreer());
        }
        geintegreerd.termen.add(new Paar(c, 0));
        geintegreerd = new Polynoom(geintegreerd);
        return geintegreerd;
    }

    /*
        Dit vereenvoudigt het polynoom door de paren
        met gelijke machten bij elkaar op te tellen.
    */
    public ArrayList<Paar> vereenvoudig()
    {
        /*
            Dit maakt twee polynomen, waarvan 1 een kopie
            is van het polynoom dat vereenvoudigt moet worden.
        */
        Polynoom vereenvoudigd = new Polynoom();
        Polynoom onvereenvoudigd = new Polynoom();
        onvereenvoudigd.termen.addAll(this.termen);

        /*
            Zolang de laatste term nog niet bereikt is,
            wordt er gekeken of het volgende paar dezelfde macht
            heeft als het huidige paar. Als dat zo is worden ze bij
            elkaar opgeteld en wordt het volgende paar verwijdert.
            Als dat niet zo is verplaatst het huidige paar 1 naar rechts.
            Op deze manier blijft hij de paren optellen zolang de machten
            gellijk zijn en dan gaat hij verder naar de volgende macht.
        */
        int i = 0;
        while(i < onvereenvoudigd.termen.size() - 1)
        {
            if(onvereenvoudigd.termen.get(i).geefMacht() == onvereenvoudigd.termen.get(i + 1).geefMacht())
            {
                onvereenvoudigd.termen.set(i, onvereenvoudigd.termen.get(i).telOp(onvereenvoudigd.termen.get(i + 1)));
                onvereenvoudigd.termen.remove(i + 1);
            }
            else
            {
                i++;
            }
        }

        vereenvoudigd.termen.addAll(onvereenvoudigd.termen);
        return vereenvoudigd.termen;
    }

    /*
        Dit kijkt of 2 polynomen gelijk zijn.
    */
    public boolean equals(Polynoom ander) {
        /*
            Als de 2 polynomen een andere lengte hebben kunnen ze niet
            gelijk zijn.
        */
        if(this.termen.size() != ander.termen.size())
        {
            return false;
        }

        /*
            Dit bekijkt voor elke term of het paar op die plek in het eerste
            polynomen hetzelfde is als het paar op dezelfde plek
            in het tweede polynoom.
            Als dat niet zo is returned hij false.
            Als dat voor elke term zo is retured hij true.
        */
        for(int i = 0; i < this.termen.size(); i++)
        {
            if(!this.termen.get(i).equals(ander.termen.get(i)))
            {
                return false;
            }
        }
        return true;
    }

    /*
        Dit kijkt of een object hetzlefde is als een polynoom.
        Hij kijkt of het object een polynoom is en als dat
        zo is maakt hij er een polynoom van en gebruikt hij de equals
        methode hierboven.
    */
    public boolean equals(Object o)
    {
        if (o instanceof Polynoom)
            return this.equals((Polynoom)o);

        return false;
    }

    /*
        Dit zet het polynoom om in een string om te printen.
    */
    public String toString()
    {

        /*
            Dit maakt een string aan waaar de termen
            aan worden toegevoegd.
        */
        String polynoomString;

        /*
            Als het polynoom leeg is print hij 0.
        */
        if(termen.size() == 0)
        {
            return("0");
        }

        /*
            Als de eerste term negatief is wordt het paar vermenigvuldigt
            met -1 en wordt er een min en een spatie voorgeplakt.
            Daarna voegt hij het paar toe aan polynoomString.
        */
        if(termen.get(0).geefCoefficient() < 0)
        {
            polynoomString = "- " + termen.get(0).vermenigvuldig(new Paar(-1, 0)) + "";
        }
        else
        {
            polynoomString = termen.get(0) + "";
        }

        /*
            Voor elke andere term kijk hij of hij negatief is.
            Als dat zo is vermenigvuldigt hij dit paar met -1
            en plakt hij er een - en een spatie voor, anders
            plakt hij er gewoon + en een spatie voor.
            Daarna wordt dit paar toegevoegd aan polynoomString.
        */
        for (int i = 1; i < termen.size(); i++)
        {
            if(termen.get(i).geefCoefficient() > 0)
            {
                polynoomString += " + " + termen.get(i);
            }
            else if(termen.get(i).geefCoefficient() < 0)
            {
                polynoomString += " - " + termen.get(i).vermenigvuldig(new Paar(-1, 0));
            }
        }
        return polynoomString;
    }

    /*
        Dit leest een bestand en maakt er termen van.
    */
    private static ArrayList<Paar> leesPolynoom(File bestand)
    {
        ArrayList<Paar> polynoomTermen = new ArrayList<Paar>();

        /*
            Dit probeert het bestand in te lezen en anders geeft het
            een exception.
        */
        try
        {
            Scanner input = new Scanner(bestand);

            /*
                Zolang er een nieuwe regel is, splitst hij deze
                bij de komma en maakt hij een paar van het eerste en het tweede
                deel. Als de regel in meer of minder dan 2 delen geplitst wordt,
                dan throwt hij een exception.
            */
            while (input.hasNextLine())
            {
                /*
                    Dit splist de regel in bij de komma op in delen.
                */
                String[] regel = input.nextLine().split(",");

                for (int i = 0; i < regel.length; i++)
                {
                    regel[i] = regel[i].trim();
                }

                /*
                    Dit kijkt of er twee delen zijn en probeert dan een paar te maken hiervan.
                */
                if(regel.length != 2)
                {
                    throw new IllegalArgumentException("Er staat een fout in het bestand.");
                }
                else
                {
                    try
                    {
                        polynoomTermen.add(new Paar(Double.parseDouble(regel[0]), Integer.parseInt(regel[1])));
                    }
                    catch(IllegalArgumentException e)
                    {
                        throw new IllegalArgumentException("Er is iets fout gegaan met inlezen.");
                    }

                }
            }
            input.close();
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException("Er is iets fout gegaan met inlezen.");
        }

        return polynoomTermen;
    }

    private static ArrayList<Paar> leesDoubles(double... lijst) {
        try
        {
            ArrayList<Paar> termen = new ArrayList<Paar>();
            for (int i = 0; i < lijst.length; i = i + 2)
            {
                termen.add(new Paar(lijst[i], (int) lijst[i + 1]));
            }
            return termen;
        }
        catch(Exception e)
        {
            throw new IndexOutOfBoundsException("De rij getallen heeft een oneven lengte.");
        }
    }
}
