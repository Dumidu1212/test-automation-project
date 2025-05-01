package com.sliit.sepqm.testing.profile;

import com.sliit.sepqm.testing.database.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Full-stack test (real servlet container).
 * We insert one user into the in-memory H2 DB *before every test*
 * so /api/users/{id} always has something to return.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserProfileApiTest {

    @Autowired
    private UserRepository repo;

    private Long seededId;

    @BeforeAll
    void restAssuredSetup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port    = 8080;
        RestAssured.requestSpecification =
                RestAssured.given().contentType(ContentType.JSON);
    }

    @BeforeEach
    void seedDatabase() {
        // clear anything left over from previous runs
        repo.deleteAll();
        // {noop} disables bcrypt for this dummy record – we don’t log in anyway
        User u = new User("Alice", "alice@example.com",
                "{noop}dummyPw", "X", "USER");
        seededId = repo.save(u).getId();
    }

    @Test
    void getUserProfile_returnsCorrectData() {
        given()
                .when()
                .get("/api/users/" + seededId)
                .then()
                .statusCode(200)
                .body("id", equalTo(seededId.intValue()))
                .body("email", equalTo("alice@example.com"))
                .body("$", not(hasKey("password")));
    }
}
