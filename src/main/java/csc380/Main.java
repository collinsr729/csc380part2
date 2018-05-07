package csc380;

import java.util.Calendar;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class Main {

	static Calendar now;
	
	public static void main(String[] args) throws AddressException, MessagingException {
		
		myFrame frame = new myFrame();
		frame.openFrame();
		Map map = new Map();
		frame.setAutoRequestFocus(true);
		
		InformationCollector data = frame.getData();
		
		if(frame.loadComplete)
		{
			System.out.println("Entered");
			map.calculateRoute(data.getLoad(data.getAllLoads().size() - 1).getAddresses());
			System.out.println("Success");
		}
	}// end main
}// end Main