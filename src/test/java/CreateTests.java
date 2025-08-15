import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class CreateTests extends TestBase {
    @Test
    void successfulCreateUserTest() {
    String userData = "{\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"leader\"\n" +
            "}";
    given()
                .header("x-api-key", APIKEY)
                .body(userData)
                .contentType(JSON)
                .log().uri()

            .when()
                .post("/users")

            .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .body("id", is(notNullValue()))
                .body("id", matchesPattern("^\\d{3}$"))
                .body("createdAt", is(notNullValue()));
    }

    @Test
    void possibleToCreateUserWithoutInfoTest() {
        String userData = "{}";
        given()
                .header("x-api-key", APIKEY)
                .body(userData)
                .contentType(JSON)
                .log().uri()

            .when()
                .post("/users")

            .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("id", is(notNullValue()))
                .body("id", matchesPattern("^\\d{3}$"))
                .body("createdAt", is(notNullValue()));
    }
}
