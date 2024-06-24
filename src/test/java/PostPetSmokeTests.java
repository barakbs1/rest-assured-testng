import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;


public class PostPetSmokeTests extends BaseAPI{

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = this.baseURI;
        RestAssured.port = this.port;
        RestAssured.basePath = this.basePath;
    }

    @Test
    public void postPetBasicTest() throws IOException {
        String requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/requests/postPetRequestBody.json")));
        given()
                .body(requestBody)
                .relaxedHTTPSValidation()
                .contentType("application/json").
        when()
                .post("").
        then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("responses/postPetResponseScheme.json")); // src/test/resources
    }

    @Test
    public void postPetEmptyBodyTest() {
        given()
                .body("")
                .relaxedHTTPSValidation()
                .contentType("application/json").
        when()
                .post("").
        then()
                .statusCode(405)
                .body("code", equalTo(405))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("no data"));
    }
}