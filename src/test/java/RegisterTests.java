import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class RegisterTests extends TestBase {
    @Test
    void successfulRegisterTest() {
        String regData = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        given()
                .header("x-api-key", APIKEY)
                .body(regData)
                .contentType(JSON)
                .log().uri()

            .when()
                .post("/register")

            .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(notNullValue()))
                .body("id", is(instanceOf(Integer.class)))
                .body("token", is(notNullValue()))
                .body("token", matchesPattern("^.{17}$"));
    }

    @Test
    void registerWithoutEmailTest() {
        String regData = "{\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        given()
                .header("x-api-key", APIKEY)
                .body(regData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void registerWithoutPasswordTest() {
        String regData = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\"\n" +
                "}";
        given()
                .header("x-api-key", APIKEY)
                .body(regData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void registerWithNoDataTest() {
        String regData = "{}";
        given()
                .header("x-api-key", APIKEY)
                .body(regData)
                .contentType(JSON)
                .log().uri()

            .when()
                .post("/register")

            .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }
}
