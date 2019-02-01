/*Course: CSCI 1110
 * Author: Ross J. Robertson
 * Purpose: This checks if the strings of the input have the same character
 * for the last character of the previous string as the first character of
 * the next string. The input is expected to be an integer denoting how many
 * strings will come after that, then those strings, which will be checked in
 * the aforementioned way. If the sequence of strings does in fact have those
 * particular characters for each string relative to where it is in the set,
 * then the program will print "Valid". If this isn't the case, then the program
 * will print "Invalid [the word that should come after] does not follow [the
 * word that does come before the previously mentioned word]".
 * Last date modified: June 3rd
 */

import java.util.*;

public class Problem1{

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        //This is for keeping track of how many strings we should have
        //from the input.
        int dataLength = in.nextInt();
        String prevString = "";

        //This iterates through the strings and checks if they meet the
        //requirements to be considered valid. (ie. the previous word's last
        //character always matches the first character of the word that comes
        //after).
        for(int i =0; i < dataLength; i++){
            String currentString = in.next();

            //This covers the case of the first word to compare and sets up
            //prevString as the first string to check, then moves through
            //the loop so that we can actually compare two different strings.
            if(prevString.equals("")){
                prevString = currentString;
            }

            //Condition checking the aforementioned characters. 
            else if((prevString.substring(prevString.length() - 1, prevString.length())).equals(currentString.substring(0, 1))){
                prevString = currentString;
            }

            //This covers the case when the characters we're interested in
            //don't match and prints an appropriate statement regarding
            //the strings that don't follow properly.
            else{
                System.out.println("Invalid: " + currentString + " does not follow " + prevString);
                return;
            }
        }
        System.out.println("Valid");
    }
}