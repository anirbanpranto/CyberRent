package model;
import java.util.*;

public class Favourite {

    //private int favouriteListID;
    private int tenantID;
    private ArrayList<Property> favouriteProperty;

    public Favourite(int tenantID){
        this.tenantID = tenantID;
        this.favouriteProperty = new ArrayList<Property>();
    }

    public void addFavourite(Property p){
        for(int i = 0; i < favouriteProperty.size(); i++){
            if(favouriteProperty.get(i).getPropertyID() == p.getPropertyID()){
                System.out.println("Already Added to favourite.");
            }else{
                this.favouriteProperty.add(p);
            }
        }
    }

    public void removeFavourite(Property p){
        this.favouriteProperty.remove(p);
    }

    public void clearFavourite(){
        this.favouriteProperty.clear();
    }

    public ArrayList<Property> getFavouriteProperty(){
        return favouriteProperty;
    }

}
