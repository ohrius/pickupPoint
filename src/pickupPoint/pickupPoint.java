package pickupPoint;

public class pickupPoint {
	String navn;
	String adresse;
	String by;
	String �pningstider;

	public pickupPoint(String navn, String adresse, String by, String �pningstider) {
		super();
		this.navn = navn;
		this.adresse = adresse;
		this.by = by;
		this.�pningstider = �pningstider;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s, �pningstider: %s", navn, adresse, by, �pningstider);
	}
}