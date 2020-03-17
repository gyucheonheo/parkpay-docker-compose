package edu.iit.cs445.gheo1.park;

import edu.iit.cs445.gheo1.common.UniqueIdGenerator;
import edu.iit.cs445.gheo1.park.locationinfo.LocationInfo;
import edu.iit.cs445.gheo1.park.payment.PaymentInfo;

public class Park {
	
	private String pid;
	private LocationInfo locationInfo;
	private PaymentInfo paymentInfo;
	
	public Park() {
		this.setPid(UniqueIdGenerator.getUniqueID());
	}
	
	public Park(LocationInfo locationInfo, PaymentInfo paymentInfo) {
		this.setPid(UniqueIdGenerator.getUniqueID());
		this.locationInfo = locationInfo;
		this.paymentInfo = paymentInfo;
		
	}
	
	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public boolean isNil() {
		return false;
	}
	public LocationInfo getLocationInfo() {
		return this.locationInfo;
	}
	
	public void setLocationInfo(LocationInfo locationInfo) {
		this.locationInfo = locationInfo;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
}
