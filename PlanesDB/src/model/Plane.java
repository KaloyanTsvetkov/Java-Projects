package model;

import javax.swing.JOptionPane;

//Start Class Plane
//equal to table planes from DB
public class Plane {
    //Plane Class Fields.
    private String planeID;
    private String model;
    private String yearOfProduce;
    private long traveledKM;
    private int places;

//Plane Class Constructor.
    public Plane(String planeID, String model, String yearOfProduce, long traveledKM, int places) {
        setPlaneID(planeID);
        setModel(model);
        setYearOfProduce(yearOfProduce);
        setTraveledKM(traveledKM);
        setPlaces(places);
    }// end five-argument Plane constructor 

//Getters and Setters for Plane fields.
//Setters - If == null, show Error message.
    //PlaneID methods
    public String getPlaneID() {
        return planeID;
    }// end method getPlaneID

    public void setPlaneID(String planeID) {
        if (!planeID.equals("")) {
            this.planeID = planeID;
        } else {
            JOptionPane.showMessageDialog(null, "You must enter plane ID!",
                    "Plane ID", JOptionPane.ERROR_MESSAGE);
        }
    }// end method setPlaneID

    //Model methods
    public String getModel() {
        return model;
    }// end method getModel

    public void setModel(String model) {
        if (!model.equals("")) {
            this.model = model;
        } else {
            JOptionPane.showMessageDialog(null, "You must enter plane MODEL!",
                    "Plane Model", JOptionPane.ERROR_MESSAGE);
        }
    }// end method setModel

    //YearOfProduce methods
    public String getYearOfProduce() {
        return yearOfProduce;
    }// end method getYearOfProduce

    public void setYearOfProduce(String yearOfProduce) {
        if (!yearOfProduce.equals("")) {
            this.yearOfProduce = yearOfProduce;
        } else {
            JOptionPane.showMessageDialog(null, "You must enter plane Year Of Produce!",
                    "Year Of Produce", JOptionPane.ERROR_MESSAGE);
        }
    }// end method setYearOfProduce

    //TraveledKM methods
    public long getTraveledKM() {
        return traveledKM;
    }// end method getTraveledKM

    public void setTraveledKM(long traveledKM) {
        if (traveledKM>=0) {
            this.traveledKM = traveledKM;
        } else {
            JOptionPane.showMessageDialog(null, "You must enter Traveled Kilometers!",
                    "Traveled Kilometers", JOptionPane.ERROR_MESSAGE);
        }
    }// end method setTraveledKM

    //Places methods
    public int getPlaces() {
        return places;
    }// end method getPlaces

    public void setPlaces(int places) {
        if(places>=0){
            this.places = places;
        } else {
            JOptionPane.showMessageDialog(null, "You must enter a Plane Places!",
                    "Plane Places", JOptionPane.ERROR_MESSAGE);
        }
    }// end method setPlaces

    //Overridet toString() method.
    @Override
    public String toString() {
        String result = "";
        result = result + getPlaneID() + "   " + getModel() + "   " + getYearOfProduce();
        return result;
    }// end method toString()
}// end class Plane
