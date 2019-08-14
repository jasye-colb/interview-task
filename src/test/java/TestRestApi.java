import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class TestRestApi {

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Test Description: Simple GET request example.")
    public void testGetToGoogle_200StatusCodeReturned() {
        given().when().get("http://www.google.com").then().statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Test Description: Simple POST request example.")
    public void testPostToJsonplaceholder_201StatusCodeReturned() {
        Map<String, Object> post = createDummyPostObj();

        given().contentType("application/json")
                .body(post)
                .when()
                .post("http://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201);
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Test Description: Simple POST request example.")
    public void testPutToJsonplaceholder_200StatusCodeReturned() {
        Map<String, Object> post = createDummyPostObj();
        post.put("id", 15);

        given().contentType("application/json")
                .body(post)
                .pathParam("postId", 15)
                .when()
                .put("http://jsonplaceholder.typicode.com/posts/{postId}")
                .then()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Test Description: Simple DELETE request example.")
    public void testDeleteToJsonplaceholder_200StatusCodeReturned() {
        Map<String, Object> post = createDummyPostObj();

        // Set up before test - create post and get ID.
        int postId = given().contentType("application/json")
                .body(post)
                .when()
                .post("http://jsonplaceholder.typicode.com/posts")
                .then()
                .extract()
                .path("id");

        // Actual test - delete post by ID.
        given().pathParam("postId", postId)
                .when()
                .delete("http://jsonplaceholder.typicode.com/posts/{postId}")
                .then()
                .statusCode(200);

    }

    private Map<String, Object> createDummyPostObj() {
        Map<String, Object> post = new HashMap<>();
        post.put("title", "foo");
        post.put("body", "bar");
        post.put("userId", 1);
        return post;
    }
}
