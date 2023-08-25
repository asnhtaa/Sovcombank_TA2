package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By nameField = By.cssSelector("[placeholder='First Name']");
    private By lastNameField = By.cssSelector("[placeholder='Last Name']");
    private By emailField = By.cssSelector("#userEmail");
    private By femaleGenderCheckbox = By.xpath("//input[@value='Female']");
    private By phoneNumberField = By.cssSelector("#userNumber");
    private By birthField = By.cssSelector("#dateOfBirthInput");
    private By selectMonth = By.cssSelector("[class*='month-select'");
    private By selectYear = By.cssSelector("[class*='year-select'");
    private By selectDate = By.cssSelector(".react-datepicker__day--025");
    private By subjectsField = By.xpath("//div[contains(@class, 'subject')]");
    private By sportHobbiesCheckbox = By.cssSelector("#hobbies-checkbox-1");
    private By uploadPicture = By.cssSelector("#uploadPicture");
    private By selectState = By.cssSelector("#state");
    private By selectCity = By.cssSelector("#city");
    private By modalHeader = By.cssSelector("modal-header");

    public FormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private WebElement findElement(By locator) {
        WebElement elementLocation = driver.findElement(locator);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded();", elementLocation);
        return elementLocation;
    }

    public void typeFirstName(String firstName) {
        findElement(nameField).sendKeys(firstName);
    }

    public void typeLastName(String lastName) {
        findElement(lastNameField).sendKeys(lastName);
    }

    public void typeEmail(String email) {
        findElement(emailField).sendKeys(email);
    }

    public void selectGenderFemale() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", findElement(femaleGenderCheckbox));
    }

    public void typePhoneNumber(String phoneNumber) {
        findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void typeDateOfBirth(String monthOfBirth, String yearOfBirth) {
        findElement(birthField).click();
        Select monthDropdown = new Select(findElement(selectMonth));
        monthDropdown.selectByVisibleText(monthOfBirth);
        Select yearDropdown = new Select(findElement(selectYear));
        yearDropdown.selectByVisibleText(yearOfBirth);
        findElement(selectDate).click();
    }

    public void typeSubjects(String subjects) {
        driver.findElement(subjectsField).sendKeys(subjects);
        Select dropdown = new Select(findElement(selectCity));
        dropdown.selectByIndex(1);
    }

    public void selectSportAsHobbies() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", findElement(sportHobbiesCheckbox));
    }

    public void uploadPicture() {
        String filePath = "resources/image.jpg";
        findElement(uploadPicture).sendKeys(filePath);
    }

    public void selectStateByIndex(Integer stateIndex) {
        Select dropdown = new Select(findElement(selectState));
        dropdown.selectByIndex(stateIndex);
    }

    public void selectCityByIndex(Integer cityIndex) {
        Select dropdown = new Select(findElement(selectCity));
        dropdown.selectByIndex(cityIndex);
    }

    public String getFormSubmissionResult() {
        return findElement(modalHeader).getText();
    }


}
