package model;
import java.util.*;

public class Property {
    private int id;
    private String listerType; //[Owner,Agent]
    private int listerID;
    private int propertyID;
    //private int areaID;
    private String status = "active"; // [active,inactive]
    private String projectName;
    private int floorSize;
    private double psf;
    private String furnishStatus; // [fully, partially, unfurnished]
    private int numberOfBedroom; // [1,2,3,4,...]
    private int numberOfBathroom;
    private ArrayList<String> facilities; // swimming pool, gym room and etc
    private ArrayList<String> keyFeatures; // air conditioning, washing machine
    private int rental_price; // per month
    private String address;
    private String city;
    private String state;
    private String propertyType; // condo, townhouse
    private ArrayList<String> photo;

    // Sample to create a Property class object:
    /*
        ArrayList<String> f = new ArrayList<>();
        ArrayList<String> k = new ArrayList<>();
        ArrayList<String> photos = new ArrayList<>();
        Property p = new Property.Builder().projectName("ABC").floorSize(1000).psf(0.9).furnishStatus("Fully").
                                         numberOfBedroom(2).numberOfBathroom(1).facilities(f).
                                         keyFeatures(k).rental_price(1000).address("address").
                                         city("Cyberjaya").state("Selangor").propertyType("condominium").
                                         photo(photos).build(1,"Owner",1, "Active");         // to read existing property

        Property p = new Property.Builder().projectName("ABC").floorSize(1000).psf(0.9).furnishStatus("Fully").
                                         numberOfBedroom(2).numberOfBathroom(1).facilities(f).
                                         keyFeatures(k).rental_price(1000).address("address").
                                         city("Cyberjaya").state("Selangor").propertyType("condominium").
                                         photo(photos).createProperty("Owner",1);   // to create new property
    */

    public Property(int id, String listerType, int listerID, String status, Builder p){
        this.id = id;
        this.listerType = listerType;
        this.listerID = listerID;
        this.status = status;
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

    public void writeFile(){
        // this function will be used in Controller
        Database.writeData("Property", Arrays.asList(Integer.toString(id),  listerType     , Integer.toString(listerID) ,
                                                     status, projectName, Integer.toString(floorSize), Double.toString(psf), 
                                                     furnishStatus,Integer.toString(numberOfBedroom), Integer.toString(numberOfBathroom), Database.makeString(facilities), 
                                                     Database.makeString(keyFeatures),Integer.toString(rental_price), address,city, 
                                                     state, propertyType, Database.makeString(photo)));
        
        Database.writeUpdate("Property", Arrays.asList(Integer.toString(id)));
    }


    public int getID(){return id;}
    public String getListerType(){return listerType;}
    public int getListerID(){return listerID;}
    public int getPropertyID(){return propertyID;}
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
    public void setpsf(double p){psf = p;}
    public String getFurnishStatus(){return furnishStatus;}
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
        public Builder facilities(ArrayList<String> f){facilities = f; return this;}    
        public Builder keyFeatures(ArrayList<String> k){keyFeatures = k; return this;}
        public Builder rental_price(int r){rental_price = r; return this;}
        public Builder address(String a){address = a; return this;}
        public Builder city(String c){city = c; return this;}
        public Builder state(String s){state = s; return this;}
        public Builder propertyType(String p){propertyType = p; return this;}
        public Builder photo(ArrayList<String> p){photo = p; return this;}

        public Property build(int id, String listerType, int listerID, String status){ // read in exisiting property
            return new Property(id, listerType, listerID, status, this);
        }

        public Property createProperty(String listerType, int listerID){ // create new property
            int id = Database.readUpdate("Property")+1;

            return new Property(id, listerType, listerID, "Active", this);
        }

        
    }    
}

// For sorting properties according to rental price
class SortByRentalPrice implements Comparator<Property>{
    @Override
    public int compare(Property p1 , Property p2){
        if(p1.getRental_price() > p2.getRental_price())
            return 1;
        else if(p1.getRental_price() == p2.getRental_price())
            return 0;
        else
            return -1;
    }
}
