package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;

public class GlobalState {
    private Stage publicStage;
    private boolean isLoggedIn = false; //this becomes true when someone logs in
    private Map<int, String> session; //this will store the email and the role

    public GlobalState(){

    }

    public void saveStage(Stage s){
        this.publicStage = s;
    }

    //Singleton Design Pattern
    public static GlobalState getInstance(){
        if(instance==null){
            instance = new GlobalState();
        }
        return instance;
    }

    public Stage getStage(){
        return this.publicStage;
    }

    private static GlobalState instance;

    //this model will have all the public data performed without login
}
