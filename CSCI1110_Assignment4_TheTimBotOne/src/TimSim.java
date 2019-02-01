// Course: CSCI 1110
// Author: Ross J. Robertson
// LDM: July 2nd, 2018
// Purpose: To simulate the world of DohNat, and its inhabitants.


import java.util.*;

public class TimSim {

    public static void main(String [] args){

        Scanner in = new Scanner(System.in);

        int rows = in.nextInt();
        int columns = in.nextInt();
        int jolts = in.nextInt();
        int growth = in.nextInt();
        int numOfRounds = in.nextInt();
        int timBotConfigs = in.nextInt();
        ArrayList<TimBot> allBots = new ArrayList<TimBot>();

        //Here we create our planet using the variables given through input.
        DohNat planet = new DohNat(rows, columns, jolts, growth);

        planet.initializePlanet(planet, rows, columns, jolts, growth);

        for(int i = 0; i < timBotConfigs; i++){

            String personality = in.next();
            int id = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            int initialJolts = in.nextInt();
            TimBot curBot;


            //This adds a bot of the appropriate personality to DohNat, and puts said bot into
            //an arraylist.
            if(personality.equals("chicken")){
                curBot = new ChickenBot(id, initialJolts);
                planet.setTimBot(curBot, x, y);
                allBots.add(curBot);
            }
            if(personality.equals("spresso")){
                curBot = new SpressoBot(id, initialJolts);
                planet.setTimBot(curBot, x, y);
                allBots.add(curBot);
            }
            if(personality.equals("angry")){
                curBot = new AngryBot(id, initialJolts);
                planet.setTimBot(curBot, x, y);
                allBots.add(curBot);
            }
            if(personality.equals("bully")){
                curBot = new BullyBot(id, initialJolts);
                planet.setTimBot(curBot, x, y);
                allBots.add(curBot);
            }

        }

        for(int i = 0; i < numOfRounds; i++){



            //Starts the round.
            planet.newRound();

            //Does the sense phase of the round.
            planet.doSensePhase();

            //Does the fire phase for the round.
            planet.doFirePhase();

            //Does the Defense phase for the round.
            planet.doDefensePhase();

            //Does the move phase for the round.
            planet.doMovePhase();

            //Does the Battle phase for the round, cleaning up any
            //districts that may have more than one bot present.
            planet.doBattlePhase();

            //Cleans up any dead timbots off of DohNat.
            planet.doCleanPhase();

            //Does the harvest phase for the round.
            planet.doHarvestPhase();

            //Prints out the current state of DohNat affairs, starting
            //with what round has currently just finished.

            System.out.println("Round " + i);
            System.out.println(planet.toString());


            //Checks to see if there are at least two timbots left.
            //If there is only one or zero timbots left then this
            //breaks the loop, ending the simulation.
            if(planet.countBots() <= 1){
                break;
            }

        }





    }
}