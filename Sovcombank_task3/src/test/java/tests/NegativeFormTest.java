package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeFormTest extends BaseTest {

    @Test
    public void testMissingFirstName() {
        FormPage.typeFirstName("John");
        FormPage.typeLastName("Doe");
        FormPage.typeEmail("johndoe@example.com");
        FormPage.selectGenderFemale();
        FormPage.typePhoneNumber("1234567890");
        FormPage.typeDateOfBirth("June", "1990");
        FormPage.typeSubjects("Computer");
        FormPage.selectSportAsHobbies();
        FormPage.uploadPicture();
        FormPage.selectStateByIndex(1);
        FormPage.selectCityByIndex(2);
        Assert.assertTrue(FormPage.getFormSubmissionResult().isEmpty(), "Form submission message is displayed.");
    }

    @Test
    public void testInvalidEmailFormat() {
        FormPage.typeLastName("Doe");
        FormPage.typeEmail("invalidEmail");
        FormPage.selectGenderFemale();
        FormPage.typePhoneNumber("1234567890");
        FormPage.typeDateOfBirth("June", "1990");
        FormPage.typeSubjects("Computer");
        FormPage.selectSportAsHobbies();
        FormPage.uploadPicture();
        FormPage.selectStateByIndex(1);
        FormPage.selectCityByIndex(2);
        Assert.assertTrue(FormPage.getFormSubmissionResult().isEmpty(), "Form submission message is displayed.");
    }

    @Test
    public void testMissingGenderSelection() {

        FormPage.typeFirstName("John");
        FormPage.typeLastName("Doe");
        FormPage.typeEmail("johndoe@example.com");
        FormPage.typePhoneNumber("1234567890");
        FormPage.typeDateOfBirth("June", "1990");
        FormPage.typeSubjects("Computer");
        FormPage.selectSportAsHobbies();
        FormPage.uploadPicture();
        FormPage.selectStateByIndex(1);
        FormPage.selectCityByIndex(2);
        Assert.assertTrue(FormPage.getFormSubmissionResult().isEmpty(), "Form submission message is displayed.");
    }

    @Test
    public void testInvalidPhoneNumber() {

        FormPage.typeFirstName("John");
        FormPage.typeLastName("Doe");
        FormPage.typeEmail("johndoe@example.com");
        FormPage.selectGenderFemale();
        FormPage.typePhoneNumber("123");
        FormPage.typeDateOfBirth("June", "1990");
        FormPage.typeSubjects("Computer");
        FormPage.selectSportAsHobbies();
        FormPage.uploadPicture();
        FormPage.selectStateByIndex(1);
        FormPage.selectCityByIndex(2);
        Assert.assertTrue(FormPage.getFormSubmissionResult().isEmpty(), "Form submission message is displayed.");
    }

    @Test
    public void testInvalidDateOfBirth() {
        FormPage.typeFirstName("John");
        FormPage.typeLastName("Doe");
        FormPage.typeEmail("johndoe@example.com");
        FormPage.selectGenderFemale();
        FormPage.typePhoneNumber("1234567890");
        FormPage.typeDateOfBirth("February", "2025");
        FormPage.typeSubjects("Computer");
        FormPage.selectSportAsHobbies();
        FormPage.uploadPicture();
        FormPage.selectStateByIndex(1);
        FormPage.selectCityByIndex(2);
        Assert.assertTrue(FormPage.getFormSubmissionResult().isEmpty(), "Form submission message is displayed.");
    }

    @Test
    public void testInvalidSubjects() {

        FormPage.typeFirstName("John");
        FormPage.typeLastName("Doe");
        FormPage.typeEmail("johndoe@example.com");
        FormPage.selectGenderFemale();
        FormPage.typePhoneNumber("1234567890");
        FormPage.typeDateOfBirth("June", "1990");
        FormPage.typeSubjects("InvalidSubject");
        FormPage.selectSportAsHobbies();
        FormPage.uploadPicture();
        FormPage.selectStateByIndex(1);
        FormPage.selectCityByIndex(2);
        Assert.assertTrue(FormPage.getFormSubmissionResult().isEmpty(), "Form submission message is displayed.");
    }

    @Test
    public void testMissingHobbiesSelection() {

        FormPage.typeFirstName("John");
        FormPage.typeLastName("Doe");
        FormPage.typeEmail("johndoe@example.com");
        FormPage.selectGenderFemale();
        FormPage.typePhoneNumber("1234567890");
        FormPage.typeDateOfBirth("June", "1990");
        FormPage.typeSubjects("Computer");
        FormPage.uploadPicture();
        FormPage.selectStateByIndex(1);
        FormPage.selectCityByIndex(2);
        Assert.assertTrue(FormPage.getFormSubmissionResult().isEmpty(), "Form submission message is displayed.");
    }

    @Test
    public void testMissingStateSelection() {

        FormPage.typeFirstName("John");
        FormPage.typeLastName("Doe");
        FormPage.typeEmail("johndoe@example.com");
        FormPage.selectGenderFemale();
        FormPage.typePhoneNumber("1234567890");
        FormPage.typeDateOfBirth("June", "1990");
        FormPage.typeSubjects("Computer");
        FormPage.selectSportAsHobbies();
        FormPage.uploadPicture();
        FormPage.selectCityByIndex(2);
        Assert.assertTrue(FormPage.getFormSubmissionResult().isEmpty(), "Form submission message is displayed.");
    }

    @Test
    public void testMissingCitySelection() {

        FormPage.typeFirstName("John");
        FormPage.typeLastName("Doe");
        FormPage.typeEmail("johndoe@example.com");
        FormPage.selectGenderFemale();
        FormPage.typePhoneNumber("1234567890");
        FormPage.typeDateOfBirth("June", "1990");
        FormPage.typeSubjects("Computer");
        FormPage.selectSportAsHobbies();
        FormPage.uploadPicture();
        FormPage.selectStateByIndex(1);
        Assert.assertTrue(FormPage.getFormSubmissionResult().isEmpty(), "Form submission message is displayed.");
        FormPage.selectStateByIndex(1);
}
}
