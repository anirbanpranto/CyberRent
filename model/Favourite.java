package model;
import java.util.*;

public class Favourite {

    private int favouriteID;
    private int userID;
    private String role;
    private int propertyID;
    private static int globalId = Database.readUpdate("Favourite")+1;

    //add a new favourite property by a tenant
    public Favourite(int id, int userID, String role, int propertyID){
        this.favouriteID = id;
        this.userID = userID;
        this.role = role;
        this.propertyID = propertyID;
    }

    public static Favourite createFavourite(int userID, String role, int propertyID){
        int Id = ++globalId;
        Database.writeData("Favourite",Arrays.asList(Integer.toString(Id),Integer.toString(userID),role,Integer.toString(propertyID)));
        Database.writeUpdate("Favourite",Arrays.asList(Integer.toString(Id)));
        return new Favourite(Id,userID,role,propertyID);
    }

    public void writeFile(){
        Database.writeData("Favourite",Arrays.asList(
                Integer.toString(favouriteID),
                Integer.toString(userID),
                role,
                Integer.toString(propertyID)));

        Database.writeUpdate("Favourite",Arrays.asList(Integer.toString(favouriteID)));
    }

    public int getFavouriteListID(){
        return favouriteID;
    }

    public int getUserID(){
        return userID;
    }

    public int getPropertyID(){
        return propertyID;
    }

    public String getRole(){
        return role;
    }

}
