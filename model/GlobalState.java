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
    private boolean isPersonal = false;
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
    private ArrayList<Owner> owner;
    private ArrayList<Tenant> tenant;
    private ArrayList<Agent> agent;
    private Property selected;

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
                    photo(photos).build(Integer.parseInt(l.get(0)), l.get(1),Integer.parseInt(l.get(2)));
            tempProp.add(p);
        }
        this.propeties = tempProp;
        System.out.println("System total properties: " + propeties.size());

        List<List<String>> strAgent = Database.readData("Agent");
        ArrayList<Agent> tempAgent = new ArrayList<>();
        for(int i = 0; i < strAgent.size(); i++){
            System.out.println(Integer.parseInt(strAgent.get(i).get(0)) + " " + strAgent.get(i).get(1)+ " " +strAgent.get(i).get(2)+ " " + strAgent.get(i).get(3)+ " " + strAgent.get(i).get(5)+ " " + strAgent.get(i).get(7));
            Agent t = new Agent(Integer.parseInt(strAgent.get(i).get(0)), strAgent.get(i).get(1), strAgent.get(i).get(2), strAgent.get(i).get(3), strAgent.get(i).get(5), strAgent.get(i).get(7));
            tempAgent.add(t);
        }
        this.agent = tempAgent;
        System.out.println("System total agents: " + agent.size());

        List<List<String>> strOwner = Database.readData("Owner");
        ArrayList<Owner> tempOwner = new ArrayList<>();
        for(int i = 0; i < strOwner.size(); i++){
            Owner t = new Owner(Integer.parseInt(strOwner.get(i).get(0)), strOwner.get(i).get(1), strOwner.get(i).get(2), strOwner.get(i).get(3), strOwner.get(i).get(5));
            tempOwner.add(t);
        }
        this.owner = tempOwner;
        System.out.println("System total owners: " + owner.size());

        List<List<String>> strTenant = Database.readData("Tenant");
        ArrayList<Tenant> tempTenant = new ArrayList<>();
        for(int i = 0; i < strTenant.size(); i++){
            Tenant t = new Tenant(Integer.parseInt(strTenant.get(i).get(0)), strTenant.get(i).get(1), strTenant.get(i).get(2), strTenant.get(i).get(3), "Tenant", strTenant.get(i).get(5));
            tempTenant.add(t);
        }
        this.tenant = tempTenant;
        System.out.println("System total tenants: " + tenant.size());

    }

    public void setIsPersonal(boolean flag){
        this.isPersonal = flag;
    }

    public boolean getIsPersonal(){
        return this.isPersonal;
    }

    public void EditProfilePerformed(String tableName){ //edit the temporary data and put them in persistent data
        //make an ArrayList of the Table values = []
        if(tableName.equals("Agent")){
            for(int i = 0; i < this.agent.size(); i++){
                if(agent.get(i).getId() == this.loggedInId){
                    agent.get(i).setEmail(this.email);
                    agent.get(i).setName(this.fullName);
                    agent.get(i).setPhoneNumber(this.phoneNumber);
                    agent.get(i).setLicenseNo(this.agentLicense);
                    agent.get(i).setPassword(this.password);
                    Database.writeAllData("Agent", Database.AgentToList(agent));
                    break;
                }
            }
        }
        if(tableName.equals("Owner")){
            for(int i = 0; i < this.owner.size(); i++){
                if(owner.get(i).getId() == this.loggedInId){
                    owner.get(i).setEmail(this.email);
                    owner.get(i).setName(this.fullName);
                    owner.get(i).setPhoneNumber(this.phoneNumber);
                    owner.get(i).setPassword(this.password);
                    Database.writeAllData("Owner", Database.OwnerToList(owner));
                    break;
                }
            }
        }
        if(tableName.equals("Tenant")){
            for(int i = 0; i < this.tenant.size(); i++){
                if(tenant.get(i).getId() == this.loggedInId){
                    tenant.get(i).setEmail(this.email);
                    tenant.get(i).setName(this.fullName);
                    tenant.get(i).setPhoneNumber(this.phoneNumber);
                    tenant.get(i).setPassword(this.password);
                    Database.writeAllData("Tenant", Database.TenantToList(tenant));
                    break;
                }
            }
        }
        //flush values inside our database
        
        //Database.writeAllData(tableName, All);
    }

    public void setSelected(Property p){
        this.selected = p;
    }

    public Agent getAgent(int id){
        for(Agent a:this.agent){
            if(a.getId() == id){
                return a;
            }
        }
        return null;
    }

    public Owner getOwner(int id){
        for(Owner a:this.owner){
            if(a.getId() == id){
                return a;
            }
        }
        return null;
    }

    public Property getSelected(){
        return this.selected;
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

    //read latest favourite csv in system, cuz user will add new favourite to csv during system run.
    public void updateTempFavouriteList(){
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
    public void setAgentLicense(String newAgentLicense){ System.out.println(newAgentLicense); this.agentLicense = newAgentLicense; }
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
