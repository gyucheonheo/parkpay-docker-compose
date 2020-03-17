package edu.iit.cs445.gheo1.park.payment;

public class NullPaymentInfo extends PaymentInfo{
	private String error;
	
	@Override
	public boolean isNil() {
		return true;
	}
	
	public String getError() {
		return this.error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
}
