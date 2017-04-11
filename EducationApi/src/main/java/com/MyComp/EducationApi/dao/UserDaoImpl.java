package com.MyComp.EducationApi.dao;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.MyComp.EducationApi.bean.UserBean;
import com.MyComp.EducationApi.model.User;
import com.MyComp.EducationApi.utility.NestlingsUtil;
import com.google.gson.Gson;

@Repository("user")
@Transactional

public class UserDaoImpl implements UserDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public String register(UserBean userBean) {
    if (userBean == null) {
      System.out.println("NULL addressBean Obj");
    }

    User user = new User();
    if (userBean.getUserType() != null) {
      user.setUserType(userBean.getUserType());
    }
    if (userBean.getFirstName() != null) {
      user.setFirstName(userBean.getFirstName());
    }
    if (userBean.getMiddleName() != null) {
      user.setMiddleName(userBean.getMiddleName());
    }
    if (userBean.getLastName() != null) {
      user.setLastName(userBean.getLastName());
    }
    if (userBean.getUserType() != null) {
      user.setUserType(userBean.getUserType());
    }
    if (userBean.getGenderID() != null) {
      user.setGenderID(userBean.getGenderID());
    }
    if (userBean.getLoginType() != null) {
      user.setLoginType(userBean.getLoginType());
    }
    if (userBean.getEmailID() != null) {
      user.setEmailID(userBean.getEmailID());
    }
    if (userBean.getPhoneNumber() != null) {
      user.setPhoneNumber(userBean.getPhoneNumber());
    }
    if (userBean.getPassword() != null) {
      user.setPassword(userBean.getPassword());
    }
    if (userBean.getForgotPassword() != null) {
      user.setForgotPassword(userBean.getForgotPassword());
    }
    if (userBean.getDob() != null) {
      user.setDob(userBean.getDob());
    }

    user.setFaceBookLogin(userBean.isFaceBookLogin());

    if (userBean.getFaceBookID() != null) {
      user.setFaceBookID(userBean.getFaceBookID());
    }
    // java date conversion
    /*
     * if(userBean.getDob() != null){ String stringDate = userBean.getDob();
     * Date date = null; try { date = new
     * SimpleDateFormat("MM/dd/yyyy").parse(stringDate); } catch (ParseException
     * e) { // TODO Auto-generated catch block e.printStackTrace(); }
     * user.setDob(date); }
     */
    if (userBean.getRefreshToken() != null) {
      user.setRefreshToken(userBean.getRefreshToken());
    }
    if (userBean.getAccessToken() != null) {
      user.setAccessToken(userBean.getAccessToken());
    }
    if (userBean.getAuthenticationToken() != null) {
      user.setAuthenticationToken(userBean.getAuthenticationToken());
    }
    if (userBean.getProfileImage() != null) {
      user.setProfileImage(userBean.getProfileImage());
    }
    if (userBean.getGoogleID() != null) {
      user.setGoogleID(userBean.getGoogleID());
    }
    if (userBean.isGPlusLogin() != false) {
      user.setGPlusLogin(userBean.isGPlusLogin());
    }

    if (userBean.getHereFor() != null) {
      user.setHereFor(userBean.getHereFor());
    }

    user.setCreatedDate(new Date());
    user.setUpdatedDate(new Date());

    try {

      Session session = sessionFactory.getCurrentSession();

      session.saveOrUpdate(user);
      System.out.println("user.id:" + user.getUserID());
    } catch (Exception e) {
      System.out.println("error msg" + e.getMessage());
      if (e.getMessage().startsWith("Duplicate entry")) {
        // return "User already exist";
        return "{'status':'user already exist'}";

      }
      e.printStackTrace();

    }
    System.out.println("Sucessfully updated");
    Gson gson = new Gson();
    gson.toJson(user);
    System.out.println(gson.toJson(user));
    JSONObject jsonObject = new JSONObject();
    JSONObject jsonObjectData = new JSONObject();
    try {
      jsonObjectData.put(NestlingsUtil.USER_TYPE, user.getUserType());
      jsonObjectData.put(NestlingsUtil.USER_EXIST, 0);
      jsonObjectData.put(NestlingsUtil.USER_ID, user.getUserID());
      jsonObject.put(NestlingsUtil.STATUS, NestlingsUtil.SUCCESS);
      jsonObject.put(NestlingsUtil.DATA, jsonObjectData);
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return jsonObject.toString();
  }

  @Override
  public String updateProfile(UserBean userBean) {

    Session session = sessionFactory.getCurrentSession();
    User user = new User();
    user = (User) session.get(User.class, userBean.getUserID());

    if (userBean.getFirstName() != null) {
      user.setFirstName(userBean.getFirstName());
    }
    if (userBean.getMiddleName() != null) {
      user.setMiddleName(userBean.getMiddleName());
    }
    if (userBean.getLastName() != null) {
      user.setLastName(userBean.getLastName());
    }
    if (userBean.getGenderID() != null) {
      user.setGenderID(userBean.getGenderID());
    }
    if (userBean.getPhoneNumber() != null) {
      user.setPhoneNumber(userBean.getPhoneNumber());
    }
    if (userBean.getDob() != null) {
      user.setDob(userBean.getDob());
    }
    if (userBean.getAddressID() != null) {
      user.setAddressID(userBean.getAddressID());
    }
    if (userBean.getHomeAddrsID() != 0) {
      user.setHomeAddrsID(userBean.getHomeAddrsID());
    }

    if (userBean.getStatus() != null) {
      user.setStatus(userBean.getStatus());
    }

    if (userBean.getHereFor() != null) {
      user.setHereFor(userBean.getHereFor());
    }
    session.saveOrUpdate(user);

    return null;
  }

  @Override
  public List<User> search(UserBean userBean) {
    // TODO Auto-generated method stub
    User user = new User();
    List<User> userList = null;
    try {
      Session session = sessionFactory.getCurrentSession();
      Criteria criteria = session.createCriteria(User.class);
      Disjunction or = Restrictions.disjunction();
      if (userBean.getFirstName() != null && !userBean.getFirstName().trim().equalsIgnoreCase(""))
        or.add(Restrictions.like("firstName", "%" + userBean.getFirstName() + "%"));

      if (userBean.getLastName() != null && !userBean.getLastName().trim().equalsIgnoreCase(""))
        or.add(Restrictions.like("lastName", "%" + userBean.getLastName() + "%"));

      if (userBean.getEmailID() != null && !userBean.getEmailID().trim().equalsIgnoreCase(""))
        or.add(Restrictions.like("emailID", "%" + userBean.getEmailID() + "%"));

      if (userBean.getStatus() != null && !userBean.getStatus().equalsIgnoreCase("Any Status"))
        or.add(Restrictions.like("status", "%" + userBean.getStatus() + "%"));
      criteria.add(or);
      userList = (List<User>) criteria.list();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return userList;
  }

  //////// Please saveCoverImage >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  @Override
  public String saveCoverImage(UserBean userBean) {
    // TODO Auto-generated method stub

    User user = new User();
    Session session = sessionFactory.getCurrentSession();
    System.out.println("User DAo UserID=" + user.getUserID());
    System.out.println("UserDAo saveCoverImage");
    user = (User) session.get(User.class, userBean.getUserID());
    if (userBean.getCoverImage() != null)
      user.setCoverImage(userBean.getCoverImage());
    if (userBean.getCoverCropImage() != null)
      user.setCoverCropImage(userBean.getCoverCropImage());
    /*
     * try { System.out.println("Blob creating"); Blob blob =
     * Hibernate.getLobCreator(sessionFactory.getCurrentSession())
     * .createBlob(userBean.getFile().getInputStream(),
     * userBean.getFile().getSize()); System.out.println("Blob created");
     * user.setCoverImage(blob);
     * 
     * } catch (IOException e) { e.printStackTrace(); }
     */

    try {

      session.saveOrUpdate(user);
      System.out.println("database saved");

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.out.println("database not saving");
      return "{'status':'failed'}";
    }

    return "{'status':'Success'}";
  }

  @Override
  public void authenticate(int id) {
    Session session = sessionFactory.getCurrentSession();
    User user = new User();
    user = (User) session.get(User.class, id);
    user.setVerified(true);
    session.saveOrUpdate(user);
    // TODO Auto-generated method stub

  }

  @Override
  public String login(UserBean userBean) {
    Session session = sessionFactory.getCurrentSession();
    String SQL_QUERY = "";
    Query query = null;
    System.out.println("facebook ID: " + userBean.getFaceBookID());
    System.out.println("isFaceBookLogin :" + userBean.isFaceBookLogin());

    JSONObject jsonObject = new JSONObject();
    if (userBean.getFaceBookID() == null && userBean.getGoogleID() == null) {
      SQL_QUERY = " from User as o where o.emailID=? and o.password=?";
      query = session.createQuery(SQL_QUERY);
      query.setParameter(0, userBean.getEmailID());
      query.setParameter(1, userBean.getPassword());

      List<User> list = query.list();
      if ((list != null) && (list.size() > 0)) {
        System.out.println("User found");
        try {
          jsonObject.put(NestlingsUtil.STATUS, NestlingsUtil.SUCCESS);
          jsonObject.put(NestlingsUtil.USER_ID, list.get(0).getUserID());
          jsonObject.put(NestlingsUtil.USER_TYPE, list.get(0).getUserType());
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      } else {
        try {
          jsonObject.put(NestlingsUtil.STATUS, NestlingsUtil.FAIL);
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }

    } else if (userBean.getFaceBookID() != null && userBean.getGoogleID() == null) {
      SQL_QUERY = " from User as o where o.faceBookID=?";
      query = session.createQuery(SQL_QUERY);
      query.setParameter(0, userBean.getFaceBookID());

      List<User> list = query.list();

      if ((list != null) && (list.size() > 0)) {
        System.out.println("Facebook User found");
        try {
          jsonObject.put(NestlingsUtil.STATUS, NestlingsUtil.SUCCESS);
          jsonObject.put(NestlingsUtil.USER_ID, list.get(0).getUserID());
          jsonObject.put(NestlingsUtil.USER_TYPE, list.get(0).getUserType());
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      } else {
        String userRegisterRetun = register(userBean);
        System.out.println("Facebook registered returned Data :" + userRegisterRetun);
        try {
          JSONObject jsonObjectRegi = new JSONObject(userRegisterRetun);
          jsonObject = (JSONObject) jsonObjectRegi.get(NestlingsUtil.DATA);
          jsonObject.put(NestlingsUtil.STATUS, NestlingsUtil.SUCCESS);
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }

    } else if (userBean.getFaceBookID() == null && userBean.getGoogleID() != null) {
      SQL_QUERY = " from User as o where o.googleID=?";
      query = session.createQuery(SQL_QUERY);
      query.setParameter(0, userBean.getGoogleID());

      List<User> list = query.list();

      if ((list != null) && (list.size() > 0)) {
        System.out.println("Google plus User found");
        try {
          jsonObject.put(NestlingsUtil.STATUS, NestlingsUtil.SUCCESS);
          jsonObject.put(NestlingsUtil.USER_ID, list.get(0).getUserID());
          jsonObject.put(NestlingsUtil.USER_TYPE, list.get(0).getUserType());
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      } else {
        String userRegisterRetun = register(userBean);
        System.out.println("Google Plus registered returned Data :" + userRegisterRetun);
        try {
          JSONObject jsonObjectRegi = new JSONObject(userRegisterRetun);
          jsonObject = (JSONObject) jsonObjectRegi.get(NestlingsUtil.DATA);
          jsonObject.put(NestlingsUtil.STATUS, NestlingsUtil.SUCCESS);
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    return jsonObject.toString();

  }

  @Override
  public void forgetPassword(UserBean userBean) {
    // TODO Auto-generated method stub

  }

  @Override
  public User fetch(int userID) {
    // TODO Auto-generated method stub
    User user = new User();

    try {

      Session session = sessionFactory.getCurrentSession();

      user = (User) session.get(User.class, userID);

      System.out.println("user.id:" + user.getUserID());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return user;

  }

  // Adding image or file to database >>>>>>>>>>>>>>>
  @Override
  public String saveProfileImage(UserBean userBean) {
    // TODO Auto-generated method stub
    User user = new User();
    Session session = sessionFactory.getCurrentSession();
    System.out.println("userDAo saveProfileImage");
    user = (User) session.get(User.class, userBean.getUserID()); // userBean.getUserID()
    System.out.println("user in Dao");
    System.out.println("userID in DAO" + user.getUserID());
    System.out.println("user Image" + userBean.getProfileImage());
    if (userBean.getProfileImage() != null) {
      user.setProfileImage(userBean.getProfileImage());
    }

    if (userBean.getProfileCropImage() != null) {
      user.setProfileCropImage(userBean.getProfileCropImage());
    }
    try {

      session.saveOrUpdate(user);
      System.out.println("database saved");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.out.println("database saving");
      return "{'status':'failed'}";
    }

    JSONObject jsonObject = new JSONObject();
    try {
      jsonObject.put(NestlingsUtil.STATUS, NestlingsUtil.SUCCESS);
      jsonObject.put(NestlingsUtil.USER_ID, user.getUserID());
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    System.out.println(jsonObject.toString());

    return jsonObject.toString(); // jsonObject.toString();
  }
  // <<<<<<<<<< Adding image or file to database

  //////////// Retrive image or Files >>>>>>>>
  @Override
  public User downloadImage(Integer userID) {
    Session session = sessionFactory.getCurrentSession();
    return (User) session.get(User.class, userID);
  }
  // <<<<<<<<<<<< Retrive image or Files

  //////////// Retrive cover image or Files >>>>>>>>
  @Override
  public User downloadCoverImage(Integer userID) {
    Session session = sessionFactory.getCurrentSession();
    return (User) session.get(User.class, userID);
  }

  //// searching for already exist emailID >>>>>>>>
  @Override
  public String searchEmail(UserBean userBean) {

    // TODO Auto-generated method stub

    Session session = sessionFactory.getCurrentSession();
    String SQL_QUERY = " from User as o where o.emailID=?";
    Query query = session.createQuery(SQL_QUERY);
    query.setParameter(0, userBean.getEmailID());
    List<User> list = query.list();
    if ((list != null) && (list.size() > 0)) {
      System.out.println("User Dao layer: User exist");

      JSONObject jsonObject = new JSONObject();
      JSONObject jsonObjectData = new JSONObject();
      try {
        jsonObjectData.put(NestlingsUtil.USER_EXIST, 1);
        jsonObject.put(NestlingsUtil.STATUS, NestlingsUtil.SUCCESS);
        jsonObject.put(NestlingsUtil.DATA, jsonObjectData);
      } catch (JSONException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return jsonObject.toString();

    }

    return null;

  }

  // userID fetch + Username fetch

  @Override
  public String getUserName(int userID) {
    // TODO Auto-generated method stub
    User user = new User();
    Gson gson = new Gson();
    UserBean userbean = new UserBean();
    String json = null;
    try {

      Session session = sessionFactory.getCurrentSession();

      user = (User) session.get(User.class, userID);
      userbean.setUserID(user.getUserID());
      userbean.setFirstName(user.getFirstName());
      userbean.setMiddleName(user.getMiddleName());
      userbean.setLastName(user.getLastName());
      userbean.setUserType(user.getUserType());
      userbean.setEmailID(user.getEmailID());

      json = gson.toJson(userbean);
      System.out.println("user.id:" + user.getUserID());
      System.out.println("FirstName:" + user.getFirstName());
      System.out.println("MiddleName:" + user.getMiddleName());
      System.out.println("LastName:" + user.getLastName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return json;

  }
  
  @Override
  public User getUserDetails(int userID) {
    // TODO Auto-generated method stub
    User user = new User();
    Gson gson = new Gson();
    UserBean userbean = new UserBean();
    String json = null;
    try {

      Session session = sessionFactory.getCurrentSession();

      user = (User) session.get(User.class, userID);
      System.out.println("user.id:" + user.getUserID());
      System.out.println("FirstName:" + user.getFirstName());
      System.out.println("MiddleName:" + user.getMiddleName());
      System.out.println("LastName:" + user.getLastName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return user;
    
  }
  //// get mentor relationship list>>>>>>>>>>>>

  /// Faculty List>>>>>>>>
  @Override
  public String getFacultyList() {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    Gson gson = new Gson();
    String SQL_QUERY = "  from User as o where o.userType =? ";
    Query query = session.createQuery(SQL_QUERY);

    query.setParameter(0, NestlingsUtil.UserType.LECTURER);
    List<User> list = query.list();
    String json = gson.toJson(list);
    return json;
  }

  //// Alumni List>>>>

  @Override
  public String getAlumniList() {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    Gson gson = new Gson();
    String SQL_QUERY = "  from User as o where o.userType =? ";
    Query query = session.createQuery(SQL_QUERY);

    query.setParameter(0, NestlingsUtil.UserType.ALUMNI);
    List<User> list = query.list();
    String json = gson.toJson(list);
    return json;
  }

  // get user search list for all query
//  @Override
//  public List<SearchBean> getUsersSearchAll(String searchName) {
//    // TODO Auto-generated method stub
//    Session session = sessionFactory.getCurrentSession();
//    /// SELECT o.collegeName, o.userID
//    String hql = "from User as o where o.firstName like ?";
//    Query query = session.createQuery(hql);
//    query.setParameter(0, searchName + "%");
//    List<User> results = query.list();
//    List<SearchBean> userList = new ArrayList<SearchBean>();
//    for (User list : results) {
//      SearchBean searchData = new SearchBean();
//      searchData.setName(list.getFirstName() + " " + list.getLastName());
//      searchData.setUserID(list.getUserID());
//      searchData.setUserType(list.getUserType());
//      userList.add(searchData);
//    }
//    return userList;
//
//  }

  @Override
  public User checkMailExist(String emailID) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();

    List<User> list;
    try {
      String SQL_QUERY = "from User as o where o.emailID = ?";
      Query query = session.createQuery(SQL_QUERY);
      query.setParameter(0, emailID);
      list = query.list();
      if ((list != null) && (list.size() > 0)) {
        System.out.println(" " + list.get(0).toString());
        return list.get(0);
      }

    } catch (HibernateException e) {
      // TODO Auto-generated catch block
      System.out.println("error message " + e.getMessage());
      /* e.printStackTrace(); */

    }
    /*
     * System.out.println(" "+list.get(0).toString()); if((list != null) &&
     * (list.size() > 0)){ System.out.println(" "+list.get(0).toString());
     * return list.get(0).toString(); } else{ return "null"; }
     */
    return null;

  }

  @Override
  public String resetPassword(UserBean userBean) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    User user = new User();
    System.out.println("user ID:" +userBean.getUserID());
    try {
      user = (User) session.get(User.class, userBean.getUserID());
      user.setPassword(userBean.getPassword());
      session.saveOrUpdate(user);
    } catch (HibernateException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    JSONObject jsonObject = new JSONObject();
    try {

      jsonObject.put(NestlingsUtil.STATUS, NestlingsUtil.SUCCESS);
      jsonObject.put(NestlingsUtil.DATA, "Password changed");
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return jsonObject.toString();
  }

  @Override
  public String sendLostPasswordToken(UserBean userBean) {
    // TODO Auto-generated method stub
    /*
     * Assert.notNull(userBean.getEmailID()); VerificationToken token = null;
     * User user = userRepository.findByEmailAddress(userBean.getEmailID()); if
     * (user != null) { token = user.getActiveLostPasswordToken(); if (token ==
     * null) { token = new VerificationToken(user,
     * VerificationToken.VerificationTokenType.lostPassword,
     * config.getLostPasswordTokenExpiryTimeInMinutes());
     * user.addVerificationToken(token); userRepository.save(user); }
     * emailServicesGateway.sendVerificationToken(new
     * EmailServiceTokenModel(user, token, getConfig().getHostNameUrl())); }
     * 
     * return token;
     */
    return null;
  }

  @Override
  public String saveProfileImage1(UserBean userBean) {
    // TODO Auto-generated method stub

    if (userBean == null) {
      System.out.println("Null Obj");
      return null;
    }

    User user = fetch(userBean.getUserID());

    if (userBean.getFile() != null) {

      String storePathNName = "/var/lib/tomcat7/webapps/media/profile-images/profile" + userBean.getUserID();
      File newFile = NestlingsUtil.saveFileToServer(userBean.getFile(), storePathNName);
      user.setProfileImg(
          "http://ec2-52-37-130-221.us-west-2.compute.amazonaws.com:8080/media/profile-images/" + newFile.getName());
      updateUser(user);
    }

    return "Image succefully added";
  }

  @Override
  public String saveCoverImage1(UserBean userBean) {
    // TODO Auto-generated method stub
    if (userBean == null) {
      System.out.println("Null Obj");
      return null;
    }

    User user = fetch(userBean.getUserID());

    if (userBean.getFile() != null) {

      String storePathNName = "/var/lib/tomcat7/webapps/media/profile-images/cover" + userBean.getUserID();
      File newFile = NestlingsUtil.saveFileToServer(userBean.getFile(), storePathNName);
      user.setCoverImg(
          "http://ec2-52-37-130-221.us-west-2.compute.amazonaws.com:8080/media/profile-images/" + newFile.getName());
      updateUser(user);
    }

    return "Image succefully added";
  }

  @Override
  public String updateUser(User user) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    try {
      session.saveOrUpdate(user);
    } catch (HibernateException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }

    return "Succes";
  }

}
