package edu.iit.cs445.gheo1.boundary;

import java.text.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface ReportBoundary {
	boolean isRidValid(String rid);
	boolean isRidForAdmission(String rid);
	JsonArray getReports();
	JsonObject createAdmissionReport();
	JsonObject createRevenueReport();
	JsonObject addAdditionalInformationForAdmissionReport(String startDate, String endDate) throws ParseException;
	JsonObject addAdditionalInformationForRevenueReport(String startDate, String endDate) throws ParseException;	
}
