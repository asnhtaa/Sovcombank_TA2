package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveFormTest extends BaseTest {
    @Test
    public void testPositiveFormSubmission() {
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
        Assert.assertEquals("Form submission is incorrect!", FormPage.getFormSubmissionResult(), "Thanks for submitting the form");
    }
}