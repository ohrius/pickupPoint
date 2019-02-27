package pickupPoint;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class pickupFunctions {
	
	final private static String BRINGJSON = "https://api.bring.com/pickuppoint/api/pickuppoint/NO/postalCode/%s.json";
	private static pickupPoint pickupPoint;
	private static JTextArea JPickupPoints;
	private static JFrame frame;
	
	public static void lagGui() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			JFrame.setDefaultLookAndFeelDecorated(true);
		}

		frame = new JFrame("Vis nærliggende pickuppoints");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setResizable(false);

		JPanel JTop = new JPanel(new GridLayout(0, 3, 20, 10));
		JPanel JBottom = new JPanel(new GridLayout(0, 1));
		JLabel JLabelPostnummer = new JLabel("Skriv inn postnummer", SwingConstants.CENTER);
		JTextField JPostnummer = new JTextField(4);
		JButton JButtonOk = new JButton("Ok");

		JPickupPoints = new JTextArea(25, 40);
		JPickupPoints.setEditable(false);
		JPickupPoints.setWrapStyleWord(true);
		JPickupPoints.setLineWrap(true);
		JScrollPane JScroll = new JScrollPane(JPickupPoints, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JTop.add(JLabelPostnummer);
		JTop.add(JPostnummer);
		JTop.add(JButtonOk);
		JBottom.add(JScroll);

		frame.add(JTop);
		frame.add(JBottom);

		JButtonOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				visPickupPoints(JPostnummer.getText());
			}
		});

		frame.pack();
		frame.setVisible(true);
	}
	
	
	private static void visPickupPoints(String postnummer) {

		try {
			JPickupPoints.setText("");
			String JSONUri = String.format(BRINGJSON, postnummer);
			JSONObject obj = new JSONObject(IOUtils.toString(new URL(JSONUri), Charset.forName("UTF-8")));
			JSONArray arr = obj.getJSONArray("pickupPoint");
			for (int i = 0; i < arr.length(); i++) {
				pickupPoint = new pickupPoint(arr.getJSONObject(i).getString("name"),
						arr.getJSONObject(i).getString("address"), arr.getJSONObject(i).getString("city"),
						arr.getJSONObject(i).getString("openingHoursNorwegian"));
				JPickupPoints.append(pickupPoint.toString() + "\n\n");
				JPickupPoints.setCaretPosition(0);

			}
		} catch (JSONException | IOException e) {
			JPickupPoints.append(feilmelding());
			JPickupPoints.setCaretPosition(0);
			System.out.println(e);
		}

	}
	public static String feilmelding() {
		return "Det skjedde en feil.\nPostnummer kan ikke inneholde bokstaver og må ha en lengde på 4 siffer.";
	}
}
