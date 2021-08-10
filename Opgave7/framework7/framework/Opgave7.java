/**
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 * Deze klasse tekent een fractal die de gebruiker meegeeft.
 */

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Opgave7 extends JPanel implements ActionListener
{
    private final static int HEIGHT = 800, WIDTH = 800;

    /*
        Verhoog de frames per seconde om het tekenproces te versnellen.
     */
    private final static double FRAMES_PER_SECOND = 1000.0;
    Timer timer = new Timer((int) (1000.0 / FRAMES_PER_SECOND), this);


    private Regels regels;
    private Turtle turtle;
    private Lindenmayer lindenmayer;
    private ArrayList<Line2D.Double> lijnBuffer;
    private static boolean startProcessing;

    /*
        Deze variabelen zijn voor het tekenen.
    */
    private Point2D startpunt;
    private Point2D eindpunt;
    private Line2D.Double lijn;



    public static void main(String[] args) throws InterruptedException
    {
        /*
            Initialiseer het JFrame en JPanel die gebruikt worden om de
            visuele interface te maken.
        */

        JFrame frame = new JFrame("Inleiding Programmeren - Lindenmayer");
        JPanel simulation;

        /*
            Dit probeert een object Opgave7 te maken en het bestand uit te lezen.
            Als dit niet lukt gooit hij een exception.
        */
        try
        {
            simulation = new Opgave7(args[0].toLowerCase().trim());
            simulation.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            frame.add(simulation);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.pack();

            /*
                Dit start het processen van de gegenereerde String.
            */
            startProcessing = true;
        }
        catch(Exception e)
        {
            System.out.println("Er is iets mis gegaan bij het inlezen.");
            System.out.print("Controleer of het bestand juist is en of u de juiste naam heeft meegegeven. ");
            System.out.println("Bijvoorbeeld: java Opgave 7 sierpinski.");
            System.exit(1);
        }
    }


    /*
        Constructor die de klasseninstanties initialiseert;
    */
    Opgave7(String bestandNaam)
    {
        timer.start();
        lijnBuffer = new ArrayList<Line2D.Double>();
        regels = new Regels(bestandNaam);
        turtle = new Turtle(regels);
        lindenmayer = new Lindenmayer(regels);

        /*
            Dit genereerd de lindenmayerString. Het geeft een 0 mee
            omdat de de huidige recursiediepte 0 is, omdat hij het
            nog geen enkele keer heeft berekent.
        */
        lindenmayer.genereerString(0);
        startProcessing = false;
    }

     /*
        Deze methode wordt aangeroepen op elk geregistreerd evenement. Op het moment
        is het enige geregistreerde evenement de timer die bovenaan de klasse
        is gedefineerd. Elke keer als deze methode wordt aangeroepen, roept het
        de repaint methode om de content van de grafische interface te updaten.
     */
    public void actionPerformed(ActionEvent e)
    {
        repaint();
        Toolkit.getDefaultToolkit().sync();

    }

     /*
        De paint methode tekent de inhoud van de listBuffer Arraylist op
        het scherm. Het is belangrijk om het scherm elke keer dat deze methode
        wordt aangeroepen schoon te maken, dus we moeten elke keer elke lijn
        in de buffer opnieuw tekenen.
     */
    public void paint(Graphics graphics)
    {

        /*
            Grafische objecten zijn meestal 3D objecten. Omdat we in 2 dimensies
            werken, casten wij het naar een 2D representatie van zichzelf.
        */
        Graphics2D graphics2D = (Graphics2D) graphics;

        /*
            Als hij aan het processen is voegt hij steeds de nieuwe lijn aan
            de buffer toe.
        */
        if (startProcessing)
        {
            /*
                Dit verkrijgt het startpunt van de turtle en voert dan een stap uit.
            */
            startpunt = turtle.getPunt();
            turtle.berekenStap(lindenmayer.getString());

            /*
                Als de stap geen pop was verkrijgt hij het eindpunt, maakt hij een
                lijn tussen het start- en eindpunt en voegt deze lijn toe aan de buffer.
            */
            if(!turtle.getIsPop())
            {
                eindpunt = turtle.getPunt();
                lijn = new Line2D.Double(startpunt, eindpunt);
                lijnBuffer.add(lijn);
            }

            /*
                Als elk karakter ingelezen is, stopt het processen.
            */
            if(turtle.getHuidigeLijn() >= lindenmayer.getLength())
            {
                startProcessing = false;
            }
        }

        /*
            Dit zorgt wevoor dat het scherm altijd geleegd wordt door een
            rechthoek ter grootte van het window leeg te maken.
        */
        graphics2D.clearRect(0, 0, WIDTH, HEIGHT);

        /*
            Dit zet de breedte en kleur van de lijn.
        */
        graphics2D.setStroke(new BasicStroke((float) turtle.getBreedte()));
        graphics2D.setColor(new Color(0F, 0.6F, 0.1F));

        /*
            Dit tekent elke lijn in de buffer.
        */
        for(int i = 0; i < lijnBuffer.size(); i++)
        {
            graphics2D.draw(lijnBuffer.get(i));
        }
    }
}

