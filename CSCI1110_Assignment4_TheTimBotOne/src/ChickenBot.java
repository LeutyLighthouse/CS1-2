// Course: CSCI 1110
// Author: Ross J. Robertson
// LDM: July 1st, 2018
// Purpose: To have a TimBot with a non-confrontational personality.




public class ChickenBot extends TimBot{
 
  
  /* 
   * Here's some references for the variables used in TimBot
   * in case any of this is hard to understand, or keep track of.
  protected int id;
  protected int jolts;
  protected boolean[] potentialBots;
  protected int[] potentialRipeness;
  protected String personality;
  */

    public ChickenBot(int id, int jolts){

        super(id, jolts);
        this.personality = "C";

    }


    public int getNextMove() {

        //ChickenBot makes sure it has at least one jolt of energy
        //before moving.
        if(jolts >= 1){

            int [] points = new int[5];

            //First, we'll assign points for our top criteria for choosing
            //where to move next.
            if(potentialBots[District.CURRENT] == false){
                points[District.CURRENT] += 12;
            }
            if(potentialBots[District.NORTH] == false){
                points[District.NORTH] += 12;
            }
            if(potentialBots[District.EAST] == false){
                points[District.EAST] += 12;
            }
            if(potentialBots[District.SOUTH] == false){
                points[District.SOUTH] += 12;
            }
            if(potentialBots[District.WEST] == false){
                points[District.WEST] += 12;
            }


            //Second, we'll assign points for where to move based on
            //the spaces adjacent to the spaces we just looked at,
            //however because the sensors cannot reach that far we will
            //always have to assume that all spots have the same weight
            //of preference, and therefore that criteria becomes null.



            //Third, we'll look at the spot that has the least number
            //of rounds before spresso can grow, and give it an
            //appropriate number of points relative to how preferable
            //the criteria is.

            int ripeness = 1000;
            int ripeLocation = 0;

            for(int i = 0; i < potentialRipeness.length ; i++){
                if(potentialRipeness[i] < ripeness){
                    ripeness = potentialRipeness[i];
                    ripeLocation = i;
                }
            }

            points[ripeLocation] += 8;


            //Finally, we'll look at the order of the districts as listed
            //above and assign where to go.
            points[District.CURRENT] += 5;
            points[District.NORTH] += 4;
            points[District.EAST] += 3;
            points[District.SOUTH] += 2;
            points[District.WEST] += 1;

            int destination = 0;
            int mostPoints = 0;

            for(int i = 0; i < points.length; i++){
                if(mostPoints < points[i]){
                    mostPoints = points[i];
                    destination = i;
                }
            }


            return destination;
        }

        //If the bot doesn't have at least 1 jolt of energy
        //it'll stay where it is.
        return District.CURRENT;
    }





}