package model;
import java.util.*;

public class Owner extends User{
    private static int globalId = 100;
    public Owner(int Id, String fullName, String userName, String password, String email, String phoneNumber){
        super(Id, fullName, userName, password, email, "Active", "Owner", phoneNumber);
    }

    public static Owner createOwner(String fullName, String userName, String password, String email, String phoneNumber){
        int Id = ++globalId;
        Database.writeData("Owner", Arrays.asList(Integer.toString(Id), fullName, userName, password, email, "Active", "Owner", phoneNumber));
        Database.writeUpdate("Owner", Arrays.asList(Integer.toString(Id)));
        return new Owner(Id, fullName, userName, password, email, phoneNumber);
    }
}
