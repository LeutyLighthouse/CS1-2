/* Program name: ChallengeProblem.java
 * Author: Ross Roberton
 * LMD: May 20th, 2018
 * Purpose: To take a series of integer inputs, where the first integer
 * is the number of remaining integers, and to find the mean and median
 * of those integers that come after the first integer of the input.
 * Once the mean and median are found they are then printed out.
 */
import java.util.*;
import java.util.Collections;

public class ChallengeProblem{

    public static void main(String[] args){


        double mean = 0;
        double median = 0;
        int currentInt = 0;

        Scanner in = new Scanner(System.in);

        int dataLength = in.nextInt();

        //This creates a list out of the remaining integers from the input
        //and reorganizes them from smallest to largest in myList.
        ArrayList<Integer> myList = new ArrayList<Integer>();
        for(int i = 0; i < dataLength; i++){
            myList.add(in.nextInt());
        }
        Collections.sort(myList);

        //If the first integer is zero.
        if(dataLength == 0){
            System.out.println("There are no integers to examine and determine the mean and median of.");
            return;
        }
        //If the first integer given is negative.
        else if(dataLength < 0){
            System.out.println("Invalid amount of integers to examine.");
            return;
        }
        //Loop for if the amount of integers in the set is even.
        else if((dataLength % 2) == 0){

            for(int i = 0; i < dataLength; i++){

                currentInt = myList.get(i);
                mean = mean + currentInt;

                if((i == ((dataLength / 2) - 1 ))||((i == (dataLength / 2)))){
                    median = median + currentInt;
                }

            }
            mean = mean/dataLength;
            median = median/2;
        }
        //Loop for if the amount of integers in the set is odd.
        else if((dataLength % 2) == 1){
            for(int i = 0; i < dataLength; i++){

                currentInt = myList.get(i);
                mean = mean + currentInt;

                if(i == (dataLength / 2)){
                    median = currentInt;
                }

            }
            mean = mean/dataLength;
        }
        else{
            System.out.println("Invalid input.");
            return;
        }
        System.out.println("Mean: " + mean + "\n" + "Median: " + median);
    }
}