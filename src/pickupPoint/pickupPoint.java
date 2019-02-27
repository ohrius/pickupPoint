package pickupPoint;

public class pickupPoint {
	String navn;
	String adresse;
	String by;
	String Âpningstider;

	public pickupPoint(String navn, String adresse, String by, String Âpningstider) {
		super();
		this.navn = navn;
		this.adresse = adresse;
		this.by = by;
		this.Âpningstider = Âpningstider;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s,\n≈pningstider: %s", navn, adresse, by, Âpningstider);
	}
}