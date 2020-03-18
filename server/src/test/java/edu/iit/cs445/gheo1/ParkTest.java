package edu.iit.cs445.gheo1;

import java.util.List;

import static org.junit.Assert.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import edu.iit.cs445.gheo1.boundary.ParkBoundary;
import edu.iit.cs445.gheo1.manager.ParkManager;
import edu.iit.cs445.gheo1.park.Park;
import edu.iit.cs445.gheo1.park.locationinfo.Geo;
import edu.iit.cs445.gheo1.park.locationinfo.LocationInfo;
import edu.iit.cs445.gheo1.park.payment.CarPayment;
import edu.iit.cs445.gheo1.park.payment.MotorCyclePayment;
import edu.iit.cs445.gheo1.park.payment.PaymentInfo;
import edu.iit.cs445.gheo1.park.payment.RvPayment;

public class ParkTest{
  	private ParkBoundary pi = new ParkManager();
    private static String pid;


  @Test
  public void create_park(){
    Geo geo = new Geo(1.0, 1.0);
    LocationInfo locationInfo = new LocationInfo("testName", "testRegion", "testAddress", "testPhone", "testWeb", geo);
    CarPayment car = new CarPayment(1.0, 1.0);
    MotorCyclePayment motorcycle = new MotorCyclePayment(1.0, 1.0);
    RvPayment rv = new RvPayment(1.0, 1.0);
    PaymentInfo paymentInfo = new PaymentInfo(car, motorcycle, rv);
    assertTrue( pi.createPark(locationInfo, paymentInfo) instanceof String );
  }

  @Before
  public void setup(){
    Geo geo = new Geo(1.0, 1.0);
    LocationInfo locationInfo = new LocationInfo("testName", "testRegion", "testAddress", "testPhone", "testWeb", geo);
    CarPayment car = new CarPayment(1.0, 1.0);
    MotorCyclePayment motorcycle = new MotorCyclePayment(1.0, 1.0);
    RvPayment rv = new RvPayment(1.0, 1.0);
    PaymentInfo paymentInfo = new PaymentInfo(car, motorcycle, rv);
    ParkTest.pid = pi.createPark(locationInfo, paymentInfo);
  }

  @Test
  public void get_park_by_pid(){
    Park p = pi.getParkByPid(ParkTest.pid);
    assertEquals("testName", p.getLocationInfo().getName());
    assertEquals("testRegion", p.getLocationInfo().getRegion());
    assertEquals("testAddress", p.getLocationInfo().getAddress());
    assertEquals("testPhone", p.getLocationInfo().getPhone());
    assertEquals("testWeb", p.getLocationInfo().getWeb());

    assertEquals(1.0, p.getLocationInfo().getGeo().getLat(), 0);
		assertEquals(1.0, p.getLocationInfo().getGeo().getLng(), 0);

		assertEquals(1.0, p.getPaymentInfo().getCar().getIn(), 0);
		assertEquals(1.0, p.getPaymentInfo().getCar().getOut(), 0);

		assertEquals(1.0, p.getPaymentInfo().getMotorcycle().getIn(), 0);
		assertEquals(1.0, p.getPaymentInfo().getMotorcycle().getOut(), 0);

		assertEquals(1.0, p.getPaymentInfo().getRv().getIn(), 0);
		assertEquals(1.0, p.getPaymentInfo().getRv().getOut(), 0);
  }

  @Test
  public void update_a_park(){
    CarPayment car = new CarPayment(1.0, 1.0);
    MotorCyclePayment motorcycle = new MotorCyclePayment(1.0, 1.0);
    RvPayment rv = new RvPayment(1.0, 1.0);
    PaymentInfo paymentInfo = new PaymentInfo(car, motorcycle, rv);
    Geo geo = new Geo(1.0, 1.0);
    LocationInfo locationInfo = new LocationInfo("UpdateName", "UpdateRegion", "UpdateAddress", "UpdatePhone", "UpdateWeb", geo);
    pi.updateParkByPid(ParkTest.pid, locationInfo, paymentInfo);
    Park p = pi.getParkByPid(ParkTest.pid);
    assertEquals("UpdateName", p.getLocationInfo().getName());
    assertEquals("UpdateRegion", p.getLocationInfo().getRegion());
    assertEquals("UpdateAddress", p.getLocationInfo().getAddress());
    assertEquals("UpdatePhone", p.getLocationInfo().getPhone());
    assertEquals("UpdateWeb", p.getLocationInfo().getWeb());
  }

  @Test
	public void park_manager_get_Park_By_Pid_As_JsonObject() {
	    JsonObject res = pi.getParkByPidAsJsonObject(ParkTest.pid);

      assertEquals(ParkTest.pid, res.get("pid").getAsString());

      assertEquals("testName", res.get("location_info").getAsJsonObject().get("name").getAsString());
  		assertEquals("testRegion", res.get("location_info").getAsJsonObject().get("region").getAsString());
  		assertEquals("testAddress", res.get("location_info").getAsJsonObject().get("address").getAsString());
  		assertEquals("testPhone", res.get("location_info").getAsJsonObject().get("phone").getAsString());
  		assertEquals("testWeb", res.get("location_info").getAsJsonObject().get("web").getAsString());

  		assertEquals(1.0, res.get("location_info").getAsJsonObject().get("geo").getAsJsonObject().get("lat").getAsDouble(),0);
  		assertEquals(1.0, res.get("location_info").getAsJsonObject().get("geo").getAsJsonObject().get("lng").getAsDouble(),0);

  		assertEquals(1.0, res.get("payment_info").getAsJsonObject().get("car").getAsJsonArray().get(0).getAsDouble(), 0);
  		assertEquals(1.0, res.get("payment_info").getAsJsonObject().get("car").getAsJsonArray().get(1).getAsDouble(), 0);

  		assertEquals(1.0, res.get("payment_info").getAsJsonObject().get("motorcycle").getAsJsonArray().get(0).getAsDouble(), 0);
  		assertEquals(1.0, res.get("payment_info").getAsJsonObject().get("motorcycle").getAsJsonArray().get(1).getAsDouble(), 0);

  		assertEquals(1.0, res.get("payment_info").getAsJsonObject().get("rv").getAsJsonArray().get(0).getAsDouble(), 0);
  		assertEquals(1.0, res.get("payment_info").getAsJsonObject().get("rv").getAsJsonArray().get(1).getAsDouble(), 0);
  	}

    @Test
    public void park_manager_get_Park_By_Keyword() {
      String keywordForFailure = "Failure";
      String keywordForSuccess ="test";
      JsonArray result = pi.getParkByKeyword(keywordForFailure);

      assertFalse(result.size() > 0);

      result = pi.getParkByKeyword(keywordForSuccess);

      assertTrue(result.size() == 1);
    }

    @Test
    public void park_manager_get_Parks_For_AdmissionReport() {
      JsonArray result = pi.getParksForAdmissionReport();

      assertEquals(ParkTest.pid, result.get(0).getAsJsonObject().get("pid").getAsString());
      assertEquals("testName", result.get(0).getAsJsonObject().get("name").getAsString());

    }
    @Test
  	public void park_manager_delete_park() {
  		pi.deleteParkByPid(ParkTest.pid);

  		assertTrue(pi.getParks().size() == 0);
  	}

  @After
  public void cleanup(){
    List<Park> Parks = pi.getParks();
    Parks.clear();
  }


}
