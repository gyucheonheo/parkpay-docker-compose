package edu.iit.cs445.gheo1.controller;

import com.google.gson.Gson;

import edu.iit.cs445.gheo1.boundary.VisitorBoundary;
import edu.iit.cs445.gheo1.manager.VisitorManager;
import edu.iit.cs445.gheo1.visitor.Visitor;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/visitors")
public class VisitorController {
	
	private VisitorBoundary vi = new VisitorManager();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllVisitor(@Context UriInfo uriInfo) {
		Gson gson = new Gson();
		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
		String key = params.getFirst("key");
		String s ="";
		if(key == null) {
			s = gson.toJson(vi.getAllVisitor());
		} else {
			s = gson.toJson(vi.getVisitorByKeyword(key));
		}
		return Response.ok().entity(s).build();
	}
	
	@Path("{vid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVisitorByVid(@PathParam("vid") String vid) {
		Visitor v = vi.getVisitorByVid(vid);
		if(v.isNil()) {
			return Response.status(Response.Status.NOT_FOUND).entity("Not found : " + vid).build();
		}
		String s = vi.getVisitorDetailByVid(vid);
		return Response.ok().entity(s).build();
	}
	
}
