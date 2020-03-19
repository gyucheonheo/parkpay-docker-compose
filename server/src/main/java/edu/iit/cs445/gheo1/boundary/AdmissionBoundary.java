package edu.iit.cs445.gheo1.boundary;

import java.text.ParseException;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.admission.Admission;
import edu.iit.cs445.gheo1.common.Vehicle;
import edu.iit.cs445.gheo1.park.Park;
import edu.iit.cs445.gheo1.visitor.Visitor;

public interface AdmissionBoundary {
	List<Admission> getAdmissions();
	Admission createOrder(Park p, String vid, Vehicle vehicle, Visitor visitor);
    boolean isVidValidForPid(String pid, String vid);
	JsonArray getAllOrder();
	JsonObject getOrderByOid(String oid);
	JsonArray getOrderByKeyword(String keyword);
	JsonArray getAdmissionsBetween(String startDate, String endDate) throws ParseException;
	List<Admission> getOrderByVid(String vid);
}
