package model;

import java.util.*;

public class Property {
    private int id;
    private String listerType; //[Owner,Agent]
    private int listerID;
    private int propertyID;
    private static int idCount = 100; 
    //private int areaID;
    private String status = "active"; // [active,inactive]
    private String projectName;
    private int floorSize;
    private double psf;
    private String furnishStatus; // [fully, partially, unfurnished]
    private int numberOfBedroom; 
    private int numberOfBathroom;
    private ArrayList<String> facilities; // swimming pool, gym room and etc
    private ArrayList<String> keyFeatures; // air conditioning, washing machine
    private int rental_price; // per month
    private String address;
    private String city;
    private String state;
    private String propertyType; // condo, townhouse
    private ArrayList<String> photo;
    
    public Property(int id, String listerType, int listerID, Builder p){
        this.id = id;
        this.listerType = listerType;
        this.listerID = listerID;
        this.propertyID = idCount;
        idCount++; //property
        this.projectName = p.projectName;
        this.floorSize = p.floorSize;
        this.psf = p.psf;
        this.furnishStatus = p.furnishStatus; // fully, partially, unfurnished
        this.numberOfBedroom = p.numberOfBedroom; 
        this.numberOfBathroom = p.numberOfBathroom;
        this.facilities = p.facilities; // swimming pool, gym room and etc
        this.keyFeatures = p.keyFeatures; // air conditioning, washing machine
        this.rental_price = p.rental_price; // per month
        this.address = p.address;
        this.city = p.city;
        this.state = p.state;
        this.propertyType = p.propertyType; // condo, townhouse
        this.photo = p.photo;
    }
    public int getID(){return id;}
    public String getListerType(){return listerType;}
    public int listerID(){return listerID;}
    public int propertyID(){return propertyID;}
    public String getStatus(){return status;}
    public void setStatus(){
        if (status.equals("active")) status = "inactive";
        else status = "active";
    }
    public String getProjectName(){return projectName;}
    public void setProjectName(String p){projectName = p;}
    public int getFloorSize(){return floorSize;}
    public void setFloorSize(int f){floorSize = f;}
    public double getpsf(){return psf;}
    public void setpsf(int p){psf = p;}
    public String furnishStatus(){return furnishStatus;}
    public void setFurnishStatus(String f){furnishStatus = f;}
    public int getNumberOfBedroom(){return numberOfBedroom;}
    public void setNumberOfBedroom(int n){numberOfBedroom = n;}
    public int getNumberOfBathroom(){return numberOfBathroom;}
    public void setNumberOfBathroom(int n){numberOfBathroom = n;}
    public ArrayList<String> getFacilities(){return facilities;}
    public void setFacilties(ArrayList<String> f){facilities.addAll(f);}
    public ArrayList<String> getKeyFeatures(){return keyFeatures;}
    public void setKeyFeatures(ArrayList<String> k){keyFeatures.addAll(k);}
    public int getRental_price(){return rental_price;}
    public void setRental_price(int r){rental_price = r;}
    public String getAddress(){return address;}
    public void setAddress(String a){address = a;}
    public String getCity(){return city;}
    public void setCity(String c){city = c;}
    public String getState(){return state;}
    public void setState(String s){state = s;}
    public String getPropertyType(){return propertyType;}
    public void setPropertyType(String p){propertyType = p;}
    public ArrayList<String> getPhoto(){return photo;}
    public void setPhoto(ArrayList<String> p){photo.addAll(p);}

    public static class Builder {
        private String projectName;
        private int floorSize;
        private double psf;
        private String furnishStatus; 
        private int numberOfBedroom; 
        private int numberOfBathroom;
        private ArrayList<String> facilities; 
        private ArrayList<String> keyFeatures; 
        private int rental_price; 
        private String address;
        private String city;
        private String state;
        private String propertyType; 
        private ArrayList<String> photo;
    
        public Builder projectName(String p){projectName = p; return this;}
        public Builder floorSize(int f){floorSize = f; return this;}
        public Builder psf(double p){psf = p; return this;}
        public Builder furnishStatus(String f){furnishStatus = f; return this;}
        public Builder numberOfBedroom(int n){numberOfBedroom = n; return this;}
        public Builder numberOfBathroom(int n){numberOfBathroom = n; return this;}
        public Builder facilities(ArrayList<String> f){facilities.addAll(f); return this;}    
        public Builder keyFeatures(ArrayList<String> k){keyFeatures.addAll(k); return this;}
        public Builder rental_price(int r){rental_price = r; return this;}
        public Builder address(String a){address = a; return this;}
        public Builder city(String c){city = c; return this;}
        public Builder state(String s){state = s; return this;}
        public Builder propertyType(String p){propertyType = p; return this;}
        public Builder photo(ArrayList<String> p){photo.addAll(p); return this;}

        public Property build(int id, String listerType, int listerID){return new Property(id, listerType, listerID, this);}
    }    
}