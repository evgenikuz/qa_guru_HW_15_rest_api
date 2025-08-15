import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class DeleteTests extends TestBase {
    @Test
    void successfulDeleteTest() {
    given()
                .header("x-api-key", APIKEY)
                .log().uri()

            .when()
                .delete("/users/2")

            .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
