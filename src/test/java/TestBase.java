import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    protected static String APIKEY = "reqres-free-v1";

    @BeforeAll
    public static void onSetup() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }
}
