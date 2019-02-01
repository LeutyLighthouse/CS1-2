/*Course: CSCI 1110
 * Author: Ross J. Robertson
 * Purpose: To take in a set of inputs where the first input is an integer
 * denoting the amount of strings that will come after it, then those strings,
 * then an integer denoting how many strings should be in the output, then
 * sorting them and outputting said strings, each on its own line. The way
 * that the program sorts the strings for the output is: it organizes the
 * strings so that any strings prior to the one after it will have its last
 * character match the first character of the next word. If there is more
 * than one option it will take only the first possible option read from
 * the input. The program also ensures that there are no duplicate strings
 * that come out of the output. If no sequence of words is possible to meet
 * these requirements for the output, then the program will print "IMPOSSIBLE".
 * Last date modified: June 3rd
 */

import java.util.*;

public class Problem2{

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);


        //This is the number of words we'll find from the input.
        int dataLength = in.nextInt();

        //This first arraylist will keep track of the input strings as they
        //were received from the input.
        ArrayList<String> origin = new ArrayList<String>();
        //This arraylist will keep track of how we want the output to look.
        ArrayList<String> outList = new ArrayList<String>();

        //This takes the input strings and throws them into origin.
        for(int i = 0; i < dataLength; i++){
            origin.add(in.next());
        }

        //This will be the amount of strings we want.
        int neededLength = in.nextInt();

        //This ensures we have a starting point for comparison of previous
        //strings for the program by putting the first string of origin into
        //outList.
        outList.add(origin.get(0));

        //This iterates through each of the arraylists and checks if there is a
        //suitable word to come after (ie. the last character of the string
        //before matches the first character of the word that comes after).
        //It checks the last character of the outList string to make sure that the
        //sequence comes out in the proper order for the output.
        for(int i = 0; i < outList.size(); i++){
            for(int j = 0; j < dataLength; j++){
                if(((outList.get(i)).substring((outList.get(i)).length() - 1)).equals((origin.get(j)).substring(0,1))){

                    //This ensures that there are no duplicate strings in the output.
                    if((outList.contains(origin.get(j))) == false){
                        outList.add(origin.get(j));

                        //this ensures that once a suitable word is found the loop moves
                        //onto the next word to check.
                        j = dataLength;
                    }
                }

            }
        }

        //This case is when the output list hasn't found any words suitable to
        //come after the first, or the output list doesn't have enough strings
        //for the required output length so therefore it's impossible to meet
        //the required output.
        if((outList.size() <= 1)||(outList.size() < neededLength)){
            System.out.println("IMPOSSIBLE");
        }


        //This prints out the output strings one line at a time for each string.
        if(outList.size() > 1){
            for(int k = 0; k < neededLength; k++){
                System.out.println(outList.get(k));
            }
        }

    }
}