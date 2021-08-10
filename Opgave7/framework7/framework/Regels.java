/**
 * Regels
 *
 * Dit is een klasse om de property bestanden uit te lezen.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Regels
{

    private InputStream invoer;
    private String[] inleesregels;

    /*
        Dit maakt een map voor de regels met een String als value
        en een map voor de turtle eigenschappen met een double als
        value;
    */
    private Map<Character, String> map = new HashMap<Character, String>();
    private Map<String, Double> turtleMap = new HashMap<String, Double>();


    Regels(String bestandNaam)
    {
        Properties prop = new Properties();

        /*
            Dit probeert het bestand uit te lezen.
        */
        try
        {
            /*
                Dit verkrijgt het bestand
            */
            invoer = new FileInputStream("invoer/" + bestandNaam + ".properties");
            prop.load(invoer);

            /*
                Dit voegt de naam, recursiediepte en het axioma toe aan de
                map voor de regels.
            */
            map.put('n', prop.getProperty("name"));
            map.put('r' ,prop.getProperty("recursion.depth"));
            map.put('a', prop.getProperty("axiom"));

            /*
                Dit voegt de rest van de properties toe aan de turtlemap,
                (behalve de rules).
            */
            turtleMap.put("x", Double.parseDouble(prop.getProperty("turtle.pos.x")));
            turtleMap.put("y", Double.parseDouble(prop.getProperty("turtle.pos.y")));
            turtleMap.put("hoek", Double.parseDouble(prop.getProperty("turtle.angle")));
            turtleMap.put("lengte", Double.parseDouble(prop.getProperty("turtle.line.length")));
            turtleMap.put("breedte", Double.parseDouble(prop.getProperty("turtle.line.width")));
            turtleMap.put("hoekverandering", Double.parseDouble(prop.getProperty("turtle.angle.modifier")));

            /*
                Dit splitst de rules property in delen en voegt deze individueel
                toe aan de map voor regels.
            */
            inleesregels = prop.getProperty("rules").split(",");
            for(int i = 0; i < inleesregels.length; i++)
            {
                String[] huidigeRegel = inleesregels[i].split(" ");

                /*
                    Het eerste deel van de regel wordt de key, het tweede deel de value.
                */
                map.put(huidigeRegel[0].toCharArray()[0], huidigeRegel[1]);
            }
            invoer.close();
        }
        catch(Exception e)
        {
        }
    }

    /*
        Dit returned de map voor de regels.
    */
    public Map<Character, String> getMap()
    {
        return map;
    }

    /*
        Dit returned de turtlemap.
    */
    public Map<String, Double> getTurtleMap()
    {
        return turtleMap;
    }
}