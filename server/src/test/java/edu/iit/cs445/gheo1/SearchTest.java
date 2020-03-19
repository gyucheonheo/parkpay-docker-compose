package edu.iit.cs445.gheo1;

import java.text.ParseException;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.boundary.SearchBoundary;
import edu.iit.cs445.gheo1.boundary.AdmissionBoundary;
import edu.iit.cs445.gheo1.boundary.CommentBoundary;
import edu.iit.cs445.gheo1.boundary.ParkBoundary;
import edu.iit.cs445.gheo1.boundary.ReportBoundary;

import edu.iit.cs445.gheo1.park.Park;
import edu.iit.cs445.gheo1.park.locationinfo.Geo;
import edu.iit.cs445.gheo1.park.locationinfo.LocationInfo;
import edu.iit.cs445.gheo1.park.payment.Payment;
import edu.iit.cs445.gheo1.park.payment.PaymentInfo;
import edu.iit.cs445.gheo1.park.payment.CarPayment;
import edu.iit.cs445.gheo1.park.payment.MotorCyclePayment;
import edu.iit.cs445.gheo1.park.payment.PaymentInfo;
import edu.iit.cs445.gheo1.park.payment.RvPayment;
import edu.iit.cs445.gheo1.common.Vehicle;
import edu.iit.cs445.gheo1.visitor.Visitor;

import edu.iit.cs445.gheo1.manager.ParkManager;
import edu.iit.cs445.gheo1.manager.OrderManager;
import edu.iit.cs445.gheo1.manager.CommentManager;
import edu.iit.cs445.gheo1.manager.SearchManager;


public class SearchTest{
  ParkBoundary pi = new ParkManager();
  CommentBoundary ci = new CommentManager();
  AdmissionBoundary oi = new OrderManager();
  SearchBoundary si = new SearchManager();

  @Before
  public void setup(){
    pi.getParks().clear();
    ci.getComments().clear();
    oi.getAdmissions().clear();

    Geo geo = new Geo(1.0, 1.0);
    LocationInfo locationInfo = new LocationInfo("testName", "testRegion", "IL", "testPhone", "testWeb", geo);
    CarPayment car = new CarPayment(1.0, 1.0);
    MotorCyclePayment motorcycle = new MotorCyclePayment(1.0, 1.0);
    RvPayment rv = new RvPayment(1.0, 1.0);
    PaymentInfo paymentInfo = new PaymentInfo(car, motorcycle, rv);
    String pid = pi.createPark(locationInfo, paymentInfo);
    Park p = pi.getParkByPid(pid);
    String vid = "vid";

    Vehicle vehicle = new Vehicle("car", "IL", "testPlate");
    Payment payment = new Payment("0000000000000000", "tester", "11/11",60616);
    Visitor visitor = new Visitor("tester", "test@test.test", payment);

    oi.createOrder(p, vid, vehicle, visitor);

  }

  @Test
  public void search_by_keyword(){
    JsonObject result = si.searchByKeyword("IL");
    assertEquals("testName", result.get("parks").getAsJsonArray().get(0).getAsJsonObject().get("location_info").getAsJsonObject().get("name").getAsString());
    assertEquals("testRegion", result.get("parks").getAsJsonArray().get(0).getAsJsonObject().get("location_info").getAsJsonObject().get("region").getAsString());
    assertEquals("IL", result.get("parks").getAsJsonArray().get(0).getAsJsonObject().get("location_info").getAsJsonObject().get("address").getAsString());
    assertEquals("testPhone", result.get("parks").getAsJsonArray().get(0).getAsJsonObject().get("location_info").getAsJsonObject().get("phone").getAsString());
    assertEquals("testWeb", result.get("parks").getAsJsonArray().get(0).getAsJsonObject().get("location_info").getAsJsonObject().get("web").getAsString());
  }

  @Test
  public void searchByDate()  throws ParseException  {
    JsonObject result = si.searchByDate("20161010", "20191020");

    assertEquals("Admissions report", result.get("admission_report").getAsJsonObject().get("name").getAsString());
    assertEquals("2016-10-10", result.get("admission_report").getAsJsonObject().get("start_date").getAsString());
    assertEquals("2019-10-20", result.get("admission_report").getAsJsonObject().get("end_date").getAsString());
    assertEquals("testName", result.get("admission_report").getAsJsonObject().get("detail_by_park").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString());

    assertEquals("Revenue report", result.get("revenue_report").getAsJsonObject().get("name").getAsString());
    assertEquals("2016-10-10", result.get("revenue_report").getAsJsonObject().get("start_date").getAsString());
    assertEquals("2019-10-20", result.get("revenue_report").getAsJsonObject().get("end_date").getAsString());
    assertEquals("testName", result.get("revenue_report").getAsJsonObject().get("detail_by_park").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString());
  }
}
