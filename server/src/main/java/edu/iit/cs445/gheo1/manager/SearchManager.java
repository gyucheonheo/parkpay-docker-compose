package edu.iit.cs445.gheo1.manager;

import java.text.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.boundary.SearchBoundary;
import edu.iit.cs445.gheo1.boundary.AdmissionBoundary;
import edu.iit.cs445.gheo1.boundary.CommentBoundary;
import edu.iit.cs445.gheo1.boundary.ParkBoundary;
import edu.iit.cs445.gheo1.boundary.ReportBoundary;

public class SearchManager implements SearchBoundary {
	
    public JsonObject searchByKeyword(String keyword ) {
		ParkBoundary pi = new ParkManager();
		CommentBoundary ci = new CommentManager();
		AdmissionBoundary oi = new OrderManager();

		JsonArray parkResult = pi.getParkByKeyword(keyword);
		JsonArray commentResult = ci.getCommentByKeyword(keyword);
		JsonArray orderResult = oi.getOrderByKeyword(keyword);

		JsonObject result = new JsonObject();

		result.add("parks", parkResult);
		result.add("notes", commentResult);
		result.add("orders", orderResult);
		return result;
    }

    public JsonObject searchByDate(String startDate, String endDate) throws ParseException{
	ReportBoundary ri = new ReportManager();
	ri.createAdmissionReport();
	ri.createRevenueReport();

	JsonObject admissionReport = ri.addAdditionalInformationForAdmissionReport(startDate, endDate);
	JsonObject revenueReport = ri.addAdditionalInformationForRevenueReport(startDate, endDate);

	JsonObject result = new JsonObject();
	result.add("admission_report", admissionReport);
	result.add("revenue_report", revenueReport);

	return result;
    }
}
