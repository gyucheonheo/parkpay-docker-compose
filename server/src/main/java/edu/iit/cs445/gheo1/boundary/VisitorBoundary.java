package edu.iit.cs445.gheo1.boundary;

import java.util.List;

import edu.iit.cs445.gheo1.visitor.Visitor;

public interface VisitorBoundary {
	void createVisitor(String vid, String name, String email);
	List<Visitor> getAllVisitor();
	List<Visitor> getVisitorByKeyword(String keyword);
	Visitor getVisitorByVid(String vid);
	String getVisitorDetailByVid(String vid);
 	Visitor findVisitorByEmail(String email);
}
