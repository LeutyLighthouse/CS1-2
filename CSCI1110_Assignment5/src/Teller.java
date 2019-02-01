//Course: CSCI 1110
//Author: Ross J. Robertson
//LDM: July 16th, 2018
//Purpose: To simulate the qualities we're interested in for each teller.



import java.util.*;


public class Teller extends Person{
  
  //This is where the client goes when the client has left the line, but
  //is being served by this teller.
  private Client client = null;
  
  public Teller(String name, int arrivalTime, int amountOfTime, String typeOfPerson, String typeOfBusiness){
    
    super(name, arrivalTime, amountOfTime, typeOfPerson, typeOfBusiness);
    
  }
  
  public void setClient(Client client){
    this.client = client;
  }
  
  public Client getClient(){return client;}
  
  //This should only be called when the teller has finished serving the
  //client and the client leaves the bank.
  public void resetClient(){
    this.client = null;
  }
  
  //Used extensively as a null checker so that you won't run into
  //a null pointer exception.
  public boolean hasClient(){
    if(this.client == null){
      return false;
    }
    else{
      return true;
    }
  }
  
  public boolean speakTheSameLanguage(){
    if(this.client != null){
      for(int i = 0; i < client.getLanguages().size(); i++){
        if(spokenLanguages.contains(client.getLanguages().get(i))){
          return true;
      
        }
      }
    }
    return false;
  }
  
}