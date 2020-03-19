package edu.iit.cs445.gheo1.comment;

import java.util.Date;

import edu.iit.cs445.gheo1.common.ChangeDateFormat;
import edu.iit.cs445.gheo1.common.UniqueIdGenerator;

public class Comment {
	private String vid;
	private String nid;
	private String pid;
	private String date;
	private String title;
	private String text;
	
	public Comment() {
	
	}
	public Comment(String vid, String pid, String title, String text) {
		this.vid = vid;
		this.setNid(UniqueIdGenerator.getUniqueID());
		this.pid = pid;
		this.date = ChangeDateFormat.toyyyyMMdd(new Date());
		this.title = title;
		this.text = text;
	}
	public boolean isNil() {
		return false;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = ChangeDateFormat.toyyyyMMdd(new Date());
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
