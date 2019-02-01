// Course: CSCI 1110
// Author: Ross J. Robertson
// LDM: July 1st, 2018
// Purpose: To make a bot that really just hates everything.


public class AngryBot extends SpressoBot{
  
    /* 
    * Here's some references for the variables used in TimBot
    * in case any of this is hard to understand, or keep track of.
    protected int id;
    protected int jolts;
    protected boolean[] potentialBots;
    protected int[] potentialRipeness;
    protected String personality;
    */

    public AngryBot(int id, int jolts){

        super(id, jolts);
        this.personality = "A";

    }


    public int getNextMove(){

        int destination = District.CURRENT;

        int [] points = new int[5];

        //If this bot has 2 or less jolts it'll act like a SpressoBot.
        if(jolts <= 2){

            //First, we'll look at the spot that has the least number
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

            //Secondly, let's check if any of the scanned districts are empty
            //and if they are we'll give them points relative to how important
            //they are to the AngryBot.

            if(potentialBots[District.CURRENT] == false){
                points[District.CURRENT] += 6;
            }
            if(potentialBots[District.NORTH] == false){
                points[District.NORTH] += 6;
            }
            if(potentialBots[District.EAST] == false){
                points[District.EAST] += 6;
            }
            if(potentialBots[District.SOUTH] == false){
                points[District.SOUTH] += 6;
            }
            if(potentialBots[District.WEST] == false){
                points[District.WEST] += 6;
            }

            //Criteria 3 has the same problem as ChickenBot, so we'll treat all
            //the districts the same (ie. we won't let it influence SpressoBot's
            //decision, since all districts will return the same thing).

            //Finally, we'll give points to favour the districts based on the order:
            //current, north, east, south, west.
            points[District.CURRENT] += 5;
            points[District.NORTH] += 4;
            points[District.EAST] += 3;
            points[District.SOUTH] += 2;
            points[District.WEST] += 1;

            //And now from the points for each location we'll decide based on which district
            //has the most points.
            int mostPoints = 0;

            for(int i = 0; i < points.length; i++){
                if(mostPoints < points[i]){
                    mostPoints = points[i];
                    destination = i;
                }
            }

            return destination;
        }

        //This will trigger AngryBot's personality traits if AngryBot's energy is
        //three or greater
        if(jolts >= 3){

            //Firstly, let's check if any of the scanned districts are occupied
            //and if they are we'll give them points relative to how important
            //they are to the AngryBot.


            if(potentialBots[District.NORTH] == true){
                points[District.NORTH] += 12;
            }
            if(potentialBots[District.EAST] == true){
                points[District.EAST] += 12;
            }
            if(potentialBots[District.SOUTH] == true){
                points[District.SOUTH] += 12;
            }
            if(potentialBots[District.WEST] == true){
                points[District.WEST] += 12;
            }

            //Second, we'll look at the spot that has the least number
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

            //Finally, we'll give points to favour the districts based on the order:
            //current, north, east, south, west.
            points[District.CURRENT] += 5;
            points[District.NORTH] += 4;
            points[District.EAST] += 3;
            points[District.SOUTH] += 2;
            points[District.WEST] += 1;

            //And now from the points for each location we'll decide based on which district
            //has the most points.
            int mostPoints = 0;

            for(int i = 0; i < points.length; i++){
                if(mostPoints < points[i]){
                    mostPoints = points[i];
                    destination = i;
                }
            }

            return destination;
        }




        return destination;
    }




}