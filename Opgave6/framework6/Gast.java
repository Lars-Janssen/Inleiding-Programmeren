/**
 *
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 *  Deze Klasse geeft de naam van een gast.
*/

import java.lang.Math;

public class Gast
{
    private String voornaam;
    private String achternaam;

    private static final String[] VOORNAMEN =
    {
        "Adam", "Sofie", "Johan", "Yuri", "Marie", "Fred", "Lisa", "Robin",
        "Claartje", "Freek", "Piet", "Pietje", "Erik", "Bas", "Pavlov", "Igor",
        "Ivan", "Geertje", "Klaas", "Snorri", "Anna", "Lotte", "Sara", "Roos",
        "Noor", "Thor", "Jean", "Karel", "Dick", "Richard", "Dzjengis", "Emma",
        "Howard Phillips", "Peter", "Sint", "Albert", "Dirk Jan", "Taylor",
        "Linus", "James", "Bjarne", "Jurriaan", "Jan-Klaassen", "Niki", "Jan"
    };

    private static final String[] ACHTERNAMEN =
    {
    "de Vries", "Smit", "Petersen", "Vonk", "Janssen", "Klaassen",
    "de Trompetter", "Baantjes", "de Jong", "Sanchez", "Bakker", "Eggertsson",
    "Sturluson", "Valjean", "de Grote", "Precies", "Khan", "Snorremans",
    "de Cock met C-O-C-K", "Stallman", "Lovecraft", "Erklaas", "Gagarin",
    "Einstein", "Heijn", "de Geer", "Swift", "Torvalds", "Gosling",
    "Stroustrup", "Lauda"
    };

    /*
        Dit genereert een willekeurige naam door een willekeurige
        voor- en achternaam aan elkaar te plakken.
    */
    Gast()
    {
        this(VOORNAMEN[(int) (Math.random() * VOORNAMEN.length)],
             ACHTERNAMEN[(int) (Math.random() * ACHTERNAMEN.length)]);
    }

    /*
        Dit maakt een gast met een naam.
    */
    Gast(String voornaam, String achternaam)
    {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
    }

    /*
        Dit print de volledige naam.
    */
    @Override
    public String toString() {

        return "    " + voornaam + " " + achternaam;
    }
}