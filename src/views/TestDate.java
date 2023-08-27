package views;

import java.awt.SystemColor;
import java.time.Instant;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

public class TestDate {

	public static void main(String[] args) {
		JFrame jframe = new JFrame("Teste");
		jframe.setBounds(0,  0, 600, 400);
		JPanel jpanelTest =  new JPanel();

		JDateChooser txtDataN = new JDateChooser();
		
		
		txtDataN = new JDateChooser();
		txtDataN.setBounds(560, 278, 285, 36);
		txtDataN.getCalendarButton().setIcon(new ImageIcon(RegistroHospede.class.getResource("/imagenes/icon-reservas.png")));
		txtDataN.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtDataN.setDateFormatString("yyyy-MM-dd");
		jpanelTest.add(txtDataN);
		

		Instant indt = txtDataN.getDate().toInstant();
		
		System.out.println(indt + " : " + txtDataN);
		
//		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		jframe.add(jpanelTest);
		
		jframe.pack();
		jframe.setVisible(true);
		
	}

}
