package pl.degath.rest;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.RestAssured;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@MicronautTest
public class StudentPerformanceControllerTest {

    @Inject
    EmbeddedServer embeddedServer;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = embeddedServer.getURL().toString();
    }

    @Test
    public void testHomePage() {
        RestAssured.given()
                .get("/")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void sortStudentsByPerformance() {
        byte[] input = "Charlie,50.0\nLucy,80.0\nLinus,60\n".getBytes();

        RestAssured.given()
                .multiPart("file", "test.txt", input)
                .multiPart("algorithm", "bubble")
                .post("/")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void downloadSortedStudents() {
        RestAssured.given()
                .get("/downloadSortedStudents")
                .then()
                .assertThat()
                .statusCode(200);
    }
}