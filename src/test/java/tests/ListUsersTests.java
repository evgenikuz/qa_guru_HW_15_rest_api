package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ListUsersTests extends TestBase{
    @Test
    void successfulGetListOfUsersTest() {
        given()
                .header("x-api-key", APIKEY)
                .log().uri()

            .when()
                .queryParam("page", "2")
                .get("/users")

            .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", is(2))
                .body("total", is(12))
                .body("data", is(notNullValue()))
                .body("data.id[0]", is(instanceOf(Integer.class)))
                .body("data.email[0]", containsString("@"))
                .body("data.first_name[0]", is(instanceOf(String.class)))
                .body("data.last_name[0]", is(instanceOf(String.class)))
                .body("data.avatar[0]", matchesPattern("^https://.*\\.jpg$"));
    }
}
