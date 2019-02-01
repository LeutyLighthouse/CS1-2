/* Program name: Problem3.java
 * Author: Ross Roberton
 * LMD: May 20th, 2018
 * Purpose: To take a series of integer inputs, where the first integer
 * is the number of remaining integers, and to find the mean and median
 * of those integers that come after the first integer of the input.
 * The integers that come after the first integer of the input should
 * be in order from smallest to largest. (The practical application of this
 * program would be to figure out the mean and median of a class' grades.)
 * Once the mean and median are found they are then printed out.
 */
import java.util.*;

public class Problem3{

    public static void main(String[] args){


        double mean = 0;
        double median = 0;
        int currentInt = 0;

        Scanner in = new Scanner(System.in);

        int dataLength = in.nextInt();
        //This statement will run if the integer denoting how many integers there
        //are to examine is zero. This is basically here to protect against a
        //case when incorrect information is put into the input.
        if(dataLength == 0){
            System.out.println("There are no integers to examine and determine the mean and median of.");
            return;
        }
        //This statement will run if the integer denoting how many integers there
        //are to examine is negative. This is basically here to protect against a
        //case when incorrect information is put into the input.
        else if(dataLength < 0){
            System.out.println("Invalid amount of integers to examine.");
            return;
        }
        //This process is executed if the amount of integers that the program is going
        //to examine is even. Then it will find the mean and the median of the input.
        else if((dataLength % 2) == 0){

            for(int i = 0; i < dataLength; i++){

                currentInt = in.nextInt();
                mean = mean + currentInt;

                if((i == ((dataLength / 2) - 1 ))||((i == (dataLength / 2)))){
                    median = median + currentInt;
                }

            }
            mean = mean/dataLength;
            median = median/2;
        }
        //This process is executed if the amount of integers that the program is going
        //to examine is odd. Then it will find the mean and the median of the input.
        else if((dataLength % 2) == 1){
            for(int i = 0; i < dataLength; i++){
                currentInt = in.nextInt();
                mean = mean + currentInt;

                if(i == (dataLength / 2)){
                    median = currentInt;
                }

            }
            mean = mean/dataLength;
        }
        //This is here in case the input doesn't even contain a number for it's first
        //value. It's purpose is to protect against cases where the input doesn't
        //work at all for what's needed to make the program run.
        else{
            System.out.println("Invalid input.");
            return;
        }
        System.out.println("Mean: " + mean + "\n" + "Median: " + median);
    }
}