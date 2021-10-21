package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PublicModel {
    private Stage publicStage;

    public PublicModel(){

    }

    public void saveStage(Stage s){
        this.publicStage = s;
    }

    //Singleton Design Pattern
    public static PublicModel getInstance(){
        if(instance==null){
            instance = new PublicModel();
        }
        return instance;
    }

    public Stage getStage(){
        return this.publicStage;
    }

    private static PublicModel instance;

    //this model will have all the public data performed without login
}
