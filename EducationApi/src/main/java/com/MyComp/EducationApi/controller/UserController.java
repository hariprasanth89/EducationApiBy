package com.MyComp.EducationApi.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.MyComp.EducationApi.bean.UserBean;
import com.MyComp.EducationApi.model.User;
import com.MyComp.EducationApi.service.UserService;
import com.MyComp.EducationApi.utility.AmazonSESSample;
import com.MyComp.EducationApi.utility.NestlingsUtil;
import com.google.gson.Gson;

@Controller
@RequestMapping("/user")

@Transactional

public class UserController {
  Logger logger = Logger.getLogger(UserController.class);
  @Autowired
  UserService userService;

  @Autowired
  private SessionFactory sessionFactory;

  /**
   * @Autowired AmazonSESSample amazonSESSample;
   */

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public @ResponseBody String register(@RequestBody UserBean userBean) {
    System.out.println("register");
    String data = userService.register(userBean);
    System.out.println(data);

    /*
     * send email for authentication once successfully finishes preliminary
     * registration process
     */
    /*
     * applicationMailer.sendMail(null, userBean.getEmailID(),
     * "Nestlings Authentication Process",
     * "<h1>Click the link below to authenticate</h1><br>"
     * +NestlingsUtil.getBaseURL()+"/user/authenticate/"+String.valueOf(userID))
     * ;
     */
    return data;
  }

  @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
  public @ResponseBody String sendEmail(@RequestBody UserBean userBean) {
    System.out.println("sendEmail");
    
    try {
    	String user = userService.getUserName(userBean.getUserID());
        JSONObject jsonObject1 = new JSONObject(user);
    	userBean.setFirstName(jsonObject1.getString("firstName"));
      AmazonSESSample.sendMail(userBean.getEmailID(), userBean.getUserID(), userBean.getReferenceCode(),userBean.getFirstName());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    /*
     * applicationMailer.sendMail(null, userBean.getEmailID(), "Nestlings",
     * "<h1>Click the link below to start using the exciting Nestlings application</h1><br>"
     * +"http://ec2-52-37-130-221.us-west-2.compute.amazonaws.com:8080/UI/index.html"
     * );
     */
    return "Success";
  }

//  @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
//  public @ResponseBody String updateProfile(@RequestBody UserAddressBean userAddressBean) {
//    System.out.println("updateProfile");
//    AddressBean addressBean = new AddressBean();
//    addressBean.setAddressID(userAddressBean.getAddressID());
//    addressBean.setStreetName(userAddressBean.getStreetName());
//    addressBean.setCity(userAddressBean.getCity());
//    addressBean.setState(userAddressBean.getState());
//    addressBean.setZip(userAddressBean.getZip());
//    addressBean.setCountry(userAddressBean.getCountry());
//    //double[] geocode = Geocoding.getGeocode(addressBean);
//    //addressBean.setLatitude(new BigDecimal(geocode[0]));
//    //addressBean.setLongitude(new BigDecimal(geocode[1]));
//    String dataAdd = addressService.add(addressBean);
//
//    JSONObject jsonObject;
//    String addressID = null;
//    try {
//      jsonObject = new JSONObject(dataAdd);
//      System.out.println(jsonObject.getString(NestlingsUtil.STATUS));
//      addressID = jsonObject.getString(NestlingsUtil.ADDRESS_ID);
//      System.out.println(addressID);
//    } catch (JSONException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//    
//    AddressBean homeAddressBean = new AddressBean();
//    homeAddressBean.setAddressID(userAddressBean.getHomeAddrsID());
//    homeAddressBean.setStreetName(userAddressBean.getHstreetName());
//    homeAddressBean.setCity(userAddressBean.getHcity());
//    homeAddressBean.setState(userAddressBean.getHstate());
//    homeAddressBean.setZip(userAddressBean.getHzip());
//    homeAddressBean.setCountry(userAddressBean.getHcountry());
//    String homeDataAdd = addressService.add(homeAddressBean);
//
//    JSONObject jsonObject1;
//    int homeAddrsID = 0;
//    try {
//      jsonObject1 = new JSONObject(homeDataAdd);
//      System.out.println(jsonObject1.getString(NestlingsUtil.STATUS));
//      homeAddrsID = jsonObject1.getInt(NestlingsUtil.ADDRESS_ID);
//      System.out.println(addressID);
//    } catch (JSONException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//    
//   
//    UserBean userBean = new UserBean();
//
//    userBean.setUserID(userAddressBean.getUserID());
//    userBean.setAddressID(addressID);
//    userBean.setFirstName(userAddressBean.getFirstName());
//    userBean.setMiddleName(userAddressBean.getMiddleName());
//    userBean.setLastName(userAddressBean.getLastName());
//    userBean.setDob(userAddressBean.getDob());
//    userBean.setPhoneNumber(userAddressBean.getPhoneNumber());
//    userBean.setGenderID(userAddressBean.getGenderID());
//    userBean.setStatus(userAddressBean.getStatus());
//    userBean.setHereFor(userAddressBean.getHereFor());
//    userBean.setHomeAddrsID(homeAddrsID); 
//    String data = userService.updateProfile(userBean);
//    return data;
//  }

  @RequestMapping(value = "/authenticate/{userID}", method = RequestMethod.GET)
  public @ResponseBody String authenticate(@PathVariable("userID") int userID) {
    System.out.println("authenticate");
    userService.authenticate(userID);
    return "Success";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public @ResponseBody String login(@RequestBody UserBean userBean) {
    System.out.println("login");
    return userService.login(userBean);
  }

  @RequestMapping(value = "/fetch/{userID}", method = RequestMethod.GET)
  public @ResponseBody String fetch(@PathVariable("userID") int userID) {
    System.out.println("fetch");
    System.out.println(userID);
    return userService.fetch(userID);
  }
  
  @RequestMapping(value = "/search", method = RequestMethod.POST)
  public @ResponseBody String searchStudents(@RequestBody UserBean userBean) {
	  List<User>  list =userService.search(userBean);
	  Gson gson = new Gson();
		String json = gson.toJson(list);	
    return json;
  }

  @RequestMapping(value = "/userupdate", method = RequestMethod.POST)
  public @ResponseBody String userUpdate(@RequestBody UserBean userBean) {
    System.out.println("user upadate");
    String data = userService.updateProfile(userBean);
    System.out.println(data);
    return data;
  }

//  @RequestMapping(value = "/search/{string}", method = RequestMethod.GET)
//  public @ResponseBody String search(@PathVariable("string") String string) {
//    System.out.println("search");
//    return searchService.search(string);
//  }

  @RequestMapping(value = "/saveprofileimage2", method = RequestMethod.POST)
  public @ResponseBody String saveProfileImage2(@ModelAttribute("userBean") UserBean userBean,
      @RequestParam("file") MultipartFile file) {

    System.out.println("UserID:" + userBean.getUserID());
    // System.out.println("Name:" + document.getName());
    // System.out.println("Desc:" + document.getDescription());
    System.out.println("File:" + file.getOriginalFilename());
    System.out.println("ContentType:" + file.getContentType());

    try {
      System.out.println("Blob creating");
      Blob blob = Hibernate.getLobCreator(sessionFactory.getCurrentSession()).createBlob(file.getInputStream(),
          file.getSize());
      System.out.println("Blob created");
      // userBean.setFilename(file.getOriginalFilename());
      userBean.setProfileImage(blob);
      // userBean.setContentType(file.getContentType());
    } catch (IOException e) {
      e.printStackTrace();
    }

    return userService.saveProfileImage(userBean);
  }
  
  @RequestMapping(value = "/saveprofileimage", method = RequestMethod.POST)
	public @ResponseBody String saveProfileImage(@RequestBody UserBean userBean) {

		System.out.println("UserID:" + userBean.getUserID());
		try {
			System.out.println("Blob creating");
			if(userBean.getProfileCropImageUrl() != null){
			byte[] imageInByte = NestlingsUtil.getImageFromUrl(userBean.getProfileCropImageUrl(),150,150);
			Blob blob = Hibernate.getLobCreator(sessionFactory.getCurrentSession()).createBlob(imageInByte);
			userBean.setProfileCropImage(blob);
			System.out.println("Blob created");
			}
			
			if(userBean.getProfileImageUrl() != null){
			byte[] imageInByte2 = NestlingsUtil.getImageFromUrl(userBean.getProfileImageUrl(),150,150);
			Blob blob2 = Hibernate.getLobCreator(sessionFactory.getCurrentSession()).createBlob(imageInByte2);
			userBean.setProfileImage(blob2);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return userService.saveProfileImage(userBean);
	}


  //// coverImage >>>>>>>
  @RequestMapping(value = "/savecoverimag2", method = RequestMethod.POST)
  public @ResponseBody String saveCollegeImage2(@ModelAttribute("userBean") UserBean userBean,
      @RequestParam("file") MultipartFile file) {

    System.out.println("UserID:" + userBean.getUserID());
    System.out.println("File:" + file.getOriginalFilename());
    System.out.println("ContentType:" + file.getContentType());

    userBean.setFile(file);
    userService.saveCoverImage(userBean);
    return "Succes";
  }
  
  @RequestMapping(value = "/savecoverimage", method = RequestMethod.POST)
  public @ResponseBody String saveCollegeImage(@RequestBody UserBean userBean) {

	  System.out.println("UserID:" + userBean.getUserID());
		try {
			System.out.println("Blob creating");
			if(userBean.getCoverCropImageUrl() != null){
			byte[] imageInByte = NestlingsUtil.getImageFromUrl(userBean.getCoverCropImageUrl(),1200,600);
			Blob cropblob = Hibernate.getLobCreator(sessionFactory.getCurrentSession()).createBlob(imageInByte);
			userBean.setCoverCropImage(cropblob);
			System.out.println("Blob created");
			}
			
			if(userBean.getCoverImageUrl() != null){
			byte[] imageInByte2 = NestlingsUtil.getImageFromUrl(userBean.getCoverImageUrl(),1200,600);
			Blob blob2 = Hibernate.getLobCreator(sessionFactory.getCurrentSession()).createBlob(imageInByte2);
			userBean.setCoverImage(blob2);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

    userService.saveCoverImage(userBean);
    return "Succes";
  }

  //// retrieve cover image
  @RequestMapping(value = "/getcoverimage/{userID}", method = RequestMethod.GET)
  public  @ResponseBody String downloadCoverImage(@PathVariable("userID") Integer userID, HttpServletResponse response) {

    User user = userService.downloadImage(userID);
    try {
      response.setHeader("Content-Disposition", "inline;filename=\"" + "cover_picture.png" + "\"");
      OutputStream out = response.getOutputStream();
      response.setContentType("image/png"); // userbean.getContentType()
      if (user.getCoverImage() != null)
        IOUtils.copy(user.getCoverImage().getBinaryStream(), out);
      out.flush();
      out.close();

    } catch (IOException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  //// retrieve image
  @RequestMapping(value = "/download/{userID}", method = RequestMethod.GET)
  public @ResponseBody String downloadfile(@PathVariable("userID") Integer userID, HttpServletResponse response) {

    User user = userService.downloadImage(userID);
    try {
      response.setHeader("Content-Disposition", "inline;filename=\"" + "profile_picture.png" + "\"");
      OutputStream out = response.getOutputStream();
      response.setContentType("image/png"); // userbean.getContentType()
      if (user.getProfileImage() != null)
        IOUtils.copy(user.getProfileImage().getBinaryStream(), out);
      out.flush();
      out.close();

    } catch (IOException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }
  
  @RequestMapping(value = "/downloadcrop/{userID}", method = RequestMethod.GET)
  public @ResponseBody String downloadCropFile(@PathVariable("userID") Integer userID, HttpServletResponse response) {

    User user = userService.downloadImage(userID);
    try {
      response.setHeader("Content-Disposition", "inline;filename=\"" + "profile_picture.png" + "\"");
      OutputStream out = response.getOutputStream();
      response.setContentType("image/png"); // userbean.getContentType()
      if (user.getProfileImage() != null)
        IOUtils.copy(user.getProfileCropImage().getBinaryStream(), out);
      out.flush();
      out.close();

    } catch (IOException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }
  
  @RequestMapping(value = "/downloadcovercrop/{userID}", method = RequestMethod.GET)
  public @ResponseBody String downloadCoverCropFile(@PathVariable("userID") Integer userID, HttpServletResponse response) {

    User user = userService.downloadImage(userID);
    try {
      response.setHeader("Content-Disposition", "inline;filename=\"" + "profile_picture.png" + "\"");
      OutputStream out = response.getOutputStream();
      response.setContentType("image/png"); // userbean.getContentType()
      if (user.getCoverCropImage() != null)
        IOUtils.copy(user.getCoverCropImage().getBinaryStream(), out);
      out.flush();
      out.close();

    } catch (IOException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  // fetch Username + userID
  @RequestMapping(value = "/username/{userID}", method = RequestMethod.GET)
  public @ResponseBody String Fetch(@PathVariable("userID") int userID) {
    System.out.println("username");
    System.out.println(userID);
    return userService.getUserName(userID);
  }

//  @RequestMapping(value = "/searchbytype", method = RequestMethod.POST)
//  public @ResponseBody String searchByType(@RequestBody UserBean userBean) {
//    System.out.println("search");
//    System.out.println("FirstName :" + userBean.getFirstName());
//    System.out.println("userType :" + userBean.getUserType());
//    return searchService.searchByType(userBean);
//  }

//  @RequestMapping(value = "/splsearchbytype", method = RequestMethod.POST)
//  public @ResponseBody String splSearchByType(@RequestBody UserBean userBean) {
//    System.out.println("search");
//    System.out.println("FirstName :" + userBean.getFirstName());
//    System.out.println("userType :" + userBean.getUserType().toString());
//    String searchType = userBean.getUserType().toString();
//    String string = userBean.getFirstName();
//    if (searchType == "COLLEGE") {
//      return collegeService.searchClg(string);
//    }
//
//    if (searchType == "BANK") {
//      return bankService.searchBnk(string);
//    }
//
//    if (searchType == "COMPANY") {
//      return companyService.searchCmpny(string);
//    }
//    if (searchType == "INSTITUTION") {
//      return institutionService.searchIstn(string);
//    }
//    return null;
//  }
//
//  @RequestMapping(value = "/usermoreinfo", method = RequestMethod.POST)
//  public @ResponseBody String addUserMoreInfo(@RequestBody UserMoreInfoBean userMoreInfoBean) {
//    System.out.println("login");
//    return userMoreInfoService.addUserMoreInfo(userMoreInfoBean);
//  }

//  @RequestMapping(value = "/getusermoreinfo/{userID}", method = RequestMethod.GET)
//  public @ResponseBody String getUserMoreInfo(@PathVariable("userID") int userID) {
//    System.out.println("username");
//    System.out.println(userID);
//    return userMoreInfoService.getUserMoreInfo(userID);
//  }

  @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
  public @ResponseBody String forgotPassword(@RequestBody UserBean userBean) {
    System.out.println("emailID" + userBean.getEmailID());
     
    return userService.forgetPassword(userBean.getEmailID());
  }

  @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
  public @ResponseBody String resetPassword(@RequestBody UserBean userBean) {
    System.out.println("emailID" + userBean.getEmailID());
    return userService.resetPassword(userBean);
  }

  @RequestMapping(value = "/saveprofileimage1", method = RequestMethod.POST)
  public @ResponseBody String saveProfileImage1(@ModelAttribute("postBean") UserBean userBean,
      @RequestParam("file") MultipartFile file) {

    System.out.println("File:" + file.getOriginalFilename());
    System.out.println("ContentType:" + file.getContentType());
    userBean.setFile(file);

    return userService.saveProfileImage1(userBean);
  }

  @RequestMapping(value = "/savecoverimage1", method = RequestMethod.POST)
  public @ResponseBody String saveCoverImage1(@ModelAttribute("postBean") UserBean userBean,
      @RequestParam("file") MultipartFile file) {

    System.out.println("File:" + file.getOriginalFilename());
    System.out.println("ContentType:" + file.getContentType());
    userBean.setFile(file);

    return userService.saveCoverImage1(userBean);
  }
  
  @RequestMapping(value = "/askrecommend", method = RequestMethod.POST)
  public @ResponseBody String askRecommend(@RequestBody UserBean userBean) {

    return userService.askRecommend(userBean.getUserID());
  }
}
