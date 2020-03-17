package edu.iit.cs445.gheo1.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.iit.cs445.gheo1.admission.Admission;
import edu.iit.cs445.gheo1.boundary.AdmissionBoundary;
import edu.iit.cs445.gheo1.boundary.ParkBoundary;
import edu.iit.cs445.gheo1.boundary.VisitorBoundary;
import edu.iit.cs445.gheo1.common.UniqueIdGenerator;
import edu.iit.cs445.gheo1.common.ChangeDateFormat;
import edu.iit.cs445.gheo1.common.Vehicle;
import edu.iit.cs445.gheo1.controller.service.Parse;
import edu.iit.cs445.gheo1.manager.OrderManager;
import edu.iit.cs445.gheo1.manager.ParkManager;
import edu.iit.cs445.gheo1.manager.VisitorManager;
import edu.iit.cs445.gheo1.park.Park;
import edu.iit.cs445.gheo1.validation.Validation;
import edu.iit.cs445.gheo1.visitor.Visitor;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/orders")
public class OrderController {

	private AdmissionBoundary ai = new OrderManager();
	Parse parse = new Parse();
	Validation v = new Validation();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrder(@Context UriInfo uriInfo) {
		Gson gson = new Gson();
		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
		String key = params.getFirst("key");
		String s ="";
		if(key == null) {
			s = gson.toJson(ai.getAllOrder());
		} else {
			s = gson.toJson(ai.getOrderByKeyword(key));
		}
		return Response.ok().entity(s).build();
	}
	@Path("{oid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrderByOid(@PathParam("oid") String oid) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject a = ai.getOrderByOid(oid);
		if(a.isJsonNull()) {
			return Response.status(Response.Status.NOT_FOUND).entity("Not found : " + oid).build();
		}
		String s = gson.toJson(a);
		return Response.status(Response.Status.OK).entity(s).build();
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createOrder(String json) {
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

		JsonObject vehicleObj = jsonObject.get("vehicle").getAsJsonObject();
		JsonObject visitorObj = jsonObject.get("visitor").getAsJsonObject();
		String pid = jsonObject.get("pid").getAsString();
		ParkBoundary bi = new ParkManager();
		Park p = bi.getParkByPid(pid);
		if( p.isNil() ) {
			return Response.status(Response.Status.NOT_FOUND).entity("pid not found : " + pid).build();
		}
		JsonObject errorMessage = new JsonObject();

		String error = v.isVehicleValid(vehicleObj);
		if( !error.equals("ok")) {
	    	errorMessage.addProperty("type", "localhost:8080/ParkService");
	    	errorMessage.addProperty("title", "Your request didn't pass the validation");
	    	errorMessage.addProperty("detail", error);
	    	errorMessage.addProperty("status", 400);
	    	errorMessage.addProperty("instance", "/orders");

	    	return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(errorMessage)).build();
		}
		error = v.isVisitorValid(visitorObj);
		if( !error.equals("ok")) {
	    	errorMessage.addProperty("type", "localhost:8080/ParkService");
	    	errorMessage.addProperty("title", "Your request didn't pass the validation");
	    	errorMessage.addProperty("detail", error);
	    	errorMessage.addProperty("status", 400);
	    	errorMessage.addProperty("instance", "/orders");

	    	return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(errorMessage)).build();
		}

		Vehicle vehicle = parse.ParseVehicle(vehicleObj);
		Visitor visitor = parse.ParseVisitor(visitorObj);
		VisitorBoundary vi = new VisitorManager();
		String vid;
		if( vi.findVisitorByEmail(visitor.getEmail()).isNil()){
			vid = UniqueIdGenerator.getUniqueID();
		} else {
			vid = vi.findVisitorByEmail(visitor.getEmail()).getVid();
		}
		Admission admission = ai.createOrder(p, vid, vehicle, visitor);
		admission.getVehicle().getType();

		String oid = admission.getOid();
		JsonObject res = new JsonObject();
		res.addProperty("oid", oid);
		String s = gson.toJson(res);
		return Response.status(Response.Status.CREATED).entity(s).build();
	}

}
