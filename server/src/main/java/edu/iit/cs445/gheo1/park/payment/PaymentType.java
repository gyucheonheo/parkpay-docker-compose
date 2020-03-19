package edu.iit.cs445.gheo1.park.payment;

public abstract class PaymentType {
	private double in;
	private double out;
	
	public PaymentType(double in, double out) {
		this.in = in;
		this.out = out;
	}
	
	public double getIn() {
		return in;
	}
	public void setIn(double in) {
		this.in = in;
	}
	public double getOut() {
		return out;
	}
	public void setOut(double out) {
		this.out = out;
	}
	
	
}
