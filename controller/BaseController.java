package controller;

import javafx.event.ActionEvent;

/**
 * Provide the slot information of board
 * 
 * @author Anirban Bala Pranto
 */
public class BaseController {
  private String x = "Bruh";
  
  public void up(ActionEvent e){
    System.out.println("Base Interaction");
  }

  public String getX(){
    return this.x;
  } 
}
