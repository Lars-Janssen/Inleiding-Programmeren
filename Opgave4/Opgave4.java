/* DNAMatch v0.1
 *
 * Naam         :  Lars Janssen
 * UvAnetID     :  12882712
 * Studie       :  BSc Informatica
 *
 * author  :  Dr. Quakerjack.
 * date    :  24-09-2018
 * version :  0.1
 *
 * This program makes a database for the user to store DNA-sequences in.
 * It also allows them to compare two sequences or retrieve a sequence
 * from the database which is similar to a given sequence.
 * For a full list of commands, start the program, type help, and hit enter.
*/
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Opgave4
{
    /*
        This makes the database to add the sequences to.
    */
    public static ArrayList<String> database = new ArrayList<String>();

    /*
        This welcomes the user and calls the function to read the console.
    */
    public static void main(String[]args)
    {
        System.out.println("Welcome to DNA Matcher v0.1 \n");
        executeConsole();
    }

    /*
        This checks the user input and returns it if it is not empty.
    */
    private static String readLine(Scanner input)
    {
        while(true)
        {
            if(input.hasNextLine())
            {
                return input.nextLine();
            }
        }
    }

    /*
        This scans a file and adds its contents to the database.
        It gets the file with the given name and
        afterwards it looks if there is an unread line, reads it
        and adds the line to the database.
        If the file does not exist it gives an error message.
    */
    private static void readFromFile(String file)
    {
        file = file.toLowerCase();
        File fl = new File(file);
        try
        {
            Scanner s = new Scanner(fl);
            while(s.hasNextLine())
            {
                addStringToDatabase(s.nextLine());
            }
            s.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error, file does not exist");
        }
    }

    /*
        This prints a list of the commands the user can use.
    */
    private static void helpUser()
    {
    System.out.println("LIST OF COMMANDS\n");
    System.out.println("    list               print database");
    System.out.println("    add ...            add to database");
    System.out.println("    compare ... ...    compare two strings");
    System.out.println("    read ...           read from file and add to database");
    System.out.println("    retrieve ...       find in database");
    System.out.println("    remove ...         remove from database");
    System.out.println("    quit               stop");
    System.out.println("    help               print this text");
    System.out.println();
    }

    /*
        This prints a list of the sequences in the database.
    */
    private static void listDatabase()
    {
        for(String s : database)
        {
            System.out.println(s);
        }
        System.out.println();
    }


    /*
        This checks if the database already contains the given string.
        If it does not, it adds it, and if it does it gives an error message
        and continues.
    */
    private static void addStringToDatabase(String input)
    {
        /*
            This turns the input into a char array and checks if every character
            in it is an A, C, T or G.
        */
        char[] characters = input.toCharArray();
        boolean isDNA = true;

        for(int i = 0; i < characters.length; i++)
        {
            if(characters[i] != 'A' && characters[i] != 'C' && characters[i] != 'T' && characters[i] != 'G')
            {
                isDNA = false;
            }
        }

        /*
            This checks if the string is a DNA-sequence and not in the database.
        */
        if(isDNA == true && !database.contains(input))
        {
            database.add(input);
        }
        else if(isDNA == false)
        {
            System.out.println("Warning: " + input + " is not a DNA sequence, skipping.");
        }
        else
        {
            System.out.println("Warning: " + input + " already added, skipping.");
        }
    }


    /*
        This checks if the database contains the given string.
        If it does, it removes it, and if it does not it gives an
        error message and continues.
    */
    private static void removeFromDatabase(String i)
    {
        if(database.contains(i))
        {
            database.remove(database.indexOf(i));
        }
        else
        {
            System.out.println(i + " not in database, nothing done \n");
        }
    }

    /*
        This prints the difference of two strings by calling a
        function to calculate it.
    */
    private static void compareStrings(String sequenceOne, String sequenceTwo)
    {
        System.out.println("Difference = " + calculateDistance(sequenceOne, sequenceTwo, true) + "\n");
    }

    /*
        This calculates the Levenshtein distance between two strings by using an algoritm.
        The Levenshtein distance is calculated by adding one to it if a letter has to be
        inserted, removed or changed.
    */
    private static int calculateDistance(String sequenceOne, String sequenceTwo, boolean doPrint)
    {
        /*
            This makes a 2d array to calculate the distance.
        */
        int[][] distanceArray = new int[sequenceOne.length() + 1][sequenceTwo.length() +1];

        /*
            This makes a row of 0, 1, 2, 3, ... at the top of the table
            and a column of 0, 1, 2, 3, ... at the left of the table.
        */
        for(int i = 0;i<=sequenceOne.length();i++)
        {
            distanceArray[i][0] = i;
        }

        for(int j = 0;j <= sequenceTwo.length();j++)
        {
            distanceArray[0][j] = j;
        }

        /*
            This calculates the number for every other cell in the table
            by using the algoritm.
        */
        for(int j = 1;j <= sequenceTwo.length();j++)
        {
            for(int i = 1;i <= sequenceOne.length();i++)
            {
                /*
                    If the character on place i in sequence a is the same as
                    the character on place j in sequence b, the distance stays the same.
                    Otherwise delete a letter from the longer sequence, insert a letter in
                    the shortest sequence, or replace the character, depending on which costs less.
                */
                if(sequenceOne.charAt(i-1) == sequenceTwo.charAt(j-1))
                {
                    distanceArray[i][j] = distanceArray[i-1][j-1];
                }
                else
                {
                    int delete = distanceArray[i-1][j] + 1;
                    int insert = distanceArray[i][j-1] + 1;
                    int replace = distanceArray[i-1][j-1] + 1;

                    /*
                        This takes the minimum of delete, insert, and replace.
                    */
                    distanceArray[i][j] = Math.min(Math.min(delete, insert), replace);
                }
            }
        }

        /*
            This prints the tables if needed.
        */
        if(doPrint)
        {
            printTable(sequenceOne, sequenceTwo, distanceArray);
        }

        /*
            This returns the number in the bottom left of the table.
            This is the Levenshtein distance.
        */
        return distanceArray[sequenceOne.length()][sequenceTwo.length()];
    }

    /*
        This prints the table of Levenshtein distances by reading the 2d array.
    */
    private static void printTable(String sequenceOne,String sequenceTwo,int[][] distanceArray)
    {

        for(int row = 0; row <= sequenceOne.length(); row++)
        {
            for(int column = 0; column <= sequenceTwo.length(); column++)
            {
                System.out.print(distanceArray[row][column] + "  ");
            }
            System.out.println("\n");
        }
    }

    /*
        This shows the sequences in the database which look the most like the input sequence.
    */
    private static void getFromDatabase(String input)
    {
        /*
            This makes a string array from the strings in the database.
        */
        String[] sequenceArray = new String[database.size()];
        sequenceArray = database.toArray(sequenceArray);

        int currentPlace = 0;
        int temporaryDistance;
        String temporarySequence;
        int[] distanceArray = new int[sequenceArray.length];

        /*
            This calculates the Levenshtein distance to the input sequence
            for every sequence in the database.
        */
        for(String s : database)
        {
            distanceArray[currentPlace] = calculateDistance(input, s, false);
            currentPlace++;
        }

        currentPlace = 1;

        /*
            For more information about gnome sort, visit the wikipedia page.
        */
        while(currentPlace < distanceArray.length)
        {
            /*
                Move over one place if the distance in the current place is more
                than or equal to the distance in the place before.
                Else, swap the distances and sequences and move back one place
                if possible or forward if it is not possible to move back.
            */
            if(distanceArray[currentPlace - 1] <= distanceArray[currentPlace])
            {
                currentPlace++;
            }
            else
            {
                temporaryDistance = distanceArray[currentPlace - 1];
                temporarySequence = sequenceArray[currentPlace - 1];

                /*
                    Swap the Levenshtein distance and sequence from the current place
                    and one place back.
                */
                distanceArray[currentPlace - 1] = distanceArray[currentPlace];
                sequenceArray[currentPlace - 1] = sequenceArray[currentPlace];
                distanceArray[currentPlace] = temporaryDistance;
                sequenceArray[currentPlace] = temporarySequence;

                if(currentPlace != 1)
                {
                    currentPlace--;
                }
                else
                {
                    currentPlace++;
                }
            }
        }

        /*
            Print the 3 sequences with the least distance in order of increasing distance.
        */
        System.out.println("Best matches: ");
        for(int r = 0; r < Math.min(3, sequenceArray.length); r++)
        {
            System.out.println(distanceArray[r] + "    " + sequenceArray[r]);
        }
    }

    /*
        This split the input in blocks, and looks if the first block is a command.
        If so, it executes the command if the number of arguments is correct.
    */
    private static void executeConsole()
    {
        boolean quit = false;
        Scanner inputScanner = new Scanner(System.in);
        do
        {
            System.out.print("console> ");
            String input = readLine(inputScanner).trim();

            /*
                This splits up the input at the spaces and converts the first block
                to lowercase.
            */
            String[] inputArray = input.split("\\s+");
            String command = inputArray[0].toLowerCase();

            /*
                This first checks the number of arguments in the input,
                then it looks which command has been used and executes that command.
            */
            if(inputArray.length == 1)
            {
                if(command.equals("help"))
                {
                    helpUser();
                }
                else if(command.equals("list"))
                {
                    listDatabase();
                }
                else if(command.equals("quit"))
                {
                    quit = true;
                }
                else
                {
                    System.out.println("Not a command or wrong number of arguments, skipping...");
                }
            }
            else if(inputArray.length == 2)
            {
                inputArray[1] = inputArray[1].toUpperCase();
                if(command.equals("read"))
                {
                    readFromFile(inputArray[1]);
                }
                else if(command.equals("add"))
                {
                    addStringToDatabase(inputArray[1]);
                }
                else if(command.equals("remove"))
                {
                    removeFromDatabase(inputArray[1]);
                }
                else if(command.equals("retrieve"))
                {
                    getFromDatabase(inputArray[1]);
                }
                else
                {
                    System.out.println("Not a command or wrong number of arguments, skipping...");
                }
            }
            else if(inputArray.length == 3)
            {
                inputArray[1] = inputArray[1].toUpperCase();
                inputArray[2] = inputArray[2].toUpperCase();
                if(command.equals("compare"))
                {
                    compareStrings(inputArray[1], inputArray[2]);
                }
                else
                {
                    System.out.println("Not a command or wrong number of arguments, skipping...");
                }
            }
            else
            {
                System.out.println("No input, skipping...");
            }
        }
        while(!quit);
        inputScanner.close();
    }
}
