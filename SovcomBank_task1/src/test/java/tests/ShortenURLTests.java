package tests;

import Utilities.TestData;
import Utilities.TestDataLoader;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.util.List;

@Epic("ShortenURLTests")
@Feature("API Testing")
public class ShortenURLTests {

    private static final String BASE_URL = "https://cleanuri.com/api/v1";

    // Метод для отправки запроса на сокращение URL
    private Response sendShortenRequest(String url) {
        return given()
                .param("url", url)
                .when()
                .post(BASE_URL + "/shorten")
                .then()
                .extract().response();
    }

    @ParameterizedTest
    @MethodSource("positiveTestData")
    @Story("Positive Tests")
    @Description("Test valid URL shortening")
    public void testValidUrl(TestData testData) {
        Allure.step("Test Data ID: " + testData.getDescription());
        Allure.step("Long URL: " + testData.getUrl());
        String longUrl = testData.getUrl();
        Response response = sendShortenRequest(longUrl);

        response.then()
                .statusCode(200)
                .body("result_url", containsString(testData.getExpectedResult()));
    }

    @ParameterizedTest
    @MethodSource("negativeTestData")
    @Story("Negative Tests")
    @Description("Test invalid URL shortening")
    public void testInvalidUrls(TestData testData) {
        Allure.step("Test Data ID: " + testData.getDescription());
        Allure.step("Long URL: " + testData.getUrl());
        String invalidUrl = testData.getUrl();
        Response response = sendShortenRequest(invalidUrl);

        assertEquals(400, response.getStatusCode());
        Allure.step("Test failed with invalid URL: " + invalidUrl);
    }

    @ParameterizedTest
    @MethodSource("specificTestData")
    @Story("Negative Tests")
    @Description("Test invalid request method")
    public void testInvalidRequestMethod(TestData testData) {
        Allure.step("Test Data ID: " + testData.getDescription());
        Allure.step("Long URL: " + testData.getUrl());
        String invalidUrl = testData.getUrl();
        Response response = given()
                .spec(ApiSpecs.requestSpec())
                .param("url", invalidUrl)
                .when()
                .get(BASE_URL + "/shorten")
                .then()
                .extract().response();

        assertEquals(405, response.getStatusCode());
        Allure.step("Test failed with invalid request method for URL: " + invalidUrl);
    }

    @ParameterizedTest
    @MethodSource("specificTestData")
    @Story("Negative Tests")
    @DisplayName("Test URL length exceeds limit")
    public void testUrlLengthExceedsLimit(TestData testData) {
        Allure.step("Test Data ID: " + testData.getDescription());
        Allure.step("Long URL: " + testData.getUrl());
        String invalidUrl = testData.getUrl() + "a".repeat(4000);
        Response response = sendShortenRequest(invalidUrl);

        assertEquals(400, response.getStatusCode());
        Allure.step("Test failed with invalid URL: " + invalidUrl);
    }

    @ParameterizedTest
    @MethodSource("specificTestData")
    @Story("Negative Tests")
    @Description("Test rate limit exceeded")
    public void testRateLimitExceeded(TestData testData) throws InterruptedException {
        Allure.step("Test Data ID: " + testData.getDescription());
        Allure.step("Long URL: " + testData.getUrl());
        int requestsCount = 10;
        for (int i = 0; i < requestsCount; i++) {
            given()
                    .param("url", "https://example.com/")
                    .when()
                    .post(BASE_URL + "/shorten");
            Thread.sleep(100);
        }
        Response response = given()
                .param("url", "https://example.com/")
                .when()
                .post(BASE_URL + "/shorten");

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 429,
                "Unexpected status code: " + statusCode);
        Allure.step("Test failed with rate limit exceeded");
    }

    // Методы загрузки тестовых данных
    public static List<TestData> positiveTestData() throws IOException {
        return TestDataLoader.loadTestData("src/test/TestData/testData.json", "positive");
    }

    public static List<TestData> negativeTestData() throws IOException {
        return TestDataLoader.loadTestData("src/test/TestData/testData.json", "negative");
    }

    public static List<TestData> specificTestData() throws IOException {
        return TestDataLoader.loadTestData("src/test/TestData/testData.json", "specific");
    }

    // Спецификации для API запросов
    private static class ApiSpecs {
        static io.restassured.specification.RequestSpecification requestSpec() {
            return new io.restassured.builder.RequestSpecBuilder()
                    .setBaseUri(BASE_URL)
                    .build();
        }
    }
}