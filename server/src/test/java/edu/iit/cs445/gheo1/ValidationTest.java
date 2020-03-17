package edu.iit.cs445.gheo1;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.validation.Validation;

public class ValidationTest {
	Validation v = new Validation();
	@Test
	public void isDateValid_bad_month() {
		String date="20182012";
		assertFalse(v.isDateValid(date).equals("ok"));
	}
	
	@Test
	public void isDateValid_good_month() {
		String date="20181212";
		assertTrue(v.isDateValid(date).equals("ok"));
	}
	@Test
	public void isDateValid_bad_day() {
		String date="20181232";
		assertFalse(v.isDateValid(date).equals("ok"));
	}
	@Test
	public void isDateValid_good_day() {
		String date="20181231";
		assertTrue(v.isDateValid(date).equals("ok"));
	}
	
	@Test
	public void isVisitorValid_visitor_null() {
		JsonObject visitorObj = null;
		assertFalse(v.isVisitorValid(visitorObj).equals("ok"));
	}
	@Test
	public void isVisitorValid_no_email() {
		JsonObject visitorObj = new JsonObject();
		visitorObj.addProperty("name", "Gyucheon");
		visitorObj.addProperty("email", "");
		assertFalse(v.isVisitorValid(visitorObj).equals("ok"));
	}
	
	@Test
	public void isVisitorValid_no_card() {
		JsonObject visitorObj = new JsonObject();
		JsonObject paymentObj = new JsonObject();
		paymentObj.addProperty("card", "");
		visitorObj.addProperty("name", "Gyucheon");
		visitorObj.addProperty("email", "gheo1@hawk.iit.edu");
		visitorObj.add("payment_info", paymentObj);
		assertFalse(v.isVisitorValid(visitorObj).equals("ok"));
	}
	
	@Test
	public void isVisitorValid_card_more_16() {
		JsonObject visitorObj = new JsonObject();
		JsonObject paymentObj = new JsonObject();
		paymentObj.addProperty("card", "12345678901234567");
		visitorObj.addProperty("name", "Gyucheon");
		visitorObj.addProperty("email", "gheo1@hawk.iit.edu");
		visitorObj.add("payment_info", paymentObj);
		assertFalse(v.isVisitorValid(visitorObj).equals("ok"));
	}
	
	@Test
	public void isVisitorValid_card_no_name_on_card() {
		JsonObject visitorObj = new JsonObject();
		JsonObject paymentObj = new JsonObject();
		paymentObj.addProperty("card", "1234567890123456");
		paymentObj.addProperty("name_on_card", "");
		visitorObj.addProperty("name", "Gyucheon");
		visitorObj.addProperty("email", "gheo1@hawk.iit.edu");
		visitorObj.add("payment_info", paymentObj);
		
		assertFalse(v.isVisitorValid(visitorObj).equals("ok"));
	}
	
	@Test
	public void isVisitorValid_card_bad_date_format() {
		JsonObject visitorObj = new JsonObject();
		JsonObject paymentObj = new JsonObject();
		paymentObj.addProperty("card", "1234567890123457");
		paymentObj.addProperty("name_on_card", "Gyucheon");
		paymentObj.addProperty("expiration_date", "1122");
		visitorObj.addProperty("name", "Gyucheon");
		visitorObj.addProperty("email", "gheo1@hawk.iit.edu");
		visitorObj.add("payment_info", paymentObj);
		assertFalse(v.isVisitorValid(visitorObj).equals("ok"));
	}
	
	@Test
	public void isVisitorValid_card_less_than_fifteen_bad_date_format() {
		JsonObject visitorObj = new JsonObject();
		JsonObject paymentObj = new JsonObject();
		paymentObj.addProperty("card", "12345678901234");
		paymentObj.addProperty("name_on_card", "Gyucheon");
		paymentObj.addProperty("expiration_date", "1122");
		visitorObj.addProperty("name", "Gyucheon");
		visitorObj.addProperty("email", "gheo1@hawk.iit.edu");
		visitorObj.add("payment_info", paymentObj);
		assertFalse(v.isVisitorValid(visitorObj).equals("ok"));
	}
	
	@Test
	public void isVisitorValid_card_no_date_format() {
		JsonObject visitorObj = new JsonObject();
		JsonObject paymentObj = new JsonObject();
		paymentObj.addProperty("card", "1234567890123457");
		paymentObj.addProperty("name_on_card", "Gyucheon");
		paymentObj.addProperty("expiration_date", "");
		visitorObj.addProperty("name", "Gyucheon");
		visitorObj.addProperty("email", "gheo1@hawk.iit.edu");
		visitorObj.add("payment_info", paymentObj);
		assertFalse(v.isVisitorValid(visitorObj).equals("ok"));
	}
	
	@Test
	public void isVisitorValid_card_no_zipcode() {
		JsonObject visitorObj = new JsonObject();
		JsonObject paymentObj = new JsonObject();
		paymentObj.addProperty("card", "1234567890123457");
		paymentObj.addProperty("name_on_card", "Gyucheon");
		paymentObj.addProperty("expiration_date", "11/20");
		paymentObj.addProperty("zip", "");
		visitorObj.addProperty("name", "Gyucheon");
		visitorObj.addProperty("email", "gheo1@hawk.iit.edu");
		visitorObj.add("payment_info", paymentObj);
		assertFalse(v.isVisitorValid(visitorObj).equals("ok"));
	}
	
	@Test
	public void isVisitorValid_card_good_visitor() {
		JsonObject visitorObj = new JsonObject();
		JsonObject paymentObj = new JsonObject();
		paymentObj.addProperty("card", "1234567890123457");
		paymentObj.addProperty("name_on_card", "Gyucheon");
		paymentObj.addProperty("expiration_date", "11/20");
		paymentObj.addProperty("zip", "60616");
		visitorObj.addProperty("name", "Gyucheon");
		visitorObj.addProperty("email", "gheo1@hawk.iit.edu");
		visitorObj.add("payment_info", paymentObj);
		assertTrue(v.isVisitorValid(visitorObj).equals("ok"));
	}
	
	@Test
	public void isVehicleValid_no_vehicle_obj() {
		JsonObject vehicleObj = null;
		assertFalse(v.isVehicleValid(vehicleObj).equals("ok"));
	}
	
	@Test
	public void isVehicleValid_no_state() {
		JsonObject vehicleObj = new JsonObject();
		vehicleObj.addProperty("state", "");
		assertFalse(v.isVehicleValid(vehicleObj).equals("ok"));
	}
	
	@Test
	public void isVehicleValid_bad_state() {
		JsonObject vehicleObj = new JsonObject();
		vehicleObj.addProperty("state", "ILL");
		assertFalse(v.isVehicleValid(vehicleObj).equals("ok"));
	}
	
	@Test
	public void isVehicleValid_no_plate() {
		JsonObject vehicleObj = new JsonObject();
		vehicleObj.addProperty("state", "IL");
		vehicleObj.addProperty("plate", "");
		assertFalse(v.isVehicleValid(vehicleObj).equals("ok"));
	}
	
	@Test
	public void isVehicleValid_no_type() {
		JsonObject vehicleObj = new JsonObject();
		vehicleObj.addProperty("state", "IL");
		vehicleObj.addProperty("plate", "GOCUBS");
		vehicleObj.addProperty("type", "");
		assertFalse(v.isVehicleValid(vehicleObj).equals("ok"));
	}
	
	@Test
	public void isVehicleValid_bad_type() {
		JsonObject vehicleObj = new JsonObject();
		vehicleObj.addProperty("state", "IL");
		vehicleObj.addProperty("plate", "GOCUBS");
		vehicleObj.addProperty("type", "airplane");
		assertFalse(v.isVehicleValid(vehicleObj).equals("ok"));
	}
	
	@Test
	public void isVehicleValid_car() {
		JsonObject vehicleObj = new JsonObject();
		vehicleObj.addProperty("state", "IL");
		vehicleObj.addProperty("plate", "GOCUBS");
		vehicleObj.addProperty("type", "car");
		assertTrue(v.isVehicleValid(vehicleObj).equals("ok"));
	}
	
	@Test
	public void isVehicleValid_rv() {
		JsonObject vehicleObj = new JsonObject();
		vehicleObj.addProperty("state", "IL");
		vehicleObj.addProperty("plate", "GOCUBS");
		vehicleObj.addProperty("type", "rv");
		assertTrue(v.isVehicleValid(vehicleObj).equals("ok"));
	}
	
	@Test
	public void isVehicleValid_mt() {
		JsonObject vehicleObj = new JsonObject();
		vehicleObj.addProperty("state", "IL");
		vehicleObj.addProperty("plate", "GOCUBS");
		vehicleObj.addProperty("type", "motorcycle");
		assertTrue(v.isVehicleValid(vehicleObj).equals("ok"));
	}
	
	@Test
	public void isCommentValid_no_cinfo() {
		JsonObject cinfoObj = null;
		assertFalse(v.isCommentValid(cinfoObj, null).equals("ok"));
	}
	
	@Test
	public void isCommentValid_no_vid() {
		JsonObject cinfoObj = new JsonObject();
		assertFalse(v.isCommentValid(cinfoObj, null).equals("ok"));
	}
	
	@Test
	public void isCommentValid_empty_vid() {
		JsonObject cinfoObj = new JsonObject();
		cinfoObj.addProperty("vid", "");
		assertFalse(v.isCommentValid(cinfoObj, null).equals("ok"));
	}
	
	@Test
	public void isCommentValid_no_title() {
		JsonObject cinfoObj = new JsonObject();
		cinfoObj.addProperty("vid", "10");
		assertFalse(v.isCommentValid(cinfoObj, null).equals("ok"));
	}
	
	@Test
	public void isCommentValid_empty_title() {
		JsonObject cinfoObj = new JsonObject();
		cinfoObj.addProperty("vid", "10");
		cinfoObj.addProperty("title", "");
		assertFalse(v.isCommentValid(cinfoObj, null).equals("ok"));
	}
	
	@Test
	public void isCommentValid_no_text() {
		JsonObject cinfoObj = new JsonObject();
		cinfoObj.addProperty("vid", "10");
		cinfoObj.addProperty("title", "title");
		assertFalse(v.isCommentValid(cinfoObj, null).equals("ok"));
	}
	
	@Test
	public void isCommentValid_empty_text() {
		JsonObject cinfoObj = new JsonObject();
		cinfoObj.addProperty("vid", "10");
		cinfoObj.addProperty("title", "title");
		cinfoObj.addProperty("text", "");
		assertFalse(v.isCommentValid(cinfoObj, null).equals("ok"));
	}
	
	@Test
	public void isCommentValid_good_text() {
		JsonObject cinfoObj = new JsonObject();
		cinfoObj.addProperty("vid", "10");
		cinfoObj.addProperty("title", "title");
		cinfoObj.addProperty("text", "text");
		assertTrue(v.isCommentValid(cinfoObj, null).equals("ok"));
	}
	
	@Test
	public void isLocationValid_no_linfoObj() {
		JsonObject linfoObj = null;
		assertFalse(v.isLocationValid(linfoObj).equals("ok"));
	}
	
	@Test
	public void isLocationValid_no_region() {
		JsonObject linfoObj = new JsonObject();
		assertFalse(v.isLocationValid(linfoObj).equals("ok"));		
	}
	
	@Test
	public void isLocationValid_empty_region() {
		JsonObject linfoObj = new JsonObject();
		linfoObj.addProperty("region", "");
		assertFalse(v.isLocationValid(linfoObj).equals("ok"));		
	}
	
	@Test
	public void isLocationValid_no_address() {
		JsonObject linfoObj = new JsonObject();
		linfoObj.addProperty("region", "region");
		assertFalse(v.isLocationValid(linfoObj).equals("ok"));		
	}
	
	@Test
	public void isLocationValid_empty_address() {
		JsonObject linfoObj = new JsonObject();
		linfoObj.addProperty("region", "region");
		linfoObj.addProperty("address", "");
		assertFalse(v.isLocationValid(linfoObj).equals("ok"));		
	}
	
	@Test
	public void isLocationValid_no_web() {
		JsonObject linfoObj = new JsonObject();
		linfoObj.addProperty("region", "region");
		linfoObj.addProperty("address", "address");
		assertFalse(v.isLocationValid(linfoObj).equals("ok"));		
	}
	
	@Test
	public void isLocationValid_empty_web() {
		JsonObject linfoObj = new JsonObject();
		linfoObj.addProperty("region", "region");
		linfoObj.addProperty("address", "address");
		linfoObj.addProperty("web", "");
		assertFalse(v.isLocationValid(linfoObj).equals("ok"));		
	}
	
	@Test
	public void isLocationValid_no_geo() {
		JsonObject linfoObj = new JsonObject();
		linfoObj.addProperty("region", "region");
		linfoObj.addProperty("address", "address");
		linfoObj.addProperty("web", "localhost:8080");
		assertFalse(v.isLocationValid(linfoObj).equals("ok"));		
	}
	
	@Test
	public void isLocationValid_good_linfo() {
		JsonObject linfoObj = new JsonObject();
		JsonObject geo = new JsonObject();
		linfoObj.addProperty("region", "region");
		linfoObj.addProperty("address", "address");
		linfoObj.addProperty("web", "localhost:8080");
		linfoObj.add("geo", geo);
		assertTrue(v.isLocationValid(linfoObj).equals("ok"));		
	}
	
	@Test
	public void isPaymentValid_no_pinfo() {
		JsonObject pinfoObj = null;
		assertFalse(v.isPaymentValid(pinfoObj).equals("ok"));
	}
	
	@Test
	public void isPaymentValid_no_mt() {
		JsonObject pinfoObj = new JsonObject();
		JsonArray motorcycle = new JsonArray();
		pinfoObj.add("motorcycle", motorcycle);
		assertFalse(v.isPaymentValid(pinfoObj).equals("ok"));
	}
	
	@Test
	public void isPaymentValid_mt_less_info() {
		JsonObject pinfoObj = new JsonObject();
		JsonArray motorcycle = new JsonArray();
		motorcycle.add(1.0);
		pinfoObj.add("motorcycle", motorcycle);
		assertFalse(v.isPaymentValid(pinfoObj).equals("ok"));
	}
	
	@Test
	public void isPaymentValid_mt_more_info() {
		JsonObject pinfoObj = new JsonObject();
		JsonArray motorcycle = new JsonArray();
		motorcycle.add(1.0);
		motorcycle.add(1.0);
		motorcycle.add(1.0);
		pinfoObj.add("motorcycle", motorcycle);
		assertFalse(v.isPaymentValid(pinfoObj).equals("ok"));
	}
	
	@Test
	public void isPaymentValid_mt_negative_in_info() {
		JsonObject pinfoObj = new JsonObject();
		JsonArray motorcycle = new JsonArray();
		motorcycle.add(-1.0);
		motorcycle.add(1.0);
		pinfoObj.add("motorcycle", motorcycle);
		assertFalse(v.isPaymentValid(pinfoObj).equals("ok"));
	}
	
	@Test
	public void isPaymentValid_mt_negative_out_info() {
		JsonObject pinfoObj = new JsonObject();
		JsonArray motorcycle = new JsonArray();
		motorcycle.add(1.0);
		motorcycle.add(-1.0);
		pinfoObj.add("motorcycle", motorcycle);
		assertFalse(v.isPaymentValid(pinfoObj).equals("ok"));
	}
	
	@Test
	public void isPaymentValid_less_info_car() {
		JsonObject pinfoObj = new JsonObject();
		JsonArray motorcycle = new JsonArray();
		motorcycle.add(1.0);
		motorcycle.add(1.0);
		pinfoObj.add("motorcycle", motorcycle);
		JsonArray car = new JsonArray();
		pinfoObj.add("car", car);
		car.add(1.0);
		assertFalse(v.isPaymentValid(pinfoObj).equals("ok"));
	}
	
	@Test
	public void isPaymentValid_more_info_car() {
		JsonObject pinfoObj = new JsonObject();
		JsonArray motorcycle = new JsonArray();
		motorcycle.add(1.0);
		motorcycle.add(1.0);
		pinfoObj.add("motorcycle", motorcycle);
		JsonArray car = new JsonArray();
		pinfoObj.add("car", car);
		car.add(1.0);
		car.add(1.0);
		car.add(1.0);
		assertFalse(v.isPaymentValid(pinfoObj).equals("ok"));
	}
	
	@Test
	public void isPaymentValid_negative_in_car() {
		JsonObject pinfoObj = new JsonObject();
		JsonArray motorcycle = new JsonArray();
		motorcycle.add(1.0);
		motorcycle.add(1.0);
		pinfoObj.add("motorcycle", motorcycle);
		JsonArray car = new JsonArray();
		pinfoObj.add("car", car);
		car.add(-1.0);
		car.add(1.0);
		assertFalse(v.isPaymentValid(pinfoObj).equals("ok"));
	}
	
	@Test
	public void isPaymentValid_negative_out_car() {
		JsonObject pinfoObj = new JsonObject();
		JsonArray motorcycle = new JsonArray();
		motorcycle.add(1.0);
		motorcycle.add(1.0);
		pinfoObj.add("motorcycle", motorcycle);
		JsonArray car = new JsonArray();
		pinfoObj.add("car", car);
		car.add(1.0);
		car.add(-1.0);
		assertFalse(v.isPaymentValid(pinfoObj).equals("ok"));
	}
	
	@Test
	public void isPaymentValid_less_info_rv() {
		JsonObject pinfoObj = new JsonObject();
		JsonArray motorcycle = new JsonArray();
		motorcycle.add(1.0);
		motorcycle.add(1.0);
		pinfoObj.add("motorcycle", motorcycle);
		JsonArray car = new JsonArray();
		pinfoObj.add("car", car);
		car.add(1.0);
		car.add(1.0);
		JsonArray rv = new JsonArray();
		pinfoObj.add("rv", rv);
		rv.add(1.0);
		assertFalse(v.isPaymentValid(pinfoObj).equals("ok"));
	}
	
	@Test
	public void isPaymentValid_more_info_rv() {
		JsonObject pinfoObj = new JsonObject();
		JsonArray motorcycle = new JsonArray();
		motorcycle.add(1.0);
		motorcycle.add(1.0);
		pinfoObj.add("motorcycle", motorcycle);
		JsonArray car = new JsonArray();
		pinfoObj.add("car", car);
		car.add(1.0);
		car.add(1.0);
		JsonArray rv = new JsonArray();
		pinfoObj.add("rv", rv);
		rv.add(1.0);
		rv.add(1.0);
		rv.add(1.0);
		assertFalse(v.isPaymentValid(pinfoObj).equals("ok"));
	}
	
	@Test
	public void isPaymentValid_negative_in_rv() {
		JsonObject pinfoObj = new JsonObject();
		JsonArray motorcycle = new JsonArray();
		motorcycle.add(1.0);
		motorcycle.add(1.0);
		pinfoObj.add("motorcycle", motorcycle);
		JsonArray car = new JsonArray();
		pinfoObj.add("car", car);
		car.add(1.0);
		car.add(1.0);
		JsonArray rv = new JsonArray();
		pinfoObj.add("rv", rv);
		rv.add(-1.0);
		rv.add(1.0);
		assertFalse(v.isPaymentValid(pinfoObj).equals("ok"));
	}
	
	@Test
	public void isPaymentValid_negative_out_rv() {
		JsonObject pinfoObj = new JsonObject();
		JsonArray motorcycle = new JsonArray();
		motorcycle.add(1.0);
		motorcycle.add(1.0);
		pinfoObj.add("motorcycle", motorcycle);
		JsonArray car = new JsonArray();
		pinfoObj.add("car", car);
		car.add(1.0);
		car.add(1.0);
		JsonArray rv = new JsonArray();
		pinfoObj.add("rv", rv);
		rv.add(1.0);
		rv.add(-1.0);
		assertFalse(v.isPaymentValid(pinfoObj).equals("ok"));
	}
	
	@Test
	public void isPaymentValid_good() {
		JsonObject pinfoObj = new JsonObject();
		JsonArray motorcycle = new JsonArray();
		motorcycle.add(1.0);
		motorcycle.add(1.0);
		pinfoObj.add("motorcycle", motorcycle);
		JsonArray car = new JsonArray();
		pinfoObj.add("car", car);
		car.add(1.0);
		car.add(1.0);
		JsonArray rv = new JsonArray();
		pinfoObj.add("rv", rv);
		rv.add(1.0);
		rv.add(1.0);
		assertTrue(v.isPaymentValid(pinfoObj).equals("ok"));
	}
	
	
}
