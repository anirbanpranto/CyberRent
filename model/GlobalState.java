package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;

public class GlobalState {
    private Stage publicStage;
    private boolean isLoggedIn = false; //this becomes true when someone logs in
    private Map<Integer, Owner> owenerLookup; //loaded when constructor is called
    private Map<Integer, Agent> agentLookup; //loaded when constructor is called
    private Map<Integer, Tenant> tenantLookup; //loaded when constructor is called
    private Map<Integer, Admin> adminLookup; //loaded when constructor is called
    /*
      the point of having these lookup tables are related to the data that we're gonna display. For propeties, we need to show the data of affiliated Owners, Agents. This is also useful for logging in.

    */
    int loggedInId = -1; //this will store the email and the role after login
    String role = null;
    private ArrayList<Property> propeties; //to be loaded in the globalstate constructor
    private ArrayList<Property> personalProperty = null; //null at the begining, but its loaded when the login method is called
    private ArrayList<Property> favoriteProperty = null; //null at the begining, but its loaded when the login method is called
    private ArrayList<Owner> owner = null;
    private ArrayList<Tenant> tenant = null;
    private ArrayList<Agent> agent = null;

    private GlobalState(){
        //load all properties here from database
        //read the database for properties and use those values to create an ArrayList of all properties
        //read the database and load the object
    }

    private void EditPerformed(String tableName){ //edit the temporary data and put them in persistent data
        //make an ArrayList of the Table values = []
        //flush values inside our database
        ArrayList<List<String>> All = null;
        Database.writeAllData(tableName, All);
    }

    public void saveStage(Stage s){
        this.publicStage = s;
    }

    public void setSession(int id, String role){
        this.loggedInId = id;
        this.role = role;
    }

    public int getLoggedInId(){
        return this.loggedInId;
    }

    public String getRole(){
        return this.role;
    }

    public void flushSession(){
        this.loggedInId = -1;
        this.role = null;
        this.isLoggedIn = false;
    }

    //Singleton Design Pattern
    public static GlobalState getInstance(){
        if(instance==null){
            instance = new GlobalState();
        }
        return instance;
    }

    public Stage getStage(){
        return this.publicStage;
    }

    public ArrayList<Property> getProperties(){
        return this.propeties;
    }

    public void setPersonalProperties(ArrayList<Property> p){
        this.personalProperty = p;
    }

    public ArrayList<Property> getPersonalProperties(){
        return this.personalProperty;
    }

    public ArrayList<Property> getFavoriteProperties(){
        return this.favoriteProperty;
    }
    
    public void setLoginStatus(){
        if (this.isLoggedIn){
            this.isLoggedIn = false;
        }
        else{
            this.isLoggedIn = true;
        }
    }

    public boolean getLoginStatus(){
        return this.isLoggedIn;
    }
    
    private static GlobalState instance;

    //this model will have all the public data performed without login
}
