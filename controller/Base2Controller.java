package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Provide the slot information of board
 * 
 * @author Anirban Bala Pranto
 */
public class ViewChanger {
  private String x = "Bruh";
  
  public void down(ActionEvent event){
    Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
    try
    {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Base.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene(root));
    }
    catch (java.io.IOException ioe)
    {
        ioe.printStackTrace();
    }
  }

  public String getX(){
    return this.x;
  } 
}