package model;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;

public class Database{
  public static void writeData(String tableName, List<String> Entry){
    String fileName = tableName + ".csv";
    String filePath = "./Database/"+fileName;
    File csvFile = new File(filePath);
    if (csvFile.isFile()) {
        try{
            FileWriter pw = new FileWriter(filePath,true);
            System.out.println("Writing " + fileName);
            for(int i = 0; i < Entry.size(); i++){
                  pw.append(Entry.get(i));
                  if(i == Entry.size() - 1){
                    pw.append("\n");
                  }
                  else{
                    pw.append(",");
                  }
            }
            pw.flush();
            pw.close();
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }
    else{
      try{
          FileWriter pw = new FileWriter(filePath);
          for(int i = 0; i < Entry.size(); i++){
                pw.append(Entry.get(i));
                if(i == Entry.size() - 1){
                  pw.append("\n");
                }
                else{
                  pw.append(",");
                }
          }
          pw.flush();
          pw.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  public static void writeUpdate(String tableName, List<String> Entry){
    String fileName = tableName + "Id" + ".csv";
    String filePath = "./Database/"+fileName;
    File csvFile = new File(filePath);
    try{
          FileWriter pw = new FileWriter(filePath);
          for(int i = 0; i < Entry.size(); i++){
                pw.append(Entry.get(i));
                if(i == Entry.size() - 1){
                  pw.append("\n");
                }
                else{
                  pw.append(",");
                }
          }
          pw.flush();
          pw.close();
    }
    catch(Exception e){
        e.printStackTrace();
    }
  }
  
  public static int readUpdate(String tableName){
    String fileName = tableName + "Id.csv";
    String filePath = "./Database/"+fileName;
    File csvFile = new File(filePath);
    List<List<String>> table = new ArrayList<List<String>>();
    String row = "";
    if (csvFile.isFile()) {
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
            row = csvReader.readLine();
            csvReader.close();
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }
    try{
        int ans = Integer.parseInt(row);
        return ans;
    }
    catch(NumberFormatException e){
        return 100;
    }
  }

  public static void copyFileUsingStream(File source, File dest) throws IOException {
    InputStream is = null;
    OutputStream os = null;
    try {
        is = new FileInputStream(source);
        os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
    } finally {
        is.close();
        os.close();
    }
  }

  public static List<String> parseArray(String list){
    String[] data = list.split("/");
    List<String> temp = Arrays.asList(data);
    return temp;
  }

  public static String makeString(List<String> data){
    String out = "[";
    for(int i = 0; i < data.size(); i++){
      out += data.get(i);
      if(i != (data.size() - 1)){
        out += "/";
      }
    }
    out += "]";
    return out;
  }

  public static List<List<String>> readData(String tableName){
    String fileName = tableName + ".csv";
    String filePath = "./Database/"+fileName;
    File csvFile = new File(filePath);
    List<List<String>> table = new ArrayList<List<String>>();
    if (csvFile.isFile()) {
        try{
            String row = null;
            BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                List<String> temp = Arrays.asList(data);
                table.add(temp);
            }
            csvReader.close();
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }
    return table;
  }
}
