package edu.iit.cs445.gheo1.park.payment;

public class Payment {

	private String card;
	private String name;
	private String expDate;
	private int zipcode;
	public Payment() {

	}
	public Payment(String card, String name, String expDate, int zipcode) {
		if(card.length() == 16) {
		this.card = "xxxxxxxxxxxx"+card.substring(12, 16);
		} else {
			this.card = "xxxxxxxxxxx"+card.substring(11, 15);
		}
		this.name = name;
		this.expDate = expDate;
		this.zipcode = zipcode;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

}
