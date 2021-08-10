/*
    Deze klasse bestaat uit een coefficient en een macht
    en vormt 1 term van een polynoom.
    De coefficient is een reeel getal en de macht een
    positief geheel getal.
*/
class Paar implements Comparable<Paar>
{

    final private double coefficient;
    final private int macht;

    /*
        Deze constructor neemt een double en een int
        en maakt de coefficient de double en de macht de int.
        Als de macht negatief is wordt hij 0 en geeft het een bericht.
    */
    Paar(double coefficient, int macht)
    {
        this.coefficient = coefficient;
        if(macht >= 0)
        {
            this.macht = macht;
        }
        else
        {
            throw new IllegalArgumentException("Er is iets fout gegaan bij het maken van een paar,"
                                               + " de macht is negatief.");
        }
    }

    /*
        Deze constructor neemt een Paar en geeft de coefficient en macht door
        aan de constructor hierboven.
    */
    Paar(Paar p)
    {
        this(p.coefficient, p.macht);
    }

    /*
        De methode geeft de coefficient van een paar.
    */
    public double geefCoefficient()
    {
        return coefficient;
    }

    /*
        De methode geeft de macht van een paar.
    */
    public int geefMacht()
    {
        return macht;
    }


    /*
        Deze methode geeft de som van twee paren
        door de coefficienten op te tellen.
    */
    public Paar telOp(Paar ander)
    {
        double opgeteldCoefficient = this.coefficient + ander.coefficient;
        Paar opgeteldPaar = new Paar(opgeteldCoefficient, this.macht);
        return opgeteldPaar;
    }

    /*
        Deze methode geeft het product van twee paren,
        door de coefficienten te vermenigvuldigen
        en de machten op te tellen.
    */
    public Paar vermenigvuldig(Paar ander)
    {
        double vermenigvuldigdCoefficient = this.coefficient * ander.coefficient;
        int vermenigvuldigdMacht = this.macht + ander.macht;
        Paar vermenigvuldigdPaar = new Paar(vermenigvuldigdCoefficient, vermenigvuldigdMacht);
        return vermenigvuldigdPaar;
    }

    /*
        Deze methode geeft de differentiatie een paar,
        door de coefficient te vermenigvuldigen met de macht
        en de macht 1 te verlagen.
    */
    public Paar differentieer()
    {
        if(this.macht > 0)
        {
            double gedifCoefficient = this.macht * this.coefficient;
            int gedifMacht = this.macht - 1;
            Paar gedifPaar = new Paar(gedifCoefficient, gedifMacht);
            return gedifPaar;
        }
        else
        {
            return new Paar(0, 0);
        }
    }

    /*
        Deze methode geft de integraal een paar,
        door de macht 1 te verhogen
        en de coefficient te delen door deze nieuwe macht.
    */
    public Paar integreer()
    {
        int geintMacht = this.macht + 1;
        double geintCoefficient = this.coefficient / geintMacht;
        Paar geintPaar = new Paar(geintCoefficient, geintMacht);
        return geintPaar;
    }

    /*
        Deze methode vergelijkt twee machten en returned het verschil.
        Dit wordt gebruikt om het polynoom te sorteren.
    */
    public int compareTo(Paar ander)
    {
        return ander.macht - this.macht;
    }

    /*
        Deze methode kijkt of twee paren hetzelfde zijn,
        door met de compareTo methode te kijken of de machten gelijk zijn
        en te kijken of de coefficienten hetzelfde zijn.
    */
    public boolean equals(Paar ander)
    {

        if(this.compareTo(ander) == 0 && this.coefficient == ander.coefficient)
        {
            return true;
        }
        return false;
    }

    /*
        Deze methode zet het paar om in een String om te printen.
    */
    public String toString()
    {
        if(macht == 0)
        {
            return(coefficient + "");
        }

        if(macht == 1 && coefficient == 1.0 || coefficient == 0)
        {
            return("x");
        }

        if(macht == 1)
        {
            return(coefficient + " x");
        }

        if(coefficient == 1.0)
        {
            return("x^" + macht);
        }

        return(coefficient + " x^" + macht);
    }
}
