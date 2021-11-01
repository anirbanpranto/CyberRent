package model;

import model.Property;
import model.SortByRentalPrice;
import model.Test;
import java.util.*;

public class SearchEngine {
    //private static ArrayList<Property> desiredProperties = new ArrayList<>();
    private static ArrayList<Property> allProperties = new ArrayList<>();
    private static ArrayList<Property> tempProperties = new ArrayList<>();

    public static ArrayList<Property> search(String projectName, String propertyType, int price_Min, int price_Max, int floorSize_Min, int floorSize_Max, 
                                             double psf_Min, double psf_Max, String numberOfBedRoom, ArrayList<String> facilities, 
                                             ArrayList<String> keyFeatures){
        copyProperties();

        search_Name(projectName);
        search_propertyType(propertyType);
        search_Price(price_Min, price_Max);
        search_floorSize(floorSize_Min, floorSize_Max);
        search_psf(psf_Min, psf_Max);
        search_numberOfbedroom(numberOfBedRoom);
        search_Facilities(facilities);
        search_keyFeatures(keyFeatures);

        return allProperties;
        
    }

    public static ArrayList<Property> search(String projectName, String propertyType, int price_Min, int price_Max, int floorSize_Min, int floorSize_Max, 
                                             double psf_Min, double psf_Max, String numberOfBedRoom, ArrayList<String> facilities, 
                                             ArrayList<String> keyFeatures, String sortType, String status){
        copyProperties();
        
        sortByX(sortType);

        search_Name(projectName);
        search_propertyType(propertyType);
        search_Price(price_Min, price_Max);
        search_floorSize(floorSize_Min, floorSize_Max);
        search_psf(psf_Min, psf_Max);
        search_numberOfbedroom(numberOfBedRoom);
        search_Facilities(facilities);
        search_keyFeatures(keyFeatures);
        search_status(status);

        return allProperties;
        
    }

    private static void copyProperties(){
        allProperties.clear();
        tempProperties = GlobalState.getInstance().getProperties();
        
        for(int i = 0; i < tempProperties.size(); i++){
            allProperties.add(tempProperties.get(i));
        }
    }

    private static void sortByX(String sortType){
        if(sortType.equals("(Price) From Lowest"))
            Collections.sort(allProperties, new SortByRentalPrice());
        else if(sortType.equals("(Price) From Highest")){
            Collections.sort(allProperties, new SortByRentalPrice());
            Collections.reverse(allProperties);
        }
    }

    private static void search_Name(String projectName){
        int counter = 0;

        while(allProperties.size() != counter){
            String current = allProperties.get(counter).getProjectName().toLowerCase();
            if(projectName.equals(""))
                break;
            else if(!(current.toLowerCase().contains(projectName.toLowerCase()))){
                allProperties.remove(counter);
                continue;
            }
            counter++;
        }
    }

    private static void search_propertyType(String propertyType){
        int counter = 0;

        while(allProperties.size() != counter){
            String current = allProperties.get(counter).getPropertyType().toLowerCase();
            if(propertyType.toLowerCase().equals("All Residential"))
                break;
            else if(!(current.equals(propertyType.toLowerCase()))){
                allProperties.remove(counter);
                continue;
            }
            counter++;
        }
    }

    private static void search_Price(int min, int max){
        int counter = 0;

        while(allProperties.size() != counter){
            Property current = allProperties.get(counter);
            if(current.getRental_price() < min || current.getRental_price() > max){
                allProperties.remove(current);
                continue;
            }
            counter++;
        }
    }

    private static void search_floorSize(int min, int max){
        int counter = 0;

        while(allProperties.size() != counter){
            Property current = allProperties.get(counter);
            if(current.getFloorSize() < min || current.getFloorSize() > max){
                allProperties.remove(current);
                continue;
            }
            counter++;
        }
    }

    private static void search_psf(double min, double max){
        int counter = 0;

        while(allProperties.size() != counter){
            Property current = allProperties.get(counter);
            if(current.getpsf() < min || current.getpsf() > max){
                allProperties.remove(current);
                continue;
            }
            counter++;
        }
    }

    private static void search_numberOfbedroom(String numberOfBedRoom){
        int counter = 0;
        int numOfBedRoom = 0;

        if(!(numberOfBedRoom.equals("Any")))
            numOfBedRoom = Character.getNumericValue(numberOfBedRoom.charAt(0));
        
        while(allProperties.size() != counter){
            int current = allProperties.get(counter).getNumberOfBedroom();
            if(numberOfBedRoom.equals("Any"))
                break;
            else if(current < numOfBedRoom){
                System.out.println(current + " and " + numOfBedRoom);
                allProperties.remove(counter);
                continue;
            }
            counter++;
        }
    }

    private static void search_Facilities(ArrayList<String> facilities){
        int counter = 0;
        
        while(allProperties.size() != counter){
            ArrayList<String> current = allProperties.get(counter).getFacilities();
            for(int i = 0; i < facilities.size(); i++){
                if(current.contains(facilities.get(i)))
                    continue;
                else{
                    allProperties.remove(counter);
                    counter--;
                    break;
                }
            }
            counter++;
        }
        
    }

    private static void search_keyFeatures(ArrayList<String> keyFeatures){
        int counter = 0;
        
        while(allProperties.size() != counter){
            ArrayList<String> current = allProperties.get(counter).getKeyFeatures();
            for(int i = 0; i < keyFeatures.size(); i++){
                if(current.contains(keyFeatures.get(i)))
                    continue;
                else{
                    allProperties.remove(counter);
                    counter--;
                    break;
                }
            }
            counter++;
        }
    }

    private static void search_status(String status){
        int counter = 0;

        while(allProperties.size() != counter){
            String current = allProperties.get(counter).getStatus().toLowerCase();
            if(!(current.equals(status.toLowerCase()))){
                allProperties.remove(counter);
                continue;
            }
            counter++;
        }
    }
}
