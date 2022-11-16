package com.ezio.common;

import java.util.Properties;

/*import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
*/

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




public class Mail {
	private static final int BUFFER_SIZE = 4096;

	public int sendMail(String subject, String mail,String clmessage, String name,String senderMail) {

		final String username = "dushyantlilhare01@gmail.com";
		final String password = "9552160516";

		System.out.println("subject " + subject);
		System.out.println("mail " + mail);
		System.out.println("clmessage " + clmessage);
		System.out.println("name " + name);

		Properties props = new Properties();
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");// 465
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			// Session s = Session.getInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("dushyantlilhare01@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
			System.out.println("mail " + mail);
			message.setSubject(subject);
			//message.setSubject("New IB Order Received #"+ orderid);
			/* System.out.println("user_id "+user_id); */
			/*
			 * String
			 * link="<a href='http://35.200.142.134/DentaLinks/admin/verificationcheck?user_id="
			 * +user_id+"'>Click Here For Verification</a>";
			 * System.out.println("link "+link);
			 */
			message.setContent("Name:- " + name + "<br>Email:- " + senderMail +"<br>Message:- " + clmessage +"", "text/html");

			Transport.send(message);
			/* System.out.println("user_id"+user_id); */
			System.out.println("Done");
			// userName, mobile, area, society, flatNumber, service
			// ,pickupTimeSlot, deliveryTimeSlot,
			// numberOfCloths, status, timestamp
			/*
			 * public static int sendMailForResume(CareerInquiryBean bean) {
			 * 
			 * final String username = "example@domain.com"; final String password =
			 * "abcd123"; Properties props = new Properties(); props.put("mail.smtp.host",
			 * "smtp.gmail.com"); props.put("mail.smtp.socketFactory.port", "465");
			 * props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			 * props.put("mail.smtp.auth", "true"); props.put("mail.smtp.port", "465");
			 * 
			 * Session session = Session.getDefaultInstance(props, new
			 * javax.mail.Authenticator() { protected PasswordAuthentication
			 * getPasswordAuthentication() { return new
			 * PasswordAuthentication(username,password); } });
			 * 
			 * try {
			 * 
			 * Message message = new MimeMessage(session); message.setFrom(new
			 * InternetAddress("demotech155@gmail.com"));
			 * message.setRecipients(Message.RecipientType.TO,
			 * InternetAddress.parse("demotech155@gmail.com"));
			 * message.setSubject("Resume: "+bean.getFname()+" "+bean.getLname()); BodyPart
			 * messageBodyPart1 = new MimeBodyPart(); messageBodyPart1.
			 * setContent("<p style='font-weight:bold;font-size:large;color:#0074D9'>Resume Inquiry</p><P style='font-size:14px;color:#3D9970'>Name: "
			 * +bean.getFname()+" "+bean.getLname()
			 * +"</p><P style='color:#001f3f'>Qualification: "+bean.getQualification()
			 * +"</p><p style='color:#001f3f'>Experience: "+bean.getExperience()
			 * +"</p><p style='color:#001f3f'>Message: "+bean.getMessage()
			 * +"</p><p style='color:#001f3f'>Email ID: "+bean.getEmail()
			 * +"</p><p style='color:#001f3f'>Phone: "+bean.getPhone()+"</p>","text/html");
			 * 
			 * //4) create new MimeBodyPart object and set DataHandler object to this object
			 * MimeBodyPart messageBodyPart2 = new MimeBodyPart(); String filename =
			 * bean.getDocumentName();//change accordingly DataHandler dataHandler = new
			 * DataHandler(new InputStreamDataSource(bean.getResume()));
			 * messageBodyPart2.setDataHandler(dataHandler);
			 * messageBodyPart2.setFileName(filename);
			 * 
			 * //5) create Multipart object and add MimeBodyPart objects to this object
			 * Multipart multipart = new MimeMultipart();
			 * multipart.addBodyPart(messageBodyPart1);
			 * multipart.addBodyPart(messageBodyPart2);
			 * 
			 * //6) set the multiplart object to the message object
			 * message.setContent(multipart);
			 * 
			 * // writes the file to the client byte[] bytearray; BufferedInputStream bis =
			 * new BufferedInputStream(bean.getResume()); ByteArrayOutputStream bao = new
			 * ByteArrayOutputStream(); byte[] buffer = new byte[4096]; int length = 0;
			 * while ((length = bis.read(buffer)) != -1) { bao.write(buffer, 0, length); }
			 * bao.close(); bis.close(); bytearray = bao.toByteArray();
			 * 
			 * // Multipart multipart = new MimeMultipart();
			 * 
			 * MimeBodyPart messageBodyPart = new MimeBodyPart(); messageBodyPart.
			 * setContent("<p style='font-weight:bold;font-size:large;color:#0074D9'>Resume Inquiry</p><P style='font-size:14px;color:#3D9970'>Name: "
			 * +bean.getFname()+" "+bean.getLname()
			 * +"</p><P style='color:#001f3f'>Qualification: "+bean.getQualification()
			 * +"</p><p style='color:#001f3f'>Experience: "+bean.getExperience()
			 * +"</p><p style='color:#001f3f'>Message: "+bean.getMessage()
			 * +"</p><p style='color:#001f3f'>Email ID: "+bean.getEmail()
			 * +"</p><p style='color:#001f3f'>Phone: "+bean.getPhone()+"</p>", "text/html");
			 * 
			 * // creates body part for the attachment MimeBodyPart attachPart = new
			 * MimeBodyPart(); String attachFile = bean.getDocumentName();
			 * 
			 * 
			 * DataHandler dataHandler = new DataHandler(new ByteArrayDataSource(bytearray,
			 * bean.getDocumentType())); attachPart.setDataHandler(dataHandler);
			 * attachPart.setFileName(attachFile);
			 * 
			 * // adds parts to the multipart multipart.addBodyPart(messageBodyPart);
			 * multipart.addBodyPart(attachPart);
			 * 
			 * message.setContent(multipart);
			 * 
			 * 
			 * 
			 * Transport.send(message);
			 * 
			 * System.out.println("Done");
			 * 
			 * } catch (MessagingException | IOException e) { e.printStackTrace(); return 0;
			 * }
			 * 
			 * return 1; }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMail() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSubject() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDate(String date) {
		// TODO Auto-generated method stub
		
	}
}
