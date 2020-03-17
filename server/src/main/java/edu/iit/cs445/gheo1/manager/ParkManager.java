package edu.iit.cs445.gheo1.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.boundary.ParkBoundary;
import edu.iit.cs445.gheo1.park.NullPark;
import edu.iit.cs445.gheo1.park.Park;
import edu.iit.cs445.gheo1.park.locationinfo.LocationInfo;
import edu.iit.cs445.gheo1.park.payment.PaymentInfo;

public class ParkManager implements ParkBoundary{
	private static List<Park> Parks = new ArrayList<Park>();

	public List<Park> getParks(){
		return Parks;
	}
	public final String createPark(LocationInfo locationInfo, PaymentInfo paymentInfo) {
		Park p = new Park(locationInfo,paymentInfo);
		Parks.add(p);
		return p.getPid();
	}
		
	public final void updateParkByPid(String pid, LocationInfo locationInfo, PaymentInfo paymentInfo ) {
		Park p = findParkByPid(pid);
		p.setLocationInfo(locationInfo);
		p.setPaymentInfo(paymentInfo);

	}
	
	public final void deleteParkByPid(String pid) {
		Park p = findParkByPid(pid);
		Parks.remove(p);
	}
	
	public final JsonArray getAllParks(){
		JsonArray parkSets = getSimpleParkInfo(Parks);
		return parkSets;
	}
	public Park getParkByPid(String pid) {
		return findParkByPid(pid);
	}
	public JsonObject getParkByPidAsJsonObject(String pid) {
		Park p = findParkByPid(pid);
		JsonObject park = new JsonObject();
		JsonObject locationInfo = new JsonObject();
		JsonObject geo = new JsonObject();

		park.addProperty("pid", p.getPid());
		locationInfo.addProperty("name",  p.getLocationInfo().getName());
		locationInfo.addProperty("region", p.getLocationInfo().getRegion());
		locationInfo.addProperty("address", p.getLocationInfo().getAddress());
		locationInfo.addProperty("phone", p.getLocationInfo().getPhone());
		locationInfo.addProperty("web", p.getLocationInfo().getWeb());
		
		geo.addProperty("lat", p.getLocationInfo().getGeo().getLat());
		geo.addProperty("lng", p.getLocationInfo().getGeo().getLng());
		
		locationInfo.add("geo", geo);
		
		park.add("location_info", locationInfo);
		
		JsonObject paymentInfo = new JsonObject();
		JsonArray motorcycle = new JsonArray();
		JsonArray car = new JsonArray();
		JsonArray rv = new JsonArray();
		
		motorcycle.add(p.getPaymentInfo().getMotorcycle().getIn());
		motorcycle.add(p.getPaymentInfo().getMotorcycle().getOut());
		
		car.add(p.getPaymentInfo().getCar().getIn());
		car.add(p.getPaymentInfo().getCar().getOut());
		
		rv.add(p.getPaymentInfo().getRv().getIn());
		rv.add(p.getPaymentInfo().getRv().getOut());
		
		paymentInfo.add("motorcycle", motorcycle);
		paymentInfo.add("car", car);
		paymentInfo.add("rv", rv);
		
		park.add("payment_info", paymentInfo);

		return park;
	}
	
	public JsonArray getParkByKeyword(String keyword){
		List<Park> parks = findParkByKeyword(keyword);
		JsonArray parkSets = getSimpleParkInfo(parks);
		return parkSets;
	}
	
	public JsonArray getParksForAdmissionReport() {
		JsonArray parkSets = new JsonArray();

		for(Park p: Parks) {
			JsonObject park = new JsonObject();
			
			park.addProperty("pid",p.getPid());
			park.addProperty("name", p.getLocationInfo().getName());
			
			parkSets.add(park);
		}
		return parkSets;
	}
	
	private JsonArray getSimpleParkInfo(List<Park> parks) {
		JsonArray parkSets = new JsonArray();

		for(Park p: parks) {
			JsonObject park = new JsonObject();
			JsonObject location_info = new JsonObject();
			JsonObject geo = new JsonObject();
			
			park.addProperty("pid",p.getPid());
			
			location_info.addProperty("name", p.getLocationInfo().getName());
			location_info.addProperty("region", p.getLocationInfo().getRegion());
			location_info.addProperty("address", p.getLocationInfo().getAddress());
			location_info.addProperty("phone", p.getLocationInfo().getPhone());
			location_info.addProperty("web", p.getLocationInfo().getWeb());
			
			geo.addProperty("lat", p.getLocationInfo().getGeo().getLat());
			geo.addProperty("lng", p.getLocationInfo().getGeo().getLng());
			
			location_info.add("geo", geo);
			
			park.add("location_info", location_info);
			
			parkSets.add(park);
		}
		return parkSets;
	}
	
	private List<Park> findParkByKeyword(String keyword){
	    keyword = keyword.toLowerCase();
		List<Park> results = new ArrayList<Park>();
		Iterator<Park> pi = Parks.listIterator();
		while(pi.hasNext()) {
			Park p = pi.next();
			if(p.getLocationInfo().getName().toLowerCase().contains(keyword) ||
			   p.getLocationInfo().getAddress().toLowerCase().contains(keyword) ||
			   p.getLocationInfo().getRegion().toLowerCase().contains(keyword) ||
			   p.getLocationInfo().getPhone().toLowerCase().contains(keyword) ||
			   p.getLocationInfo().getWeb().toLowerCase().contains(keyword)) results.add(p);
		}
		return results;
	}
	private Park findParkByPid(String pid) {
		Iterator<Park> pi = Parks.listIterator();
		while(pi.hasNext()) {
			Park p = pi.next();
			if(p.getPid().equals(pid)) return p;
		}
		return new NullPark();
	}

}
