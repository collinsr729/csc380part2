package csc380;

import java.util.ArrayList;
import java.util.Calendar;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class Main {
	
	public static void main(String[] args) throws AddressException, MessagingException {
		
		myFrame frame = new myFrame();
		frame.openFrame();
		frame.setAutoRequestFocus(true);
	}// end main
}// end Main