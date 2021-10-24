package model;
import java.util.*;

public class Test{
    public void databaseWriteTest(){
      List<String> row = Arrays.asList("1", "Anirban Bala", "Codeboi08", "anirbanpranto@gmail.com", "spiderman123");
      Database.writeData("User", row);
    }
    
    public void readUpdateTest(){
      int id = Database.readUpdate("Owner");
      System.out.println(id);
    }

    public void databaseReadTest(){
      List<List<String>> list = Database.readData("Owner");
      for(int i = 0; i < list.size(); i++){
        for(int j = 0; j < list.get(i).size(); j++){
          System.out.println(list.get(i).get(j));
        }
      }
    }
    
    public void OwnerIntegration(){
        Owner temp = Owner.createOwner("Anirban Bala", "codeboi08", "spiderman_4", "anirbanpranto@gmail.com", "0189717552");
    }

    public void parseTest(){
      String arrayField = "[item1 item2 item3 item4]";
      arrayField = arrayField.replace("[", "");
      arrayField = arrayField.replace("]", "");
      List<String> data = Database.parseArray(arrayField);
      for(int i = 0; i < data.size(); i++){
        System.out.println(data.get(i));
      }
    }

    public void convertTest(){
      List<String> array = Arrays.asList("item1", "item2", "item3");
      String output = Database.makeString(array);
      System.out.println(output);
    }
}
