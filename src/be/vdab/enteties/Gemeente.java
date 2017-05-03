package be.vdab.enteties;

public class Gemeente {

	private String naam;
	private int postcode;

	public Gemeente() {
	}

	public Gemeente(String naam, int postcode) {
		this.naam = naam;
		this.postcode = postcode;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

}
