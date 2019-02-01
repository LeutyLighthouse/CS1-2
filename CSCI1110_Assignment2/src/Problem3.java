/*Course: CSCI 1110
 * Author: Ross J. Robertson
 * Purpose: To take in an integer and figure out what prime factors make
 * up that number and then prints out those prime factors and how many times
 * each prime factor is needed.
 * Last date modified: June 3rd
 */

import java.util.*;

public class Problem3{

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        //This simply reads in the number we want to find the prime factors
        //of and assigns it.
        int number = in.nextInt();
        int factorCount = 0;

        //This list keeps track of which unique prime factors we'll need
        //to use for the number given to us.
        ArrayList<Integer> listOfFactors = new ArrayList<Integer>();

        //This list keeps track of how many times we'll need each prime
        //factor. The index of the count corresponds to the prime factor
        //that needs to be multiplied by the count.
        ArrayList<Integer> listOfCounts = new ArrayList<Integer>();

        //Here we have a calculator that keeps track of which prime factors can
        //make up the number that we give it as well as the amount of each unique
        //prime factor necessary to produce the original number. Each time a
        //significant value is discovered it gets put in the corresponding
        //arraylist.
        for(int i = 2; i <= number; i++){
            factorCount = 0;
            while((number % i) == 0){
                number = number / i;
                factorCount++;
            }
            if(factorCount != 0){
                listOfFactors.add(i);
                listOfCounts.add(factorCount);
            }
        }

        /*Here we're examining the size of the list so that we know exactly how
         * many prime factors we have to deal with. We're doing this so that we
         * know when the list will end and can make sure the grammatical structure
         * of the output can meet what's expected. (ie. so that we don't have a comma
         * at the end of our output).
         */
        int size = listOfFactors.size();

        //For this we're getting the values from the arrays and arranging them to
        //fit the grammatical structure that's desired (eg. INPUT: listOfFactors = {2,3,5},
        //listOfCounts = {1,2,1} OUTPUT: 2,3(2),5 ).
        for(int j = 0; j < size; j++){

            //This prints out the grammatical structure needed for all but the last entry.
            if(j < (size - 1)){
                System.out.print(listOfFactors.get(j));
                if((listOfCounts.get(j)) > 1){
                    System.out.print("(" + (listOfCounts.get(j)) + ")");
                }
                System.out.print(",");
            }

            //This prints out the grammatical structure needed for the last entry.
            else if(j == (size - 1)){
                System.out.print(listOfFactors.get(j));
                if((listOfCounts.get(j)) > 1){
                    System.out.print("(" + (listOfCounts.get(j)) + ")");
                }
            }
        }
        System.out.print("\n");
    }
}