package edu.iit.cs445.gheo1.boundary;

import java.util.List;

import com.google.gson.JsonArray;

import edu.iit.cs445.gheo1.comment.Comment;

public interface CommentBoundary {

	List<Comment> getComments();
	JsonArray getAllComments();
	Comment getCommentByNid(String nid);
	Comment getCommentByPidandNid(String pid, String nid);
	JsonArray getCommentByKeyword(String keyword);
	void updateCommentByNid(String nid, String vid, String title, String text);
	JsonArray getCommentByPid(String pid);
	List<Comment> getCommentByVid(String vid);
	Comment createComment(String vid, String pid, String title, String text);

}
