package edu.iit.cs445.gheo1.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.admission.Admission;
import edu.iit.cs445.gheo1.boundary.AdmissionBoundary;
import edu.iit.cs445.gheo1.boundary.CommentBoundary;
import edu.iit.cs445.gheo1.boundary.VisitorBoundary;
import edu.iit.cs445.gheo1.comment.Comment;
import edu.iit.cs445.gheo1.common.ChangeDateFormat;
import edu.iit.cs445.gheo1.visitor.NullVisitor;
import edu.iit.cs445.gheo1.visitor.Visitor;

public class VisitorManager implements VisitorBoundary {
	private static List<Visitor> Visitors = new ArrayList<Visitor>();

	public List<Visitor> getVisitors(){
		return Visitors;
	}
	public void createVisitor(String vid, String name, String email) {
		if(findVisitorByEmail(email).isNil()) {
			Visitors.add(new Visitor(vid, name, email));
		}
	}

	public List<Visitor> getAllVisitor(){
		return Visitors;
	}
	public List<Visitor> getVisitorByKeyword(String keyword){
		return findVisitorByKeyword(keyword);
	}
	public Visitor getVisitorByVid(String vid) {
		return findVisitorByVid(vid);
	}
	
	public Visitor findVisitorByEmail(String email) {
		Iterator<Visitor> vi = Visitors.listIterator();
		while(vi.hasNext()) {
			Visitor v = vi.next();
			if(v.getEmail().equals(email)) return v;
		}
		return new NullVisitor();
	}

	public String getVisitorDetailByVid(String vid) {
		Gson gson = new Gson();
		AdmissionBoundary ai = new OrderManager();
		CommentBoundary ci = new CommentManager();

		Visitor v = findVisitorByVid(vid);
		List<Admission> orderList = ai.getOrderByVid(vid);
		List<Comment> commentList = ci.getCommentByVid(vid);

		JsonObject returnObject = new JsonObject();

		JsonObject visitorObject = new JsonObject();
		JsonArray orderSets = new JsonArray();
		JsonArray noteSets = new JsonArray();

		visitorObject.addProperty("name", v.getName());
		visitorObject.addProperty("email", v.getEmail());

		for(Admission a : orderList) {
			JsonObject orderSet = new JsonObject();
			orderSet.addProperty("oid", a.getOid());
			orderSet.addProperty("pid", a.getPid());
			orderSet.addProperty("date", ChangeDateFormat.toyyyyMMdd(a.getDate()) );

			orderSets.add(orderSet);
		}
		for(Comment c : commentList) {
			JsonObject noteSet = new JsonObject();
			noteSet.addProperty("nid", c.getNid());
			noteSet.addProperty("pid", c.getPid());
			noteSet.addProperty("date", c.getDate());
			noteSet.addProperty("title", c.getTitle());

			noteSets.add(noteSet);
		}

		returnObject.addProperty("vid", vid);
		returnObject.add("visitor", visitorObject);
		returnObject.add("orders", orderSets);
		returnObject.add("notes", noteSets);

		return gson.toJson(returnObject);
	}

	private List<Visitor> findVisitorByKeyword(String keyword){
	    keyword = keyword.toLowerCase();
		Iterator<Visitor> vi = Visitors.listIterator();
		List<Visitor> results = new ArrayList<Visitor>();
		while(vi.hasNext()) {
			Visitor v = vi.next();
			if( v.getEmail().toLowerCase().contains(keyword) || v.getName().toLowerCase().contains(keyword))
				results.add(v);
		}
		return results;
	}
	private Visitor findVisitorByVid(String vid) {
		Iterator<Visitor> vi = Visitors.listIterator();
		while(vi.hasNext()) {
			Visitor v = vi.next();
			if(v.getVid().equals(vid)) return v;
		}
		return new NullVisitor();
	}



}
