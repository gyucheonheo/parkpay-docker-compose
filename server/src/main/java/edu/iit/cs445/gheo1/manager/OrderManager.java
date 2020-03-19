package edu.iit.cs445.gheo1.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.admission.Admission;
import edu.iit.cs445.gheo1.admission.NullAdmission;
import edu.iit.cs445.gheo1.boundary.AdmissionBoundary;
import edu.iit.cs445.gheo1.boundary.VisitorBoundary;
import edu.iit.cs445.gheo1.common.ChangeDateFormat;
import edu.iit.cs445.gheo1.common.Vehicle;
import edu.iit.cs445.gheo1.park.Park;
import edu.iit.cs445.gheo1.visitor.Visitor;

public class OrderManager implements AdmissionBoundary {
	private static List<Admission> Admissions = new ArrayList<Admission>();

	public Admission createOrder(Park p, String vid, Vehicle vehicle, Visitor visitor) {
		VisitorBoundary vi = new VisitorManager();
		vi.createVisitor(vid, visitor.getName(), visitor.getEmail());
		double amount = getAmount(p, vehicle);
		visitor.setVid(vid);
		Admission admission = new Admission(p.getPid(), vid, amount, vehicle, visitor);
		Admissions.add(admission);
		return admission;
	}

	public List<Admission> getAdmissions(){
		return Admissions;
	}

	public JsonArray getAllOrder(){
		JsonArray orders = getSimpleOrder(Admissions);
		return orders;
	}

	public JsonObject getOrderByOid(String oid) {
		Admission a = findAdmissionByOid(oid);
		if( a.isNil()){
			return new JsonObject();
		}
		JsonObject res = new JsonObject();
		JsonObject vehicle = new JsonObject();

		res.addProperty("oid", a.getOid());
		res.addProperty("pid", a.getPid());
		res.addProperty("amount", a.getAmount());
		res.addProperty("vid", a.getVid());
		res.addProperty("date", ChangeDateFormat.toyyyyMMdd(a.getDate()));

		vehicle.addProperty("state", a.getVehicle().getState());
		vehicle.addProperty("plate", a.getVehicle().getPlate());
		vehicle.addProperty("type", a.getVehicle().getType());
		res.add("vehicle", vehicle);

		JsonObject visitor = new JsonObject();
		visitor.addProperty("name", a.getVisitor().getPayment().getName());
		visitor.addProperty("email", a.getVisitor().getEmail());

		JsonObject payment_info = new JsonObject();
		payment_info.addProperty("card", a.getVisitor().getPayment().getCard());
		payment_info.addProperty("name_on_card", a.getVisitor().getPayment().getName());
		payment_info.addProperty("expiration_date", a.getVisitor().getPayment().getExpDate());
		payment_info.addProperty("zip", a.getVisitor().getPayment().getZipcode());

		visitor.add("payment_info", payment_info);

		res.add("visitor", visitor);

		JsonObject payment_processing = new JsonObject();
		payment_processing.addProperty("card_transaction_id", "123-4567-89");
		payment_processing.addProperty("date_and_time", ChangeDateFormat.toyyyyMMdd(a.getDate()));
		res.add("payment_processing", payment_processing);

		return res;
	}

	public List<Admission> getOrderByVid(String vid){
		return findAdmissionByVid(vid);
	}

    public boolean isVidValidForPid(String pid, String vid){
	return findAdmissionByPidAndVid(pid, vid);
    }

	public JsonArray getOrderByKeyword(String keyword){
		List<Admission> admissions = findAdmissionByKeyword(keyword);
		JsonArray orders = getSimpleOrder(admissions);
		return orders;
	}
	public JsonArray getAdmissionsBetween(String startDate, String endDate) throws ParseException{
		if( (startDate == null && endDate == null) || (startDate == "" && endDate == "") ) {
			return getAllOrder();
		}
		if (startDate != null && (endDate == null || endDate=="") ){
			return getAdmissionsWithStartDate(startDate);
		}
		if( startDate == null && (endDate != null || endDate=="") ) {
			return getAdmissionsWithEndDate(endDate);
		}
		return new JsonArray();
	}

	private JsonArray getAdmissionsWithStartDate(String startDate) throws ParseException {
		Iterator<Admission> admission = Admissions.listIterator();
		JsonArray orders = new JsonArray();
		Date start = new SimpleDateFormat("yyyyMMdd").parse(startDate);
		while(admission.hasNext()) {
			Admission a = admission.next();
			Date dateToCheck = a.getDate();
			if(dateToCheck.after(start)) {
				JsonObject order = new JsonObject();
				order.addProperty("oid", a.getOid());
				order.addProperty("pid", a.getPid());
				order.addProperty("date", ChangeDateFormat.toyyyyMMdd(a.getDate()));
				order.addProperty("type", a.getVehicle().getType());
				order.addProperty("amount", a.getAmount());

				orders.add(order);
			}
		}
		return orders;
	}

	private JsonArray getAdmissionsWithEndDate(String endDate) throws ParseException {
		Iterator<Admission> admission = Admissions.listIterator();
		JsonArray orders = new JsonArray();
		Date end = new SimpleDateFormat("yyyyMMdd").parse(endDate);
		while(admission.hasNext()) {
			Admission a = admission.next();
			Date dateToCheck = a.getDate();
			if(dateToCheck.before(end)) {
				JsonObject order = new JsonObject();
				order.addProperty("oid", a.getOid());
				order.addProperty("pid", a.getPid());
				order.addProperty("date", ChangeDateFormat.toyyyyMMdd(a.getDate()));
				order.addProperty("type", a.getVehicle().getType());
				order.addProperty("amount", a.getAmount());

				orders.add(order);
			}
		}
		return orders;
	}

	private double getAmount(Park p, Vehicle vehicle) {
		if(vehicle.getType().equals("car")) {
			return calculateAmountByCar(p, vehicle);
		}
		if(vehicle.getType().equals("motorcycle")) {
			return calculateAmountByMotorCycle(p, vehicle);
		}
		if(vehicle.getType().equals("rv")) {
			return calculateAmountByRv(p, vehicle);
		}
		return -1.0;
	}
	private boolean isInState(Park p, String state) {
		String s = p.getLocationInfo().getAddress(); // need to parse state
		if(s.contains(state)) {
			return true;
		} else {
			return false;
		}
	}
	private double calculateAmountByCar(Park p, Vehicle vehicle) {
		if( this.isInState(p, vehicle.getState()) ){
			return p.getPaymentInfo().getCar().getIn();
		} else {
			return p.getPaymentInfo().getCar().getOut();
		}
	}
	private double calculateAmountByMotorCycle(Park p, Vehicle vehicle) {
		if( this.isInState(p, vehicle.getState()) ){
			return p.getPaymentInfo().getMotorcycle().getIn();
		} else {
			return p.getPaymentInfo().getMotorcycle().getOut();
		}
	}
	private double calculateAmountByRv(Park p, Vehicle vehicle) {
		if( this.isInState(p, vehicle.getState()) ){
			return p.getPaymentInfo().getRv().getIn();
		} else {
			return p.getPaymentInfo().getRv().getOut();
		}
	}

	private JsonArray getSimpleOrder(List<Admission> admissions) {
		JsonArray orders = new JsonArray();
		for(Admission a : admissions) {
			JsonObject order = new JsonObject();

			order.addProperty("oid", a.getOid());
			order.addProperty("pid", a.getPid());
			order.addProperty("date", ChangeDateFormat.toyyyyMMdd(a.getDate()));
			order.addProperty("type", a.getVehicle().getType());
			order.addProperty("amount", a.getAmount());

			orders.add(order);
		}
		return orders;
	}
    private boolean findAdmissionByPidAndVid(String pid, String vid){
	Iterator<Admission> admission = Admissions.listIterator();
	while(admission.hasNext()){
	    Admission a = admission.next();
	    if(a.getPid().equals(pid) && a.getVid().equals(vid)){
		return true;
	    }
	}
	return false;
    }

	private Admission findAdmissionByOid(String oid) {
		Iterator<Admission> admission = Admissions.listIterator();
		while(admission.hasNext()) {
			Admission a = admission.next();
			if(a.getOid().equals(oid)) {
				return a;
			}
		}
		return new NullAdmission();
	}
	private List<Admission> findAdmissionByVid(String vid){
		Iterator<Admission> admission = Admissions.listIterator();
		List<Admission> results = new ArrayList<Admission>();
		while(admission.hasNext()) {
			Admission a = admission.next();
			if(a.getVid().equals(vid)) {
				results.add(a);
			}
		}
		return results;
	}

	private List<Admission> findAdmissionByKeyword(String keyword){
	    keyword = keyword.toLowerCase();
		List<Admission> results = new ArrayList<Admission>();
		Iterator<Admission> ai = Admissions.listIterator();
		while(ai.hasNext()) {
			Admission a = ai.next();
			if(a.getVehicle().getPlate().toLowerCase().contains(keyword) ||
			   a.getVehicle().getState().toLowerCase().contains(keyword) ||
			   a.getVehicle().getType().toLowerCase().contains(keyword) ||
			   a.getVisitor().getEmail().toLowerCase().contains(keyword) ||
			   a.getVisitor().getName().toLowerCase().contains(keyword) ||
			   a.getVisitor().getPayment().getCard().toLowerCase().contains(keyword) ||
			   a.getVisitor().getPayment().getExpDate().toLowerCase().contains(keyword) ||
			   a.getVisitor().getPayment().getName().toLowerCase().contains(keyword)) {
				results.add(a);
			}
		}
		return results;
	}
}
