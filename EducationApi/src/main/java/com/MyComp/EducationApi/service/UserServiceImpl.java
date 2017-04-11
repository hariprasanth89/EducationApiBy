package com.MyComp.EducationApi.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.MyComp.EducationApi.bean.UserBean;
import com.MyComp.EducationApi.dao.UserDao;
import com.MyComp.EducationApi.model.User;
import com.MyComp.EducationApi.utility.AmazonSESSample;
import com.MyComp.EducationApi.utility.NestlingsUtil;
import com.google.gson.Gson;

@Service("UserService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

  @Autowired
  UserDao userDao;

  @Override
  public String register(UserBean userBean) {

    String userexist = userDao.searchEmail(userBean);
    if (userexist == null) {
      System.out.println("user notexist");

      String userData = userDao.register(userBean);

      JSONObject jsonObjectRegiData = new JSONObject();
      int userID = 0;
      try {
        JSONObject jsonObjectRegi = new JSONObject(userData);
        jsonObjectRegiData = (JSONObject) jsonObjectRegi.get(NestlingsUtil.DATA);
        userID = (int) jsonObjectRegiData.get(NestlingsUtil.USER_ID);
        System.out.println("user ID in usersevice for points" + userID);
      //  pointsService.addRegistrationPoint(userID);
       // AmazonSESSample.sendSignupMail(userBean.getEmailID(), userID, userBean.getFirstName());
      } catch (JSONException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      return userData;
    }
    ;

    return userexist;
  }

  @Override
  public String updateProfile(UserBean userBean) {
    return userDao.updateProfile(userBean);
  }

  @Override
  public void authenticate(int id) {
    userDao.authenticate(id);
  }

  @Override
  public String login(UserBean userBean) {

    return userDao.login(userBean);

  }

  @Override
  public String forgetPassword(String emailID) {
    User user = userDao.checkMailExist(emailID);
    if (user != null) {
      AmazonSESSample.sendPswrdResetMail(user.getEmailID(), user.getUserID(),user.getFirstName());
      JSONObject jsonObject = new JSONObject();
      try {
        jsonObject.put(NestlingsUtil.STATUS, NestlingsUtil.SUCCESS);
        jsonObject.put(NestlingsUtil.DATA, "Reset link sent to your email ID.");
      } catch (JSONException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return jsonObject.toString();
    }
    JSONObject jsonObject = new JSONObject();
    try {
      jsonObject.put(NestlingsUtil.STATUS, NestlingsUtil.FAIL);
      jsonObject.put(NestlingsUtil.DATA, "Please check your email ID.");
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return jsonObject.toString();
  }

  @Override
  public String fetch(int userID) {
    // TODO Auto-generated method stub

    Gson gson = new Gson();
    String json = null;
    User user = userDao.fetch(userID);
    json = gson.toJson(user);
    return json;
  }

  @Override
  public String saveProfileImage(UserBean userBean) {
    // TODO Auto-generated method stub
    System.out.println("Calling userDao.saveProfileImage");
    return userDao.saveProfileImage(userBean);
  }

  @Override
  public String saveCoverImage(UserBean userBean) {
    // TODO Auto-generated method stub
    return userDao.saveCoverImage(userBean);
  }

  @Override
  public User downloadCoverImage(Integer userID) {
    // TODO Auto-generated method stub
    return userDao.downloadCoverImage(userID);
  }

  @Override
  public User downloadImage(Integer userID) {
    // TODO Auto-generated method stub
    return userDao.downloadImage(userID);
  }

  @Override
  public String getUserName(int userID) {
    return userDao.getUserName(userID);
  }
  
  @Override
  public User getUserDetails(int userID) {
    return userDao.getUserDetails(userID);
  }

  @Override
  public String getFacultyList() {
    // TODO Auto-generated method stub
    return userDao.getFacultyList();
  }

  @Override
  public String getAlumniList() {
    // TODO Auto-generated method stub
    return userDao.getAlumniList();
  }

  @Override
  public User getCoverImage(Integer userID) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String resetPassword(UserBean userBean) {
    // TODO Auto-generated method stub
    return userDao.resetPassword(userBean);
  }

  @Override
  public String saveProfileImage1(UserBean userBean) {
    // TODO Auto-generated method stub
    return userDao.saveProfileImage1(userBean);
  }

  @Override
  public String saveCoverImage1(UserBean userBean) {
    // TODO Auto-generated method stub
    return userDao.saveCoverImage1(userBean);
  }

  @Override
  public String askRecommend(int userID) {
    // TODO Auto-generated method stub
	User user = userDao.fetch(userID);
    AmazonSESSample.sendRecommend(user.getEmailID(), userID, user.getFirstName(), user.getLastName(), user.getProfileImage());
    return "mail sent";
  }
  
  @Override
  public List<User> search(UserBean userBean){
	  List<User> userList = userDao.search(userBean);
	  return userList;
  }

}
