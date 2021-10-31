package model;
import java.util.*;

public class Agent extends Tenant{
    private static int globalId = Database.readUpdate("Agent") + 1;
    private String licenseNo;
    public Agent(int Id, String fullName, String password, String email, String phoneNumber, String licenseNo){
        super(Id, fullName, password, email, "Agent", phoneNumber);
        this.licenseNo = licenseNo;
    }

    public static Agent createAgent(String fullName, String password, String email, String phoneNumber, String licenseNo){
        int Id = ++globalId;
        Database.writeData("Agent", Arrays.asList(Integer.toString(Id), fullName, password, email, "Agent", phoneNumber, licenseNo));
        Database.writeUpdate("Agent", Arrays.asList(Integer.toString(Id)));
        return new Agent(Id, fullName, password, email, phoneNumber, licenseNo);
    }

    public ArrayList<Property> getAgentProperty(){
        //this fetches all own property of this agent
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
















