package com.MyComp.EducationApi.dao;

import java.util.List;

import com.MyComp.EducationApi.bean.UserBean;
import com.MyComp.EducationApi.model.User;

public interface UserDao {

  String register(UserBean userBean);

  String updateProfile(UserBean userBean);

  void authenticate(int id);

  String login(UserBean userBean);

  void forgetPassword(UserBean userBean);

  User fetch(int userID);

  String saveProfileImage(UserBean userBean);

  User downloadImage(Integer userID);

  String searchEmail(UserBean userBean);

  String getUserName(int userID);

  String getFacultyList();

  String getAlumniList();

  String saveCoverImage(UserBean userBean);

  User downloadCoverImage(Integer userID);

  User checkMailExist(String emailID);

  String resetPassword(UserBean userBean);

  String sendLostPasswordToken(UserBean userBean);

  String saveProfileImage1(UserBean userBean);

  String saveCoverImage1(UserBean userBean);

  String updateUser(User user);

  List<User> search(UserBean userBean);
  
  User getUserDetails(int userID);
}
