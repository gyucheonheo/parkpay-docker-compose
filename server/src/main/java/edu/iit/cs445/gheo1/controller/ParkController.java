package edu.iit.cs445.gheo1.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.iit.cs445.gheo1.boundary.CommentBoundary;
import edu.iit.cs445.gheo1.boundary.ParkBoundary;
import edu.iit.cs445.gheo1.comment.Comment;
import edu.iit.cs445.gheo1.controller.service.Parse;
import edu.iit.cs445.gheo1.manager.CommentManager;
import edu.iit.cs445.gheo1.manager.ParkManager;
import edu.iit.cs445.gheo1.park.Park;
import edu.iit.cs445.gheo1.park.locationinfo.LocationInfo;
import edu.iit.cs445.gheo1.park.payment.PaymentInfo;
import edu.iit.cs445.gheo1.validation.Validation;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/parks")
public class ParkController {
    private ParkBoundary bi = new ParkManager();
    Parse parse = new Parse();
	  Validation v = new Validation();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPark(String json) {
    	Gson gson = new Gson();
    	JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

    	JsonObject linfoObj = jsonObject.get("location_info").getAsJsonObject();
    	JsonObject pinfoObj = jsonObject.get("payment_info").getAsJsonObject();
      JsonObject errorMessage = new JsonObject();
      JsonObject errors = new JsonObject();
      v.isLocationValid(linfoObj, errors);
      v.isPaymentValid(pinfoObj, errors);

      if(errors.entrySet().size() != 0){
        errorMessage.addProperty("type", "localhost:8080/parkpay");
        errorMessage.addProperty("title", "Your request didn't pass the validation");
        errorMessage.add("detail", errors);
        errorMessage.addProperty("status", 400);
        errorMessage.addProperty("instance", "/parks");
        return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(errorMessage)).build();
      }

		  LocationInfo lInfo = parse.ParseLocationInfo(linfoObj);
    	PaymentInfo pInfo = parse.ParsePaymentInfo(pinfoObj);

    	String pid = bi.createPark(lInfo, pInfo);
    	JsonObject res = new JsonObject();
    	res.addProperty("pid", pid);

    	String s = gson.toJson(res);

    	return Response.status(Response.Status.CREATED).entity(s).build();
    }

    @Path("{pid}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
	public Response updateParkByPid(@PathParam("pid") String pid, String json) {
    	Park p = bi.getParkByPid(pid);
    	if(p.isNil()) {
    		return Response.status(Response.Status.NOT_FOUND).entity("Not Found : " + pid).build();
    	}
    	Gson gson = new Gson();
    	JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

    	JsonObject linfoObj = jsonObject.get("location_info").getAsJsonObject();
    	JsonObject pinfoObj = jsonObject.get("payment_info").getAsJsonObject();
    	JsonObject errorMessage = new JsonObject();
      JsonObject errors = new JsonObject();
      LocationInfo lInfo = parse.ParseLocationInfo(linfoObj);
      PaymentInfo pInfo = parse.ParsePaymentInfo(pinfoObj);

      v.isLocationValid(linfoObj, errors);
      v.isPaymentValid(pinfoObj, errors);

      if(errors.entrySet().size() !=0){
        errorMessage.addProperty("type", "localhost:8080/parkpay");
        errorMessage.addProperty("title", "Your request didn't pass the validation");
        errorMessage.add("detail", errors);
        errorMessage.addProperty("status", 400);
        errorMessage.addProperty("instance", "/parks");
        return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(errorMessage)).build();
      }

    	bi.updateParkByPid(pid, lInfo, pInfo);

    	return Response.status(Response.Status.NO_CONTENT).build();
    }
    @Path("{pid}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteParkById(@PathParam("pid") String pid) {
    	Park p = bi.getParkByPid(pid);
    	if(p.isNil()) {
    		return Response.status(Response.Status.NOT_FOUND).entity("Not Found" + pid).build();
    	}
    	bi.deleteParkByPid(pid);

    	return Response.status(Response.Status.NO_CONTENT).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPark(@Context UriInfo uriInfo) {
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
    	String key = params.getFirst("key");
    	String s ="";
    	if(key == null) {
    		s = gson.toJson(bi.getAllParks());
    	} else {
    		s = gson.toJson(bi.getParkByKeyword(key));
    	}
    	return Response.status(Response.Status.OK).entity(s).build();
    }

    @Path("{pid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParkByPid(@PathParam("pid") String pid) {
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	Park p = bi.getParkByPid(pid);
		if(p.isNil()) {
			return Response.status(Response.Status.NOT_FOUND).entity("Not Found : " + pid).build();
		} else {
			String s = gson.toJson(bi.getParkByPidAsJsonObject(pid));
			return Response.status(Response.Status.OK).entity(s).build();
		}
    }

    @Path("{pid}/notes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommentsByPid(@PathParam("pid") String pid) {
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	Park p = bi.getParkByPid(pid);
    	if(p.isNil()) {
    		return Response.status(Response.Status.NOT_FOUND).entity("Not Found : " + pid).build();
    	} else {
    		CommentBoundary ci = new CommentManager();
    		String s = gson.toJson(ci.getCommentByPid(pid));
    		return Response.status(Response.Status.OK).entity(s).build();
    	}
    }
    @Path("{pid}/notes/{nid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommentByPidandNid(@PathParam("pid") String pid, @PathParam("nid") String nid) {
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	Park p = bi.getParkByPid(pid);
    	if(p.isNil()) {
    		return Response.status(Response.Status.NOT_FOUND).entity("pid Not found : " + pid).build();
    	}
    	CommentBoundary ci = new CommentManager();
    	Comment c = ci.getCommentByNid(nid);
    	if(c.isNil()) {
    		return Response.status(Response.Status.NOT_FOUND).entity("nid Not found : " + nid).build();
    	}
    	c = ci.getCommentByPidandNid(pid, nid);
    	if(c.isNil()) {
    		return Response.status(Response.Status.BAD_REQUEST).entity("nid is not associated with the Park;"+pid).build();
    	}
    	String s = gson.toJson(ci.getCommentByPidandNid(pid, nid));

    	return Response.status(Response.Status.OK).entity(s).build();


    }
    @Path("{pid}/notes")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCommentByPid(@PathParam("pid") String pid, String json) {
    	Gson gson = new Gson();
    	Park p = bi.getParkByPid(pid);
    	if(p.isNil()) {
    		return Response.status(Response.Status.NOT_FOUND).entity("Not Found : " + pid).build();
    	} else {
    		JsonObject cinfoObj = new JsonParser().parse(json).getAsJsonObject();
    		String error = v.isCommentValid(cinfoObj, pid);
    		if(!error.equals("ok")) {
    			JsonObject errorMessage = new JsonObject();
    	    	errorMessage.addProperty("type", "localhost:8080/ParkService/");
    	    	errorMessage.addProperty("title", "Your request didn't pass the validation");
    	    	errorMessage.addProperty("detail", error);
    	    	errorMessage.addProperty("status", 400);
    	    	errorMessage.addProperty("instance", "/parks/"+pid+"/notes");
    	    	return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(errorMessage)).build();
    		}
    		CommentBoundary ci = new CommentManager();
    		Comment comment = ci.createComment(cinfoObj.get("vid").getAsString(), pid, cinfoObj.get("title").getAsString(), cinfoObj.get("text").getAsString());
    		String nid = comment.getNid();
		JsonObject res = new JsonObject();
		res.addProperty("nid", nid);
    		String s = gson.toJson(res);
    		return Response.status(Response.Status.CREATED).entity(s).build();
    	}
    }

    @PostConstruct
    public void postConstruct() {
        // This method gets executed exactly once, after the servlet container has been created
        // A good place to place code that needs to be executed once, at startup
    }
}
