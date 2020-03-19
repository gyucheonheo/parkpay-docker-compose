package edu.iit.cs445.gheo1.validation;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.iit.cs445.gheo1.boundary.AdmissionBoundary;
import edu.iit.cs445.gheo1.manager.OrderManager;
public class Validation {
    private boolean isValidCardNumber(String card){
    	if( card.length() < 15){
    	    return false;
    	}
    	if( card.length() > 16){
    	    return false;
    	}

    	return true;
    }

    public String isDateValid(String date){

	    if (Integer.parseInt(date.substring(4,5)) > 1 ){
	      return "The request for a month is bad";
	    }
	    if(date.substring(4,5).equals(1) && Integer.parseInt(date.substring(5,6)) > 2){
	      return "The request for a month is bad";
	    }
	    if( Integer.parseInt(date.substring(6,8)) > 31){
	      return "The request for a day is bad";
	    }
	    return "ok";
    }
	public void isVisitorValid(JsonObject visitorObj, JsonObject errors) {
		if(visitorObj == null) {
			errors.addProperty("visitor", "No Visitor Information");
		}
		if(visitorObj.get("email").getAsString().isEmpty()) {
			errors.addProperty("email",  "No email information");
		}
		JsonObject paymentObj = visitorObj.get("payment_info").getAsJsonObject();
		if(paymentObj == null) {
			errors.addProperty("payment", "No payment information");
		}
		if(paymentObj.get("card").getAsString().isEmpty()) {
			errors.addProperty("card", "No card number information");
		}
		if(!isValidCardNumber(paymentObj.get("card").getAsString())){
			errors.addProperty("card", "Card number should be the 15 or 16 digits");
		}

		if(paymentObj.get("name_on_card").getAsString().isEmpty()) {
			errors.addProperty("name_on_card", "No name on the card information");
		}
		String exp = paymentObj.get("expiration_date").getAsString();
		String pattern ="[0-9]{2}/[0-9]{2}";
		if(exp.isEmpty()) {
			errors.addProperty("payment", "No payment information");
		}
		if(!exp.matches(pattern)) {
			errors.addProperty("expiration_date", "Invalid Exp date (MM/YY is Valid)");
		}
		if(paymentObj.get("zip").getAsString().isEmpty()) {
			errors.addProperty("zip", "No zipcode information");
		}
		return;


	}
	public void isVehicleValid(JsonObject vehicleObj, JsonObject errors) {

		if(vehicleObj == null) {
			errors.addProperty("vehicle", "No vehicle information");
		}
		if(vehicleObj.get("state").getAsString().isEmpty()) {
			errors.addProperty("state", "No state information");
		}
		if(vehicleObj.get("state").getAsString().length() != 2) {
			errors.addProperty("state", "State information should be the 2 characters");
		}
		if(vehicleObj.get("plate").getAsString().isEmpty()) {
			errors.addProperty("plate", "No plate information");
		}

		String pattern = "car||rv||motorcycle";
		String type = vehicleObj.get("type").getAsString();

		if(type.isEmpty()) {
			errors.addProperty("type", "No car type information");
		}
		if( !type.matches(pattern)) {
			errors.addProperty("type", "Car type should be one among car, rv, and motorcycle");
		}

		return;

	}

  public String isCommentValid(JsonObject cinfoObj, String pid) {
  	if(cinfoObj == null) {
  		return "No comment information";
  	}
  	if(cinfoObj.get("vid") == null) {
  		return "vid is null";
  	}
  	AdmissionBoundary ai = new OrderManager();
  	if(pid != null) {
  		if(ai.isVidValidForPid(pid, cinfoObj.get("vid").getAsString()) == false){
  			return "vid did not order park pid";
  	    }
  	}
  	if(cinfoObj.get("vid").getAsString().isEmpty()) {
  		return "vid is emtpy";
  	}
  	if(cinfoObj.get("title") == null) {
  		return "title is null";
  	}
  	if(cinfoObj.get("title").getAsString().isEmpty()) {
  		return "title is empty";
  	}
  	if(cinfoObj.get("text") == null) {
  		return "text is null";
  	}
  	if(cinfoObj.get("text").getAsString().isEmpty()) {
  		return "text is empty";
  	}
  	return "ok";
  }
	public void isLocationValid(JsonObject linfoObj, JsonObject errors) {
		if(linfoObj == null){
			errors.addProperty("location_info", "No Location Information");
		}
		if(linfoObj.get("name") == null) {
		      errors.addProperty("name" , "name is null");
		}
		if(linfoObj.get("name").getAsString().isEmpty()){
			errors.addProperty("name", "name is empty");
		}
		if(linfoObj.get("region") == null) {
      errors.addProperty("region" , "region is null");
		}
		if(linfoObj.get("region").getAsString().isEmpty()) {
      errors.addProperty("region", "region is empty");
		}
		if(linfoObj.get("address") == null) {
      errors.addProperty("address", "address is null");
		}
		if(linfoObj.get("address").getAsString().isEmpty()) {
      errors.addProperty("address", "address is empty");
		}
		if(linfoObj.get("web") == null) {
      errors.addProperty("web", "web is null");
		}
		if(linfoObj.get("web").getAsString().isEmpty()) {
      errors.addProperty("web", "web is empty");
		}
		if(linfoObj.get("geo") == null) {
      errors.addProperty("geo", "geo is null");
		}
	    if(linfoObj.get("geo").getAsJsonObject().get("lat").getAsString().isEmpty()){
	      errors.addProperty("lat", "lat is empty");
	    }
	    if(linfoObj.get("geo").getAsJsonObject().get("lng").getAsString().isEmpty()){
	      errors.addProperty("lng", "lng is empty");
	    }
			return;
	}

	public void isPaymentValid(JsonObject pinfoObj,JsonObject errors){
		if(pinfoObj == null) {
      errors.addProperty("payment_info", "No payment information");
		}
  	JsonArray motorcycleArr = pinfoObj.getAsJsonArray("motorcycle");
		if(motorcycleArr.isJsonNull()) {
      errors.addProperty("motorcycle", "No motorcycle information");
		}
		if(motorcycleArr.size() < 2) {
      errors.addProperty("motorcycle", "Not enough information for motorcycle fee");
		}
		if(motorcycleArr.size() > 2) {
      errors.addProperty("motorcycle", "Too many information for motorcycle fee");
		}
  	if(motorcycleArr.get(0).getAsDouble() < 0) {
      errors.addProperty("motorcycle_in_state", "In-state motorcycle fee must be positive numbers or zero");
  	}
  	if(motorcycleArr.get(1).getAsDouble() < 0) {
      errors.addProperty("motorcycle_out_state", "Out-state motorcycle fee must be positive numbers or zero");
  	};

  	JsonArray carArr = pinfoObj.getAsJsonArray("car");

		if(carArr.isJsonNull()) {
      errors.addProperty("car", "No car information");
		}
		if(carArr.size() < 2) {
      errors.addProperty("car", "Not enough information for car fee");
		}
		if(carArr.size() > 2) {
      errors.addProperty("car", "Too many information for car fee");
		}

  	if(carArr.get(0).getAsDouble() < 0) {
      errors.addProperty("car_in_state", "In-state car fee must be positive numbers or zero");
  	}
  	if(carArr.get(1).getAsDouble() < 0) {
      errors.addProperty("car_in_state", "Out-state car fee must be positive numbers or zero");
  	};
  	JsonArray rvArr = pinfoObj.getAsJsonArray("rv");
		if(rvArr.isJsonNull()) {
      errors.addProperty("rv", "No rv information");
		}
		if(rvArr.size() < 2) {
      errors.addProperty("rv", "Not enough information for rv fee");
		}
		if(rvArr.size() > 2) {
      errors.addProperty("rv", "Too many information for rv fee");
		}
  	if(rvArr.get(0).getAsDouble() < 0) {
      errors.addProperty("rv_in_state", "In-state rv fee must be positive numbers or zero");
  	}
  	if(rvArr.get(1).getAsDouble() < 0) {
      errors.addProperty("rv_out_state", "Out-state rv fee must be positive numbers or zero");
  	}
    return ;
	}

}
