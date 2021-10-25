import java.security.PublicKey;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import model.GlobalState;

public class Main extends Application{
  @Override
  public void start(Stage primaryStage) throws Exception{
      //singleton design pattern
      GlobalState mainScreen = GlobalState.getInstance();
      mainScreen.saveStage(primaryStage);
      Parent root = FXMLLoader.load(getClass().getResource("view/Base.fxml"));
      primaryStage.setTitle("Login");
      primaryStage.setScene(new Scene(root, 1280, 720, Color.BLACK));
      primaryStage.show();
  }

  public static void main(String[] args){
      System.out.println("Hello");
      launch(args);

      ArrayList<String> f = new ArrayList<>();
      ArrayList<String> k = new ArrayList<>();
      ArrayList<String> photos = new ArrayList<>();
      Property p = new Property.Builder().projectName("ABC").floorSize(1000).psf(0.9).furnishStatus("Fully").
              numberOfBedroom("2").numberOfBathroom(1).facilities(f).
              keyFeatures(k).rental_price(1000).address("address").
              city("Cyberjaya").state("Selangor").propertyType("condominium").
              photo(photos).build("Owner",1);
      //System.out.println(p.getProjectName());
      //System.out.println(p.getPropertyType());
      //System.out.println(p.getFacilities());
      //System.out.println(p.getFurnishStatus());

      Property q = new Property.Builder().projectName("FFF").floorSize(1000).psf(0.9).furnishStatus("Fully").
              numberOfBedroom("2").numberOfBathroom(1).facilities(f).
              keyFeatures(k).rental_price(1000).address("address").
              city("Cyberjaya").state("Selangor").propertyType("condominium").
              photo(photos).build("Owner",1);

      Property r = new Property.Builder().projectName("DEF").floorSize(1000).psf(0.9).furnishStatus("Fully").
              numberOfBedroom("2").numberOfBathroom(1).facilities(f).
              keyFeatures(k).rental_price(1000).address("address").
              city("Cyberjaya").state("Selangor").propertyType("condominium").
              photo(photos).build("Owner",1);

      Property s = new Property.Builder().projectName("CAT").floorSize(1000).psf(0.9).furnishStatus("Fully").
              numberOfBedroom("2").numberOfBathroom(1).facilities(f).
              keyFeatures(k).rental_price(1000).address("address").
              city("Cyberjaya").state("Selangor").propertyType("condominium").
              photo(photos).build("Owner",1);


  }
}
