package edu.iit.cs445.gheo1.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.iit.cs445.gheo1.boundary.CommentBoundary;
import edu.iit.cs445.gheo1.comment.Comment;
import edu.iit.cs445.gheo1.manager.CommentManager;
import edu.iit.cs445.gheo1.validation.Validation;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/notes")
public class CommentController {

	private CommentBoundary ci = new CommentManager();
	Validation v = new Validation();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllComments(@Context UriInfo uriInfo) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
		String key = params.getFirst("key");
		String s = "";
		if(key == null) {
			s = gson.toJson(ci.getAllComments());
		} else {
			s = gson.toJson(ci.getCommentByKeyword(key));
		}
		return Response.status(Response.Status.OK).entity(s).build();
	}
	
	@Path("{nid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommentByNid(@PathParam("nid") String nid) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Comment c = ci.getCommentByNid(nid);
		if(c.isNil()) {
			return Response.status(Response.Status.NOT_FOUND).entity("nid Not found :" + nid).build();
		}
		JsonObject noteByNid = new JsonObject();
		noteByNid.addProperty("vid", c.getVid());
		noteByNid.addProperty("title", c.getTitle());
		noteByNid.addProperty("text", c.getText());
		String s = gson.toJson(noteByNid);
		return Response.status(Response.Status.OK).entity(s).build();
	}
	
	@Path("{nid}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCommentByNid(@PathParam("nid") String nid, String json) {
		Gson gson = new Gson();
		Comment c = ci.getCommentByNid(nid);
		if(c.isNil()) {
			return Response.status(Response.Status.NOT_FOUND).entity("nid Not found : " + nid).build();
		}
		JsonObject cinfoObj = new JsonParser().parse(json).getAsJsonObject();
		String error = v.isCommentValid(cinfoObj, "");
		if(!error.equals("ok")) {
			JsonObject errorMessage = new JsonObject();
	    	errorMessage.addProperty("type", "localhost:8080/ParkService/");
	    	errorMessage.addProperty("title", "Your request didn't pass the validation");
	    	errorMessage.addProperty("detail", error);
	    	errorMessage.addProperty("status", 400);
	    	errorMessage.addProperty("instance", "/notes/"+nid);
	    	return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(errorMessage)).build();
		}
		String vid = cinfoObj.get("vid").getAsString();
		String title = cinfoObj.get("title").getAsString();
		String text = cinfoObj.get("text").getAsString();
		
		ci.updateCommentByNid(nid, vid, title, text);
		
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
}
