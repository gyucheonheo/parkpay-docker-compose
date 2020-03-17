package edu.iit.cs445.gheo1.visitor;

import edu.iit.cs445.gheo1.park.payment.Payment;

public class Visitor {
	private String vid;
	private String name;
	private String email;
	private Payment payment;

	public Visitor() {
		
	}
	public Visitor(String vid, String name, String email) {
		this.vid = vid;
		this.name = name;
		this.email = email;
	}
	public Visitor(String name, String email, Payment payment) {
		this.name = name;
		this.email = email;
		this.payment = payment;
	}
	public boolean isNil() {
		return false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	
	
	
}
