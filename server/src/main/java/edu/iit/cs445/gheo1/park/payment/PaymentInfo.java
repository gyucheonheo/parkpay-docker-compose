package edu.iit.cs445.gheo1.park.payment;

public class PaymentInfo {
	private PaymentType car;
	private PaymentType motorcycle;
	private PaymentType rv;
	
	public PaymentInfo() {
		
	}
	public PaymentInfo(PaymentType car, PaymentType motorcycle, PaymentType rv) {
		this.car = car;
		this.motorcycle = motorcycle;
		this.rv = rv;
	}
	
	public boolean isNil() {
		return false;
	}
	public PaymentType getCar() {
		return car;
	}
	public void setCar(PaymentType car) {
		this.car = car;
	}
	public PaymentType getMotorcycle() {
		return motorcycle;
	}
	public void setMotorcycle(PaymentType motorcycle) {
		this.motorcycle = motorcycle;
	}
	public PaymentType getRv() {
		return rv;
	}
	public void setRv(PaymentType rv) {
		this.rv = rv;
	}
	
	
}
