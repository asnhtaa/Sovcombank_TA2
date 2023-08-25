package Tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static Tests.APISpecifications.*;

public class RandomUserNegativeTests {

    private final String API_URL = "https://randomuser.me/api/";

    @Test
    @Description("Requesting invalid data format")
    public void testInvalidFormat() {
        given()
                .spec(requestSpec)
                .param("format", "pdf")
                .when()
                .get(API_URL)
                .then()
                .spec(responseSpec400);
    }

    @Test
    @Description("Requesting invalid nationality")
    public void testInvalidNationality() {
        given()
                .spec(requestSpec)
                .param("nat", "invalid_nat")
                .when()
                .get(API_URL)
                .then()
                .spec(responseSpec400);
    }

    @Test
    @Description("Requesting exceeding user limit")
    public void testExceedingUserLimit() {
        given()
                .spec(requestSpec)
                .param("results", 5001)
                .when()
                .get(API_URL)
                .then()
                .spec(responseSpec400);
    }

    @Test
    @Description("Sending request with invalid fields")
    public void testInvalidFields() {
        given()
                .spec(APISpecifications.requestSpec)
                .param("inc", "invalid_field")
                .when()
                .get(API_URL)
                .then()
                .spec(responseSpec400);
    }

    @Test
    @Description("Sending request with invalid fields page value")
    public void testInvalidPageValue() {
        given()
                .spec(requestSpec)
                .param("page", "!")
                .when()
                .get(API_URL)
                .then()
                .spec(responseSpec400);
    }

    @Test
    @Description("Sending request with invalid API version")
    public void testInvalidAPIVersion() {
        given()
                .spec(requestSpec)
                .param("version", "99.9")
                .when()
                .get(API_URL)
                .then()
                .spec(responseSpec400);
    }

    @Test
    @Description("Sending request with missing gender values")
    public void testMissingGenderValue() {
        given()
                .spec(requestSpec)
                .param("gender", "")
                .when()
                .get(API_URL)
                .then()
                .spec(responseSpec400);
    }
}