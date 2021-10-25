package model;
import java.util.*;

public class Owner extends User{
    private static int globalId = Database.readUpdate("Owner");

    public Owner(int Id, String fullName, String password, String email, String phoneNumber){
        super(Id, fullName, password, email, "Owner", phoneNumber);
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public static Owner createOwner(String fullName, String userName, String password, String email, String phoneNumber){
        int Id = ++globalId;
        Database.writeData("Owner", Arrays.asList(Integer.toString(Id), fullName, userName, password, email, "Active", "Owner", phoneNumber));
        Database.writeUpdate("Owner", Arrays.asList(Integer.toString(Id)));
        return new Owner(Id, fullName, password, email, phoneNumber);
    }

    public ArrayList<Property> getOwnerProperty(){
        //this fetches all own property of this owner
        GlobalState state = GlobalState.getInstance();
        if(state.getPersonalProperties().size() > 0){
            return state.getFavoriteProperties();
        }
        ArrayList<Property> allProperty = state.getProperties();
        ArrayList<Property> ownerList = new ArrayList<Property>();
    
        for(int i = 0; i < allProperty.size(); i++){
            if(allProperty.get(i).getListerID() == this.getId() && allProperty.get(i).getListerType() == "Owner"){
                ownerList.add(allProperty.get(i));
            }
        }

        state.setPersonalProperties(ownerList);
        return ownerList;
    }
    
    
}
















