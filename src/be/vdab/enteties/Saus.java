package be.vdab.enteties;

import java.util.HashSet;
import java.util.Set;

public class Saus {
private long nummer;
private String naam;
	private Set<String> ingredienten = new HashSet<>();

	public Saus(long nummer, String naam, Set<String> ingredienten) {
	this.nummer = nummer;
	this.naam = naam;
	this.ingredienten = ingredienten;
}
public Saus() {
}
public long getNummer() {
	return nummer;
}
public void setNummer(long nummer) {
	this.nummer = nummer;
}
public String getNaam() {
	return naam;
}
public void setNaam(String naam) {
	this.naam = naam;
}

	public Set<String> getIngredienten() {
	return ingredienten;
}

	public void setIngredienten(Set<String> ingredienten) {
	this.ingredienten = ingredienten;
}



}
