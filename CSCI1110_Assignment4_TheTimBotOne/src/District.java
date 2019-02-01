/**
 File: District.java
 Author: Alex Brodsky
 Purpose: CSCI 1110, Assignment 4 Template

 Description: This class specifies the template for the District class.
 */

//Modified by: Ross J. Robertson

import java.util.*;

/**
 This is the District class for representing disctricts on the planet DohNat
 Each District object is responsible for keeping track of the spresso plants
 in the district, the TimBot (if one is present), and any incoming ion shots
 coming into and out of the district.  It is also repsonsible for gathering
 sense data from surrunding districts and passing it to the TimBot during the
 Sense phase.
 */


public class District {
    // Thse are the district ID used to index into the spresso and bots
    // arrays passed to the senseDistrict method and also used to specify
    // where the TimBot wishes to fire the ion cannon.
    public static final int CURRENT = 0;
    public static final int NORTH = 1;
    public static final int EAST = 2;
    public static final int SOUTH = 3;
    public static final int WEST = 4;

    /**
     This constructor initializes the district, its spresso plant counter, and
     saves planet, its coordinates, which it will need to query adjoining
     districts.  When a district is instantiated, the Spresso is ready for
     harvest.

     parameters: planet: reference to DohNat so that adjacent districts can be
     accessed.
     x : x coordinate of the district
     y : y coordinate of the district
     jolts : number of jolts that the TimBot gets from a harvest.
     growth : amount of time it takes to grow spresso
     */

    private int x;
    private int y;
    private int jolts;
    private int growth;
    private DohNat planet;
    private TimBot bot;
    private int[] spresso;
    private boolean[] bots;
    private ArrayList<TimBot> botsInDistrict = new ArrayList<TimBot>();
    private int numOfShots;
    private int curGrowth;



    public District( DohNat planet, int x, int y, int jolts, int growth ) {
        // YOUR CODE HERE

        this.x = x;
        this.y = y;
        this.jolts = jolts;
        this.growth = growth;
        this.planet = planet;
        this.spresso = new int[5];
        this.bots =  new boolean[5];
        this.numOfShots = 0;
        this.bot = null;
        this.curGrowth = 0;

    }

    /**
     This method adds a TimBot to the district.  Only one TimBot may occupy
     a district.  It is an error if a TimBot is added to a district that
     has one.

     return: true on success, and false on error
     */
    public boolean setTimBot( TimBot bot ) {

        if(this.hasTimBot() == false){
            this.bot = bot;

            botsInDistrict.add(bot);

            return true;
        }



        return false;
    }


    /**
     This method performs starts the next round for the TimBot in
     the district.  It should call the TimBot's startRound() method.
     This method need not do anything if no functional TimBot is present.
     */
    public void startNewRound() {

        if(planet.getDistrict(x, y).hasTimBot()){
            if(bot.isFunctional()){
                bot.startRound();
            }
        }
        else{
            this.bot = null;
        }

    }


    /**
     This method performs the Sense phase for the TimBot in the district.
     This method need not do anything if no functional TimBot is present.
     */
    public void doSensePhase() {

        if(planet.getDistrict(x, y).hasTimBot()){
            if(bot.isFunctional()){
                spresso[0] = planet.getDistrict(x, y).hasSpresso();
                spresso[1] = planet.getDistrict(x, y - 1).hasSpresso();
                spresso[2] = planet.getDistrict(x + 1, y).hasSpresso();
                spresso[3] = planet.getDistrict(x, y + 1).hasSpresso();
                spresso[4] = planet.getDistrict(x - 1, y).hasSpresso();

                bots[0] = planet.getDistrict(x, y).hasTimBot();
                bots[1] = planet.getDistrict(x, y - 1).hasTimBot();
                bots[2] = planet.getDistrict(x + 1, y).hasTimBot();
                bots[3] = planet.getDistrict(x, y + 1).hasTimBot();
                bots[4] = planet.getDistrict(x - 1, y).hasTimBot();

                bot.senseDistricts(spresso, bots);
            }
        }

    }


    /**
     This method returs true if there is a TimBot in the District and
     false otherwise.

     return: true if TimBot is present
     */
    public boolean hasTimBot() {

        if(this.bot == null){
            return false;
        }

        return true;
    }


    /**
     This method returs number of rounds remaining until  spresso plants
     are ready for harvest.

     return: number of rounds before spresso is ready.
     */
    public int hasSpresso() {
        return curGrowth;
    }


    /**
     This method performs the Move phase for the TimBot in the district.
     This method need not do anything if no functional TimBot is present.
     */
    public void doMovePhase() {

        if(this.hasTimBot()){
            if(this.bot.isFunctional()){

                int nextMove = bot.getNextMove();

                if(nextMove == District.CURRENT){
                    //don't do anything.
                }
                else if(nextMove == District.NORTH){
                    planet.getDistrict(x, y - 1).moveTimBot(this.bot);

                    //Uncomment the line below to track the movement behaviour of each bot that moves North.
                    //System.out.println("Bot id: "+bot.getID()+" is moving North, coords are x: "+x+" y: "+y);

                    //This cleans up the district the bot was
                    //just in so we don't get duplicate bots.
                    this.bot = null;
                    botsInDistrict.clear();
                }
                else if(nextMove == District.EAST){
                    planet.getDistrict(x + 1, y).moveTimBot(this.bot);

                    //Uncomment the line below to track the movement behaviour of each bot that moves East.
                    //System.out.println("Bot id: "+bot.getID()+" is moving East, coords are x: "+x+" y: "+y);

                    //This cleans up the district the bot was
                    //just in so we don't get duplicate bots.
                    this.bot = null;
                    botsInDistrict.clear();
                }
                else if(nextMove == District.SOUTH){
                    planet.getDistrict(x, y + 1).moveTimBot(this.bot);

                    //Uncomment the line below to track the movement behaviour of each bot that moves South.
                    //System.out.println("Bot id: "+bot.getID()+" is moving South, coords are x: "+x+" y: "+y);

                    //This cleans up the district the bot was
                    //just in so we don't get duplicate bots.
                    this.bot = null;
                    botsInDistrict.clear();
                }
                else if(nextMove == District.WEST){
                    planet.getDistrict(x - 1, y).moveTimBot(this.bot);

                    //Uncomment the line below to track the movement behaviour of each bot that moves West.
                    //System.out.println("Bot id: "+bot.getID()+" is moving West, coords are x: "+x+" y: "+y);

                    //This cleans up the district the bot was
                    //just in so we don't get duplicate bots.
                    this.bot = null;
                    botsInDistrict.clear();

                }

            }
        }

    }


    /**
     This method moves the TimBot into the District during the Move phase.
     It is ok if there are multiple TimBots in the District, since they will
     be taken care of during the Battle phase.

     Parameter: bot: The TimBot moving into the District.
     */
    public void moveTimBot( TimBot bot ) {
        if(this.hasTimBot() == true){
            if(this.bot.isFunctional()){
                botsInDistrict.add(bot);
            }
        }
        else if(this.hasTimBot() == false){
            this.bot = bot;
            botsInDistrict.add(bot);

        }


    }


    /**
     This method performs the Battle phase for the TimBot(s) in the district.
     This method need not do anything if less than two TimBots are present.
     */
    public void doBattlePhase() {
        if(botsInDistrict.size() >= 2){

            int winner = 0;
            int runnerUp = 0;
            int maxLocation = 0;
            int runnerUpLocation = 0;

            //This finds the bot with the highest energy level.
            for(int i = 0; i < botsInDistrict.size() - 1; i++){

                int curEnergy = botsInDistrict.get(i).getEnergyLevel();
                int nextEnergy = botsInDistrict.get(i + 1).getEnergyLevel();

                if(winner <= nextEnergy){
                    winner = nextEnergy;
                    maxLocation = i + 1;
                }
                if(curEnergy >= winner){
                    winner = curEnergy;
                    maxLocation = i;
                }
            }

            //This stores the winner for later use
            TimBot botWinner = botsInDistrict.get(maxLocation);

            //Removes the bot with the highest energy level so that we can
            //find the runner up bot in terms of energy level.
            botsInDistrict.remove(maxLocation);

            //This finds the runner up if there were three or more bots
            //in the district for the battle phase.
            if(botsInDistrict.size() >= 2){

                for(int i = 0; i < botsInDistrict.size() - 1; i++){

                    int curEnergy = botsInDistrict.get(i).getEnergyLevel();
                    int nextEnergy = botsInDistrict.get(i + 1).getEnergyLevel();

                    if(runnerUp <= nextEnergy){
                        runnerUp = nextEnergy;
                        runnerUpLocation = i + 1;
                    }
                    if(curEnergy >= runnerUp){
                        runnerUp = curEnergy;
                        runnerUpLocation = i;
                    }
                }

                //This holds on to the runner up bot for later use in this method.
                TimBot botRunnerUp = botsInDistrict.get(runnerUpLocation);

            }

            if(botsInDistrict.size() == 1){
                TimBot botRunnerUp =  botsInDistrict.get(0);

            }



            if(runnerUp == winner){
                botsInDistrict.clear();
                this.bot = null;
            }

            if(runnerUp < winner){
                botsInDistrict.clear();
                this.bot = botWinner;
                botsInDistrict.add(this.bot);


                for(int i = 0; i < runnerUp; i++){
                    bot.useShield();
                }

            }


        }


    }

    //This cleans any of the dead bots in the district off the map.

    public void doCleanPhase(){
        if(this.hasTimBot()){
            if(this.bot.isFunctional() == false){
                this.bot = null;
                this.botsInDistrict.clear();
            }
        }
    }


    /**
     This method performs the Fire phase for the TimBot in the district.
     This method need not do anything if less than two TimBots are present.
     */
    public void doFirePhase() {
        if(this.bot != null){
            if(this.bot.isFunctional()){
                if(this.bot.fireCannon() != null){

                    if(bot.fireCannon()[District.NORTH] > 0){
                        for(int i = 0; i < bot.fireCannon()[District.NORTH]; i++){
                            planet.getDistrict(x, y - 1).fireAtDistrict();

                        }
                    }
                    if(bot.fireCannon()[District.EAST] > 0){
                        for(int i = 0; i < bot.fireCannon()[District.EAST]; i++){
                            planet.getDistrict(x + 1, y).fireAtDistrict();
                        }
                    }
                    if(bot.fireCannon()[District.SOUTH] > 0){
                        for(int i = 0; i < bot.fireCannon()[District.SOUTH]; i++){
                            planet.getDistrict(x, y + 1).fireAtDistrict();
                        }
                    }
                    if(bot.fireCannon()[District.WEST] > 0){
                        for(int i = 0; i < bot.fireCannon()[District.WEST]; i++){
                            planet.getDistrict(x - 1, y).fireAtDistrict();
                        }
                    }
                }
            }
        }
    }


    /**
     This method is called by adjacent Districts if a TimBot in
     those districts is firing at the TimBot in the present district.
     Every invocation of this method constitutes a single ion shot
     at the TimBot in this district.  This method need not do
     anything if is no TimBot are present.
     */
    public void fireAtDistrict() {
        if(planet.getDistrict(x, y).hasTimBot()){
            if(bot.isFunctional()){
                if(bot.fireCannon() != null){
                    numOfShots++;
                }
            }
        }
    }


    /**
     This method performs the Defense phase for the TimBot in the district.
     This method need not do anything if is no TimBot are present.
     */
    public void doDefensePhase() {
        if(planet.getDistrict(x, y).hasTimBot()){
            if(bot.isFunctional()){
                int x = 0;

                for(int i = 0; i < numOfShots; i++){
                    bot.useShield();
                    x++;
                }

                numOfShots -= x;

            }
        }

    }


    /**
     This method performs the Harvest phase for the TimBot in the district.
     This mehtod will always be called at the end of the round.  If the
     spresso is not ready to be harvested, then the method should decrement
     a counter that counts down to when the spresso will be ready for harvest.
     Otherwise, if the spresso is ready for harvest and a TimBot is present,
     the TimBot harvests the spresso and the counter is reset to count down.
     Otherwise, nothing is done.
     */
    public void doHarvestPhase() {
        if(this.curGrowth > 0){
            this.curGrowth--;
        }

        if(this.curGrowth == 0){

            if(planet.getDistrict(x, y).hasTimBot()){
                if(bot.isFunctional()){
                    bot.harvestSpresso(this.jolts);
                    this.curGrowth = growth;
                }
            }


        }
    }



    /**
     This method returns a String describing state of the District using
     the format:
     |bot counter|
     where bot is either the string representing the TimBot in the District
     or 9 spaces if there is no TimBot; counter is the number of rounds left
     till the spress is ready for harvest.   The counter should have width 2.
     E.g.,
     |(N 42  7)  3|
     Shows a district with TimBot with personality N, ID 42, energy level 7,
     and there are three rounds left till the spresso is ready for harvest.
     */
    public String toString() {
        String districtState = "";
        if(planet.getDistrict(x, y).hasTimBot()){
            if(curGrowth > 9){
                districtState = "|"+bot.toString()+" "+curGrowth+"|";
            }
            if(curGrowth <= 9){
                districtState = "|"+bot.toString()+"  "+curGrowth+"|";
            }

        }
        else{
            if(curGrowth > 9){
                districtState += "|          "+curGrowth+"|";
            }
            if(curGrowth <= 9){
                districtState += "|           "+curGrowth+"|";
            }

        }

        return districtState;

    }

}
