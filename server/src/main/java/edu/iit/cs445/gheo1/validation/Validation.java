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
	public String isVisitorValid(JsonObject visitorObj) {
		if(visitorObj == null) {
			return "No Visitor Information";
		}
		if(visitorObj.get("email").getAsString().isEmpty()) {
			return "No email information";
		}
		JsonObject paymentObj = visitorObj.get("payment_info").getAsJsonObject();
		if(paymentObj == null) {
			return "No Payment information";
		}
		if(paymentObj.get("card").getAsString().isEmpty()) {
			return "No card number information";
		}
		if(!isValidCardNumber(paymentObj.get("card").getAsString())){
		    return "Card number should be the 15 or 16 digits";
		}

		if(paymentObj.get("name_on_card").getAsString().isEmpty()) {
			return "No name on the card information";
		}
		String exp = paymentObj.get("expiration_date").getAsString();
		String pattern ="[0-9]{2}/[0-9]{2}";

		if(exp.isEmpty()) {
			return "No expiration date information";
		}
		if(!exp.matches(pattern)) {
			return "Expiration date follow MM/YY format";
		}
		if(paymentObj.get("zip").getAsString().isEmpty()) {
			return "No zipcode information";
		}

		return "ok";


	}
	public String isVehicleValid(JsonObject vehicleObj) {

		if(vehicleObj == null) {
			return "No vehicle information";
		}
		if(vehicleObj.get("state").getAsString().isEmpty()) {
			return "No state information";
		}
		if(vehicleObj.get("state").getAsString().length() != 2) {
			return "State information should be the 2 characters";
		}
		if(vehicleObj.get("plate").getAsString().isEmpty()) {
			return "No plate information";
		}

		String pattern = "car||rv||motorcycle";
		String type = vehicleObj.get("type").getAsString();

		if(type.isEmpty()) {
			return "No type information";
		}
		if( !type.matches(pattern)) {
			return "Car type should be one among car, rv and motorcycle";
		}

		return "ok";

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
	public String isLocationValid(JsonObject linfoObj) {
		if(linfoObj == null){
			return "No location_info";
		}
		if(linfoObj.get("region") == null) {
			return "region is null";
		}
		if(linfoObj.get("region").getAsString().isEmpty()) {
			return "region is empty";
		}
		if(linfoObj.get("address") == null) {
			return "address is null";
		}
		if(linfoObj.get("address").getAsString().isEmpty()) {
			return "address is empty";
		}
		if(linfoObj.get("web") == null) {
			return "web is null";
		}
		if(linfoObj.get("web").getAsString().isEmpty()) {
			return "web is empty";
		}
		if(linfoObj.get("geo") == null) {
			return "geo is null";
		}
		return "ok";
	}

	public String isPaymentValid(JsonObject pinfoObj) {
		if(pinfoObj == null) {
			return "No payment_info";
		}
    	JsonArray motorcycleArr = pinfoObj.getAsJsonArray("motorcycle");
		if(motorcycleArr.isJsonNull()) {
			return "No motorcycle information";
		}
		if(motorcycleArr.size() < 2) {
			return "Not enough information for motorcycle fee";
		}
		if(motorcycleArr.size() > 2) {
			return "Too many information for motorcycle fee";
		}

    	if(motorcycleArr.get(0).getAsDouble() < 0) {
    		return "In-state motorcycle fee must be positive numbers or zero";
    	}
    	if(motorcycleArr.get(1).getAsDouble() < 0) {
    		return "Out-state motorcycle fee must be positive numbers or zero";
    	};

    	JsonArray carArr = pinfoObj.getAsJsonArray("car");
		if(carArr.isJsonNull()) {
			return "No car information";
		}
		if(carArr.size() < 2) {
			return "Not enough information for car fee";
		}
		if(carArr.size() > 2) {
			return "Too many information for car fee";
		}

    	if(carArr.get(0).getAsDouble() < 0) {
    		return "In-state car fee must be positive numbers or zero";
    	}
    	if(carArr.get(1).getAsDouble() < 0) {
    		return "Out-state car fee must be positive numbers or zero";
    	};
    	JsonArray rvArr = pinfoObj.getAsJsonArray("rv");
		if(rvArr.isJsonNull()) {
			return "No rv information";
		}
		if(rvArr.size() < 2) {
			return "Not enough information for rv fee";
		}
		if(rvArr.size() > 2) {
			return "Too many information for rv fee";
		}

    	if(rvArr.get(0).getAsDouble() < 0) {
    		return "In-state rv fee must be positive numbers or zero";
    	}
    	if(rvArr.get(1).getAsDouble() < 0) {
    		return "Out-state rv fee must be positive numbers or zero";
    	};


		return "ok";
	}

}
