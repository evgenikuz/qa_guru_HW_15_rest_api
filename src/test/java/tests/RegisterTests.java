package tests;

import models.RegisterBodyModel;
import models.RegisterErrorResponseModel;
import models.RegisterResponseModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.RequestSpec.requestSpec;
import static specs.ResponseSpec.responseSpec;

public class RegisterTests extends TestBase {
    @Test
    void successfulRegisterTest() {
        RegisterBodyModel regData = new RegisterBodyModel();
        regData.setEmail("eve.holt@reqres.in");
        regData.setPassword("pistol");

        RegisterResponseModel response = step("Make request", () ->
        given(requestSpec)
                .body(regData)

            .when()
                .post("/register")

            .then()
                .spec(responseSpec(200))
                .extract().as(RegisterResponseModel.class));

        step("Check response", () -> {
            assertNotNull(response.getId());
            assertInstanceOf(Integer.class, response.getId());
            assertNotNull(response.getToken());
            assert response.getToken().matches("^.{17}$");
        });
    }

    @Test
    void registerWithoutEmailTest() {
        RegisterBodyModel regData = new RegisterBodyModel();
        regData.setPassword("pistol");

        RegisterErrorResponseModel response = step("Make request", () ->
                given(requestSpec)
                    .body(regData)

                .when()
                    .post("/register")

                .then()
                    .spec(responseSpec(400))
                    .extract().as(RegisterErrorResponseModel.class));

        step("Check response", () ->
            assertEquals("Missing email or username", response.getError())
        );

    }

    @Test
    void registerWithoutPasswordTest() {
        RegisterBodyModel regData = new RegisterBodyModel();
        regData.setEmail("eve.holt@reqres.in");

        RegisterErrorResponseModel response = step("Make request", () ->
                given(requestSpec)
                    .body(regData)

                .when()
                    .post("/register")

                .then()
                    .spec(responseSpec(400))
                    .extract().as(RegisterErrorResponseModel.class));

        step("Check response", () ->
                assertEquals("Missing password", response.getError())
        );
    }

    @Test
    void registerWithNoDataTest() {
        RegisterBodyModel regData = new RegisterBodyModel();

        RegisterErrorResponseModel response = step("Make request", () ->
                given(requestSpec)
                    .body(regData)

                .when()
                    .post("/register")

                .then()
                    .spec(responseSpec(400))
                    .extract().as(RegisterErrorResponseModel.class));

        step("Check response", () ->
                assertEquals("Missing email or username", response.getError())
        );
    }
}
