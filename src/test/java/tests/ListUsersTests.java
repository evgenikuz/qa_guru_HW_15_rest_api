package tests;

import models.ListUsersResponseModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static specs.RequestSpec.requestSpec;
import static specs.ResponseSpec.responseSpec;

public class ListUsersTests extends TestBase{
    @Test
    void successfulGetListOfUsersTest() {
        ListUsersResponseModel response = step("Make request", () ->
        given(requestSpec)

            .when()
                .queryParam("page", "2")
                .get("/users")

            .then()
                .spec(responseSpec(200))
                .extract().as(ListUsersResponseModel.class));

        step("Check response", () -> {
            assertEquals(2, response.getPage());
            assertEquals(12, response.getTotal());
            assertNotNull(response.getData());
            assertInstanceOf(Integer.class, response.getData().get(0).getId());
            assert response.getData().get(0).getEmail().contains("@");
            assertInstanceOf(String.class, response.getData().get(0).getFirst_name());
            assertInstanceOf(String.class, response.getData().get(0).getLast_name());
            assert response.getData().get(0).getAvatar().matches("^https://.*\\.jpg$");
        });
    }
}
