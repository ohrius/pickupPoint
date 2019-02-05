package pickupPoint;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
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
 * Program som tar inn ett gitt postnummer og viser alle pickup-points i nærheten.
 * hvert pickup-point vil vise navn, adresse, by og åpningstider.
 * Bring endpoint: https://api.bring.com/pickuppoint/api/pickuppoint/NO/postalCode/{POSTNUMMER}.json
 */
public class pickupMain {
	/*	 
	 */
	
	//Variabler for å lage JSON-url
	final private static String JSONSTART = "https://api.bring.com/pickuppoint/api/pickuppoint/NO/postalCode/";
	final private static String JSONSLUTT = ".json";
	private static int postnummer;

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
		    	    // Hent postnummer fra bruker
		        	postnummer = Integer.parseInt(JPostnummer.getText());
		        	// Sjekk om postnummer har gyldig lengde
		        	if(String.valueOf(postnummer).length() == 4) {
		        	// Sett sammen JSON-URL
		        	String JSONUri = JSONSTART+postnummer+JSONSLUTT;
		        	// Behandler JSON-data
					JSONObject obj = new JSONObject(IOUtils.toString(new URL(JSONUri), Charset.forName("UTF-8")));
		        	JSONArray arr = obj.getJSONArray("pickupPoint");
		        	for(int i =0; i<arr.length();i++) {
		        		String navn = arr.getJSONObject(i).getString("name");
		        		String adresse = arr.getJSONObject(i).getString("address");
		        		String by = arr.getJSONObject(i).getString("city");
		        		String åpningstider = arr.getJSONObject(i).getString("openingHoursNorwegian");
		        		// Print resultat
			        	System.out.println(navn+ ", " + adresse + ", " + by + ", " + åpningstider + "\n");
		        	}
		        	}
		        	else {
		        		System.out.println("Postnummer må være 4 siffer!");
		        	}
				} catch (JSONException e1) {
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}catch (NumberFormatException ex) {
		            System.out.println("Input må være et nummer!");
		        }
	    	    
	        }
	    });
	    
		frame.pack();
	    frame.setVisible(true);
		//Slutt GUI
	    
	    
	}

}
