package model;
import java.util.*;

public class Owner extends User{
    private static int globalId = 100;
    public Owner(int Id, String fullName, String userName, String password, String email, String phoneNumber){
        super(Id, fullName, userName, password, email, "Active", "Owner", phoneNumber);
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setName(String name){
        this.fullName = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPhone(String phone){
        this.phoneNumber = phoneNumber;
    }

    public static Owner createOwner(String fullName, String userName, String password, String email, String phoneNumber){
        int Id = ++globalId;
        Database.writeData("Owner", Arrays.asList(Integer.toString(Id), fullName, userName, password, email, "Active", "Owner", phoneNumber));
        Database.writeUpdate("Owner", Arrays.asList(Integer.toString(Id)));
        return new Owner(Id, fullName, userName, password, email, phoneNumber);
    }

    public ArrayList<Property> getOwnerProperty(){
        //this fetches all own property of this owner
        GlobalState state = GlobalState.getInstance();
        ArrayList<Property> allProperty = state.getProperties();
        ArrayList<Property> ownerList = new ArrayList<Property>();
    
        for(int i = 0; i < allProperty.size(); i++){
            if(allProperty.get(i).getListerID() == this.getId() && allProperty.get(i).getListerType() == "Owner"){
                ownerList.add(allProperty.get(i));
            }
        }
        return ownerList;
    }
    
    
}
















