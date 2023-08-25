package Tests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import Utilities.Result;
import Utilities.Root;

import static Tests.APISpecifications.responseSpec;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class APITestHelpers {
    public static final String BASE_URL = "https://randomuser.me/api/";

    public static void validateFieldNotNull(Result user, String fieldPath) {
        assertNotNull(user);
        assertNotNull(fieldPath);
    }

    public static Response sendRequestAndValidateResponse(RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .when()
                .get()
                .then()
                .spec(responseSpec)
                .extract().response();
    }

    public static void validateResultsNotEmpty(Root userResponse) {
        assertNotNull(userResponse);
        assertNotNull(userResponse.getResults());
        assertTrue(userResponse.getResults().size() > 0);
    }
}
