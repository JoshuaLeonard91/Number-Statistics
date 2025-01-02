/*
 * Author: Joshua Leonard
 * Course: COP3503
 * Project #: 1
 * Title: Statistics Calc
 * Due Date: 6/10/2022
 *
 * Calculates various statistics from user input.
 */

import java.util.Scanner;
import java.util.Arrays;

/**
 * Program Entry Point
 */
public class ProjectOne
{
    public static void main(String[] args)
    {
        // display menu
        displayMenu();
    }

    /**
     * Gets user input that is stored as string.
     * Print out various options for the user.
     * Loops until user exits program.
     */
    public static void displayMenu()
    {
        //variable declarations
        Scanner scnr = new Scanner(System.in);
        String listInt;


        //Asks for user Input
        System.out.println("Enter List of Integers Seperated by Spaces: ");

        listInt= scnr.nextLine();
        int [] listArray = str2Array(listInt);
        Arrays.sort(listArray);

        boolean flag = true;
        //Loop user options until user types "quit" OR "5", sets the flag
        // to false will call a new displayMenu.
        do {
            //user options print statements
            System.out.println("Please make a selection:");
            System.out.println("1) Display List Statistics");
            System.out.println("2) Display List Ordered");
            System.out.println("3) Number of Odd/Even");
            System.out.println("4) Check for Prime Numbers");
            System.out.println("5) Enter New List");
            System.out.println("exit) Quit Program");

            String input = scnr.nextLine();
            //prints out what the user selected
            switch (input) {

                case "1" :  listStats(listArray);
                    break;
                case "2" :  System.out.println(Arrays.toString(listArray)+"\n");
                    break;
                case "3" :  System.out.println("Number Even: "+isEven(listArray));
                    System.out.println("Number Odd: "+isOdd(listArray)+"\n");
                    break;
                case "4" :  System.out.println("Number of Prime in list: "+isPrime(listArray)+"\n");
                    break;
                case "5" :  displayMenu();
                    flag = false;
                    break;
                case "exit" :  System.out.println("Program Exiting");
                    flag = false;
            }
        }while(flag);
    }//end displayMenu

    /**
     * Takes in a string and will
     * return an Integer Array.
     * @param listInt String from user input
     * @return intList
     */
    public static int[] str2Array(String listInt)
    {
        //variable declarations
        String [] str = listInt.split(" ");
        int size = str.length;
        int[] intList = new int[size];

        //Loops through string array and assign each
        //integer to a new integer array
        for (int i = 0; i < size; i++)
        {
            intList[i] = Integer.parseInt(str[i]);
        }
        return intList;
    }//end str2Array

    /**
     * Takes in an integer array and
     * counts how many are even and store
     * that in the even variable.
     * @param listArray Integer Array.
     * @return even
     */
    public static int isEven (int[] listArray)
    {
        //variable declarations
        int even = 0;

        //Loops through array, if array[i]mod2==0 will
        //increment even variable
        for (int i = 0; i < listArray.length; ++i)
        {
            if (listArray[i] % 2 == 0)
            {
                ++even;
            }
        }
        return even;
    }//end isEven

    /**
     * Takes in an integer array and
     * counts how many are odd and store
     * that in the odd variable.
     * @param listArray Integer Array.
     * @return odd
     */
    public static int isOdd (int[] listArray)
    {
        //variable declarations
        int odd = 0;

        //loops through array if integer[i] is odd
        //will increment variable odd
        for (int i = 0; i < listArray.length; ++i) {
            if (listArray[i] % 2 != 0) {
                ++odd;
            }
        }
        return odd;
    }//end isOdd

    /**
     * Takes in an integer array and
     * iterates through the array checking
     * if the integer is prime. If the integer is
     * prime it will increment the variable.
     * @param listArray Integer Array.
     * @return primeCount
     */
    public static int isPrime(int[] listArray)
    {
        //variable declarations
        int primeCount = 0;

        //loops through array and passes
        //isPrime[i] integer to primeCalc that
        //checks isPrime[i], if true increments
        //primeCount
        for ( int i = 0; i < listArray.length; i++ )
        {
            if( primeCalc(listArray[i]) )
            {
                ++primeCount;
            }
        }
        return primeCount;
    }//end isPrime

    /**
     * Takes in an integer and checks whether
     * that integer is prime.
     * @param n Integer.
     * @return true or false
     */
    public static boolean primeCalc(int n)
    {
        //checks if number n is prime
        if( n <= 1 )
        {
            return false;
        }
        if( n <= 3 )
        {
            return true;
        }
        if( n % 2 == 0 || n % 3 == 0 )
        {
            return false;
        }
        for( int i = 5; i * i <= n; i = i+6 )
        {
            if(n % i == 0 || n % (i +2) == 0)
            {
                return false;
            }
        }
        return true;
    }//end primeCalc

    /**
     * Takes in an integer array and
     * passes those to various methods
     * that return the values and
     * print out the statistics.
     * @param listStats Integer Array.
     */
    public static void listStats(int[] listStats)
    {
        //outputs list statistics
        System.out.printf("Min: %d\n", listStats[0]);
        System.out.printf("Max: %d\n", (listStats[listStats.length-1]));
        System.out.printf("Count: %d\n", listStats.length);
        System.out.printf("Range: %d\n", ((listStats[listStats.length-1])-(listStats[0])));
        System.out.printf("Median: %.2f\n", median(listStats));
        System.out.printf("Mean: %.2f\n", (mean(listStats)));
        System.out.println(mode(listStats));
        System.out.printf("Variance: %.2f\n", var(listStats));
        System.out.printf("Standard Deviation: %.2f\n", stdDev(listStats));
        System.out.println();
    }//end listStats

    /**
     * Takes in an integer array and
     * converts it to a double. Checks length
     * of array if mod 2==1 divides array by 2 returns middle
     * index.
     * if mod2!=1 (length/2 + length/2-1)/2 returns the average
     * of the two.
     * @param listStats Integer Array
     * @return midNbr
     */
    public static double median(int [] listStats)
    {
        //variable declarations
        double[] medianD = new double[listStats.length];
        double midNbr = 0;

        //loop to assign new array int->double
        for( int i=0; i<listStats.length; i++ ) {
            medianD[i] = listStats[i];
        }

        //checks length array
        if ( medianD.length%2 == 1 )
            midNbr =
                    medianD[medianD.length/2];
        else
            midNbr =
                    (medianD[medianD.length/2] + medianD[medianD.length/2 - 1]) / 2;
        return midNbr;
    }//end median

    /**
     * Takes in an integer array and returns the
     * sum of the array as a double.
     * @param listStats Integer Array
     * @return sum
     */
    public static double mean(int [] listStats)
    {
        //variable declarations
        double sum = 0;
        double mean = 0;

        //iterates through array assigns the sum
        //variable with sum + mean[i]
        for( int i = 0; i < listStats.length; ++i )
        {
            sum = sum + listStats[i];
        }
        mean = sum / listStats.length;

        return mean;
    }//end mean

    /**
     * Takes in an integer array and iterates
     * through counting the occurrences of each
     * integer and returns a string.
     * @param listStats Integer Array
     * @return modeStr
     */
    public static String mode(int [] listStats)
    {
        //variable declarations
        int currentMode = 0;
        int occurs = 0;
        String modeStr;

        //nested for loop iterates through each index
        for( int i = 0; i < listStats.length; ++i )
        {
            int cnt = 0;
            for( int j = 0;j < listStats.length; ++j )
            {
                //compares indexes if matches and increments cnt
                if( listStats[i] == listStats[j] )
                {
                    ++cnt;
                }
            }

            //if counter is greater than current occurrences assign
            //current mode with current index and assign occurrences
            // with current counter
            if( cnt > occurs )
            {
                currentMode = listStats[i];
                occurs = cnt;
            }
        }
        if(occurs <= 1)
        {
            modeStr = "No Mode";
        }
        else
        {
            modeStr = "Mode: " + currentMode;
        }
        return modeStr;
    }//end mode

    /**
     * Takes an integer array and returns
     * a double.
     * @param listStats Integer Array
     * @return variance
     */
    public static double var(int [] listStats)
    {
        //variable declarations
        double variance = 0;
        double sum = mean(listStats);

        //loop to assign variance
        for( int i = 0; i < listStats.length; ++i )
        {
            variance += Math.pow((double)listStats[i] - sum, 2);
        }
        variance = variance/listStats.length;

        return variance;
    }//end var

    /**
     * Takes in an integer array and returns
     * a double.
     * @param listStats Integer Array
     * @return standardDeviation
     */
    public static double stdDev(int [] listStats)
    {
        //variable declarations
        double standardDeviation = 0;
        double tempSum = 0;
        double sum = mean(listStats);

        //iterates through stdDev[i] and adds the current standardDeviation
        //with the previous, variable is initialized to 0 at first.
        for( int i = 0; i < listStats.length; ++i )
        {
            tempSum += Math.pow(((double)listStats[i] - sum),2);
        }

        tempSum = tempSum/listStats.length;
        standardDeviation = Math.sqrt(tempSum);

        return standardDeviation;
    }
}