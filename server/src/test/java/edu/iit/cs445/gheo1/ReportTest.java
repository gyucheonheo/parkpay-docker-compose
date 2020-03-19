package edu.iit.cs445.gheo1;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.cs445.gheo1.boundary.ReportBoundary;
import edu.iit.cs445.gheo1.manager.ReportManager;

public class ReportTest {
	private ReportBoundary ri = new ReportManager();

  @Test
  public void create_admission_report(){
    JsonObject result = ri.createAdmissionReport();

    int admissionRid = result.get("rid").getAsInt();

    assertEquals("Admissions report", result.get("name").getAsString());
  }
  @Test
  public void create_revenue_report() {
    JsonObject result = ri.createRevenueReport();

    int revenueRid = result.get("rid").getAsInt();

    assertEquals("Revenue report",result.get("name").getAsString());
  }

  @Test
  public void report_manager_add_additional_information_for_admission_report() throws ParseException {
    JsonObject result = ri.addAdditionalInformationForAdmissionReport("", "");
    assertEquals("", result.get("start_date").getAsString());
    assertEquals("", result.get("end_date").getAsString());
    assertEquals(0, result.get("total_admissions").getAsInt());
  }

  @Test
  public void report_manager_add_additional_information_for_admission_report_start_date() throws ParseException {
    JsonObject result = ri.addAdditionalInformationForAdmissionReport("20181111", "");
    assertEquals("2018-11-11", result.get("start_date").getAsString());
    assertEquals("", result.get("end_date").getAsString());
    assertEquals(0, result.get("total_admissions").getAsInt());
  }

  @Test
  public void report_manager_add_additional_information_for_admission_report_end_date() throws ParseException {
    JsonObject result = ri.addAdditionalInformationForAdmissionReport("", "20191111");
    assertEquals("", result.get("start_date").getAsString());
    assertEquals("2019-11-11", result.get("end_date").getAsString());
    assertEquals(0, result.get("total_admissions").getAsInt());
  }

  @Test
  public void report_manager_add_additional_information_for_admission_report_both_date() throws ParseException {
    JsonObject result = ri.addAdditionalInformationForAdmissionReport("20181111", "20191111");
    assertEquals("2018-11-11", result.get("start_date").getAsString());
    assertEquals("2019-11-11", result.get("end_date").getAsString());
    assertEquals(0, result.get("total_admissions").getAsInt());
  }

  @Test
  public void report_manager_add_additional_information_for_revenue_report() throws ParseException {
    JsonObject result = ri.addAdditionalInformationForRevenueReport("", "");
    assertEquals("", result.get("start_date").getAsString());
    assertEquals("", result.get("end_date").getAsString());
    assertEquals(0, result.get("total_orders").getAsInt());
    assertEquals(0.0, result.get("total_revenue").getAsDouble(),0);
  }

  @Test
  public void report_manager_add_additional_information_for_revenue_report_start_date() throws ParseException {
    JsonObject result = ri.addAdditionalInformationForRevenueReport("20181111", "");
    assertEquals("2018-11-11", result.get("start_date").getAsString());
    assertEquals("", result.get("end_date").getAsString());
    assertEquals(0, result.get("total_orders").getAsInt());
    assertEquals(0.0, result.get("total_revenue").getAsDouble(),0);
  }

  @Test
  public void report_manager_add_additional_information_for_revenue_report_end_date() throws ParseException {
    JsonObject result = ri.addAdditionalInformationForRevenueReport("", "20191111");
    assertEquals("", result.get("start_date").getAsString());
    assertEquals("2019-11-11", result.get("end_date").getAsString());
    assertEquals(0, result.get("total_orders").getAsInt());
    assertEquals(0.0, result.get("total_revenue").getAsDouble(),0);
  }

  @Test
  public void report_manager_add_additional_information_for_revenue_report_both_date() throws ParseException {
    JsonObject result = ri.addAdditionalInformationForRevenueReport("20181111", "20191111");
    assertEquals("2018-11-11", result.get("start_date").getAsString());
    assertEquals("2019-11-11", result.get("end_date").getAsString());
    assertEquals(0, result.get("total_orders").getAsInt());
    assertEquals(0.0, result.get("total_revenue").getAsDouble(),0);
  }
}
