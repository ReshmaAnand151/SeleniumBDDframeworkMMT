package utility.core;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import utility.properties.Property;

public class EmailNotification {

	public static void main(String[] args) {
		sendEmail(Property.getProperty("Sendermailid"), "Test Report", "Regression report",
				Property.getProperty("TOReceipient"),
		"C:\\Users\\Admin\\eclipse-workspace\\seleniumframeworkproj\\Labvantgage\\output\\report.html");
	}
	public static void sendEmail(String fromEmail, String subject, String body, String toEmail, String attachment) {
		try {
			Properties email = new Properties();
			email.put("mail.smtp.host", "smtp.nyumc.org");
			email.put("mail.smtp.port", "25");
			
			Session session = Session.getInstance(email, null);
			
			Message message = new MimeMessage(session);
			//set the from address
			message.setFrom(new InternetAddress("reshmaanand151@gmail.com"));
			//set recepient address
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("reshmaanand151@gmal.com"));
			
			//Add subject link
			message.setSubject(subject);
			//create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();
			//set body of email
			messageBodyPart1.setText(body);
			//create anoher object to add another conetnt
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			
			//mention the file which you want to send
			String filename = attachment;
			
			//create data source and pass the filename
			DataSource source = new FileDataSource(filename);
			
			//set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));
			
			//set the file
			messageBodyPart2.setFileName(filename);
			
			//create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();
			
			//add body part 1
			multipart.addBodyPart(messageBodyPart2);
			
			//add body part 2
			multipart.addBodyPart(messageBodyPart1);
			
			//set the content
			message.setContent(multipart);
			
			//finally send the mail
			Transport.send(message);
			
			System.out.println("=======Email Sent========");
			
			System.out.println("Email sent successfully");
		}catch(Exception e) {
			e.printStackTrace();
		}
}
}
