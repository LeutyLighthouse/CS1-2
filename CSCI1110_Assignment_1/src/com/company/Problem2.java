package com.company;

/* Program name: Problem3.java
 * Author: Ross Roberton
 * LMD: May 20th, 2018
 * Purpose: To examine a set of integers, where the first integer is the number of integers
 * that are going to be examined after it. Those integers that come after the first
 * are representing grades and for each number that falls within its corresponding grades
 * an asterisk is added to a histogram for each of the grades in the set. Once all of
 * the numbers have been examined a complete histogram is printed out with the
 * appropriate amount of asterisks for each grade.
 * */
import java.util.*;

public class Problem2{

    public static void main(String[] args){
        //This program takes a set of integers (grades) from input,
        //where the first integer is the amount of remaining integers,
        //and looks at each grade and creates a histogram out of asterisks for
        //each grade.

        String f = "F: ";
        String d = "D: ";
        String c = "C: ";
        String b = "B: ";
        String a = "A: ";

        Scanner in = new Scanner(System.in);

        //variable for the amount of grade ints in the presented list
        int dataLength = in.nextInt();

        //This loops through the integers that represent each of the grades and adds an
        //asterisk to each grade if the integer falls within the appropriate grade range.
        for(int i = 0; i < dataLength; i++){

            int grade = in.nextInt();

            if ((0 <= grade)&&(grade <= 49)){
                f = f + "*";
            }
            else if((50 <= grade)&&(grade <= 59)){
                d = d + "*";
            }
            else if((60 <= grade)&&(grade <= 69)){
                c = c + "*";
            }
            else if((70 <= grade)&&(grade <= 79)){
                b = b + "*";
            }
            else if (80 <= grade){
                a = a +"*";
            }
            else if(grade < 0){
                System.out.println("Invalid input. The grade cannot be negative.");
            }
            //This will run if the input isn't valid at all and will inform the user
            //that their input is invalid.
            else{
                System.out.println("Invalid input.");
            }
        }

        //Histogram gets printed out with appropriate amount of asterisks
        //for each grade.
        System.out.println(f + "\n" + d + "\n" + c + "\n" + b + "\n" + a);



    }
}