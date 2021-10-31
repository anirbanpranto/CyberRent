package model;
import java.util.*;

public class Favourite {

    private int favouriteID;
    private int userID;
    private String role;
    private int propertyID;
    private static int idCount = Database.readUpdate("Favourite")+1;

    //add a new favourite property by a tenant
    public Favourite(int userID, String role, int propertyID){
        System.out.println("Here");
        this.favouriteID = idCount;
        this.userID = userID;
        this.role = role;
        this.propertyID = propertyID;
        idCount++;
        writeFile();
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
