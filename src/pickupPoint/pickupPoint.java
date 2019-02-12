package pickupPoint;

public class pickupPoint {
	String navn;
	String adresse;
	String by;
	String åpningstider;

	public pickupPoint(String navn, String adresse, String by, String åpningstider) {
		super();
		this.navn = navn;
		this.adresse = adresse;
		this.by = by;
		this.åpningstider = åpningstider;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s, Åpningstider: %s", navn, adresse, by, åpningstider);
	}
}