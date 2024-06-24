import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;


public class GetPetSanityTests extends BaseAPI {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = this.baseURI;
        RestAssured.port = this.port;
        RestAssured.basePath = this.basePath;
    }

    @Test
    public void getPetIdOfUnsupportedTypeTest() {
        when()
                .get("/someString").
        then()
                .statusCode(404)
                .body("code", equalTo(404))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("java.lang.NumberFormatException: For input string: \"someString\""));
    }

    @Test
    public void getPetIdOfEmptyIdTest() {
        when()
                .get("").
        then()
                .statusCode(405);
    }
}