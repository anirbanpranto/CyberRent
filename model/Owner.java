package model;
import java.util.*;

public class Owner extends Tenant{
    private static int globalId = Database.readUpdate("Owner") + 1;

    public Owner(int Id, String fullName, String password, String email, String phoneNumber){
        super(Id, fullName, password, email, "Owner", phoneNumber);
    }

    public static Owner createOwner(String fullName, String password, String email, String phoneNumber){
        int Id = ++globalId;
        Database.writeData("Owner", Arrays.asList(Integer.toString(Id), fullName, password, email, "Owner", phoneNumber));
        Database.writeUpdate("Owner", Arrays.asList(Integer.toString(Id)));
        Database.writeData("email", Arrays.asList(email));
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
















