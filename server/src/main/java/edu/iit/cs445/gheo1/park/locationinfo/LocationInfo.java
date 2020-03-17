package edu.iit.cs445.gheo1.park.locationinfo;

public class LocationInfo {
	private String name;
	private String region;
	private String address;
	private String phone;
	private String web;
	private Geo geo;
	
	public LocationInfo() {
	
	}
	public LocationInfo(String name, String region, String address, String phone, String web, Geo geo) {
		this.name = name;
		this.region = region;
		this.address = address;
		this.phone = phone;
		this.web = web;
		this.geo = geo;
	}
	
	public boolean isNil() {
		return false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public Geo getGeo() {
		return geo;
	}
	public void setGeo(Geo geo) {
		this.geo = geo;
	}
	
	
}
