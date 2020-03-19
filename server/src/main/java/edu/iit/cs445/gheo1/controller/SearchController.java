package edu.iit.cs445.gheo1.controller;

import java.text.ParseException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.boundary.SearchBoundary;
import edu.iit.cs445.gheo1.manager.SearchManager;
import edu.iit.cs445.gheo1.validation.Validation;

@Path("/search")
public class SearchController {
    private SearchBoundary si = new SearchManager();
    private Validation validator = new Validation();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("key") String keyword, @QueryParam("start_date") String startDate, @QueryParam("end_date") String endDate) throws ParseException {
	Gson gson = new Gson();
	if(endDate != null){
	    if( !validator.isDateValid(endDate).equals("ok")){
		return Response.status(Response.Status.BAD_REQUEST).entity("BAD REQUEST").build();
	    }
	}
	if(startDate != null){
	    if( !validator.isDateValid(startDate).equals("ok")){
		return Response.status(Response.Status.BAD_REQUEST).entity("BAD REQUEST").build();
	    }
	}

	JsonObject resultByKeyword = si.searchByKeyword(keyword);
	JsonObject reportByDate = si.searchByDate(startDate, endDate);

	JsonArray result = new JsonArray();
	result.add(resultByKeyword);
	result.add(reportByDate);
	
	String s = gson.toJson(result);
	return Response.status(Response.Status.OK).entity(s).build();
    }
}
