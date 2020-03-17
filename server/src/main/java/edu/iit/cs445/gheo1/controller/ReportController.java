package edu.iit.cs445.gheo1.controller;

import java.text.ParseException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.boundary.ReportBoundary;
import edu.iit.cs445.gheo1.manager.ReportManager;
import edu.iit.cs445.gheo1.validation.Validation;

@Path("/reports")
public class ReportController {
	private ReportBoundary ri = new ReportManager();
	Validation validator = new Validation();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReport() throws ParseException {
		Gson gson = new Gson();
		ri.createAdmissionReport();
		ri.createRevenueReport();
		JsonArray reports = ri.getReports();

		String s = gson.toJson(reports);
		return Response.status(Response.Status.OK).entity(s).build();
	}

	@Path("{rid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReportByRid(@PathParam("rid") String rid, @QueryParam("start_date") String startDate, @QueryParam("end_date") String endDate) throws ParseException {
		Gson gson = new Gson();
		String s ="";
		JsonObject errorMessage = new JsonObject();
		String error;

		if(!ri.isRidValid(rid)) {
			return Response.status(Response.Status.NOT_FOUND).entity("Not found " + rid).build();
		}
		if(endDate != null){
			error = validator.isDateValid(endDate);
			if( !error.equals("ok")){

				errorMessage.addProperty("type", "localhost:8080/ParkService");
				errorMessage.addProperty("title", "Your request didn't pass the validation");
				errorMessage.addProperty("detail", error);
				errorMessage.addProperty("status", 400);
				errorMessage.addProperty("instance", "/reports");

				return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(errorMessage)).build();
			}
		}
		if(startDate != null){
			error = validator.isDateValid(startDate);
			if( !error.equals("ok")){

				errorMessage.addProperty("type", "localhost:8080/ParkService");
				errorMessage.addProperty("title", "Your request didn't pass the validation");
				errorMessage.addProperty("detail", error);
				errorMessage.addProperty("status", 400);
				errorMessage.addProperty("instance", "/reports");

				return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(errorMessage)).build();
			}
		}

		if( ri.isRidForAdmission(rid))
		{
			s = gson.toJson(ri.addAdditionalInformationForAdmissionReport(startDate, endDate));
		}
		if( !ri.isRidForAdmission(rid)) {
			s = gson.toJson(ri.addAdditionalInformationForRevenueReport(startDate, endDate));
		}

		return Response.status(Response.Status.OK).entity(s).build();
	}

}
