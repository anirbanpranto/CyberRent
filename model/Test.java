package model;
import java.util.*;

public class Test{
    public static void databaseWriteTest(){
      List<String> row = Arrays.asList("1", "Anirban Bala", "Codeboi08", "anirbanpranto@gmail.com", "spiderman123");
      Database.writeData("User", row);
    }

    public static void databaseReadTest(){
      List<List<String>> list = Database.readData("User");
      for(int i = 0; i < list.size(); i++){
        for(int j = 0; j < list.get(i).size(); j++){
          System.out.println(list.get(i).get(j));
        }
      }
    }
}
