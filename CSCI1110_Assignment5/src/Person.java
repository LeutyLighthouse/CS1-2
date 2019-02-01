//Course: CSCI 1110
//Author: Ross J. Robertson
//LDM: July 15th, 2018
//Purpose: To store the values important for each person, relative to the simulation.



import java.util.*;


public class Person{
  
  protected String name;
  protected int arrivalTime;
  protected int amountOfTime;
  protected String typeOfPerson;
  protected String typeOfBusiness;
  protected ArrayList<String> spokenLanguages = new ArrayList<String>();
  protected int remainingTime;
  
  public Person(String name, int arrivalTime, int amountOfTime, String typeOfPerson, String typeOfBusiness){
    
    this.name = name;
    this.arrivalTime = arrivalTime;
    this.amountOfTime = amountOfTime;
    this.typeOfPerson = typeOfPerson;
    this.typeOfBusiness = typeOfBusiness;
    this.remainingTime = amountOfTime;
    
  }
  
  public void addLanguage(String language){
    spokenLanguages.add(language);
  }
  
  public ArrayList getLanguages(){return spokenLanguages;}
  
  public int getArrival(){return arrivalTime;}
  
  public int getAmountOfTime(){return amountOfTime;}
  
  public String getName(){return name;}
  
  public String getTypeOfPerson(){return typeOfPerson;}
  
  public String getTypeOfBusiness(){return typeOfBusiness;}
  
  public void decrementRemainingTime(){
    remainingTime--;
  }
  
  public void addToRemainingTime(int num){
    remainingTime += num;
  }
  
  public int getRemainingTime(){return remainingTime;}
  
}