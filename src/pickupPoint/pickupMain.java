package pickupPoint;


/**
 * @author Morten Ohren
 *
 *         Program som tar inn ett gitt postnummer og viser alle pickup-points i
 *         n�rheten. Hvert pickup-point vil vise navn, adresse, by og
 *         �pningstider. Bring endpoint:
 *         https://api.bring.com/pickuppoint/api/pickuppoint/NO/postalCode/{POSTNUMMER}.json
 */
public class pickupMain {


	public static void main(String[] args) {
		pickupFunctions.lagGui();
	}



}// end Main
