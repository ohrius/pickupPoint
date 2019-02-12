package pickupPoint;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.commons.io.IOUtils;
import org.json.*;

/**
 * @author Morten Ohren
 *
 *         Program som tar inn ett gitt postnummer og viser alle pickup-points i
 *         nærheten. hvert pickup-point vil vise navn, adresse, by og
 *         åpningstider. Bring endpoint:
 *         https://api.bring.com/pickuppoint/api/pickuppoint/NO/postalCode/{POSTNUMMER}.json
 */
public class pickupMain {

	final private static String BRINGJSON = "https://api.bring.com/pickuppoint/api/pickuppoint/NO/postalCode/%s.json";
	private static String postnummer;

	public static void main(String[] args) {

		// GUI
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Postnummer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(1, 3, 20, 20));

		JLabel JLabelPostnummer = new JLabel("Skriv inn postnummer", SwingConstants.CENTER);
		JTextField JPostnummer = new JTextField(4);
		JButton JButtonOk = new JButton("Ok");

		frame.add(JLabelPostnummer);
		frame.add(JPostnummer);
		frame.add(JButtonOk);

		JButtonOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					postnummer = JPostnummer.getText();
					if (postnummer.length() == 4) {
						String JSONUri = String.format(BRINGJSON, postnummer);
						JSONObject obj = new JSONObject(IOUtils.toString(new URL(JSONUri), Charset.forName("UTF-8")));
						JSONArray arr = obj.getJSONArray("pickupPoint");
						for (int i = 0; i < arr.length(); i++) {
							String navn = arr.getJSONObject(i).getString("name");
							String adresse = arr.getJSONObject(i).getString("address");
							String by = arr.getJSONObject(i).getString("city");
							String åpningstider = arr.getJSONObject(i).getString("openingHoursNorwegian");
							System.out.println(navn + ", " + adresse + ", " + by + ", " + åpningstider + "\n");
						}
					} else {
						System.out.println("Postnummer må være 4 siffer!");
					}
				} catch (JSONException | IOException e1) {
					e1.printStackTrace();
				} catch (NumberFormatException ex) {
					System.out.println("Input må være et nummer!");
				}

			}
		});

		frame.pack();
		frame.setVisible(true);
		// Slutt GUI

	}

}
