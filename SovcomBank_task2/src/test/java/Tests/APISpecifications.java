package Tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.equalTo;

    public class APISpecifications {
        public static RequestSpecification requestSpec;
        public static ResponseSpecification responseSpec;
        public static ResponseSpecification responseSpec400;
        public static ResponseSpecification responseSpec500;

        static {
            requestSpec = new RequestSpecBuilder()
                    .build();

            responseSpec = new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .build();

            responseSpec400 = new ResponseSpecBuilder()
                    .expectStatusCode(400)
                    .expectBody(equalTo(""))
                    .build();

            responseSpec500 = new ResponseSpecBuilder()
                    .expectStatusCode(500)
                    .build();
        }
    }