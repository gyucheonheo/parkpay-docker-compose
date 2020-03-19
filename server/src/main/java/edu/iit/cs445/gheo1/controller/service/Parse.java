package edu.iit.cs445.gheo1.controller.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.common.Vehicle;
import edu.iit.cs445.gheo1.park.locationinfo.Geo;
import edu.iit.cs445.gheo1.park.locationinfo.LocationInfo;
import edu.iit.cs445.gheo1.park.payment.CarPayment;
import edu.iit.cs445.gheo1.park.payment.MotorCyclePayment;
import edu.iit.cs445.gheo1.park.payment.Payment;
import edu.iit.cs445.gheo1.park.payment.PaymentInfo;
import edu.iit.cs445.gheo1.park.payment.RvPayment;
import edu.iit.cs445.gheo1.visitor.Visitor;

public class Parse {

	Gson gson = new Gson();

	public Vehicle ParseVehicle(JsonObject vehicleObj) {
		String state = vehicleObj.get("state").getAsString();
		String plate = vehicleObj.get("plate").getAsString();
		String type = vehicleObj.get("type").getAsString();
		
		return new Vehicle(type, state, plate);
		
	}
	public Visitor ParseVisitor(JsonObject visitorObj) {
		String name = visitorObj.get("name").getAsString();
		String email = visitorObj.get("email").getAsString();
		Payment payment = this.ParsePayment(visitorObj.get("payment_info").getAsJsonObject());
		return new Visitor(name, email, payment);
	}
	
	public Payment ParsePayment(JsonObject paymentObj) {
		String card = paymentObj.get("card").getAsString();
		String name = paymentObj.get("name_on_card").getAsString();
		String exp = paymentObj.get("expiration_date").getAsString();
		int zipcode = paymentObj.get("zip").getAsInt();
		return new Payment(card, name, exp, zipcode);
	}
	
	public LocationInfo ParseLocationInfo(JsonObject linfoObj) {
    	Geo geo = gson.fromJson(linfoObj.getAsJsonObject("geo"), Geo.class);
    	
    	String name = linfoObj.get("name").getAsString();
    	String region = linfoObj.get("region").getAsString();
    	String address = linfoObj.get("address").getAsString();
    	String phone = linfoObj.get("phone").getAsString();
    	String web = linfoObj.get("web").getAsString();
    	
    	return new LocationInfo(name, region, address, phone, web, geo);
    	
	}
	
	public PaymentInfo ParsePaymentInfo(JsonObject pinfoObj) {
		
    	JsonArray motorcycleArr = pinfoObj.getAsJsonArray("motorcycle");
    	JsonArray carArr = pinfoObj.getAsJsonArray("car");
    	JsonArray rvArr = pinfoObj.getAsJsonArray("rv");
    	
    	double min = motorcycleArr.get(0).getAsDouble();
    	double mout = motorcycleArr.get(1).getAsDouble();
    	double cin = carArr.get(0).getAsDouble();
    	double cout = carArr.get(1).getAsDouble();
    	double rin = rvArr.get(0).getAsDouble();
    	double rout = rvArr.get(1).getAsDouble();
    	
    	MotorCyclePayment mcPayment = new MotorCyclePayment(min, mout);
    	CarPayment carPayment = new CarPayment(cin, cout);
    	RvPayment rvPayment = new RvPayment(rin, rout);
    	
    	return new PaymentInfo(carPayment, mcPayment, rvPayment);
	}
}
