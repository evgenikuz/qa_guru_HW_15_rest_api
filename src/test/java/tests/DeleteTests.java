package tests;

import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.RequestSpec.requestSpec;
import static specs.ResponseSpec.responseSpec;

public class DeleteTests extends TestBase {
    @Test
    void successfulDeleteTest() {
        step("Delete user", () -> {
            given(requestSpec)

                .when()
                    .delete("/users/2")

                .then()
                    .spec(responseSpec(204));

        });
    }
}
