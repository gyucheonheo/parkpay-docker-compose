package edu.iit.cs445.gheo1;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.admission.Admission;
import edu.iit.cs445.gheo1.boundary.AdmissionBoundary;
import edu.iit.cs445.gheo1.boundary.ParkBoundary;
import edu.iit.cs445.gheo1.common.ChangeDateFormat;
import edu.iit.cs445.gheo1.common.Vehicle;
import edu.iit.cs445.gheo1.manager.OrderManager;
import edu.iit.cs445.gheo1.manager.ParkManager;
import edu.iit.cs445.gheo1.park.Park;
import edu.iit.cs445.gheo1.park.locationinfo.Geo;
import edu.iit.cs445.gheo1.park.locationinfo.LocationInfo;
import edu.iit.cs445.gheo1.park.payment.CarPayment;
import edu.iit.cs445.gheo1.park.payment.MotorCyclePayment;
import edu.iit.cs445.gheo1.park.payment.Payment;
import edu.iit.cs445.gheo1.park.payment.PaymentInfo;
import edu.iit.cs445.gheo1.park.payment.RvPayment;
import edu.iit.cs445.gheo1.visitor.Visitor;

public class OrderTest {
  private static AdmissionBoundary ai = new OrderManager();
  private ParkBoundary pi = new ParkManager();
  private static String pid;
  private static String oid;
  private static Park p;

  @Before
  public void setup(){
    Geo geo = new Geo(1.0, 1.0);
    LocationInfo locationInfo = new LocationInfo("testName", "testRegion", "IL", "testPhone", "testWeb", geo);
    CarPayment car = new CarPayment(1.0, 2.0);
    MotorCyclePayment motorcycle = new MotorCyclePayment(3.0, 4.0);
    RvPayment rv = new RvPayment(5.0, 6.0);
    PaymentInfo paymentInfo = new PaymentInfo(car, motorcycle, rv);
    pid = pi.createPark(locationInfo, paymentInfo);
    p = pi.getParkByPid(OrderTest.pid);

    ai.getAdmissions().clear();

  }
  @Test
  public void order_manager_create_order_in_state_car() {
  String vid = "vid";

  Vehicle vehicle = new Vehicle("car", "IL", "testPlate");
  Payment payment = new Payment("0000000000000000", "tester", "11/11",60616);
  Visitor visitor = new Visitor("tester", "test@test.test", payment);

  Admission result = ai.createOrder(OrderTest.p, vid, vehicle, visitor);

  assertEquals(1.0, result.getAmount(), 0);
  }

  @Test
  public void order_manager_get_order_by_vid() {
    String vid = "vid";

    Vehicle vehicle = new Vehicle("car", "IL", "testPlate");
    Payment payment = new Payment("0000000000000000", "tester", "11/11",60616);
    Visitor visitor = new Visitor("tester", "test@test.test", payment);

    ai.createOrder(OrderTest.p, vid, vehicle, visitor);
    List<Admission> result = ai.getOrderByVid(vid);
    assertEquals(1.0, result.get(0).getAmount(), 0);
    assertEquals("car", result.get(0).getVehicle().getType());
    assertEquals("IL", result.get(0).getVehicle().getState());
    assertEquals("testPlate", result.get(0).getVehicle().getPlate());
    assertEquals("tester", result.get(0).getVisitor().getName());
    assertEquals("test@test.test", result.get(0).getVisitor().getEmail());
  }

  @Test
  public void order_manager_create_order_out_state_car() {
    String vid = "vid1";

    Vehicle vehicle = new Vehicle("car", "NY", "testPlate");
    Payment payment = new Payment("0000000000000000", "tester", "11/11",60616);
    Visitor visitor = new Visitor("tester1", "test@test.test", payment);

    Admission result = ai.createOrder(OrderTest.p, vid, vehicle, visitor);

    assertEquals(2.0, result.getAmount(), 0);
  }

  @Test
  public void order_manager_create_order_in_state_motor() {
    String vid = "vid2";

    Vehicle vehicle = new Vehicle("motorcycle", "IL", "testPlate");
    Payment payment = new Payment("0000000000000000", "tester", "11/11",60616);
    Visitor visitor = new Visitor("tester2", "test@test.test", payment);

    Admission result = ai.createOrder(OrderTest.p, vid, vehicle, visitor);

    assertEquals(3.0, result.getAmount(), 0.0);
  }

  @Test
  public void order_manager_create_order_out_state_motor() {
    String vid = "vid3";

    Vehicle vehicle = new Vehicle("motorcycle", "NY", "testPlate");
    Payment payment = new Payment("0000000000000000", "tester", "11/11",60616);
    Visitor visitor = new Visitor("tester3", "test@test.test", payment);

    Admission result = ai.createOrder(OrderTest.p, vid, vehicle, visitor);

    assertEquals(4.0, result.getAmount(), 0.0);
  }

  @Test
  public void order_manager_create_order_in_state_rv() {
    String vid = "vid4";

    Vehicle vehicle = new Vehicle("rv", "IL", "testPlate");
    Payment payment = new Payment("0000000000000000", "tester", "11/11",60616);
    Visitor visitor = new Visitor("tester4", "test@test.test", payment);

    Admission result = ai.createOrder(OrderTest.p, vid, vehicle, visitor);

    assertEquals(5.0, result.getAmount(), 0.0);
  }

  @Test
  public void order_manager_create_order_out_state_rv() {
    String vid = "vid5";

    Vehicle vehicle = new Vehicle("rv", "WA", "testPlate");
    Payment payment = new Payment("0000000000000000", "tester", "11/11",60616);
    Visitor visitor = new Visitor("tester5", "test@test.test", payment);

    Admission result = ai.createOrder(OrderTest.p, vid, vehicle, visitor);

    assertEquals(6.0, result.getAmount(), 0.0);
  }

  @Test
	public void order_manager_get_OrderBy_Oid() {

    String vid = "vid6";

    Vehicle vehicle = new Vehicle("car", "NY", "testPlate");
    Payment payment = new Payment("0000000000000000", "tester", "11/11",60616);
    Visitor visitor = new Visitor("tester6", "test@test.test", payment);

    Admission order = ai.createOrder(OrderTest.p, vid, vehicle, visitor);

		JsonObject result = ai.getOrderByOid(order.getOid());
		String today = ChangeDateFormat.toyyyyMMdd(new Date());
		assertEquals(2.0, result.get("amount").getAsDouble(), 0);
		assertEquals(today, result.get("date").getAsString());

		assertEquals("NY", result.get("vehicle").getAsJsonObject().get("state").getAsString());
		assertEquals("testPlate", result.get("vehicle").getAsJsonObject().get("plate").getAsString());
		assertEquals("car", result.get("vehicle").getAsJsonObject().get("type").getAsString());

		assertEquals("tester", result.get("visitor").getAsJsonObject().get("name").getAsString());
		assertEquals("test@test.test", result.get("visitor").getAsJsonObject().get("email").getAsString());

		assertEquals("xxxxxxxxxxxx0000", result.get("visitor").getAsJsonObject().get("payment_info").getAsJsonObject().get("card").getAsString());
		assertEquals("tester", result.get("visitor").getAsJsonObject().get("payment_info").getAsJsonObject().get("name_on_card").getAsString());
		assertEquals("11/11", result.get("visitor").getAsJsonObject().get("payment_info").getAsJsonObject().get("expiration_date").getAsString());
		assertEquals("60616", result.get("visitor").getAsJsonObject().get("payment_info").getAsJsonObject().get("zip").getAsString());
	}

  @Test
  public void order_manager_get_order_by_keyword() {
    String keyword="tester2";
    String vid = "vid2";

    Vehicle vehicle = new Vehicle("motorcycle", "IL", "testPlate");
    Payment payment = new Payment("0000000000000000", "tester", "11/11",60616);
    Visitor visitor = new Visitor("tester2", "test@test.test", payment);

    ai.createOrder(OrderTest.p, vid, vehicle, visitor);
    JsonArray result = ai.getOrderByKeyword(keyword);
    assertEquals("motorcycle", result.get(0).getAsJsonObject().get("type").getAsString());
    assertEquals(3.0, result.get(0).getAsJsonObject().get("amount").getAsDouble(),0);
  }

	@Test
	public void order_manager_get_admissions_between_no_date_provided() throws ParseException {
	}


}
