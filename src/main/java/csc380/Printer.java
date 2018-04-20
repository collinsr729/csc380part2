package csc380;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Printer 
{

	  
		public void sendEmail(ArrayList<String> addresses, int loadNumber) throws AddressException, MessagingException
		{
			
			Properties mailServerProperties;
			 Session getMailSession;
			 MimeMessage generateMailMessage;
			 String emailBody;

			mailServerProperties = System.getProperties();
			mailServerProperties.put("mail.smtp.port", "587");
			mailServerProperties.put("mail.smtp.auth", "true");
			mailServerProperties.put("mail.smtp.starttls.enable", "true");
	 
			getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("csc380receiptreceiver@gmail.com"));
			generateMailMessage.setSubject("Receipt Details for Load " + loadNumber);
			emailBody = formatReceiptForEmail(addresses);
			generateMailMessage.setContent(emailBody, "text/html");
	 
			Transport transport = getMailSession.getTransport("smtp");
	 
			transport.connect("smtp.gmail.com", "csc380receiptreceiver@gmail.com", "!1@2#3$4abc");
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		}
		
		public String formatReceiptForEmail(ArrayList<String> addresses)
		{
			String receipt = "";
			
			for(int i = 0; i < addresses.size(); i++)
			{
				receipt += "Order no. " + (i + 1) + ": " + addresses.get(i) + "<br>";
			}
			
			return receipt;
		}

}
