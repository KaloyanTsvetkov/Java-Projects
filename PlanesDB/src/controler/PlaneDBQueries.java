package controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Plane;

//Start Class PlaneDBQueries
//this class prepare all queries and have methods to exequte them
public class PlaneDBQueries {
    //PlaneDBQueries Class Fields.

    private Connection connection = null; // Connection for MySQL.
    private HashMap<String, Plane> planesMap;
    // Queries for planes Table.
    private PreparedStatement insertIntoPlanes = null;
    private PreparedStatement deleteFromPlanesByID = null;
    private PreparedStatement updateTraveledKmByID = null;
    private PreparedStatement updateSeatsByID = null;
    private PreparedStatement selectInfoByID = null;
    private PreparedStatement selectAllPlanes = null;

    //start PlaneDBQueries constructor
    public PlaneDBQueries() throws ClassNotFoundException {
        planesMap = new HashMap<String, Plane>();   //inicialize the HashMap
        try {
            Class.forName("com.mysql.jdbc.Driver"); //MySQL Driver
            //Connecting to Planes DB.
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/planedb", "root", "");

            //Preparing the queries.
            //Add a new Plane.
            insertIntoPlanes = connection.prepareStatement("INSERT INTO planes "
                    + "(Plane_ID, Model, Produce_Year, Traveled_KM, Places) "
                    + "VALUES (?, ?, ?, ?, ?)");

            //Delete a Plane By Plane_ID.
            deleteFromPlanesByID = connection.prepareStatement("DELETE FROM planes WHERE Plane_ID = ?");

            //Update KM By Plane_ID.
            updateTraveledKmByID = connection.prepareStatement("UPDATE planes "
                    + "SET Traveled_KM = ? "
                    + "WHERE Plane_ID = ?");

            //Update Seats By Plane_ID.
            updateSeatsByID = connection.prepareStatement("UPDATE planes SET Places = ? "
                    + "WHERE Plane_ID = ?");

            //Show All Info for Plane By Plane_ID.
            selectInfoByID = connection.prepareStatement("SELECT * FROM planes WHERE Plane_ID = ?");

            //Show All Planes from DB.
            selectAllPlanes = connection.prepareStatement("select * from planes");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    } // end PlaneDBQueries constructor

    //Method For Query "selectAllPlanes". Returning HashMap
    public HashMap<String, Plane> getAllPlanes() throws SQLException {
        ResultSet resultSet = null;
        Plane plane;
        //exequte selectAllPlanes query
        resultSet = selectAllPlanes.executeQuery(); //executing Query "selectAllPlanes"
        while (resultSet.next()) {
            //Create a new Plane Object
            plane = new Plane(
                    resultSet.getString("Plane_ID"),
                    resultSet.getString("Model"),
                    resultSet.getString("Produce_Year"),
                    resultSet.getLong("Traveled_KM"),
                    resultSet.getInt("Places")); //end constructor for Plane Class 
            planesMap.put(plane.getPlaneID(), plane); //Add Plane to HashMap
        }// end while
        return planesMap; //return statement (HashMap<String, Plane>)
    }// end method getAllPlanes

    //Method For Query "selectInfoByID". Returning List<Plane>
    public List<Plane> getPlaneByID(String planeID) throws SQLException {
        ResultSet resultSet = null;
        Plane plane;
        List<Plane> planesList = null;
        // set parameters, then executing "insertIntoPlanes"
        selectInfoByID.setString(1, planeID);
        //exequte selectInfoByID query
        resultSet = selectInfoByID.executeQuery();
        planesList = new ArrayList<Plane>();
        //start while for reading from ResultSet
        while (resultSet.next()) {
            //Create a new Plane Object
            plane = new Plane(
                    resultSet.getString("Plane_ID"),
                    resultSet.getString("Model"),
                    resultSet.getString("Produce_Year"),
                    resultSet.getLong("Traveled_KM"),
                    resultSet.getInt("Places"));     //end constructor for Plane Class 
            planesList.add(plane);     //Adding To List
        }// end while

        return planesList;  //return statement 
    }// end method getPlaneByID

    //Method To Add a New Plane Query.
    public int addPlane(String planeID, String model, String yearOfProduce, long traveledKM, int places) throws SQLException {
        int result = 0;
        // set parameters, then executing "insertIntoPlanes"
        insertIntoPlanes.setString(1, planeID);
        insertIntoPlanes.setString(2, model);
        insertIntoPlanes.setString(3, yearOfProduce);
        insertIntoPlanes.setLong(4, traveledKM);
        insertIntoPlanes.setInt(5, places);

        //exequte insertIntoPlanes query
        result = insertIntoPlanes.executeUpdate();

        return result;  //return statement 
    }// end method addPlane

    //Method To Delete a Plane Query "deleteFromPlanesByID"
    public int removePlaneByID(String planeID) throws SQLException {
        int result = 0;
        // set parameter, then executing "deleteFromPlanes"
        deleteFromPlanesByID.setString(1, planeID);
        result = deleteFromPlanesByID.executeUpdate();
        // end catch
        return result;   //return statement 
    }// end method removePlaneByID

    //Method To Update a Plane Info. With Transaction.
    public boolean updateInfoByID(String planeID, long traveledKM, int places) throws SQLException {
        int result = 0;
        boolean flag = true;
        try {
            connection.setAutoCommit(false); //Start transaction
            // set parameters, then executing "updateSeatsByID"
            updateSeatsByID.setInt(1, places);
            updateSeatsByID.setString(2, planeID);
            //exequte updateSeatsByID query
            result = updateSeatsByID.executeUpdate();
            if (result != 1) {
                connection.rollback(); //If can't Update Info RollBack
                flag = false;
            }
            // set parameters, then executing "updateSeatsByID"
            updateTraveledKmByID.setLong(1, traveledKM);
            updateTraveledKmByID.setString(2, planeID);
            //exequte updateTraveledKmByID query
            result = updateTraveledKmByID.executeUpdate();
            if (result != 1) {
                connection.rollback(); //If can't Update Info RollBack
                flag = false;
            }
            connection.commit(); //End transaction
        } finally {     //Finnaly Block
            try {
                connection.setAutoCommit(true); //Set back AutoCommit - true.
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return flag;     //return statement 
    }//end method updateInfoByID

    //Method for closing the DB connection.
    public void close() {
        try {
            connection.close(); //Closing Connection
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }// end method close
}// end class PlaneDBQueries
