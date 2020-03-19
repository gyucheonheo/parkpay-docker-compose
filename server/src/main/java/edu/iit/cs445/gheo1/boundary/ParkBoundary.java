package edu.iit.cs445.gheo1.boundary;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.park.Park;
import edu.iit.cs445.gheo1.park.locationinfo.LocationInfo;
import edu.iit.cs445.gheo1.park.payment.PaymentInfo;

public interface ParkBoundary {
    JsonArray getAllParks();
    String createPark(LocationInfo locationInfo, PaymentInfo paymentInfo);
    Park getParkByPid(String pid);
    JsonObject getParkByPidAsJsonObject(String pid);
    void updateParkByPid(String pid, LocationInfo locationinfo, PaymentInfo paymentInfo);
    void deleteParkByPid(String pid);
    JsonArray getParkByKeyword(String keyword);
    JsonArray getParksForAdmissionReport();
    
    List<Park> getParks();
}
