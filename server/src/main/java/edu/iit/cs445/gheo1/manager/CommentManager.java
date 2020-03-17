package edu.iit.cs445.gheo1.manager;

import edu.iit.cs445.gheo1.boundary.CommentBoundary;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.comment.Comment;
import edu.iit.cs445.gheo1.comment.NullComment;

public class CommentManager extends ParkManager implements CommentBoundary{
	static List<Comment> Comments = new ArrayList<Comment>();

	public List<Comment> getComments(){
		return Comments;
	}
	public JsonArray getAllComments(){
		return getCommentOrientedByPid(Comments);
	}
	public Comment getCommentByNid(String nid) {
		return findCommentByNid(nid);
	}
	public JsonArray getCommentByPid(String pid){
	    JsonArray commentSets = getSimpleComment(findCommentByPid(pid), pid);
		return commentSets;
	}

	public Comment getCommentByPidandNid(String pid, String nid) {
		return findCommentByPidandNid(pid, nid);
	}
	public Comment createComment(String vid, String pid, String title, String text) {
		Comment comment = new Comment(vid, pid, title, text);
		Comments.add(comment);
		return comment;
	}
	public void updateCommentByNid(String nid, String vid, String title, String text) {
		Comment c = findCommentByNid(nid);
		c.setVid(vid);
		c.setTitle(title);
		c.setText(text);
	}

	public JsonArray getCommentByKeyword(String keyword) {
		List<Comment> comments = findCommentByKeyword(keyword);
		return getCommentOrientedByPid(comments);
	}

	public List<Comment> getCommentByVid(String vid){
		return findCommentByVid(vid);
	}

	private JsonArray getCommentOrientedByPid(List<Comment> comments) {
		JsonArray commentSetsForEachPid = new JsonArray();
		for(Comment c : comments) {
			JsonObject commentObj = new JsonObject();

			commentObj.addProperty("pid", c.getPid());
			List<Comment> noteSetsByPid = findCommentByPid(c.getPid());
			JsonArray noteSetsJsonArray = getSimpleComment(noteSetsByPid, c.getPid());
			commentObj.add("notes", noteSetsJsonArray);

			if(!commentSetsForEachPid.contains(commentObj)) {
				commentSetsForEachPid.add(commentObj);
			}
		}

		return commentSetsForEachPid;
	}

    private JsonArray getSimpleComment(List<Comment> comments, String pid) {
	JsonObject result = new JsonObject();
	JsonArray notes = new JsonArray();
	JsonArray commentSets = new JsonArray();
		for(Comment c : comments) {
			JsonObject comment = new JsonObject();

			comment.addProperty("nid", c.getNid());
			comment.addProperty("date", c.getDate());
			comment.addProperty("title", c.getTitle());

			commentSets.add(comment);
		}
		result.add("notes", commentSets);
		result.addProperty("pid", pid);
		notes.add(result);
		return notes;
	}
	private Comment findCommentByPidandNid(String pid, String nid) {
		Iterator<Comment> ci = findCommentByPid(pid).listIterator();
		while(ci.hasNext()) {
			Comment c = ci.next();
			if(c.getNid().equals(nid)) return c;
		}
		return new NullComment();
	}

	private Comment findCommentByNid(String nid){
		Iterator<Comment> ci = Comments.listIterator();
		while(ci.hasNext()) {
			Comment c = ci.next();
			if(c.getNid().equals(nid)) {
				return c;
			}
		}
		return new NullComment();
	}
	private List<Comment> findCommentByPid(String pid) {
		Iterator<Comment> ci = Comments.listIterator();
		List<Comment> parkComments = new ArrayList<Comment>();
		while(ci.hasNext()) {
			Comment c = ci.next();
			if(c.getPid().equals(pid)) {
				parkComments.add(c);
			}
		}
		return parkComments;
	}
	private List<Comment> findCommentByVid(String vid){
		Iterator<Comment> ci = Comments.listIterator();
		List<Comment> results = new ArrayList<Comment>();
		while(ci.hasNext()) {
			Comment c = ci.next();
			if(c.getVid().equals(vid)) {
				results.add(c);
			}
		}
		return results;
	}

	private List<Comment> findCommentByKeyword(String keyword){
	    keyword = keyword.toLowerCase();
		List<Comment> results = new ArrayList<Comment>();
		Iterator<Comment> ci = Comments.listIterator();
		while(ci.hasNext()) {
			Comment c = ci.next();
			if(c.getText().toLowerCase().contains(keyword) ||
			   c.getTitle().toLowerCase().contains(keyword)) results.add(c);
		}
		return results;
	}
}
