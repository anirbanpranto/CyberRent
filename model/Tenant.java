package model;
import java.util.*;

public class Tenant extends User {
    private ArrayList<Property> favourite = new ArrayList<>(); // to store favourite property
    private static int idCount = Database.readUpdate("Tenant")+1; 
    public Tenant(int id, String fullname, String password, String email, String phoneNumber){
        super(idCount,fullname,password,email,"Tenant",phoneNumber);
        
    }

    public static Tenant createTenant(String fullname, String password, String email, String phoneNumber){
        int id = ++idCount;
        Database.writeData("Tenant", Arrays.asList(Integer.toString(idCount), fullname, password, email, "Tenant", phoneNumber));
        Database.writeUpdate("Tenant", Arrays.asList(Integer.toString(idCount)));
        
        return new Tenant(id, fullname, password, email, phoneNumber);
    }

    public ArrayList<Property> getFavourite(){
        return favourite;
        
    }
    
    public void addFavourite(Property f){
        favourite.add(f);
        Database.writeData("Favourite", Arrays.asList(Integer.toString(getId()),Integer.toString(f.getID()))); // user id, property id
    } 

    public void removeFavourite(Property r){
        favourite.remove(r);
        // Database.removeData("Favourite", )
    }

}
