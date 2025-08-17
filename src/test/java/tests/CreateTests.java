package tests;

import models.CreateBodyModel;
import models.CreateResponseModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.RequestSpec.requestSpec;
import static specs.ResponseSpec.responseSpec;

public class CreateTests extends TestBase {
    @Test
    void successfulCreateUserTest() {
        CreateBodyModel userData = new CreateBodyModel();
        userData.setName("morpheus");
        userData.setJob("leader");

        CreateResponseModel response = step("Make request", () ->
            given(requestSpec)
                .body(userData)

            .when()
                .post("/users")

            .then()
                    .spec(responseSpec(201))
                .extract().as(CreateResponseModel.class));

        step("Check response", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());
            assertNotNull(response.getId());
            assert response.getId().matches("\\d+");
            assertNotNull(response.getCreatedAt());
        });
    }

    @Test
    void possibleToCreateUserWithoutInfoTest() {
        CreateBodyModel userData = new CreateBodyModel();
        CreateResponseModel response = step("Make request", () ->
                given(requestSpec)
                        .body(userData)

                        .when()
                        .post("/users")

                        .then()
                        .spec(responseSpec(201))
                        .extract().as(CreateResponseModel.class));
        step("Check response", () -> {
            assertNotNull(response.getId());
            assert response.getId().matches("\\d+");
            assertNotNull(response.getCreatedAt());
        });
    }
}
