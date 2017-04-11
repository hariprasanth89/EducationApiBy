package com.MyComp.EducationApi.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.MyComp.EducationApi.utility.NestlingsUtil.*;

@Entity
@Table(name = "user")
@DynamicUpdate
public class User implements Serializable {
  private static final long serialVersionUID = -723583058586873479L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  @Column(name = "userID")
  private int userID;

  @Column(name = "userType")
  private UserType userType;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "middleName")
  private String middleName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "genderID")
  private GenderID genderID;

  @Column(name = "loginType")
  private LoginType loginType;

  @Column(name = "emailID")
  private String emailID;

  @Column(name = "phoneNumber")
  private String phoneNumber;

  @Column(name = "password")
  private String password;

  @Column(name = "forgotPassword")
  private String forgotPassword;

  @Column(name = "dob")
  private Date dob;

  @Column(name = "refreshToken")
  private String refreshToken;

  @Column(name = "accessToken")
  private String accessToken;

  @Column(name = "isVerified", nullable = false, columnDefinition = "BIT", length = 1)
  private boolean isVerified;

  @Column(name = "isDeleted", nullable = false, columnDefinition = "BIT", length = 1)
  private boolean isDeleted;

  @Column(name = "createdDate")
  private Date createdDate;

  @Column(name = "updatedDate")
  private Date updatedDate;

  @Column(name = "isEmailSent", nullable = false, columnDefinition = "BIT", length = 1)
  private boolean isEmailSent;

  @Column(name = "authenticationToken")
  private String authenticationToken;

  @Column(name = "profileImage")
  @Lob
  private Blob profileImage;

  @Column(name = "profileCropImage")
  @Lob
  private Blob profileCropImage;

  @Column(name = "coverImage")
  @Lob
  private Blob coverImage;

  @Column(name = "coverCropImage")
  @Lob
  private Blob coverCropImage;

  @Column(name = "isFaceBookLogin", nullable = false, columnDefinition = "BIT", length = 1)
  private boolean isFaceBookLogin;

  @Column(name = "faceBookID")
  private String faceBookID;

  @Column(name = "addressID")
  private String addressID;

  @Column(name = "status")
  private String status;

  @Column(name = "homeAddrsID")
  private int homeAddrsID;

  @Column(name = "profileImg")
  private String profileImg;

  @Column(name = "coverImg")
  private String coverImg;

  @Column(name = "isGPlusLogin", nullable = false, columnDefinition = "BIT", length = 1)
  private boolean isGPlusLogin = false;

  @Column(name = "googleID")
  private String googleID;

  @Column(name = "hereFor")
  private String hereFor;

  @Column(name = "passwordResetToken")
  private String passwordResetToken;

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

  public String getAddressID() {
    return addressID;
  }

  public void setAddressID(String addressID) {
    this.addressID = addressID;
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

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Blob getProfileImage() {
    return profileImage;
  }

  public void setProfileImage(Blob profileImage) {
    this.profileImage = profileImage;
  }

  public Blob getProfileCropImage() {
    return profileCropImage;
  }

  public void setProfileCropImage(Blob profileCropImage) {
    this.profileCropImage = profileCropImage;
  }

  public Blob getCoverImage() {
    return coverImage;
  }

  public void setCoverImage(Blob coverImage) {
    this.coverImage = coverImage;
  }

  public Blob getCoverCropImage() {
    return coverCropImage;
  }

  public void setCoverCropImage(Blob coverCropImage) {
    this.coverCropImage = coverCropImage;
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

  public String getProfileImg() {
    return profileImg;
  }

  public void setProfileImg(String profileImg) {
    this.profileImg = profileImg;
  }

  public String getCoverImg() {
    return coverImg;
  }

  public void setCoverImg(String coverImg) {
    this.coverImg = coverImg;
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

  public String getPasswordResetToken() {
    return passwordResetToken;
  }

  public void setPasswordResetToken(String passwordResetToken) {
    this.passwordResetToken = passwordResetToken;
  }

}
