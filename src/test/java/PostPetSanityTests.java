import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;


public class PostPetSanityTests extends BaseAPI {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = this.baseURI;
        RestAssured.port = this.port;
        RestAssured.basePath = this.basePath;
    }


    @Test
    public void postPetEmptyIdFieldTest() {
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

    @Test
    public void postPetExistingIdTest() throws IOException {
        String requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/requests/postPetExistingIdRequestBody.json")));
        given()
                .body(requestBody)
                .relaxedHTTPSValidation()
                .contentType("application/json").
        when()
                .post("").
        then()
                .statusCode(200);

        when().get("/1").then().body("id", equalTo(1),
                "category.id", equalTo(4));
    }

    @Test
    public void postPetEmptyCategoryTest() throws IOException {
        String requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/requests/postPetEmptyCategoryRequestBody.json")));
        given()
                .body(requestBody)
                .relaxedHTTPSValidation()
                .contentType("application/json").
        when()
                .post("").
        then()
                .statusCode(200)
                .body("category.id", equalTo(0));
    }

    @Test
    public void postPetExcessiveCategoryFieldTest() throws IOException {
        String requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/requests/postPetExcessiveCategoryFieldRequestBody.json")));
        given()
                .body(requestBody)
                .relaxedHTTPSValidation()
                .contentType("application/json").
        when()
                .post("").
        then()
                .statusCode(200)
                .body("category", not(hasKey("test")));
    }
}