package com.MyComp.EducationApi.bean;

import java.sql.Blob;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.MyComp.EducationApi.utility.NestlingsUtil.*;

public class UserBean {

  private int userID;
  private UserType userType;
  private String firstName;
  private String middleName;
  private String lastName;
  private GenderID genderID;
  private LoginType loginType;
  private String emailID;
  private String phoneNumber;
  private String password;
  private String forgotPassword;
  private Date dob;
  private String refreshToken;
  private String accessToken;
  private boolean isVerified;
  private boolean isDeleted;
  private Date createdDate;
  private Date updatedDate;
  private boolean isEmailSent;
  private String authenticationToken;
  private Blob profileImage;
  private Blob coverImage;
  private String addressID;
  private boolean isFaceBookLogin = false;
  private String faceBookID;
  private MultipartFile file;
  private String referenceCode;
  private String passwordResetToken;
  private String status;
  private int homeAddrsID;
  private String profileCropImageUrl;
  private String profileImageUrl;
  private Blob profileCropImage;
  private String coverCropImageUrl;
  private String coverImageUrl;
  private Blob coverCropImage;
  private String hereFor;

  public String getCoverCropImageUrl() {
    return coverCropImageUrl;
  }

  public void setCoverCropImageUrl(String coverCropImageUrl) {
    this.coverCropImageUrl = coverCropImageUrl;
  }

  public String getCoverImageUrl() {
    return coverImageUrl;
  }

  public void setCoverImageUrl(String coverImageUrl) {
    this.coverImageUrl = coverImageUrl;
  }

  public Blob getCoverCropImage() {
    return coverCropImage;
  }

  public void setCoverCropImage(Blob coverCropImage) {
    this.coverCropImage = coverCropImage;
  }

  public String getProfileCropImageUrl() {
    return profileCropImageUrl;
  }

  public void setProfileCropImageUrl(String profileCropImageUrl) {
    this.profileCropImageUrl = profileCropImageUrl;
  }

  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  public Blob getProfileCropImage() {
    return profileCropImage;
  }

  public void setProfileCropImage(Blob profileCropImage) {
    this.profileCropImage = profileCropImage;
  }

  private boolean isGPlusLogin = false;
  private String googleID;

  public boolean isFaceBookLogin() {
    return isFaceBookLogin;
  }

  public void setFaceBookLogin(boolean isFaceBookLogin) {
    this.isFaceBookLogin = isFaceBookLogin;
  }

  public String getFaceBookID() {
    return faceBookID;
  }

  public void setFaceBookID(String faceBookID) {
    this.faceBookID = faceBookID;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public GenderID getGenderID() {
    return genderID;
  }

  public void setGenderID(GenderID genderID) {
    this.genderID = genderID;
  }

  public LoginType getLoginType() {
    return loginType;
  }

  public void setLoginType(LoginType loginType) {
    this.loginType = loginType;
  }

  public String getEmailID() {
    return emailID;
  }

  public void setEmailID(String emailID) {
    this.emailID = emailID;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getForgotPassword() {
    return forgotPassword;
  }

  public void setForgotPassword(String forgotPassword) {
    this.forgotPassword = forgotPassword;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public boolean isVerified() {
    return isVerified;
  }

  public void setVerified(boolean isVerified) {
    this.isVerified = isVerified;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public boolean isEmailSent() {
    return isEmailSent;
  }

  public void setEmailSent(boolean isEmailSent) {
    this.isEmailSent = isEmailSent;
  }

  public String getAuthenticationToken() {
    return authenticationToken;
  }

  public void setAuthenticationToken(String authenticationToken) {
    this.authenticationToken = authenticationToken;
  }

  public Blob getProfileImage() {
    return profileImage;
  }

  public void setProfileImage(Blob profileImage) {
    this.profileImage = profileImage;
  }

  public Blob getCoverImage() {
    return coverImage;
  }

  public void setCoverImage(Blob coverImage) {
    this.coverImage = coverImage;
  }

  public String getAddressID() {
    return addressID;
  }

  public void setAddressID(String addressID) {
    this.addressID = addressID;
  }

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }

  public String getReferenceCode() {
    return referenceCode;
  }

  public void setReferenceCode(String referenceCode) {
    this.referenceCode = referenceCode;
  }

  public String getPasswordResetToken() {
    return passwordResetToken;
  }

  public void setPasswordResetToken(String passwordResetToken) {
    this.passwordResetToken = passwordResetToken;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getHomeAddrsID() {
    return homeAddrsID;
  }

  public void setHomeAddrsID(int homeAddrsID) {
    this.homeAddrsID = homeAddrsID;
  }

  public boolean isGPlusLogin() {
    return isGPlusLogin;
  }

  public void setGPlusLogin(boolean isGPlusLogin) {
    this.isGPlusLogin = isGPlusLogin;
  }

  public String getGoogleID() {
    return googleID;
  }

  public void setGoogleID(String googleIDToken) {
    this.googleID = googleIDToken;
  }

  public String getHereFor() {
    return hereFor;
  }

  public void setHereFor(String hereFor) {
    this.hereFor = hereFor;
  }
}
