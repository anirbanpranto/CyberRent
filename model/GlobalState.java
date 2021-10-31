package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.stage.Stage;
import java.util.*;

public class GlobalState {
    private Stage publicStage;
    private boolean isLoggedIn = false; //this becomes true when someone logs in
    private Map<Integer, Owner> ownerLookup; //loaded when constructor is called
    private Map<Integer, Agent> agentLookup; //loaded when constructor is called
    private Map<Integer, Tenant> tenantLookup; //loaded when constructor is called
    private Map<Integer, Admin> adminLookup; //loaded when constructor is called
    //private Map<Integer, Area> areaLookup; //load when constructor is called
    /*
      the point of having these lookup tables are related to the data that we're gonna display. For propeties, we need to show the data of affiliated Owners, Agents. This is also useful for logging in.

    */
    int loggedInId = -1;
    String email = null;
    String fullName = null;
    String phoneNumber = null;
    String password = null;
    String role = null;
    String status = null;
    String agentLicense = null;

    private Map<Integer, String> session; //this will store the email and the role after login
    private ArrayList<Property> propeties; //to be loaded in the globalstate constructor
    //private ArrayList<Property> favourites;
    private ArrayList<Property> personalProperty; //null at the begining, but its loaded when the login method is called
    private ArrayList<Property> favoriteProperty; //null at the begining, but its loaded when the login method is called
    private ArrayList<Owner> owner = null;
    private ArrayList<Tenant> tenant = null;
    private ArrayList<Agent> agent = null;

    private GlobalState(){
        //load all properties here from database
        //read the database for properties and use those values to create an ArrayList of all properties
        //read the database and load the object
        List<List<String>> strProperties = Database.readData("Property");
        ArrayList<Property> tempProp = new ArrayList<>();
        for(int i = 0; i < strProperties.size(); i++){
            List<String> l = strProperties.get(i);
            ArrayList<String> facilities = new ArrayList<String>(Database.parseArray(l.get(10)));
            ArrayList<String> features = new ArrayList<String>(Database.parseArray(l.get(11)));
            ArrayList<String> photos = new ArrayList<String>(Database.parseArray(l.get(17)));
            Property p = new Property.Builder().projectName(l.get(4)).floorSize(Integer.parseInt(l.get(5))).psf(Double.parseDouble(l.get(6))).furnishStatus(l.get(7)).
                                         numberOfBedroom(Integer.parseInt(l.get(8))).numberOfBathroom(Integer.parseInt(l.get(9))).facilities(facilities).
                                         keyFeatures(features).rental_price(Integer.parseInt(l.get(12))).address(l.get(13)).
                                         city(l.get(14)).state(l.get(15)).propertyType(l.get(16)).
                                         photo(photos).build(l.get(1),Integer.parseInt(l.get(2)));
            tempProp.add(p);
        }
        this.propeties = tempProp;
        System.out.println("System total properties: " + propeties.size());

    }

    private void EditPerformed(String tableName){ //edit the temporary data and put them in persistent data
        //make an ArrayList of the Table values = []
        //flush values inside our database
        ArrayList<List<String>> All = null;
        //Database.writeAllData(tableName, All);
    }

    public void saveStage(Stage s){
        this.publicStage = s;
    }


    public void setSession(int id, String fullname, String password, String email, String role, String phoneNumber){
        this.loggedInId = id;
        this.fullName = fullname;
        this.password = password;
        this.email = email;
        this.role = role;
        this.phoneNumber = phoneNumber;

        ArrayList<Property> tempPersonaList = new ArrayList<>();
        for(int i = 0; i < this.propeties.size(); i++){
            if(this.propeties.get(i).getListerID() == this.loggedInId && this.propeties.get(i).getListerType().equals(this.role)){
                tempPersonaList.add(this.propeties.get(i));
                //System.out.println("Inserting" + " " + tempPersonaList.size());
            }
        }
        this.personalProperty = tempPersonaList;
        System.out.println("Personal property: " + personalProperty.size());

        //Favourite
        List<List<String>> strFavourite = Database.readData("Favourite");
        List<List<String>> strProperty = Database.readData("Property");
        ArrayList<Property> tempFavouriteList = new ArrayList<>();

        for(int i = 0; i < strFavourite.size(); i++){
            if(this.loggedInId == Integer.parseInt(strFavourite.get(i).get(1)) && this.role.equals(strFavourite.get(i).get(2))){
                for(int k = 0; k < this.propeties.size(); k++){
                    if(strFavourite.get(i).get(3).equals(strProperty.get(k).get(0))){
                        tempFavouriteList.add(this.propeties.get(k));
                    }
                }
            }
        }
        this.favoriteProperty = tempFavouriteList;
        System.out.println("Favourite property: " + favoriteProperty.size());
    }
    public int getLoggedInId(){
        return this.loggedInId;
    }
    public String getFullName(){ return this.fullName; }
    public String getPassword(){ return this.password; }
    public String getEmail() { return this.email; }
    public String getRole(){
        return this.role;
    }
    public String getPhoneNumber() { return this.phoneNumber; }
    public String getAgentLicense(){ return this.agentLicense; }
    public void setNewFullName(String newFullName){ this.fullName = newFullName; }
    public void setNewPhoneNumber(String newPhoneNumber){ this.phoneNumber = newPhoneNumber; }
    public void setNewPassword(String newPassword){ this.password = newPassword; }
    public void setAgentLicense(String newAgentLicense){ this.agentLicense = newAgentLicense; }
    public void flushSession(){
        this.loggedInId = -1;
        this.email = null;
        this.fullName = null;
        this.phoneNumber = null;
        this.password = null;
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
