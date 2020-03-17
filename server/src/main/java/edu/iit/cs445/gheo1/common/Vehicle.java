package edu.iit.cs445.gheo1.common;

public class Vehicle {
	private String type;
	private String state;
	private String plate;
	
	public Vehicle() {
		
	}
	public Vehicle(String type, String state, String plate) {
		this.type = type;
		this.state = state;
		this.plate = plate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	
	
}
