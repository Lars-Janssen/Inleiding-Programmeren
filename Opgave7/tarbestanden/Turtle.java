/**
 * Turtle
 *
 * Deze klasse volgt een schildpad op het scherm
 * en berekent de stappen die hij zet als je de karakters
 * van de lindenmayerString uitleest.
 */

import java.lang.Math;
import java.lang.reflect.Array;
import java.util.Stack;
import java.awt.geom.Point2D;

@SuppressWarnings("unchecked")
public class Turtle
{
    private double x;
    private double y;
    private double hoek;
    private double lengte;
    private double breedte;
    private double hoekverandering;
    private int huidigeLijn = 0;
    private Stack stack = new Stack<Array>();
    private boolean isPop;
    char karakter;

    /*
        Dit importeert de regels.
    */
    Turtle(Regels regels)
    {
        x = regels.getTurtleMap().get("x");
        y = regels.getTurtleMap().get("y");
        hoek = Math.toRadians(regels.getTurtleMap().get("hoek"));
        lengte = regels.getTurtleMap().get("lengte");
        breedte = regels.getTurtleMap().get("breedte");
        hoekverandering = Math.toRadians(regels.getTurtleMap().get("hoekverandering"));
    }

    /*
        Dit returned de breedte.
    */
    public double getBreedte()
    {
        return breedte;
    }

    /*
        Dit returned of de stao een pop is.
    */
    public boolean getIsPop()
    {
        return isPop;
    }

    /*
        Dit returned het punt waar de turtle is.
    */
    public Point2D getPunt()
    {
        Point2D.Double punt = new Point2D.Double(x, y);
        return punt;
    }

    /*
        Dit returned de huidige lijn.
    */
    public int getHuidigeLijn()
    {
        return huidigeLijn;
    }

    /*
        Dit berekent de volgende stap die de turtle neemt.
        De turtle staat dus eerst op een punt en zet een stap.
        Daarna staat de turtle op een ander punt.
        Deze methode bepaalt dat punt.
    */
    public void berekenStap(String lindenmayerString)
    {
        isPop = false;
        karakter = lindenmayerString.charAt(huidigeLijn);

        /*
            Als het karakter een F of een G is, zet de turtle
            een stap vooruit.
        */
        if(karakter == 'F' || karakter == 'G')
        {
            x = x + lengte * Math.cos(hoek);
            y = y + lengte * Math.sin(hoek);

        }
        /*
            Als het karakter een - is, draait de turtle naar
            links, als het karakter een + is, draait de turtle
            naar rechts.
        */
        else if(karakter == '-')
        {
            hoek -= hoekverandering;
        }
        else if(karakter == '+')
        {
            hoek += hoekverandering;
        }
        /*
            Als het karakter een [ is, voegt hij
            de x, y en hoek toe aan de stack.
        */
        else if(karakter == '[')
        {
            double[] pushArray = new double[3];
            pushArray[0] = x;
            pushArray[1] = y;
            pushArray[2] = hoek;
            stack.push(pushArray);
        }
        /*
            Als het karakter een ] is, popt hij
            de stack en maakt hij de x, y en hoek
            de bovenste wwarden.
        */
        else if(karakter == ']')
        {
            double[] popArray = new double[3];
            popArray = (double[]) stack.pop();
            x = popArray[0];
            y = popArray[1];
            hoek = popArray[2];
            isPop = true;
        }

        huidigeLijn++;
    }
}