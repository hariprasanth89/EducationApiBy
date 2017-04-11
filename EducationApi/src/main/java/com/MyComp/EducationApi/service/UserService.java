package com.MyComp.EducationApi.service;

import java.util.List;

import com.MyComp.EducationApi.bean.UserBean;
import com.MyComp.EducationApi.model.User;

public interface UserService {

  String register(UserBean userBean);

  String updateProfile(UserBean userBean);

  void authenticate(int id);

  String login(UserBean userBean);

  String fetch(int userID);

  String saveProfileImage(UserBean userBean);

  User downloadImage(Integer userID);

  String getUserName(int userID);

  String getFacultyList();

  String getAlumniList();

  User getCoverImage(Integer userID);

  User downloadCoverImage(Integer userID);

  String saveCoverImage(UserBean userBean);

  String forgetPassword(String emailID);

  String resetPassword(UserBean userBean);

  String saveProfileImage1(UserBean userBean);

  String saveCoverImage1(UserBean userBean);

  String askRecommend(int userID);

  List<User> search(UserBean userBean);
  
  User getUserDetails(int userID);
}
