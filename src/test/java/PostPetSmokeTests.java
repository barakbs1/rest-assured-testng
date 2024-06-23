import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class PostPetSmokeTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/v3/pet";
    }

    @Test
    public void postPetBasicTest() throws IOException {
        String requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/postPetRequestBody.json")));
        given()
                .body(requestBody)
                .relaxedHTTPSValidation()
                .contentType("application/json").
        when()
                .post("").
        then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("postPetResponseScheme.json")); // src/test/resources
    }
}