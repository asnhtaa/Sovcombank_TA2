package Tests;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import static Tests.APISpecifications.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Utilities.Result;
import Utilities.Root;

import static Tests.APITestHelpers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;


public class RandomUserPositiveTests {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
        requestSpec = new RequestSpecBuilder()
                .build();
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    @Test
    @Description("Sending simple valid request")
    public void testRandomUserGeneration(){
        Response response = sendRequestAndValidateResponse(requestSpec);

        Root userResponse = response.as(Root.class);
        validateResultsNotEmpty(userResponse);

        Result user = userResponse.getResults().get(0);
        validateFieldNotNull(user, "gender");
        validateFieldNotNull(user, "name");
        validateFieldNotNull(user, "location");
        validateFieldNotNull(user, "email");
        validateFieldNotNull(user, "phone");
        }

    @Test
    @Description("Sending request with multiple users")
    public void testMultipleUserGeneration() {
        int numberOfUsers = 5;
        requestSpec.queryParam("results", numberOfUsers);
        requestSpec.contentType(ContentType.JSON);

        Response response = sendRequestAndValidateResponse(requestSpec);
        String contentTypeHeader = response.getHeader("Content-Type");
        assertEquals("application/json; charset=utf-8", contentTypeHeader);

        Root userResponse = response.as(Root.class);
        validateResultsNotEmpty(userResponse);
        assertEquals(numberOfUsers, userResponse.getResults().size());
    }

    @Test
    @Description("Sending request and downloading results")
    public void testDownloadResults () {
        requestSpec.queryParam("results", 25)
                .queryParam("nat", "gb,us,es")
                .queryParam("format", "csv")
                .queryParam("dl", "");
        sendRequestAndValidateResponse(requestSpec)
                .then()
                .header("Content-Disposition", containsString("attachment;"))
                .header("Content-Type", equalTo("application/octet-stream"));
        }

    @Test
    @Description("Sending request and specifying gender")
    public void testGenderSpecification () {
        String gender = "male";
        requestSpec.queryParam("gender", gender);
        Response response = sendRequestAndValidateResponse(requestSpec);

        Root userResponse = response.as(Root.class);
        validateResultsNotEmpty(userResponse);
        assertTrue(userResponse.getResults().stream().allMatch(u -> u.getGender().equalsIgnoreCase(gender)));
    }

    @Test
    @Description("Sending request with password parameters")
    public void testPasswordControl() {
        String passwordOptions = "upper,lower,1-16";
        requestSpec.queryParam("password", passwordOptions);
        Response response = sendRequestAndValidateResponse(requestSpec);

        Root userResponse = response.as(Root.class);
        validateResultsNotEmpty(userResponse);

        assertTrue(userResponse.getResults().stream()
                .allMatch(u -> u.getLogin() != null && u.getLogin().getPassword() != null &&
                        u.getLogin().getPassword().matches("^(?=.*[A-Z])(?=.*[a-z]).{1,16}$")));
    }


    @Test
    @Description("Sending request with nationality parameters")
    public void testNationalitySelection () {
        String nationality = "gb";
        requestSpec.queryParam("nat", nationality);
        Response response = sendRequestAndValidateResponse(requestSpec);

        Root userResponse = response.as(Root.class);
        validateResultsNotEmpty(userResponse);
        assertThat(userResponse.getResults().get(0).getNat(), equalTo(nationality.toUpperCase()));
        }

    @Test
    @Description("Sending request with pagination parameters")
    public void testPagination () {
        int page = 2;
        int resultsPerPage = 10;
        String seed = "abc";
        requestSpec.queryParam("page", page)
                .queryParam("results", resultsPerPage)
                .queryParam("seed", seed);
        Response response = sendRequestAndValidateResponse(requestSpec);

        Root userResponse = response.as(Root.class);
        validateResultsNotEmpty(userResponse);
        assertThat(userResponse.getResults().get(0), notNullValue());
    }

    @Test
    @Description("Sending request and including fields")
    public void testFieldInclusion () {
        requestSpec.queryParam("inc", "gender,name,nat");
        Response response = sendRequestAndValidateResponse(requestSpec);

        Root userResponse = response.as(Root.class);
        validateResultsNotEmpty(userResponse);
        Result user = userResponse.getResults().get(0);
        assertThat(user, hasProperty("gender"));
        assertThat(user, hasProperty("name"));
        assertThat(user, hasProperty("nat"));
    }

    @Test
    @Description("Sending request and excluding fields")
        public void testFieldExclusion () {
            requestSpec.queryParam("exc", "login");
            Response response = sendRequestAndValidateResponse(requestSpec);

            Root userResponse = response.as(Root.class);
            validateResultsNotEmpty(userResponse);
            assertThat(userResponse.getResults().get(0).getLogin(), is(nullValue()));

    }

    @Test
    @Description("Sending request and checking data in response")
    public void testDataOnly () {
        requestSpec.queryParam("results", 5)
                .queryParam("inc", "name,gender,nat")
                .queryParam("noinfo", "");
        Response response = sendRequestAndValidateResponse(requestSpec);

        Root userResponse = response.as(Root.class);
        validateResultsNotEmpty(userResponse);
        assertThat(userResponse.getInfo(), nullValue());
        Result user = userResponse.getResults().get(0);
        assertThat(user, hasProperty("name"));
        assertThat(user, hasProperty("gender"));
        assertThat(user, hasProperty("nat"));
    }
}
