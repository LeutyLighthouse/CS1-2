// Course: CSCI 1110
// Author: Ross J. Robertson
// LDM: July 1st, 2018
// Purpose: To make a bot that acts like a jerk.




public class BullyBot extends ChickenBot{
  
  /* 
   * Here's some references for the variables used in TimBot
   * in case any of this is hard to understand, or keep track of.
  protected int id;
  protected int jolts;
  protected boolean[] potentialBots;
  protected int[] potentialRipeness;
  protected String personality;
  */

    public BullyBot(int id, int jolts){

        super(id, jolts);
        this.personality = "B";

    }

    public int [] fireCannon() {

        //potential targets
        int [] targets = new int[5];

        targets[0] = 0;
        targets[1] = 0;
        targets[2] = 0;
        targets[3] = 0;
        targets[4] = 0;

        //Each time that this bot is about to assign a place to fire its cannon
        //it will first make sure that it has at least three jolts of energy.

        //checking and assigning the North as a potential target.
        if(jolts >= 3){
            if(potentialBots[District.NORTH] == true){
                targets[District.NORTH] += 1;
                jolts--;
            }


            //checking and assigning the East as a potential target.
            if(jolts >= 3){
                if(potentialBots[District.EAST] == true){
                    targets[District.EAST] += 1;
                    jolts--;
                }
            }

            //checking and assigning the South as a potential target.
            if(jolts >= 3){
                if(potentialBots[District.SOUTH] == true){
                    targets[District.SOUTH] += 1;
                    jolts--;
                }
            }

            //checking and assigning the West as a potential target.
            if(jolts >= 3){
                if(potentialBots[District.WEST] == true){
                    targets[District.WEST] += 1;
                    jolts--;
                }
            }




            return targets;
        }

        return targets;
    }




}