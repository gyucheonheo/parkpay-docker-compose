package edu.iit.cs445.gheo1.manager;

import java.text.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.boundary.AdmissionBoundary;
import edu.iit.cs445.gheo1.boundary.ParkBoundary;
import edu.iit.cs445.gheo1.boundary.ReportBoundary;
import edu.iit.cs445.gheo1.common.UniqueIdGenerator;

public class ReportManager implements ReportBoundary{
	private static JsonObject admissionReport = new JsonObject();
	private static JsonObject revenueReport = new JsonObject();

	public boolean isRidValid(String rid) {
		if(ReportManager.admissionReport.get("rid").getAsString().equals(rid)) {
			return true;
		}
		if(ReportManager.revenueReport.get("rid").getAsString().equals(rid)) {
			return true;
		}

		return false;
	}

	public boolean isRidForAdmission(String rid) {
		if(ReportManager.admissionReport.get("rid").getAsString().equals(rid)) {
			return true;
		} else {
			return false;
		}
	}

	public JsonArray getReports() {
		JsonArray reports = new JsonArray();

		reports.add(ReportManager.admissionReport);
		reports.add(ReportManager.revenueReport);

		return reports;
	}

	public JsonObject createAdmissionReport() {
		JsonObject admissionReport = new JsonObject();

		admissionReport.addProperty("rid", UniqueIdGenerator.getUniqueID());
		admissionReport.addProperty("name", "Admissions report");

		ReportManager.admissionReport = admissionReport;
		return ReportManager.admissionReport;
	}

	public JsonObject createRevenueReport() {
		JsonObject revenueReport = new JsonObject();

		revenueReport.addProperty("rid", UniqueIdGenerator.getUniqueID());
		revenueReport.addProperty("name", "Revenue report");


		ReportManager.revenueReport = revenueReport;
		return ReportManager.revenueReport;
	}

	public JsonObject addAdditionalInformationForAdmissionReport(String startDate, String endDate) throws ParseException {
		AdmissionBoundary oi = new OrderManager();
		ParkBoundary pi = new ParkManager();
		JsonArray parks = pi.getParksForAdmissionReport();
		JsonArray admissionsBetween = oi.getAdmissionsBetween(startDate, endDate);
		if(startDate == null){
			startDate = "";
		}
		if(endDate == null){
			endDate = "";
		}
		admissionReport.addProperty("start_date",dateFormatWithDash(startDate));
		admissionReport.addProperty("end_date", dateFormatWithDash(endDate));
		for(JsonElement p : parks) {
			int count = 0;
			for(JsonElement order : admissionsBetween ) {
				if( order.getAsJsonObject().get("pid").getAsInt() == p.getAsJsonObject().get("pid").getAsInt()) {
					count++;
				}
			}
			p.getAsJsonObject().addProperty("admissions", count);
		}
		ReportManager.admissionReport.addProperty("total_admissions",admissionsBetween.size());
		ReportManager.admissionReport.add("detail_by_park", parks);

		return ReportManager.admissionReport;
	}

	public JsonObject addAdditionalInformationForRevenueReport(String startDate, String endDate) throws ParseException{
		AdmissionBoundary oi = new OrderManager();
		ParkBoundary pi = new ParkManager();
		JsonArray parks = pi.getParksForAdmissionReport();
		JsonArray admissionsBetween = oi.getAdmissionsBetween(startDate, endDate);
		double totalRevenue = 0.0;
		if(startDate == null){
			startDate = "";
		}
		if(endDate == null){
			endDate = "";
		}
		revenueReport.addProperty("start_date", dateFormatWithDash(startDate));
		revenueReport.addProperty("end_date", dateFormatWithDash(endDate));
		for(JsonElement p : parks) {
			int count = 0;
			double parkRevenue = 0.0;
			totalRevenue = 0.0;
			for(JsonElement order : admissionsBetween) {
				if( order.getAsJsonObject().get("pid").getAsInt() == p.getAsJsonObject().get("pid").getAsInt()) {
					count++;
					parkRevenue += order.getAsJsonObject().get("amount").getAsDouble();
				}
			}
			p.getAsJsonObject().addProperty("orders", count);
			p.getAsJsonObject().addProperty("revenue", parkRevenue);
		}

		for( JsonElement p : parks){
			totalRevenue += p.getAsJsonObject().get("revenue").getAsDouble();
		}

		ReportManager.revenueReport.addProperty("total_orders", admissionsBetween.size());
		ReportManager.revenueReport.addProperty("total_revenue", totalRevenue);
		ReportManager.revenueReport.add("detail_by_park", parks);

		return ReportManager.revenueReport;
	}

	private String dateFormatWithDash(String date){
		if(date == ""){
			return "";
		}
		return date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
	}

}
