import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.equalTo;


public class GetPetSmokeTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/v3/pet";
    }

    @Test
    public void getPetIdBasicTest() {
        when()
                .get("/2").
        then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("responses/getPetResponseScheme.json")); // src/test/resources
    }

    @Test
    public void getPetIdOfMissingIdTest() {
        when()
                .get("/20").
        then()
                .statusCode(404)
                .body("code", equalTo(1))
                .body("type", equalTo("error"))
                .body("message", equalTo("Pet not found"));
    }
}