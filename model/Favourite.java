package model;
import java.util.*;

public class Favourite {

    private int favouriteID;
    private int tenantID;
    private int propertyID;
    private static int idCount = Database.readUpdate("Favourite")+1;

    //add a new favourite property by a tenant
    public Favourite(int tenantID, int propertyID){
        this.favouriteID = idCount;
        this.tenantID = tenantID;
        this.propertyID = propertyID;
    }

    public void writeFile(){
        Database.writeData("Favourite",Arrays.asList(
                Integer.toString(favouriteID),
                Integer.toString(tenantID),
                Integer.toString(propertyID)));

        Database.writeUpdate("Favourite",Arrays.asList(Integer.toString(favouriteID)));
    }

    public int getFavouriteListID(){
        return favouriteID;
    }

    public int getTenantID(){
        return tenantID;
    }

    public int getPropertyID(){
        return propertyID;
    }

}
