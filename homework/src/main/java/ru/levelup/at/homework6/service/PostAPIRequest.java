package ru.levelup.at.homework6.service;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import ru.levelup.at.homework6.model.CreateCommentDataRequest;
import ru.levelup.at.homework6.model.CreatePostDataRequest;

public class PostAPIRequest {
    public static final String POST_ENDPOINT = "/posts";

    private final RequestSpecification requestSpecification;

    public PostAPIRequest(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response getPost() {
        return given()
            .spec(requestSpecification)
            .when()
            .get(POST_ENDPOINT)
            .andReturn();
    }

    public Response createPost(CreatePostDataRequest body) {
        return given()
            .spec(requestSpecification)
            .body(body)
            .when()
            .post(POST_ENDPOINT)
            .andReturn();
    }

    public Response getPostByParameters(final Map<String, String> parameters) {
        return given()
            .spec(requestSpecification)
            .queryParams(parameters)
            .when()
            .get(POST_ENDPOINT)
            .andReturn();
    }

    public Response getPostById(int id) {
        return given()
            .spec(requestSpecification)
            .when()
            .get(POST_ENDPOINT + "/" + id)
            .andReturn();
    }

    public Response updatePostById(CreatePostDataRequest body, int id) {
        return given()
            .spec(requestSpecification)
            .body(body)
            .when()
            .put(POST_ENDPOINT + "/" + id)
            .andReturn();
    }

    public Response deletePostById(int id) {
        return given()
            .spec(requestSpecification)
            .when()
            .delete(POST_ENDPOINT + "/" + id)
            .andReturn();
    }

}
