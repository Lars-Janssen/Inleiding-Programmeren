/**
 * lindenmayer
 *
 * Deze klasse wordt gebruikt om de lindemayerstring te
 * maken en uit te lezen.
 */
public class Lindenmayer
{
    private String lindenmayerString;
    private int length;
    private int recursieDiepte;
    private Regels regels;

    /*
        Dit verkrijgt de String en de recursieDiepte.
    */
    Lindenmayer(Regels regels)
    {
        this.regels = regels;
        lindenmayerString = this.regels.getMap().get('a');
        recursieDiepte = Integer.parseInt(this.regels.getMap().get('r'));
    }

    /*
        Dit returned de String.
    */
    public String getString()
    {
        return lindenmayerString;
    }

    /*
        Dit returned de lengte van de String.
    */
    public int getLength()
    {
        return length;
    }

    /*
        Dit returned de recursiediepte.
    */
    public int getRecursiediepte()
    {
        return recursieDiepte;
    }

    /*
        Dit genereert de lindenmayerString.
    */
    public String genereerString(int diepte)
    {
        /*
            Dit kijkt of de recursiediepte bereikt als.
            Als dat niet zo is, genereert hij de nieuwe String
            en als dat wel zo is returned hij de String.
        */
        if(diepte < recursieDiepte)
        {
            String nieuweString = "";

            /*
                Dit kijkt voor elk karakter in de string of het een key is.
                Als dat zo is voegt hij de value toe aan de nieuwe String,
                anders voegt hij het karakter zelf toe aan de nieuwe Strigg.
            */
            for(int i = 0; i < lindenmayerString.length(); i++)
            {
                char karakter = lindenmayerString.charAt(i);
                String toevoeging = regels.getMap().get(karakter);
                if(toevoeging != null)
                {
                    nieuweString += toevoeging;
                }
                else
                {
                    nieuweString += karakter;
                }
            }
            lindenmayerString = nieuweString;
            diepte++;
            genereerString(diepte);
        }
        length = lindenmayerString.length();
        return lindenmayerString;
    }
}