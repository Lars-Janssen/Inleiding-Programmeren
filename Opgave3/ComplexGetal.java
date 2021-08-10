/**
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 * Deze klasse slaat een complex getal op door het reeele deel en het
 * imaginaire deel op te slaan als breuken. Verder maakt deze klasse een
 * aantal methodes om complexe getallen op te tellen, af te trekken, te delen,
 * te vermenigvuldigen en om een complex getallen om te draaien.
 * Daarnaast maakt hij een methode toString om de complexe getallen goed
 * te printen en een equals methode om te kijken of twee complexe getallen
 * hetzelfde zijn.
 */


public class ComplexGetal
{

    Breuk re;
    Breuk im;

    /*
        Deze constructor maakt een complex getal door een breuk te maken
        voor het reeele deel met 2 ints als input en een breuk te maken
        voor het imaginaire deel met andere ints als input
    */
    ComplexGetal(int tellerRe, int noemerRe, int tellerIm, int noemerIm)
    {
        re = new Breuk(tellerRe, noemerRe);
        im = new Breuk(tellerIm, noemerIm);
    }

    /*
        Deze constructor maakt een complex getal door een breuk te maken
        voor het reeele deel en een breuk te maken voor het imaginaire deel
        met 2 breuken als input.
    */
    ComplexGetal(Breuk re, Breuk im)
    {
        this.re = re;
        this.im = im;
    }

    /*
        Deze methode telt 2 complexe getallen op door de telOp methode van
        een breuk te gebruiken om de breuken op te tellen.
    */
    ComplexGetal telOp(ComplexGetal cg)
    {
        ComplexGetal opgeteldGetal = new ComplexGetal(new Breuk(), new Breuk());
        opgeteldGetal.re = this.re.telOp(cg.re);
        opgeteldGetal.im = this.im.telOp(cg.im);
        return opgeteldGetal;
    }

    /*
        Deze methode doet hetzelfde als de telOp methode, maar dan met de trekAf
        methode van een breuk.
    */
    ComplexGetal trekAf(ComplexGetal cg)
    {
        ComplexGetal afgetrokkenGetal = new ComplexGetal(new Breuk(), new Breuk());
        afgetrokkenGetal.re = this.re.trekAf(cg.re);
        afgetrokkenGetal.im = this.im.trekAf(cg.im);
        return afgetrokkenGetal;
    }

    /*
        Deze methode vermenigvuldigt 2 complexe getallen door gebruik te maken van de telOp,
        trekAf en vermenigvuldig methodes voor een breuk.
    */
    ComplexGetal vermenigvuldig(ComplexGetal cg)
    {
        ComplexGetal vermenigvuldigdGetal = new ComplexGetal(new Breuk(), new Breuk());
        vermenigvuldigdGetal.re = this.re.vermenigvuldig(cg.re).trekAf(this.im.vermenigvuldig(cg.im));
        vermenigvuldigdGetal.im = this.re.vermenigvuldig(cg.im).telOp(this.im.vermenigvuldig(cg.re));
        return vermenigvuldigdGetal;
    }

    /*
        Deze methode deelt een complex getal door een ander complex getal.
        Dit doet hij door het tweede getal om te keren en de 2 getallen daarna
        te vermenigvuldigen.
    */
    ComplexGetal deel(ComplexGetal cg)
    {
        ComplexGetal gedeeldGetal = new ComplexGetal(new Breuk(), new Breuk());
        cg = cg.omgekeerde();
        gedeeldGetal = this.vermenigvuldig(cg);
        return gedeeldGetal;
    }

    /*
        Deze methode berekent de omgekeerde van een complex getal door gebruik te maken
        van de telOp, deel en vermenigvuldig methodes voor een breuk.
    */
    ComplexGetal omgekeerde()
    {
        ComplexGetal omgekeerdGetal = new ComplexGetal(new Breuk(), new Breuk());
        omgekeerdGetal.re = this.re.deel(this.re.vermenigvuldig(this.re).telOp(this.im.vermenigvuldig(this.im)));
        omgekeerdGetal.im = this.im.deel(this.re.vermenigvuldig(this.re).telOp(this.im.vermenigvuldig(this.im))
                                         .vermenigvuldig(new Breuk(-1)));
        return omgekeerdGetal;
    }

    /*
        Deze methode vervangt de standaard toString methode zodat de complexe
        getallen goed geprint worden.
    */
    public String toString()
    {
        /*
            Als het imaginaire deel 1 is wordt het niet geprint en als het
            imaginaire deel negatief is wordt er er geen plus tussen de delen
            geprint.
        */
        if(this.im.equals(new Breuk(1, 1)))
        {
            return(re + " + i");
        }

        if(this.im.teller < 0)
        {
            return(re + " " + im + "i");
        }

        return(re + " + " + im + "i");
    }

    /*
        Deze methode kijkt of 2 complexe getallen hetzelfde zijn.
    */
    boolean equals(ComplexGetal cg)
    {
        return this.re.equals(cg.re) && this.im.equals(cg. im);
    }
}