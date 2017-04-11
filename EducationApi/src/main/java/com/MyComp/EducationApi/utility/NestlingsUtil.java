package com.MyComp.EducationApi.utility;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

@Repository("nestlingsUtil")
public class NestlingsUtil {

  



  

  

  Logger logger = Logger.getLogger(NestlingsUtil.class);

  private static String baseURL = "http://localhost:8080/Nestlings";
  // private static String forgotPasswordMail;
  private static String webBaseURL;
  // private static String signUpMail;
  private static String basePath;

  private static Gson gson;

  public static enum UserType {
    STUDENT, // 0
    LECTURER, // 1
    ALUMNI, // 2
    COLLEGE, // 3
    COMPANY, // 4
    INSTITUTION, // 5
    BANK, // 6
    SCHOLARSHIP,// 7
    MENTOR //8
  };

  public static enum StatusType {
    PENDING, // 0
    ACCEPT, // 1
    REJECT, // 2
    INCOMPLETE,//3
    NEW //4
  };

  public static enum NotificationType {
    FRIENDREQUEST, // 0
    FRIENDACCEPT, // 1
    FRIENDREJECT, // 2
    FRIENDPENDING, // 3
    RECOMMENDATION, // 4
    POST, // 5
    COMMENT, // 6
    LIKE, // 7
    COLLEGELIST, // 8
    APPLICATION // 9
  };

  public static enum IndustryType {
    SOFTWARE, // 0
    IT, // 1
    HARDWARE // 2
  };

  public static enum Internships {
    IT, // 0
    MANAGEMENT // 1
  }

  public enum LoginType {
    DESKTOP, // 0
    MOBILE_BROWSER, // 1
    MOBILE_APP // 2
  };

  public enum GenderID {
    MALE, // 0
    FEMALE, // 1
    OTHER // 2
  };

  public enum AccountType {
    SAVINGS, // 0
    CHECKING // 1

  };

  public enum Certificates {
    PROFESSIONAL, // 0
    COLLEGIATE// 1
  };

  public enum Location {
    ONLINE, // 0
    OFFCAMPUS, // 1
    ONCAMUS// 2
  };

  public enum JuniorCollege {
    Y, // 0
    N // 1
  }

  public enum ResearchAffiliation {
    Y, // 0
    N// 1
  };

  public enum ResearchAssistance {
    Y, // 0
    N// 1
  };

  public enum TeachingAssistance {
    Y, // 0
    N// 1
  };

  public enum Mentor {
    Y, // 0
    N// 1
  };

  public enum ConferenceSpeaker {
    Y, // 0
    N// 1
  };

  public enum VisitingProfessor {
    Y, // 0
    N// 1
  };

  public enum ScholarshipType {
    Grant, // 0
    Sports, // 1
    Fellowship, // 2
    Needbased, // 3
    Military, // 4
    Religious// 5
  };

  public enum RequestStatus {
    Accepted, // 0
    NOT_Accepted, // 1
    Waiting, // 2
    Blocked
  }

  public static enum PostType {
    POST, // 0
    ARTICLE, // 1
    NOTE, // 2
    LECTURE_RECORDING, // 3
    PATENT, // 4
    WHITE_PAPER, // 5
    BLOG, // 6
    POLL, // 7
    EVENT, // 8
    MESSAGE, //9
    CLASSIFIED//10
  };

  public static enum SubjectCategory {
    CATEGORY// 0

  };

  public static enum CertificateType {
    CERTIFICATE// 0

  };
  
  public static enum TestType {
    ACT, // 0
    SAT, // 1
    GRE, // 2
    TOEFL, //3
    LANGUAGE, //4
    OTHER //5
  };

  public static String STATUS = "status";
  public static String DATA = "data";
  public static String SUCCESS = "success";
  public static String FAIL = "fail";
  public static String USER_ID = "userID";
  public static String USER_TYPE = "userType";
  public static String ADDRESS_ID = "addressID";
  public static String COLLEGE_ID = "collegeID";
  public static String STUDENTCOLLEGE_ID = "studentCollegeID";
  public static String ALUMNICOLLEGE_ID = "alumniCollegeID";
  public static String STUDENTWORK_ID = "studentWorkID";
  public static String ALUMNIWORK_ID = "alumniWorkID";
  public static String FACULTYWORK_ID = "facultyWorkID";
  public static String FACULTY_ID = "facultyID";
  public static String INSTITUTION_ID = "institutionID";
  public static String SCHOLARSHIP_ID = "scholarshipID";
  public static String BANK_ID = "bankID";
  public static String BANKINTEREST_ID = "bankInterestID";
  public static String FACULTYCOLLEGE_ID = "facultyCollegeID";
  public static String COMPANY_ID = "companyID";
  public static String USER_EXIST = "userExist";// "User already registerd with
                                                // this E-mail ID";
  public static String COLLEGEINTEREST_ID = "collegeInterestID";
  public static String TRAININGINTEREST_ID = "trainingInterestID";
  public static String IS_ACTIVE = "isActive";
  public static String POST_ID = "postID";
  public static String SCHOLARSHIPINTEREST_ID = "scholarshipinterestID";
  public static String COMPANYINTEREST_ID = "companyinterestID";
  public static String INTEREST_ID = "interestID";
  public static String APPLICATION_ID = "applicationID";
  public static String MENTORRELATIONSHIP_ID = "mentorRelationshipID";
  public static String MENTOR_ID = "mentorID";
  public static String RECOMMENDATION_ID = "recommendationID";
  public static String ISVIEWED = "isViwed";
  public static String TRUE = "TRUE";
  public static String FALSE = "FALSE";
  public static String USERMOREINFO_ID = "userMoreInfoID";
  public static String POINTS_ID = "pointsID";
  public static String MYDESK_ID = "myDeskID";
  public static String COURSE_ID = "courseID";
  public static String TESTSCORE_ID = "testScoreID";
  public static String CHILD_POST_ID = "childPostID";
  public static String RATING_ID = "ratingID";
  public static String SUBJECTS_ID = "subjectsID";
  public static String PROGRAMS_ID = "programsID";
  public static String PROGRAMSOFFERED_ID = "programsOfferedID";
  public static String MESSAGE = "message";
  public static String OK = "OK";
  public static String EMAILIDS = "emailIDs";
  public static String EXPIRED = "expired";
  public static String ALREADY_VERIFIED = "alreadyVerified";
  public static String INVALID = "invalid";
  public static String getWebBaseURL() {
    return webBaseURL;
  }

  public static void setWebBaseURL(String webBaseURL) {

    NestlingsUtil.webBaseURL = webBaseURL;
  }

  /*
   * public static String getSignUpMail() {
   * 
   * return signUpMail; }
   * 
   * public void setSignUpMail(String signUpMail) {
   * System.out.println("signUpMail=" + signUpMail); CoreUtil.signUpMail =
   * getFileContent(signUpMail); System.out.println("Check : " +
   * CoreUtil.signUpMail); }
   */

  public void setBaseURL(String baseURL) {
    System.out.println("Base URL " + baseURL);
    NestlingsUtil.baseURL = baseURL;
  }

  /*
   * public void setForgotPasswordMail(String forgotPasswordMail) {
   * System.out.println("forgotPasswordMail=" + forgotPasswordMail);
   * CoreUtil.forgotPasswordMail = getFileContent(forgotPasswordMail);
   * System.out.println("Check : " + CoreUtil.forgotPasswordMail);
   * 
   * }
   * 
   * public String getForgotPasswordMail() { return forgotPasswordMail; }
   */
  public static String getBaseURL() {
    return baseURL;
  }

  public static String getBasePath() {
    return basePath;
  }

  public static void setBasePath(String basePath) {
    NestlingsUtil.basePath = basePath;
  }

  public String getEncryptionPwd(String str) {

    java.security.MessageDigest d = null;
    String computeHash = null;

    if (str == null || str.trim().isEmpty()) {
      logger.error("Password String is empty/NULL" + str);
      return computeHash;
    }

    try {
      d = java.security.MessageDigest.getInstance("SHA-1");
      d.reset();
      d.update(str.getBytes());

      computeHash = byteArrayToHexString(d.digest());

    } catch (NoSuchAlgorithmException e) {
      logger.error("NoSuchAlgorithmException=" + str, e);
    }

    return computeHash;
  }

  private String byteArrayToHexString(byte[] b) {
    StringBuilder sb = new StringBuilder(b.length * 2);
    for (int i = 0; i < b.length; i++) {
      int v = b[i] & 0xff;
      if (v < 16) {
        sb.append('0');
      }
      sb.append(Integer.toHexString(v));
    }
    return sb.toString().toUpperCase();
  }

  public String getToken() {

    byte[] randomBytes = generateRandomString().getBytes(Charset.forName("UTF-8"));
    return byteArrayToHexString(randomBytes);
  }

  private String generateRandomString() {

    String ALPHABET = "abcdefg1234567890";
    int length = ALPHABET.length();

    StringBuilder randomString = new StringBuilder();
    Random randomNumber = new Random();

    for (int i = 0; i < 8; i++) {
      randomString.append(ALPHABET.charAt(randomNumber.nextInt(length)));
    }

    return randomString.toString();
  }

  public String getOTPToken() {

    byte[] randomBytes = generateOneTimePassword().getBytes(Charset.forName("UTF-8"));
    return byteArrayToHexString(randomBytes).substring(0, 4);
  }

  private String generateOneTimePassword() {

    String NUMBERS = "1234567890";
    int length = NUMBERS.length();

    StringBuilder randomString = new StringBuilder();
    Random randomNumber = new Random();

    for (int i = 0; i < 4; i++) {
      randomString.append(NUMBERS.charAt(randomNumber.nextInt(length)));
    }

    return randomString.toString();
  }

  /*
   * private String getFileContent(String fileName) {
   * 
   * StringBuilder result = new StringBuilder("");
   * 
   * // Get file from resources folder ClassLoader classLoader =
   * getClass().getClassLoader(); System.out.println("classLoader=" +
   * classLoader); System.out.println("fileName==" + fileName); File file = new
   * File(classLoader.getResource(fileName).getFile());
   * System.out.println("fileName==" + file.getPath() + "==" + file.getName());
   * 
   * FileInputStream inputStream = null; Scanner sc = null; try { try {
   * inputStream = new FileInputStream(file.getPath()); } catch
   * (FileNotFoundException e) { // TODO Auto-generated catch block
   * e.printStackTrace(); } sc = new Scanner(inputStream, "UTF-8"); while
   * (sc.hasNextLine()) { String line = sc.nextLine();
   * result.append(line).append("\n"); // System.out.println(line); } // note
   * that Scanner suppresses exceptions if (sc.ioException() != null) { try {
   * throw sc.ioException(); } catch (IOException e) { // TODO Auto-generated
   * catch block e.printStackTrace(); } } } finally { if (inputStream != null) {
   * try { inputStream.close(); } catch (IOException e) { // TODO Auto-generated
   * catch block e.printStackTrace(); } } if (sc != null) { sc.close(); } }
   * return result.toString();
   * 
   * }
   */
  public static String getPath(HttpServletRequest request) {
    return new File(request.getServletContext().getRealPath("/WEB-INF/")).getAbsolutePath();
  }

  public static ApnsService getApnsService(String path) {
    return APNS.newService().withCert(path + "IOS_APNS_KEYSTORE", "IOS_APNS_PASSWORD").withSandboxDestination().build();

  }

  public static String getGson(Object object) {

    if (gson == null) {
      gson = new Gson();
    }
    return gson.toJson(object);
  }

//  public static Object getGsonBean(String message, Class<TestBean> class1) {
//
//    if (gson == null) {
//      gson = new Gson();
//    }
//    return gson.fromJson(message, class1);
//  }
  
  public static File saveFileToServer(MultipartFile file, String storePathNName) {
    InputStream inputStream = null;
    OutputStream outputStream = null;
    String fileName = file.getOriginalFilename();
    String extn = FilenameUtils.getExtension(fileName);

    File newFile = new File(storePathNName + "." + extn);
    try {
      inputStream = file.getInputStream();

      if (!newFile.exists()) {
        newFile.createNewFile();
      }
      outputStream = new FileOutputStream(newFile);
      int read = 0;
      byte[] bytes = new byte[1024];

      while ((read = inputStream.read(bytes)) != -1) {
        outputStream.write(bytes, 0, read);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println(" Hidden = " + newFile.isHidden());
    System.out.println(" Path= " + newFile.getPath());
    System.out.println("Absolute Path= " + newFile.getAbsolutePath());
    return newFile;
  }
  
	public static byte[] getImageFromUrl(String dataUrl, int height, int width) throws IOException {
		String encodingPrefix = "base64,";

		byte[] imageInByte = null;
		try {
			int contentStartIndex = dataUrl.indexOf(encodingPrefix) + encodingPrefix.length();
			byte[] imageData = Base64.decodeBase64(dataUrl.substring(contentStartIndex));
			System.out.println("Blob creating");
			BufferedImage inputImage = ImageIO.read(new ByteArrayInputStream(imageData));

			inputImage = Scale.resize(inputImage, Scale.Method.QUALITY, Scale.Mode.FIT_EXACT, 150, 150);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(inputImage, "jpg", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			throw e;
		}
		return imageInByte;
	}
	
	public static String generateCode(){
		String uuid = UUID.randomUUID().toString();
		System.out.println("uuid = " + uuid);
		return uuid.substring(0,7);
	}
	

}
