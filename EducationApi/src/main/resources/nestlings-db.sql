CREATE DATABASE IF NOT EXISTS development;
Use development;
CREATE USER IF NOT EXISTS 'UserNestlings'@'localhost' IDENTIFIED WITH mysql_native_password AS '#Nest189#';
GRANT USAGE ON *.* TO 'UserNestlings'@'localhost' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
GRANT ALL PRIVILEGES ON `development`.* TO 'UserNestlings'@'localhost';

CREATE TABLE IF NOT EXISTS `userinfo` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(100) NOT NULL,
  `LastName` varchar(100) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `emailID` varchar(70) NOT NULL,
  `password` varchar(100) NOT NULL,
  `DOB` date DEFAULT NULL,
  `StreetName` varchar(100) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  `State` varchar(50) DEFAULT NULL,
  `Country` varchar(50) DEFAULT NULL,
  `ZIP` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(45) NOT NULL AUTO_INCREMENT,
  `userType` int(45) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `middleName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `genderID` int(45) DEFAULT NULL,
  `loginType` int(70) DEFAULT NULL,
  `emailID` varchar(70) NOT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `forgotPassword` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `refreshToken` varchar(45) DEFAULT NULL,
  `accessToken` varchar(45) DEFAULT NULL,
  `isVerified` bit(1) DEFAULT NULL,
  `isDeleted` bit(1) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `isEmailSent` bit(1) DEFAULT NULL,
  `authenticationToken` varchar(45) DEFAULT NULL,
  `profileImage` longblob,
  `isFaceBookLogin` bit(1) DEFAULT NULL,
  `faceBookID` varchar(20) DEFAULT NULL,
  `addressID` varchar(20) DEFAULT NULL,
  `coverImage` longblob,
  `status` varchar(255) DEFAULT NULL,
  `homeAddrsID` int(11) DEFAULT '0',
  `profileImg` varchar(200) DEFAULT NULL,
  `coverImg` varchar(200) DEFAULT NULL,
  `isGPlusLogin` bit(1) DEFAULT b'0',
  `googleID` varchar(30) DEFAULT NULL,
  `hereFor` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `emailID_UNIQUE` (`emailID`,`faceBookID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET @profileCropImage = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'user'
        AND table_schema = DATABASE()
        AND column_name = 'profileCropImage'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE user ADD profileCropImage LONGBLOB"
));

PREPARE stmt FROM @profileCropImage;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @coverCropImage = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'user'
        AND table_schema = DATABASE()
        AND column_name = 'coverCropImage'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE user ADD coverCropImage LONGBLOB"
));

PREPARE stmt FROM @coverCropImage;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @passwordResetToken = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'user'
        AND table_schema = DATABASE()
        AND column_name = 'passwordResetToken'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE user ADD passwordResetToken varchar(30) DEFAULT NULL"
));
PREPARE stmt FROM @passwordResetToken;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

CREATE TABLE IF NOT EXISTS `address` (
  `addressID` int(11) NOT NULL AUTO_INCREMENT,
  `emailID` varchar(75) DEFAULT NULL,
  `streetName` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `stateCode` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `countryCode` varchar(45) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `isd` int(45) DEFAULT NULL,
  `phoneNumber` int(11) DEFAULT NULL,
  `latitude` varchar(45) DEFAULT NULL,
  `longitude` varchar(45) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `isActive` bit(1) DEFAULT b'1',
  PRIMARY KEY (`addressID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET @addressLatitudeModify = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'address'
        AND table_schema = DATABASE()
        AND column_name = 'latitude'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE address MODIFY latitude decimal(10,7)"
));
PREPARE stmt FROM @addressLatitudeModify;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @addressLongitudeModify = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'address'
        AND table_schema = DATABASE()
        AND column_name = 'longitude'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE address MODIFY longitude decimal(10,7)"
));
PREPARE stmt FROM @addressLongitudeModify;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @addressDistance = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'address'
        AND table_schema = DATABASE()
        AND column_name = 'distance'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE address ADD distance int default 0"
));
PREPARE stmt FROM @addressDistance;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

CREATE TABLE IF NOT EXISTS `student` (
  `studentID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(45) NOT NULL,
  `ambition` varchar(45) DEFAULT NULL,
  `collegeID` int(10) DEFAULT NULL,
  `favoriteSub` varchar(45) DEFAULT NULL,
  `trainingID` int(20) DEFAULT NULL,
  `internshipID` int(20) DEFAULT NULL,
  `talent` varchar(255) DEFAULT NULL,
  `hobbies` varchar(255) DEFAULT NULL,
  `publish` int(10) DEFAULT NULL,
  `honors` varchar(255) DEFAULT NULL,
  `accadRecommand` varchar(255) DEFAULT NULL,
  `strength` varchar(255) DEFAULT NULL,
  `improvement` varchar(255) DEFAULT NULL,
  `ethnicity` varchar(255) DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  PRIMARY KEY (`studentID`),
  INDEX  `usr_id` (`userID`),
  FOREIGN KEY (`userID`)
  	REFERENCES user(`userID`)
  	ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `studentcollege` (
  `studentCollegeID` int(11) NOT NULL AUTO_INCREMENT,
  `collegeID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `grade` varchar(10) DEFAULT NULL,
  `course` varchar(100) DEFAULT NULL,
  `currentStudy` bit(1) DEFAULT NULL,
  `gpa`  float(4,2) DEFAULT NULL,
  `gre` float(4,2) DEFAULT NULL,
  `toefl` float(4,2) DEFAULT NULL,
  `gpaY1` float DEFAULT NULL,
  `gpaY2` float DEFAULT NULL,
  `gpaY3` float DEFAULT NULL,
  `regAvgY1` float DEFAULT NULL,
  `regAvgY2` float DEFAULT NULL,
  `regAvgY3` float DEFAULT NULL,
  `degreeType` varchar(50) DEFAULT NULL,
  `major` varchar(100) DEFAULT NULL,
  `minor` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`studentCollegeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `studentwork` (
  `studentWorkID` int(11) NOT NULL AUTO_INCREMENT,
  `companyID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `current` bit(1) DEFAULT NULL,
  `description` VARCHAR (500) DEFAULT NULL,
  `type` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`studentWorkID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `studenttraining` (
  `studentTrainingID` int(11) NOT NULL AUTO_INCREMENT,
  `companyID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `course` varchar(100) DEFAULT NULL,
  `current` bit(1) DEFAULT NULL,
  PRIMARY KEY (`studentTrainingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `studentproject` (
  `studentProjectID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `companyID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `current` bit(1) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `responsibilities` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`studentProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `faculty` (
  `facultyID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(45) DEFAULT NULL,
  `summary` varchar(1000) DEFAULT NULL,
  `eduQualification` varchar(100) DEFAULT NULL,
  `uniAttended` int(11) DEFAULT NULL,
  `researchAffiliation` bit(1) DEFAULT NULL,
  `researchAssistance` bit(1) DEFAULT NULL,
  `teachingAssistance` bit(1) DEFAULT NULL,
  `experience` varchar(100) DEFAULT NULL,
  `projects` varchar(100) DEFAULT NULL,
  `specializations` varchar(45) DEFAULT NULL,
  `mentor` bit(1) DEFAULT NULL,
  `followCompanies` varchar(45) DEFAULT NULL,
  `publication` int(11) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `conferenceSpeaker` bit(1) DEFAULT NULL,
  `visitingProfessor` bit(1) DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  PRIMARY KEY (`facultyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `facultycollege` (
  `facultyCollegeID` int(11) NOT NULL AUTO_INCREMENT,
  `collegeID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `grade` varchar(10) DEFAULT NULL,
  `cunrrentStudy` bit(1) DEFAULT NULL,
  PRIMARY KEY (`facultyCollegeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `facultywork` (
  `facultyworkID` int(11) NOT NULL AUTO_INCREMENT,
  `collegeID` int(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `addressID` int(45) DEFAULT NULL,
  `current` bit(1) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  PRIMARY KEY (`facultyworkID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `facultyproject` (
  `facultyProjectID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `companyID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `current` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `responsibilities` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`facultyProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `alumni` (
  `alumniID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(45) DEFAULT NULL,
  `summary` varchar(100) DEFAULT NULL,
  `eduQualification` varchar(100) DEFAULT NULL,
  `uniAttended` varchar(100) DEFAULT NULL,
  `researchAffiliation` bit(1) DEFAULT NULL,
  `LookingForInterns` bit(1) DEFAULT NULL,
  `experience` int(10) DEFAULT NULL,
  `alumniOf` varchar(100) DEFAULT NULL,
  `graduationYear` int(100) DEFAULT NULL,
  `fraternitiesOrSororities` varchar(100) DEFAULT NULL,
  `bestDay` varchar(100) DEFAULT NULL,
  `worstDay` varchar(100) DEFAULT NULL,
  `dorm` varchar(100) DEFAULT NULL,
  `professor` varchar(100) DEFAULT NULL,
  `cafeteria` varchar(100) DEFAULT NULL,
  `versity` varchar(100) DEFAULT NULL,
  `alumniRecommendation` varchar(100) DEFAULT NULL,
  `nextBestAlumni` varchar(100) DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  PRIMARY KEY (`alumniID`),
  INDEX  `usr_id` (`userID`),
  FOREIGN KEY (`userID`)
  	REFERENCES user(`userID`)
  	ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `alumnicollege` (
  `alumniCollegeID` int(11) NOT NULL AUTO_INCREMENT,
  `collegeID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `course` varchar(100) DEFAULT NULL,
  `grade` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`alumniCollegeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `alumniwork` (
  `alumniWorkID` int(11) NOT NULL AUTO_INCREMENT,
  `companyID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`alumniWorkID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `alumniproject` (
  `alumniProjectID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `companyID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `current` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `responsibilities` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`alumniProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `bank` (
  `bankID` int(11) NOT NULL AUTO_INCREMENT,
  `accountType` int(1) DEFAULT NULL,
  `accountEligibility` varchar(45) DEFAULT NULL,
  `accountProcessDays` int(45) DEFAULT NULL,
  `bankName` varchar(45) DEFAULT NULL,
  `loanEligibility` varchar(45) DEFAULT NULL,
  `loanProcessDays` int(45) DEFAULT NULL,
  `minOpeningBalance` int(45) DEFAULT NULL,
  `monthlyMaintainance` int(45) DEFAULT NULL,
  `interestTiers` int(45) DEFAULT NULL,
  `atmTransaction` int(100) DEFAULT NULL,
  `benifitInterest` int(105) DEFAULT NULL,
  `benifitCreditScore` int(45) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `isActive` bit(1) DEFAULT b'1',
   `bankImage` longblob,
  `userID` int(11) DEFAULT NULL,
  PRIMARY KEY (`bankID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `college` (
  `collegeID` int(11) NOT NULL AUTO_INCREMENT,
  `collegeName` varchar(100) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  `generalPhoneNumber` varchar(20) DEFAULT NULL,
  `financialPhoneNumber` varchar(20) DEFAULT NULL,
  `admissionsPhoneNumber` varchar(20) DEFAULT NULL,
  `email` varchar(105) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  `addressID` int(11) DEFAULT NULL,
  `certificatesOffered` varchar(200) DEFAULT NULL,
  `coursesOffered` varchar(200) DEFAULT NULL,
  `programsOffered` varchar(200) DEFAULT NULL,
  `regionalAccreditations` varchar(200) DEFAULT NULL,
  `tuitionFees` varchar(100) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `collegeImage` longblob,
  `gpa`  float(4,2) DEFAULT NULL,
  `gre` float(4,2) DEFAULT NULL,
  `toefl` float(4,2) DEFAULT NULL,
  `noOfStudents` int(10) DEFAULT NULL,
  `selectivity` varchar(200) DEFAULT NULL,
  `costToStudents` int(10) DEFAULT NULL,
  `splAffinity` varchar(200) DEFAULT NULL,
  `pcentCourseComplet` int(10) DEFAULT NULL,
  `studyOptions` varchar(250) DEFAULT NULL,
  `accreditingOrganizations` varchar(500) DEFAULT NULL,
  `environment` varchar(500) DEFAULT NULL,
  `houseAvailble` bit(1) DEFAULT NULL,
  `mealPlan` bit(1) DEFAULT NULL,
  `healthWellnesSupport` varchar(1000) DEFAULT NULL,
  `genderAdmitted` varchar(100) DEFAULT NULL,
  `religiousAffiliation` varchar(200) DEFAULT NULL,
  `specialAffinity` varchar(200) DEFAULT NULL,
  `accessibility` varchar(200) DEFAULT NULL,
  `courseCatalogue` bit(1) DEFAULT NULL,
  PRIMARY KEY (`collegeID`),
  UNIQUE KEY `emailID_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET @collegeExpiryDate = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'college'
        AND table_schema = DATABASE()
        AND column_name = 'expiryDate'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE college ADD expiryDate DATE DEFAULT NULL"
));

PREPARE stmt FROM @collegeExpiryDate;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @collegeStatus = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'college'
        AND table_schema = DATABASE()
        AND column_name = 'status'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE college ADD status int(10) DEFAULT NULL"
));

PREPARE stmt FROM @collegeStatus;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @collegeVerificationCode = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'college'
        AND table_schema = DATABASE()
        AND column_name = 'verificationCode'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE college ADD verificationCode varchar(100) DEFAULT NULL"
));

PREPARE stmt FROM @collegeVerificationCode;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

CREATE TABLE IF NOT EXISTS `company` (
  `companyID` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(45) DEFAULT NULL,
  `industryType` int(100) NULL,
  `internships` int(100) NULL,
  `valuesAndCulture` varchar(200) NULL,
  `website` varchar(100) NULL,
  `emailID` varchar(70) NULL,
  `phoneNumber` varchar(20) NULL,
  `affiliatedCompanies` varchar(200) NULL,
  `isVerified` bit(1) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `addressID` int(45) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
   `companyImage` longblob,
  PRIMARY KEY (`companyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `institution` (
  `institutionID` int(11) NOT NULL AUTO_INCREMENT,
  `institutionName` varchar(45) DEFAULT NULL,
  `specialization` varchar(100) DEFAULT NULL,
  `website` varchar(1000) DEFAULT NULL,
  `courses` varchar(1000) DEFAULT NULL,
  `certificates` int(10) DEFAULT NULL,
  `location` int(30) DEFAULT NULL,
  `affilation` varchar(100) DEFAULT NULL,
  `juniorcollege` int(10) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `institutionImage` longblob,
  PRIMARY KEY (`institutionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `scholarship` (
  `scholarshipID` int(11) NOT NULL AUTO_INCREMENT,
  `scholarshipName` varchar(100) DEFAULT NULL,
  `awardedAmount` int(45) DEFAULT NULL,
  `scholarshipType` int(45) DEFAULT NULL,
  `applicationStartDate` date DEFAULT NULL,
  `applicationEndDate` date DEFAULT NULL,
  `noOfAwardsAvailable` int(45) DEFAULT NULL,
  `selectionCriteria` varchar(100) DEFAULT NULL,
  `selectionProcess` varchar(100) DEFAULT NULL,
  `minEligibilityRequirement` varchar(100) DEFAULT NULL,
  `applyOnSite` varchar(100) DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `scholarshipImage` longblob,
  PRIMARY KEY (`scholarshipID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `friend` (
  `friendID` int(11) NOT NULL AUTO_INCREMENT,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  `friendUserID` int(11) DEFAULT NULL,
  `statusType`  int(20) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  PRIMARY KEY (`friendID`),
  INDEX  `usr_id` (`userID`),
  FOREIGN KEY (`userID`)
  	REFERENCES user(`userID`)
  	ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `friendgroup` (
  `friendGroupID` int(11) NOT NULL AUTO_INCREMENT,
  `friendGroupName` varchar(40) DEFAULT NULL,
  `createdBy` varchar(40) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  PRIMARY KEY (`friendGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `friendgroupmember` (
  `friendGroupMemberID` int(11) NOT NULL AUTO_INCREMENT,
  `friendGroupID` int(11) DEFAULT NULL,
  `userInfoID` int(11) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  PRIMARY KEY (`friendGroupMemberID`),
  KEY `userInfoID` (`userInfoID`),
  KEY `friendGroupID` (`friendGroupID`),
  CONSTRAINT `friendgroupmember_ibfk_1` FOREIGN KEY (`userInfoID`) REFERENCES `userinfo` (`UserID`),
  CONSTRAINT `friendgroupmember_ibfk_2` FOREIGN KEY (`friendGroupID`) REFERENCES `friendgroup` (`friendGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `friendrequest` (
  `friendRequestID` int(11) NOT NULL AUTO_INCREMENT,
  `userInfoID` int(11) DEFAULT NULL,
  `contactUserInfoID` int(11) DEFAULT NULL,
  `requestStatusID` int(5) DEFAULT NULL,
  `requestDate` date DEFAULT NULL,
  `actionDate` date DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `updateDate` date DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  PRIMARY KEY (`friendRequestID`),
  KEY `userInfoID` (`userInfoID`),
  KEY `contactUserInfoID` (`contactUserInfoID`),
  CONSTRAINT `friendrequest_ibfk_1` FOREIGN KEY (`userInfoID`) REFERENCES `userinfo` (`UserID`),
  CONSTRAINT `friendrequest_ibfk_2` FOREIGN KEY (`contactUserInfoID`) REFERENCES `userinfo` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `newsfeed` (
  `newsfeedID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` varchar(200) NOT NULL,
  `postdate` date NOT NULL,
  `postperson` varchar(45) NOT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `isActive` bit(1) DEFAULT b'1',
  PRIMARY KEY (`newsfeedID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `post` (
  `postID` int(11) NOT NULL AUTO_INCREMENT,
  `recieverID` int(11) NOT NULL,
  `senderID` int(11) DEFAULT NULL,
  `postDescription` varchar(100) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `noOfLikes` int(11) DEFAULT NULL,
  `noOfDislikes` int(41) DEFAULT NULL,
  `noOfComments` int(41) DEFAULT NULL,
  `isHidden` bit(1) DEFAULT NULL,
  `isDeleted` bit(1) DEFAULT NULL,
  `deletedBy` varchar(100) DEFAULT NULL,
  `deletedDate` date DEFAULT NULL,
  `deletedComments` varchar(45) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  `userType` int(5) DEFAULT NULL,
  `postType` int(45) DEFAULT NULL,
  `postContent` longblob,
  `postContentType` varchar(45) DEFAULT NULL,
  `postContentUrl` varchar(200) DEFAULT NULL,
  `eventTime` datetime DEFAULT NULL,
  `eventLocation` varchar(500) DEFAULT NULL,
  `pollOptions` varchar(200) DEFAULT NULL,
  `pollResults` varchar(20) DEFAULT NULL,
  `pollOptionCount` int(4) DEFAULT '0',
  PRIMARY KEY (`postID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='   ';

SET @postLatitudeModify = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'post'
        AND table_schema = DATABASE()
        AND column_name = 'latitude'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE post MODIFY latitude decimal(10,7)"
));

PREPARE stmt FROM @postLatitudeModify;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @postLongitudeModify = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'post'
        AND table_schema = DATABASE()
        AND column_name = 'longitude'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE post MODIFY longitude decimal(10,7)"
));

PREPARE stmt FROM @postLongitudeModify;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @postCollegeId = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'post'
        AND table_schema = DATABASE()
        AND column_name = 'collegeID'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE post ADD collegeID int"
));

PREPARE stmt FROM @postCollegeId;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @postTitle = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'post'
        AND table_schema = DATABASE()
        AND column_name = 'title'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE post ADD title varchar(100)"
));

PREPARE stmt FROM @postTitle;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @postClassCategory = (SELECT IF(
    (SELECT COUNT(*)
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE table_name = 'post'
        AND table_schema = DATABASE()
        AND column_name = 'classCategory'
    ) > 0,
    "SELECT 1",
    "ALTER TABLE post ADD classCategory varchar(25) DEFAULT NULL"
));

PREPARE stmt FROM @postClassCategory;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

CREATE TABLE IF NOT EXISTS `usercollege` (
  `userCollegeID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `collegeID` int(11) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  PRIMARY KEY (`userCollegeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `collegeinterest` (
  `collegeinterestID` int(11) NOT NULL AUTO_INCREMENT,
  `collegeID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`collegeinterestID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `bankinterest` (
  `bankinterestID` int(11) NOT NULL AUTO_INCREMENT,
  `bankID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`bankinterestID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `scholarshipinterest` (
  `scholarshipinterestID` int(11) NOT NULL AUTO_INCREMENT,
  `scholarshipID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`scholarshipinterestID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `companyinterest` (
  `companyinterestID` int(11) NOT NULL AUTO_INCREMENT,
  `companyID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`companyinterestID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `traininginterest` (
  `traininginterestID` int(11) NOT NULL AUTO_INCREMENT,
  `institutionID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`traininginterestID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `interest` (
  `interestID` int(11) NOT NULL AUTO_INCREMENT,
  `recipientID` int(11) DEFAULT NULL,
  `senderID` int(11) DEFAULT NULL,
  `userType` int(2) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`interestID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `chat` (
  `chatID` int(11) NOT NULL AUTO_INCREMENT,
  `senderID` int(11) DEFAULT NULL,
  `receiverID` int(11) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `sentDate` date DEFAULT NULL,
  PRIMARY KEY (`chatID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `mentorrelationship` (
  `mentorRelationshipID` int(11) NOT NULL AUTO_INCREMENT,
  `studentUserID` int(11) DEFAULT NULL,
  `mentorUserID` int(11) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `isMentor` bit(1) DEFAULT NULL,
  PRIMARY KEY (`mentorRelationshipID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `childpost` (
  `childpostID` int(11) NOT NULL AUTO_INCREMENT,
  `postID` int(11) DEFAULT NULL,
  `receiverID` int(11) DEFAULT NULL,
  `isLiked` bit(1) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `selectedPollOptn` varchar(50) DEFAULT NULL,
  `isVoted` bit(1) DEFAULT b'0',
  `isSaved` bit(1) DEFAULT b'0',
  PRIMARY KEY (`childpostID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `comments` (
  `commentsID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `postID` int(11) DEFAULT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `isDeleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`commentsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `notifications` (
  `notificationsID` INT(11) NOT NULL AUTO_INCREMENT,
  `receiverID` INT(11) NOT NULL,
  `senderID` INT(11) NOT NULL,
  `postID` INT(11) NOT NULL,
  `notification` VARCHAR(500) DEFAULT NULL,
  `createdDate` DATE DEFAULT NULL,
  `isRead` bit(1) DEFAULT NULL,
  `notificationType`   int(45),
  PRIMARY KEY (`notificationsID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `recommendation` (
  `recommendationID` int(11) NOT NULL AUTO_INCREMENT,
  `recipientID` int(11) DEFAULT NULL,
  `senderID` int(11) DEFAULT NULL,
  `userType` int(2) DEFAULT NULL,
  `recMessage` varchar(1000) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`recommendationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `profileviews` (
  `profileViewsID` int(11) NOT NULL AUTO_INCREMENT,
  `viewerID` int(11) DEFAULT NULL,
  `vieweeID` int(11) DEFAULT NULL,
  `viewerType` int(11) DEFAULT NULL,
  `viewedDate` date DEFAULT NULL,
  `isViewed` bit(1) DEFAULT b'1',
   PRIMARY KEY (`profileViewsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `mentor`
(
    `mentorID`     int(11) NOT NULL AUTO_INCREMENT,
    `userID`       int(11) DEFAULT NULL,
    `createdDate`  date DEFAULT NULL,
    `userType`     int(55) NOT NULL,
    PRIMARY KEY (`mentorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `usermoreinfo` (
  `userMoreInfoID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `hobbies` varchar(500) DEFAULT NULL,
  `ambition` varchar(500) DEFAULT NULL,
  `issuesICare` varchar(100) DEFAULT NULL,
  `talent` varchar(200) DEFAULT NULL,
  `languages` varchar(200) DEFAULT NULL,
  `extracurricular` varchar(250) DEFAULT NULL,
  `awardsAndAcomp` varchar(300) DEFAULT NULL,
  `essay` varchar(2000) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `isActive` bit(1) DEFAULT b'1',
  PRIMARY KEY (`userMoreInfoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `points` (
  `pointsID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `referenceCode` varchar(45) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `isActive` bit(1) DEFAULT b'1',
  PRIMARY KEY (`pointsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `applications` (
  `applicationID` int(11) NOT NULL AUTO_INCREMENT,
  `collegeID` int(11) DEFAULT NULL,
  `applierID` int(11) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `updatedDate` date DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `statusType` int(20) DEFAULT NULL,
  `userType` int(20) DEFAULT NULL,
  `isRead` bit(1) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`applicationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `course` (
  `courseID` int(11) NOT NULL AUTO_INCREMENT,
  `course` varchar(250) DEFAULT NULL,
  `isAdvanced` bit(1) DEFAULT b'0',
  `isElective` bit(1) DEFAULT b'0',
  `finalGrade` varchar(10) DEFAULT NULL,
  `issuedBy` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  PRIMARY KEY (`courseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `toeflscore` (
  `toeflscoreID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `testName` varchar(250) DEFAULT NULL,
  `testDate` date DEFAULT NULL,
  `reading` int(10) DEFAULT NULL,
  `listening` int(10) DEFAULT NULL,
  `speaking` int(10) DEFAULT NULL,
  `writing` int(10) DEFAULT NULL,
  PRIMARY KEY (`toeflscoreID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `actscore` (
  `actScoreID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `testDate` date DEFAULT NULL,
  `engScore` int(5) DEFAULT NULL,
  `mathScore` int(5) DEFAULT NULL,
  `readScore` int(5) DEFAULT NULL,
  `sciScore` int(5) DEFAULT NULL,
  `compScore` int(5) DEFAULT NULL,
  PRIMARY KEY (`actScoreID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `grescore` (
  `greScoreID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `testdate` date DEFAULT NULL,
  `vrPriFormScldScore` int(5) DEFAULT NULL,
  `vrPriFormEstyScore` int(5) DEFAULT NULL,
  `vrCurFormScldScore` int(5) DEFAULT NULL,
  `vrPrcntBelw` int(5) DEFAULT NULL,
  `qrPriFormScldScore` int(5) DEFAULT NULL,
  `qrPriFormEstyScore` int(5) DEFAULT NULL,
  `qrCurFormScldScore` int(5) DEFAULT NULL,
  `qrPrcntBelw` int(5) DEFAULT NULL,
  `arScore` int(5) DEFAULT NULL,
  `arPrcntBelw` int(5) DEFAULT NULL,
  PRIMARY KEY (`greScoreID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `mydesk` (
  `myDeskID` int(11) NOT NULL AUTO_INCREMENT,
  `postID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  PRIMARY KEY (`myDeskID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `rating` (
  `ratingID` int(11) NOT NULL AUTO_INCREMENT,
  `ratedByID` int(11) DEFAULT NULL,
  `ratedToID` int(11) DEFAULT NULL,
  `ratings` int(5) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ratingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `testscore` (
  `testScoreID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `testDate` date DEFAULT NULL,
  `english` float DEFAULT NULL,
  `math` float DEFAULT NULL,
  `science` float DEFAULT NULL,
  `reading` float DEFAULT NULL,
  `writing` float DEFAULT NULL,
  `speak` float DEFAULT NULL,
  `composite` float DEFAULT NULL,
  `score` float DEFAULT NULL,
  `max` float DEFAULT NULL,
  `min` float DEFAULT NULL,
  `testType` int(5) DEFAULT NULL,
  `testName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`testScoreID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `programs` (
  `programsID` int(11) NOT NULL AUTO_INCREMENT,
  `subjectName` varchar(100) DEFAULT NULL,
  `category` int(4) DEFAULT NULL,
  `certificateType` int(4) DEFAULT NULL,
  `area` varchar(100) DEFAULT NULL,
  `major` varchar(100) DEFAULT NULL,
  `minor` varchar(100) DEFAULT NULL,
  `prerequisite` varchar(200) DEFAULT NULL,
  `degreeType` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`programsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `programsoffered` (
  `programsOfferedID` int(11) NOT NULL AUTO_INCREMENT,
  `collegeID` int(11) DEFAULT NULL,
  `programsID` int(11) DEFAULT NULL,
  PRIMARY KEY (`programsOfferedID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;