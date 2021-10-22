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
    private Map<Integer, Area> areaLookup; //load when constructor is called
    /*
      the point of having these lookup tables are related to the data that we're gonna display. For propeties, we need to show the data of affiliated Owners, Agents. This is also useful for logging in.

    */

    private Map<int, String> session; //this will store the email and the role after login
    private ArrayList<Property> propeties; //to be loaded in the globalstate constructor
    private ArrayList<Property> personalProperty = null; //null at the begining, but its loaded when the login method is called
    private ArrayList<Property> favoriteProperty = null; //null at the begining, but its loaded when the login method is called

    private GlobalState(){
        //load all properties here from database
    }

    public void saveStage(Stage s){
        this.publicStage = s;
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

    public ArrayList<Property> getPersonalProperties(){
        return this.personalProperty;
    }

    public ArrayList<Property> getFavoriteProperties(){
        return this.favoriteProperty;
    }

    private static GlobalState instance;

    //this model will have all the public data performed without login
}
