/**
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 *  Dit programma  vraagt de gebruiker om een zin.
 *  Vervolgens filtert het de zin zodat er alleen nog kleine letters, cijfers en spaties in staan.
 *  Daarna geeft het een aantal statistieken over de zin:
 *  het aantal woorden, het aantal klinkers en of de zin een palindroom is.
 *  (Een palindroom is een zin/woord die achterstevoren zonder spaties hetzelfde is als van voren.
 *  Bijvoorbeeld pop of tarwewrat.)
 **/

 import java.util.Scanner;

public class Opgave2
{
    public static void main(String[] args)
    {
        String ongefilterdeZin;
        String gefilterdeZin;
        String korteZin;
        String karakter;
        char[] zinKarakters;
        int aantalKlinkers;
        int aantalWoorden;
        int[] frequenties;
        boolean isPalindroom;
        Scanner lezer = new Scanner(System.in);

        System.out.println("Voer een zin in:");

        /*
            Het leest de zin en verkrijgt de benodigde waardes door verschillende functies aan te roepen.
        */
        ongefilterdeZin = lezer.nextLine();
        lezer.close();
        gefilterdeZin = filteren(ongefilterdeZin);
        zinKarakters = gefilterdeZin.toCharArray();
        korteZin = inkorten(zinKarakters, gefilterdeZin);
        aantalWoorden = woordenBepalen(zinKarakters);
        aantalKlinkers = klinkersBepalen(zinKarakters);
        isPalindroom = palindroomBepalen(zinKarakters);
        frequenties = frequentiesBepalen(zinKarakters);

        /*
            Het verandert "karakters" in "karakter" bij het printen als er maar 1 karakter in de zin is.
        */
        if(gefilterdeZin.length() != 1)
        {
            karakter = "karakters";
        }
        else
        {
            karakter = "karakter";
        }

        /*
            Het print de statistieken en roept de functie aan om de grafiek te maken.
        */
        System.out.println("Lengte ongefilterde zin: " + ongefilterdeZin.length() + " " + karakter);
        System.out.println("Gefilterd:");
        System.out.println(korteZin);
        System.out.println("Lengte gefilterde zin:     "  + gefilterdeZin.length() + " " + karakter);
        System.out.println("Aantal woorden:            " + aantalWoorden);
        System.out.println("Aantal klinkers:           " + aantalKlinkers);
        System.out.println("Palindroom?                " + isPalindroom);
        System.out.println("----------------------------------------------------");
        System.out.println("Ter controle de frequenties van alle karakters");
        histogram(frequenties);
    }


    /*
        Het filtert de ingevoerde zin door eerst alle hoofdletters om te zetten naar kleine letters
        en daarna een nieuwe String te maken en daar de toegestane karakters aan toe te voegen.
        Aan het einde haalt het de spaties aan het begin en einde van de gefilterde zin ervanaf.
    */
    static String filteren(String zin)
    {
        char[] zinKarakters = zin.toCharArray();
        String gefilterd = "";

        for (int i = 0; i < zinKarakters.length; i++)
        {

            /*
                Het zet de hoofdletters om naar kleine letters.
            */
            if(zinKarakters[i] >= 'A' && zinKarakters[i] <= 'Z')
            {
                zinKarakters[i] += ('a' - 'A');
            }

            /*
                Het voegt de toegestane karakters toe aan de gefilterde zin.
            */
            if(zinKarakters[i] >= '0' && zinKarakters[i] <= '9')
            {
                gefilterd += zinKarakters[i];
            }
            else if(zinKarakters[i] >= 'a' && zinKarakters[i] <= 'z')
            {
                gefilterd += zinKarakters[i];
            }
            else if(zinKarakters[i] == ' ')
            {
                gefilterd += zinKarakters[i];
            }
        }

        gefilterd = gefilterd.trim();
        return gefilterd;
    }


    /*
        Het kort de zin in als hij langer dan 80 karakters is.
        Hij kiest daarna een knippunt in de zin zodat er niet in de woorden geknipt wordt en de ingekorte
        zin korter dan 80 karakters is.
    */
    static String inkorten(char[] zinKarakters,String zin)
    {
        String ingekort = "";
        int knippunt = 0;

        if(zinKarakters.length <= 80)
        {
            return zin;
        }

        /*
            Het zoekt de laatste spatie voor het tachtigste karakter en knipt daar.
        */
        for (int i = 79; i > 0; i--)
        {
            if(zinKarakters[i] == ' ')
            {
                knippunt = i;
                break;
            }
        }

        /*
            Het voegt alle karakters tot het knippunt toe aan de ingekorte zin.
        */
        for (int i = 0; i < knippunt; i++)
        {
            ingekort += zinKarakters[i];
        }

        ingekort += "...";
        return ingekort;
    }


    /*
        Het telt het aantal woorden in de zin door het aantal spaties te tellen
        waar geen spatie voor staat.
        Daarna voegt het nog een woord toe als de gefilterde zin niet "" is
        om het laatste woord te tellen.
    */
    static int woordenBepalen(char[] zinKarakters)
    {
        int aantalWoorden = 0;

        for(int i = 1; i < zinKarakters.length; i++)
        {
            if(zinKarakters[i] == ' ' && zinKarakters[i - 1] != ' ')
            {
                aantalWoorden++;
            }
        }

        if(zinKarakters.length != 0)
        {
            aantalWoorden++;
        }
        return aantalWoorden;
    }


    /*
        Het telt het aantal klinkers door voor elk karakter te kijken of het
        a, e, i, o, u of y is en het aantal klinkers dan te verhogen.
    */
    static int klinkersBepalen(char[] zinKarakters)
    {
        int aantalKlinkers = 0;

        for(char karakter : zinKarakters)
        {
            if(karakter == 'a' || karakter == 'e' || karakter == 'i'
               || karakter == 'o' || karakter == 'u' || karakter == 'y')
            {
                aantalKlinkers++;
            }
        }

        return aantalKlinkers;
    }


    /*
        Het bekijkt of de gefilterde zin een palindroom is door eerst de spaties weg te halen
        en daarna voor de karakters te kijken of ze hetzelfde zijn als het karakter op die plek,
        maar dan aan het einde van de zin. Dus de eerste en laatste plek enzovoort.
    */
    static boolean palindroomBepalen(char[] zinKarakters)
    {
        String zonderSpaties = "";
        char[] zonderSpatiesArray;

        /*
            Het maakt een lege String en voegt vervolgens steeds een karakter uit de zin toe als het geen spatie is.
            Daarna wordt de String in een char array omgezet.
        */
        for(char karakter : zinKarakters)
        {
            if(karakter != ' ')
            {
                zonderSpaties += karakter;
            }
        }

        zonderSpatiesArray = zonderSpaties.toCharArray();

        /*
            Het bekijkt of de karakters op de eerste en laatste plek hetzelfde zijn.
            Daarna bekijkt het of de karakters op de tweede en een na laatste plek hetzelfde zijn, enzovoort.
            Als de karakters niet aan het elkaar gelijk zijn returned het false
            en als de zin een palindroom is returned het true.
            De loop gaat naar de lengte / 2, omdat hij maar naar de helft van de zin hoeft,
            omdat de andere helft dan ook bekeken is.
        */
        for(int i = 0; i < zonderSpatiesArray.length / 2; i++)
        {
            if(zonderSpatiesArray[i] != zonderSpatiesArray[zonderSpatiesArray.length - i - 1])
            {
                return false;
            }
        }

        return true;
    }


    /*
        Het telt het aantal keer dat een bepaalde letter of cijfer of een spatie voorkomt
        ,zet dit in een int array en returned die array.
    */
    static int[] frequentiesBepalen(char[] zinKarakters)
    {
        /*
            Het maakt een array met een lengte van 37 voor de 26 letters + 10 cijfers + 1 spatie.
        */
        int[] frequentieArray = new int[37];


        /*
            Het neemt de ascii waarden van de karakters en haalt hier een waarde vanaf
            zodat ze op de goede plek in de array komen.
            0 heeft bijvoorbeeld een ascii waarde van 48 en moet op plek 26 van de array
            komen, dus haalt het er 48 vanaf en telt het er dan 26 bij op.
            De getallen 48, 32 en 97 zijn de asciiwaarden van 0, a en de spatie.
        */
        for(int i = 0; i < zinKarakters.length; i++)
        {
            if(zinKarakters[i] >= '0' && zinKarakters[i] <= '9')
            {
                frequentieArray[ (int) zinKarakters[i] - 48 + 26 ]++;
            }
            else if(zinKarakters[i] == ' ')
            {
                frequentieArray[ (int) zinKarakters[i] - 32 + 36 ]++;
            }
            else
            {
                frequentieArray[ (int) zinKarakters[i] - 97 ]++;
            }
        }

        return frequentieArray;
    }


    /*
        Het maakt een staafgrafiek waarin voor elk teken te zien is hoevaak het voorkomt.
        De grafiek is maximaal een aantal blokken hoog, wat bepaalt wordt door maxLengte.
        De stapgrootte van de grafiek wordt bepaald door het meest voorkomende teken en hoevaak
        deze voorkomt in de zin.
    */
    static void histogram(int[] frequenties)
    {
        int maxFrequentie = 0;
        int maxLengte = 12;
        int stapGrootte;

        /*
            Het berekent het maximaal aantal keer dat een karakter voorkomt.
        */
        for (int i = 0; i < frequenties.length; i++)
        {
            System.out.print(frequenties[i] + " ");
            if(maxFrequentie < frequenties[i])
            {
                maxFrequentie = frequenties[i];
            }
        }

        /*
            Het berekent de stapgrootte van de grafiek en de lengte van de grafiek
            als de grafiek korter kan zijn dan de maximumlengte.
        */
        stapGrootte = ( maxFrequentie / maxLengte ) + 1;

        if(maxFrequentie / stapGrootte < maxLengte)
        {
            maxLengte = maxFrequentie / stapGrootte;
        }

        /*
            Het print hoe vaak het meestvoorkomende karakter voorkomt en wat de stapgrootte is.
        */
        System.out.println();
        System.out.println("max is " + maxFrequentie + " en stapgrootte is " + stapGrootte);
        System.out.println("----------------------------------------------------");

        /*
            Het rint de grafiek door voor elke rij de stapgrootte met (i-1) te vermenigvuldigen
            en te kijken of het karakter vaak genoeg voorkomt.
            Er wordt vermeniguldigt met (i-1) omdat er gekeken of het karakter vaker voorkomt
            dan de vorige rij, die (i-1) keer de stapgrootte is.
            Als het karakter a bijvoorbeeld 23 keer voorkomt in een zin en stapgrootte 10 is,
            dan print hij dit:
            i = 4, dan 10 * 3 = 30, 30 > 23, dus er wordt een spatie geprint,
            i = 3, dan 10 * 2 = 20, 23 > 20, dus er wordt een * geprint,
            i = 2, dan 10 * 1 = 19, 23 > 10, dus er wordt een * geprint,
            i = 1, dan 10 * 0 = 0,  23 > 0,  dus er wordt een * geprint.
        */
        for (int i = maxLengte; i > 0; i--)
        {

            for (int y = 0; y < frequenties.length; y++)
            {

                if(frequenties[y] > stapGrootte * (i-1))
                {
                    System.out.print("* ");
                }
                else
                {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        /*
            Het print de karakters als legenda onder de grafiek.
        */
        System.out.println("a b c d e f g h i j k l m n o p q r s t u v w x y z 0 1 2 3 4 5 6 7 8 9  ");
    }
}