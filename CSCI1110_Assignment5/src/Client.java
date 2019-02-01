//Course: CSCI 1110
//Author: Ross J. Robertson
//LDM: July 15th, 2018
//Purpose: To simulate the qualities we're interested in for each client.



import java.util.*;


public class Client extends Person{
  
  //Everything in here is pretty much already done by the Person class. This
  //class just helps to differentiate clients and tellers in a more
  //intuitive sense when reading through BankSim.java.
  
  
  public Client(String name, int arrivalTime, int amountOfTime, String typeOfPerson, String typeOfBusiness){
    
    super(name, arrivalTime, amountOfTime, typeOfPerson, typeOfBusiness);
    
  }
  
}