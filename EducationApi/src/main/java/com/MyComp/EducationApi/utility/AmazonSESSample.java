package com.MyComp.EducationApi.utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;

public class AmazonSESSample {

  static final String FROM = "dewmobilitynest@gmail.com"; // Replace with your
                                                          // "From" address.
                                                          // This address must
                                                          // be verified.
  /* static final String TO = "supreethkm@gmail.com"; */ // Replace with a "To"
                                                         // address. If your
                                                         // account is still in
                                                         // the
  // sandbox, this address must be verified.

  /*
   * static final String BODY =
   * "Click the link below to start using the exciting Nestlings application " +
   * " "
   * +"http://ec2-52-37-130-221.us-west-2.compute.amazonaws.com:8080/UI/index.html";
   */

  static final String SUBJECT = "Nestlings";

  // Supply your SMTP credentials below. Note that your SMTP credentials are
  // different from your AWS credentials.
  static final String SMTP_USERNAME = "AKIAID33LS35RXS2VLXA"; // Replace with
                                                              // your SMTP
                                                              // username.
  static final String SMTP_PASSWORD = "AuCDCrn4qKokGtg8Fp8uHOBaidv8/DhBg4+vjxN7NH4j"; // Replace
                                                                                      // with
                                                                                      // your
                                                                                      // SMTP
                                                                                      // password.

  // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
  static final String HOST = "email-smtp.us-west-2.amazonaws.com";

  // Port we will connect to on the Amazon SES SMTP endpoint. We are choosing
  // port 25 because we will use
  // STARTTLS to encrypt the connection.
  static final int PORT = 587;

  static final String UI_CONTEXT_PATH = "http://www.nestlings.com";

  public static boolean send(String recieverMailId, String senderMailId, String BODY, String SUBJECT)
      throws AddressException, MessagingException {
    // TODO Auto-generated method stub
    // Create a Properties object to contain connection configuration
    // information.
    Properties props = System.getProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.port", PORT);

    // Set properties indicating that we want to use STARTTLS to encrypt the
    // connection.
    // The SMTP session will begin on an unencrypted connection, and then the
    // client
    // will issue a STARTTLS command to upgrade to an encrypted connection.
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.starttls.required", "true");

    // Create a Session object to represent a mail session with the specified
    // properties.
    Session session = Session.getDefaultInstance(props);

    // Create a message with the specified information.
    MimeMessage msg = new MimeMessage(session);
    try {
      msg.setFrom(new InternetAddress(FROM, "Nestlings"));
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recieverMailId));
    msg.setSubject(SUBJECT);
    msg.setContent(BODY, "text/html");

    // Create a transport.
    Transport transport = session.getTransport();

    // Send the message.
    try {
      System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");

      // Connect to Amazon SES using the SMTP username and password you
      // specified above.
      transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

      // Send the email.
      transport.sendMessage(msg, msg.getAllRecipients());
      System.out.println("Email sent!");
    } catch (Exception ex) {
      System.out.println("The email was not sent.");
      System.out.println("Error message: " + ex.getMessage());
    } finally {
      // Close and terminate the connection.
      transport.close();
    }

    return false;

  }

  public static void sendMail(String recieverMailId, int userID, String referenceCode, String firstName)
      throws Exception {

    /*
     * String body =
     * "Click the link below to start using the exciting Nestlings application "
     * + " " + "http://www.nestlings.com?id=" + userID + "&refer=" +
     * referenceCode;
     */

    String body = null;
    String queryString = "";
    if (referenceCode != null) {
      body = "<h2>Click the link below to start using the exciting Nestlings application</h2> " + " "
          + "<a href ='http://www.nestlings.com?id=" + userID + "&refer=" + referenceCode + "'>Nestlings</a> ";
      queryString = "id=" + userID + "&refer=" + referenceCode;
    } else {
      body = "Click the link below to start using the exciting Nestlings application " + " "
          + "http://www.nestlings.com";
      queryString = "id=" + userID;
    }
    String subject = "You�re Invited!";
    String content = readFileContent("/invite.html");
    if (content != null) {
      // body = String.format(content,firstName);
      body = content.replace("firstName", firstName);

    }
    System.out.println(body);
    send(recieverMailId, null, body, subject);

  }

  public static void sendSignupMail(String recieverMailId, int userID, String firstName) {

    /*
     * String body =
     * "Click the link below to start using the exciting Nestlings application "
     * + " " + "http://www.nestlings.com?id=" + userID + "&refer=" +
     * referenceCode;
     */

    String body = null;
    String queryString = "";

    body = "Click the link below to start using the exciting Nestlings application " + " " + "http://www.nestlings.com";
    queryString = "id=" + userID;

    String content = readFileContent("/register-notification.html");
    if (content != null) {
      body = content.replace("firstName", firstName);
      body = body.replace("welcomeUrl", UI_CONTEXT_PATH + "?" + queryString);
      // body = String.format(content,firstName,queryString);

    }
    System.out.println(body);
    String subject = "Nestlings";
    try {
      send(recieverMailId, null, body, subject);
    } catch (AddressException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MessagingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public static boolean sendApplicationMail(String recieverMailId, String senderMailId, int userID, int collegeID) {
    // TODO Auto-generated method stub
    String BODY = "One application is waiting for your reply. Click below link to Accept/Reject" + " "
        + "http://www.nestlings.com/applicantprofile.html?id=" + userID + "&col=" + collegeID;
    try {
      send(recieverMailId, senderMailId, BODY, "Application");
      return true;
    } catch (AddressException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MessagingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return false;

  }
  
  public static boolean sendApplicationProfileEMail(String recieverMailId, String senderMailId, int userID, int collegeID) {
	    // TODO Auto-generated method stub
	    String BODY = "One Applicant is interesting.Click below link to view profile " + " "
	        + "http://www.nestlings.com/friendprofile.html?connectID=" + userID;
	    try {
	      send(recieverMailId, senderMailId, BODY, "Application");
	      return true;
	    } catch (AddressException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    } catch (MessagingException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    return false;

	  }

  public static boolean sendPswrdResetMail(String emailID, int userID, String firstName) {
    // TODO Auto-generated method stub
    String body = "<h3>Click <a href ='http://www.nestlings.com/reset-password.html?id=" + emailID + "&token=" + userID
        + "'> Reset link</a> to reset your password</h3>";
    ;
    try {
      String content = readFileContent("/forgot-password.html");
      if (content != null) {

        body = content.replace("firstName", firstName);
        body = body.replace("resetUrl", UI_CONTEXT_PATH + "/reset-password.html?id=" + emailID + "&&token=" + userID);
        // body = String.format(content, firstName,emailID,userID);
        // body = content;
      }
      System.out.println(body);
      String subject = "Reset Password";
      send(emailID, null, body, subject);
      return true;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return false;

  }

  public static boolean sendRecommend(String emailID, int userID, String firstName, String lastName,
      Blob profileImage) {
    // TODO Auto-generated method stub
    String body = "<h3>Click to<a href ='http://www.nestlings.com'> recommend</a> your friend</h3>";
    try {
      String content = readFileContent("/recommend.html");
      if (content != null) {
        // body =
        // String.format(content,coverImage,firstName,emailID,userID,emailID,userID,emailID,userID);
        body = content;
        body = body.replace("recommendUrl", UI_CONTEXT_PATH + "?id=" + emailID + "&refer=" + userID);
        body = body.replace("firstName", firstName);
        body = body.replace("lastName", lastName);
        if (profileImage != null)
          body = body.replace("profileImage", (CharSequence) profileImage);
      } else {
        body = body.replace("profileImage", "http://nestlings.com/img/prof.png");
      }
      System.out.println(body);
      String subject = "Recommend";
      send(emailID, null, body, subject);
      return true;
    } catch (AddressException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MessagingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return false;

  }

  public static String readFileContent(String resourceName) {

    InputStream stream = AmazonSESSample.class.getResourceAsStream(resourceName);
    StringWriter writer = new StringWriter();
    String content = null;
    try {
      IOUtils.copy(stream, writer);
      content = writer.toString();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      if (stream != null)
        try {
          stream.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }
    return content;
  }

//  public static boolean focusgroupMail(FocusGrpAplnBean focusGrpAplnBean) {
//    // TODO Auto-generated method stub
//    System.out.println("focusgroupMail starts");
//    String body = "";
//
//    try {     System.out.println("focusgroupMail try block");
//      String content = readFileContent("/applications/focusgroupapplication.html");
//      if (content != null) {
//        // body =
//        // String.format(content,coverImage,firstName,emailID,userID,emailID,userID,emailID,userID);
//        body = content;
//        /*
//         * body = body.replace("recommendUrl",
//         * UI_CONTEXT_PATH+"?id="+emailID+"&refer="+userID); body =
//         * body.replace("firstName", firstName); body = body.replace("lastName",
//         * lastName);
//         */
//        if (focusGrpAplnBean.getFirstName() != null) {
//          body = body.replace("firstName", focusGrpAplnBean.getFirstName());
//        } else {
//          body = body.replace("firstName", "");
//        }
//
//        if (focusGrpAplnBean.getLastName() != null) {
//          body = body.replace("lastName", focusGrpAplnBean.getLastName());
//        } else {
//          body = body.replace("lastName", "");
//        }
//
//        if (focusGrpAplnBean.getAge() != 0) {
//          body = body.replace("age", Integer.toString(focusGrpAplnBean.getAge()));
//        } else {
//          body = body.replace("age", "");
//        }
//
//        if (focusGrpAplnBean.getEmailID() != null) {
//          body = body.replace("emailID", focusGrpAplnBean.getEmailID());
//        } else {
//          body = body.replace("emailID", "");
//        }
//
//        if (focusGrpAplnBean.getPhoneNo() != null) {
//          body = body.replace("phoneNo", focusGrpAplnBean.getPhoneNo()+"");
//        } else {
//          body = body.replace("phoneNo", "");
//        }
//
//        if (focusGrpAplnBean.getGenderID() != null) {
//          body = body.replace("genderID", focusGrpAplnBean.getGenderID());
//        } else {
//          body = body.replace("genderID", "");
//        }
//
//        if (focusGrpAplnBean.getEtnicity() != null) {
//          body = body.replace("etnicity", focusGrpAplnBean.getEtnicity());
//        } else {
//          body = body.replace("etnicity", "");
//        }
//
//        if (focusGrpAplnBean.getSchool() != null) {
//          body = body.replace("school", focusGrpAplnBean.getSchool());
//        } else {
//          body = body.replace("school", "");
//        }
//
//        if (focusGrpAplnBean.getStatus() != null) {
//          body = body.replace("status", focusGrpAplnBean.getStatus());
//        } else {
//          body = body.replace("status", "");
//        }
//
//        if (focusGrpAplnBean.getAcademStatus() != null) {
//          body = body.replace("academStatus", focusGrpAplnBean.getAcademStatus());
//        } else {
//          body = body.replace("academStatus", "");
//        }
//
//        if (focusGrpAplnBean.getFinAid() != null) {
//          body = body.replace("finAid", focusGrpAplnBean.getFinAid());
//
//        } else {
//          body = body.replace("finAid", "");
//        }
//
//        if (focusGrpAplnBean.getFacDepAndPos() != null) {
//          body = body.replace("facDepAndPos", focusGrpAplnBean.getFacDepAndPos());
//        } else {
//          body = body.replace("facDepAndPos", "");
//        }
//
//        if (focusGrpAplnBean.getHeardBy() != null) {
//          body = body.replace("heardBy", focusGrpAplnBean.getHeardBy());
//        } else {
//          body = body.replace("heardBy", "");
//        }
//
//        if (focusGrpAplnBean.getApplyColOnline() != null) {
//          body = body.replace("applyColOnline", focusGrpAplnBean.getApplyColOnline());
//        } else {
//          body = body.replace("applyColOnline", "");
//        }
//
//        if (focusGrpAplnBean.getColState() != null) {
//          body = body.replace("colState", focusGrpAplnBean.getColState());
//        } else {
//          body = body.replace("colState", "");
//        }
//
//        if (focusGrpAplnBean.getOnlineSpend() != null) {
//          body = body.replace("onlineSpend", focusGrpAplnBean.getOnlineSpend());
//        } else {
//          body = body.replace("onlineSpend", "");
//        }
//
//        if (focusGrpAplnBean.getHaveSmartphone() != null) {
//          body = body.replace("haveSmartphone", focusGrpAplnBean.getHaveSmartphone());
//        } else {
//          body = body.replace("haveSmartphone", "");
//        }
//
//        if (focusGrpAplnBean.getSmartphoneKind() != null) {
//          body = body.replace("smartphoneKind", focusGrpAplnBean.getSmartphoneKind());
//        } else {
//          body = body.replace("smartphoneKind", "");
//        }
//
//        if (focusGrpAplnBean.getTakeServey() != null) {
//          body = body.replace("takeServey", focusGrpAplnBean.getTakeServey());
//        } else {
//          body = body.replace("takeServey", "");
//        }
//      }
//      String subject = "Focus Group program";
//      /*send("focusgroup@nestlings.com", null, body, subject);*/
//      send("sowmya@nestlings.com", null, body, subject);
//      return true;
//    } catch (AddressException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    } catch (MessagingException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//    return false;
//
//  }

  public static boolean sendVerificationCode(String verificationCode, int collegeID,String toEmailID) {
    // TODO Auto-generated method stub
    String body = "";
    try {
      body = "Your verification code : "+verificationCode
    		  +" Click <a href='"+UI_CONTEXT_PATH+"/verification-college.collegeName3.html?id="+collegeID+"' >here</a> to enter your verification code"
    		  + " ";
      String subject = "Your Verification Code";
      send(toEmailID, "support@nestlings.com", body, subject);
      return true;
    } catch (AddressException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MessagingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return false;
  }
  
//  public static boolean internApplctnMailsendVerificationCode(FocusGrpAplnBean focusGrpAplnBean) {
//	    // TODO Auto-generated method stub
//	    String body = "";
//	    try {
//	      String content = readFileContent("/applications/internshipapplication.html");
//	      if (content != null) {
//	        body = content;
//	        /*
//	         * body = body.replace("recommendUrl",
//	         * UI_CONTEXT_PATH+"?id="+emailID+"&refer="+userID); body =
//	         * body.replace("firstName", firstName); body = body.replace("lastName",
//	         * lastName);
//	         */
//	      }
//	      System.out.println(body);
//	      String subject = "Internship Application";
//	      send("supreethkm@gmail.com", null, body, subject);
//	      return true;
//	    } catch (AddressException e) {
//	      // TODO Auto-generated catch block
//	      e.printStackTrace();
//	    } catch (MessagingException e) {
//	      // TODO Auto-generated catch block
//	      e.printStackTrace();
//	    }
//	    return false;
//	  }

}