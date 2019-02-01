/**
 File: DohNat.java
 Author: Alex Brodsky
 Purpose: CSCI 1110, Assignment 4 Template

 Description: This class specifies the template for the DohNat class.
 */

//Modified by: Ross J. Robertson

import java.util.*;

/**
 This is the DohNat base class for representing the torroidal planet
 DohNat.  It represents a grid division of the planet where each element
 of the grid is a District and where oposit edges of the grid are
 considered adjacent.
 */
public class DohNat {
    /** This constructor creates a planet divided into a grid of
     rows X columns districts.

     parameters:  rows:    number of rows in the grid
     columns: number of columns in the grid
     jolts:   number of jolts that a spresso harvest delivers
     growth:  number of rounds it taks to grow spresso plants
     */
    private int rows;
    private int columns;
    private int jolts;
    private int growth;
    private District[][] world;




    public DohNat( int rows, int columns, int jolts, int growth ) {
        //YOUR CODE HERE

        this.rows = rows;
        this.columns = columns;
        this.jolts = jolts;
        this.growth = growth;
        this.world = new District[rows][columns];



    }



    //Initializes the world of TimBot.
    public void initializePlanet( DohNat planet, int rows, int columns, int jolts, int growth){

        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++){

                world[j][i] = new District(planet, i, j, jolts, growth);

            }
        }


    }


    /** This method adds a TimBot to the District in grid element (x,y).
     where 0 <= x < width and 0 <= y < height.  This method will
     return false if there is already a TimBot in the District, or
     the coordinates are invalid.

     parameters:  bot: The TimBot object to be added
     x  : x coordinate of the destination District
     y  : y coordinate of the destination District

     returns: true on success or false otherwise.
     */
    public boolean setTimBot( TimBot bot, int x, int y ) {
    
    
    /*if((x < 0)||(x >= columns)||(y < 0)||(y >= rows)){
      return false;
    }*/
    /*if(world[y][x].hasTimBot() == true){
      return false;
    }*/

        world[y][x].setTimBot( bot );


        return true;
    }


    /** This method starts the next round of the simulation
     */
    public void newRound() {

        for(int i =0; i < columns; i++){
            for(int j = 0; j < rows; j++){

                world[j][i].startNewRound();
            }
        }

    }


    /** This method exectutes the Sense phase of the round.
     */
    public void doSensePhase() {

        for(int i =0; i < columns; i++){
            for(int j = 0; j < rows; j++){

                world[j][i].doSensePhase();
            }
        }


    }


    /** This method exectutes the Move phase of the round.
     */
    public void doMovePhase() {

        for(int i =0; i < columns; i++){
            for(int j = 0; j < rows; j++){

                world[j][i].doMovePhase();
            }
        }

    }


    /** This method exectutes the Battle phase of the round.
     */
    public void doBattlePhase() {

        for(int i =0; i < columns; i++){
            for(int j = 0; j < rows; j++){

                world[j][i].doBattlePhase();
            }
        }

    }


    /** This method exectutes the Fire phase of the round.
     */
    public void doFirePhase() {

        for(int i =0; i < columns; i++){
            for(int j = 0; j < rows; j++){

                world[j][i].doFirePhase();
            }
        }


    }


    /** This method exectutes the Defense phase of the round.
     */
    public void doDefensePhase() {

        for(int i =0; i < columns; i++){
            for(int j = 0; j < rows; j++){

                world[j][i].doDefensePhase();
            }
        }

    }


    /** This method exectutes the Harvest phase of the round.
     */
    public void doHarvestPhase() {

        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++){

                world[j][i].doHarvestPhase();
            }
        }

    }

    //Cleans the dead timbots off of the map.
    public void doCleanPhase() {

        for(int i =0; i < columns; i++){
            for(int j = 0; j < rows; j++){

                world[j][i].doCleanPhase();
            }
        }


    }

    //Counts the number of bots remaining at the end of each round,
    //and returns that number.
    public int countBots(){

        int numOfBots = 0;

        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++){


                if(world[j][i].hasTimBot() == true){
                    numOfBots++;
                }

            }
        }
        return numOfBots;
    }


    /** This method returns the District object representing district
     (x,y) on DohNat.  Coordinate may in the range -1 <= x <= width,
     and -1 <= y <= height.  Note since the antipodal edges of the
     grid are adjacent,
     if x == -1, then x = width - 1
     if y == -1, then y = height - 1
     if x == width, then x = 0
     if y == height, then y = 0

     parameters: x: x coordinate of the District
     y: y coordinate of the District

     returns the District object
     */

    public District getDistrict( int x, int y ) {

        if(x == -1){
            x = columns - 1;
        }
        if(y == -1){
            y = rows - 1;
        }
        if(x == columns){
            x = 0;
        }
        if(y == rows){
            y = 0;
        }
        return world[y][x];

    }

    /**
     This method returns a String describing state of the DohNat using
     the grid format:
     DDDD..D
     DDDD..D
     ::::  :
     DDDD..D
     where D denotes a string representing the corresponding district in
     the grid.   There should be no spaces spearating the districts, and
     each row of disticts should be terminated by a newline.
     */
    public String toString() {
        // YOUR CODE HERE (replace statement below)
        String dohnat = "";

        for(int i = 0; i < rows; i++){
            if(i != 0){
                dohnat = dohnat + "\n";
            }
            for(int j = 0; j < columns; j++){
                dohnat = dohnat + world[i][j].toString();
            }
        }

        return dohnat;
    }
}