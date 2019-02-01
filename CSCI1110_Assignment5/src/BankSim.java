/**
   File: BankSim.java
   Author: Ross J. Robertson
   Purpose: CSCI 1110, Assignment 5 
   Description: This program performs a bank simulation.
*/

import java.util.*;

/**
   This is the main class of the program.
   The program starts running in the main() of this class.
*/

public class BankSim 
{
  /**
     This method is called when the program starts running. 
     It reads instantiates the needed objects and then performs
     the bank simulation, one minute at a time.  Once no more
     clients or tellers arrive, it finishes up the simulation.
  */
  public static void main( String [] args ) 
	{

    Scanner in = new Scanner(System.in);
    
    //Variables for hourly statistics.
    ArrayList<Integer> statsPerHourP = new ArrayList<Integer>();
    ArrayList<Integer> statsPerHourB = new ArrayList<Integer>();
    int curHourPSum = 0;
    int curHourBSum = 0;
    
    Bank bank = new Bank();
    
    //This is used when we're checking to see if we've taken in
    //all of the possible input.
    boolean reachEnd = false;
    
    int arrival = in.nextInt();
    int curTime = 0;
      
    //Loop that runs for every minute in the simulation.
    while((arrival >= curTime)||(bank.getTellerLineSize() > 0)){
      
      curTime++;
      bank.incrementTimeSpentWithTeller();
      bank.decrementShiftTime();

      //Within this statement is when we take in our input.
      while((reachEnd == false)&&(arrival == curTime)){
        
        String typeOfBusiness = in.next();
        String typeOfPerson = in.next();
        String name = in.next();
        
        //For tellers this is the length of their shift and for clients
        //this is how long they'll need, initially, to be served by a
        //teller.
        int amountOfTime = in.nextInt();
        
        int numOfLanguages = in.nextInt();
      
        //Here we do the first step of adding people
        //to their respective lines depending on the information
        //given to us through the input.
        if(typeOfPerson.equals("client")&&(typeOfBusiness.equals("personal"))){
          Client client = new Client(name, arrival, amountOfTime, typeOfPerson, typeOfBusiness);
          for(int i = 0; i < numOfLanguages; i++){
            client.addLanguage(in.next());
          }
          bank.addToPersonalLine(client);
          System.out.println(arrival+" : client "+name+" arrives");
        }
      
        if(typeOfPerson.equals("client")&&(typeOfBusiness.equals("business"))){
          Client client = new Client(name, arrival, amountOfTime, typeOfPerson, typeOfBusiness);
          for(int i = 0; i < numOfLanguages; i++){
            client.addLanguage(in.next());
          }
          bank.addToBusinessLine(client);
          System.out.println(arrival+" : client "+name+" arrives");
        }
      
        if(typeOfPerson.equals("teller")){
          Teller teller = new Teller(name, arrival, amountOfTime, typeOfPerson, typeOfBusiness);
          for(int i = 0; i < numOfLanguages; i++){
            teller.addLanguage(in.next());
          }
          bank.addToTellerLine(teller);
          System.out.println(arrival+" : teller "+name+" arrives");
        }
        
        //Final condition that picks up the final line of input
        //and if it has already been picked up we can safely
        //ignore it so we don't run into an error. If this
        //isn't actually the last line of input then we now have
        //the arrival time of the next person.
        
          arrival = in.nextInt();
        
          if(arrival == -1){
            reachEnd = true;
          }
      }
      
      
      //The third step involving clients being finished and
      //leaving is executed here.
      for(int i = 0; i < bank.getTellerLineSize(); i++){
      if(bank.getTellerLine().get(i).hasClient()){
        if(bank.getTellerLine().get(i).getClient().getRemainingTime() <= 0){
          System.out.println(curTime+" : client "+bank.getTellerLine().get(i).getClient().getName()+" leaves");
          bank.getTellerLine().get(i).resetClient();
        }
      }
    }
      
      
      //The fourth step in the processing involving tellers
      //leaving is done here, which they will only do if they
      //aren't serving anyone and their shift is over.
      for(int i = 0; i < bank.getTellerLineSize(); i++){
        if(bank.getTellerLine().get(i).hasClient() == false){    
          if(bank.getTellerLine().get(i).getRemainingTime() <= 0){
            System.out.println(curTime+" : teller "+bank.getTellerLine().get(i).getName()+" leaves");
            bank.removeTeller(i);
            i--;
          }
        }
      }
      
      
      //Here we perform the fifth step in processing where the free tellers will
      //help the clients if they are suited to help them.
      for(int i = 0; i < bank.getTellerLineSize(); i++){
        
        //Here we make sure the teller isn't already serving someone.
        if(bank.getTellerLine().get(i).hasClient() == false){
          
          //Here is the decision structure if the teller's a personal teller.
          if(bank.getTellerLine().get(i).getTypeOfBusiness().equals("personal")){
            if(bank.getPersonalLine().isEmpty() == false){
              bank.getTellerLine().get(i).setClient(bank.getPersonalLine().remove());
              
              System.out.println(curTime+" : client "+bank.getTellerLine().get(i).getClient().getName()+" is served by teller "+bank.getTellerLine().get(i).getName());
              
              //If the teller and client don't speak the same language, then we add
              //five minutes to the amount of time needed to serve the client.
              if(bank.getTellerLine().get(i).speakTheSameLanguage() == false){
                bank.getTellerLine().get(i).getClient().addToRemainingTime(5);
              } 
            }
          }
          
          //Here is the decision structure if the teller's a business teller.
          if(bank.getTellerLine().get(i).getTypeOfBusiness().equals("business")){
            if(bank.getBusinessLine().isEmpty() == false){
              bank.getTellerLine().get(i).setClient(bank.getBusinessLine().remove());
              
              System.out.println(curTime+" : client "+bank.getTellerLine().get(i).getClient().getName()+" is served by teller "+bank.getTellerLine().get(i).getName());
              
              //If the teller and client don't speak the same language, then we add
              //five minutes to the amount of time needed to serve the client.
              if(bank.getTellerLine().get(i).speakTheSameLanguage() == false){
                bank.getTellerLine().get(i).getClient().addToRemainingTime(5);
              }
            }
            
            //If the business teller doesn't find someone in the business line
            //the the teller looks in the personal line.
            else if(bank.getPersonalLine().isEmpty() == false){
              bank.getTellerLine().get(i).setClient(bank.getPersonalLine().remove());
              
              System.out.println(curTime+" : client "+bank.getTellerLine().get(i).getClient().getName()+" is served by teller "+bank.getTellerLine().get(i).getName());
              
              //If the teller and client don't speak the same language, then we add
              //five minutes to the amount of time needed to serve the client.
              if(bank.getTellerLine().get(i).speakTheSameLanguage() == false){
                bank.getTellerLine().get(i).getClient().addToRemainingTime(5);
              }
            }
          } 
        }
      }
      
      
      //Here we're collecting statistics for the current minute's line size
      //and adding it to a total for the whole hour.
      curHourPSum += bank.getPersonalLineSize();
      curHourBSum += bank.getBusinessLineSize();
      
      //Here we're storing the hourly statistics and resetting the counters
      //for the next full hour.
      if((curTime % 60) == 0){
        
        //We're dividing by 60 here because we want the average to be
        //the line size per minute.
        statsPerHourP.add((curHourPSum/60));
        curHourPSum = 0;
        statsPerHourB.add((curHourBSum/60));
        curHourBSum = 0;
      } 
    }
    
    
    //Here, once the simulation has run through all of the minutes, we'll print
    //out the stats for each hour that we saved before.
    System.out.println("Hourly Average Line-Ups");
    for(int i = 0; i < statsPerHourP.size(); i++){
      System.out.println("hour "+(i+1)+" : personal "+statsPerHourP.get(i)+", business "+statsPerHourB.get(i));
    }  
  }
}
