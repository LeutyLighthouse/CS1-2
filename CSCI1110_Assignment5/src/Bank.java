//Course: CSCI 1110
//Author: Ross J. Robertson
//LDM: July 15th, 2018
//Purpose: To simulate the bank as an object that contains clients and tellers.



import java.util.*;


public class Bank{
  
  //Here are the lines for the clients and the tellers.
  private Queue<Client> personalLine;
  private Queue<Client> businessLine;
  private LinkedList<Teller> tellerLine;
  
  public Bank(){
    
    personalLine = new LinkedList<Client>();
    businessLine = new LinkedList<Client>();
    tellerLine = new LinkedList<Teller>();
    
  }
  
  public void addToPersonalLine(Client client){
    personalLine.add(client);
  }
  
  public void addToBusinessLine(Client client){
    businessLine.add(client);
  }
  
  public void addToTellerLine(Teller teller){
    tellerLine.add(teller);
  }
  
  public int getPersonalLineSize(){return personalLine.size();}
  
  public int getBusinessLineSize(){return businessLine.size();}
  
  public int getTellerLineSize(){return tellerLine.size();}
  
  public LinkedList<Teller> getTellerLine(){return tellerLine;}
  
  public Queue<Client> getPersonalLine(){return personalLine;}
  
  public Queue<Client> getBusinessLine(){return businessLine;}
  
  //This takes the client associated with each teller and decrements the
  //amount of time remaining for the client to be fully served by one minute.
  public void incrementTimeSpentWithTeller(){
    for(int i = 0; i < tellerLine.size(); i++){
      if(tellerLine.get(i).hasClient()){
        tellerLine.get(i).getClient().decrementRemainingTime();
        
      }
    }
  }
  
  //this decrements the amount of time left in the shift for the teller.
  public void decrementShiftTime(){
    for(int i = 0; i < tellerLine.size(); i++){
      tellerLine.get(i).decrementRemainingTime();
    }
  }
  
  //this removes the teller at the given index from the teller list.
  public void removeTeller(int i){
    tellerLine.remove(i);
  }
  
}