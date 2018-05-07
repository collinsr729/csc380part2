package csc380;

//----------------------------------------------------------------------------------
// download for java mail api with instructions https://javaee.github.io/javamail/
//----------------------------------------------------------------------------------7

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Email 
{
	private Properties mailServerProperties;
	private Session getMailSession;
	private MimeMessage generateMailMessage;
	private String emailBody;
	
	public Email()
	{
		
	}
	  
	public void sendEmail(ArrayList<String> addresses, Load orders, int loadNumber) throws AddressException, MessagingException
	{
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("csc380receiptsender@gmail.com")); //password to receipt receiver is
		generateMailMessage.setSubject("Receipt Details for Load " + loadNumber);											// !1@2#3$4abc
		
		emailBody = formatReceiptForEmail(addresses, orders);
		generateMailMessage.setContent(emailBody, "text/html");
 
		Transport transport = getMailSession.getTransport("smtp");
 
		transport.connect("smtp.gmail.com", "csc380receiptsender@gmail.com", "!1@2#3$4abc");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}

	public String formatReceiptForEmail(ArrayList<String> sortedAddresses, Load orders)
	{
		String receipt = "";
		
		for(int i = 0; i < sortedAddresses.size(); i++)
		{
			receipt += "<span style=\"font-weight:bold\">Delivery " + (i + 1) + "- " + sortedAddresses.get(i) + "</span><br>";
			receipt += "<i><font color = red><span style=\"font-weight:bold\"><pre>\tDetails: \n</pre></span></font></i>";
			for (Order o : orders.getOrders()) {
				if(o.getAddress() == sortedAddresses.get(i))
					receipt += "<pre>" + o.getName() + "\n</pre>";
					receipt += "<pre>" + o.getTelephoneNumber() + "\n</pre>";
					for(Item it : o.getItems()) {
						receipt += "<pre>\t\t"+ it.getFoodName() + "\n</pre><br>";
					}
			}
		}
						
		return receipt;
	}

}
