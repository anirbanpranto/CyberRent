package model;
import java.util.*;

public class Agent extends User{
    private static int globalId = Database.readUpdate("Agent");
    private String licenseNo;
    public Agent(int Id, String fullName, String password, String email, String phoneNumber, String licenseNo){
        super(Id, password, email, "Active", "Owner", phoneNumber);
        this.licenseNo = licenseNo;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public static Agent createAgent(String fullName, String userName, String password, String email, String phoneNumber, String licenseNo){
        int Id = ++globalId;
        Database.writeData("Agent", Arrays.asList(Integer.toString(Id), fullName, userName, password, email, "Active", "Owner", phoneNumber, licenseNo));
        Database.writeUpdate("Agent", Arrays.asList(Integer.toString(Id)));
        return new Agent(Id, fullName, password, email, phoneNumber, licenseNo);
    }

    public ArrayList<Property> getAgentProperty(){
        //this fetches all own property of this owner
        GlobalState state = GlobalState.getInstance();
        if(state.getPersonalProperties() != null){
            return state.getFavoriteProperties();
        }
        ArrayList<Property> allProperty = state.getProperties();
        ArrayList<Property> agentList = new ArrayList<Property>();
    
        for(int i = 0; i < allProperty.size(); i++){
            if(allProperty.get(i).getListerID() == this.getId() && allProperty.get(i).getListerType() == "Agent"){
                agentList.add(allProperty.get(i));
            }
        }

        state.setPersonalProperties(agentList);
        return agentList;
    }
    
    
}
















