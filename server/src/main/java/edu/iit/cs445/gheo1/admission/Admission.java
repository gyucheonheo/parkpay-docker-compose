package edu.iit.cs445.gheo1.admission;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.iit.cs445.gheo1.common.ChangeDateFormat;
import edu.iit.cs445.gheo1.common.UniqueIdGenerator;
import edu.iit.cs445.gheo1.common.Vehicle;
import edu.iit.cs445.gheo1.visitor.Visitor;

public class Admission {
	private String oid;
	private String pid;
	private String vid;
	private double amount;
	private Date date;
	private Vehicle vehicle;
	private Visitor visitor;

	public Admission() {

	}
	public Admission( String pid, String vid, double amount, Vehicle vehicle, Visitor visitor) {
		this.oid = UniqueIdGenerator.getUniqueID();
		this.pid = pid;
		this.vid = vid;
		this.amount = amount;
		this.vehicle = vehicle;
		this.visitor = visitor;
		this.date = new Date();
	}
	public boolean isNil() {
		return false;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public Visitor getVisitor() {
		return visitor;
	}
	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
