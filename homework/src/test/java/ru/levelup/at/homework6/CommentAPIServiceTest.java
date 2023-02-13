package ru.levelup.at.homework6;

import static  org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.levelup.at.homework6.model.CommentData;
import ru.levelup.at.homework6.model.CreateCommentDataRequest;
import ru.levelup.at.homework6.service.CommentAPIRequest;


public class CommentAPIServiceTest extends BaseAPIServiceTest {

    static Stream<Arguments> commentParametersDataProvider() {
        var comments = getComments();
        var randomNumber = new Random().nextInt(5);

        return Stream.of(
            Arguments.of(Map.of("post_id", comments[randomNumber].getPostId())),
            Arguments.of(Map.of("name", comments[randomNumber].getName())),
            Arguments.of(Map.of("email", comments[randomNumber].getEmail())),
            Arguments.of(Map.of("body", comments[randomNumber].getBody()))
        );
    }

    static Stream<Arguments> parametersForCreatingCommentsDataProvider() {
        var posts = getPosts();
        var faker = new Faker();

        List<Arguments> commentParameters = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            commentParameters.add(Arguments.of(
                Integer.toString(posts[i].getId()),
                faker.name().firstName() + " " + faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.lorem().sentence(5)
            ));
        }
        return commentParameters.stream();
    }

    private CommentData[] getCommentsByParameters(Map<String, String> parameters) {
        return new CommentAPIRequest(requestSpecification())
            .getCommentByParameters(parameters)
            .then()
            .spec(responseSpecificationWithCode200())
            .extract()
            .as(CommentData[].class);
    }

    @Test
    void getCommentsTest() {

        var responseBody = new CommentAPIRequest(requestSpecification())
             .getComment()
             .then()
             .spec(responseSpecificationWithCode200())
             .extract()
             .as(CommentData[].class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(responseBody).extracting(CommentData::getId).isNotNull();
            softly.assertThat(responseBody).extracting(CommentData::getPostId).isNotNull();
            softly.assertThat(responseBody).extracting(CommentData::getName).isNotNull();
            softly.assertThat(responseBody).extracting(CommentData::getEmail).isNotNull();
            softly.assertThat(responseBody).extracting(CommentData::getBody).isNotNull();
        });
    }

    @ParameterizedTest
    @MethodSource("commentParametersDataProvider")
    void getCommentByParameterTest(Map<String, String> parameters) {
        if (parameters.containsKey("post_id")) {
            var responseBody = getCommentsByParameters(parameters);
            assertThat(responseBody[0].getPostId()).isEqualTo(parameters.get("post_id"));
        } else if (parameters.containsKey("name")) {
            var responseBody = getCommentsByParameters(parameters);
            assertThat(responseBody[0].getName()).isEqualTo(parameters.get("name"));
        } else if (parameters.containsKey("email")) {
            var responseBody = getCommentsByParameters(parameters);
            assertThat(responseBody[0].getEmail()).isEqualTo(parameters.get("email"));
        } else if (parameters.containsKey("body")) {
            var responseBody = getCommentsByParameters(parameters);
            assertThat(responseBody[0].getBody()).isEqualTo(parameters.get("body"));
        }
    }

    @ParameterizedTest
    @MethodSource("commentIdDataProvider")
    void getCommentById(int id) {
        var responseBody = new CommentAPIRequest(requestSpecification())
            .getCommentById(id)
            .then()
            .spec(responseSpecificationWithCode200())
            .extract()
            .as(CommentData.class);

        assertThat(responseBody.getId()).isEqualTo(id);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, -55, 11001, 999999})
    void getNonexistentCommentByIdTest(int id) {
        new CommentAPIRequest(requestSpecification())
            .getCommentById(id)
            .then()
            .spec(responseSpecificationWithCode404());
    }

    @ParameterizedTest
    @MethodSource("parametersForCreatingCommentsDataProvider")
    void createCommentTest(String postId, String name, String email, String body) {
        var requestBody = CreateCommentDataRequest.builder()
                                             .postId(postId)
                                             .name(name)
                                             .email(email)
                                             .body(body)
                                             .build();

        CommentData responseBody = new CommentAPIRequest(requestSpecification())
            .createComment(requestBody)
            .then()
            .spec(responseSpecificationWithCode201())
            .extract()
            .as(CommentData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(responseBody.getId()).isNotNull();
            softly.assertThat(responseBody.getPostId()).isEqualTo(requestBody.getPostId());
            softly.assertThat(responseBody.getName()).isEqualTo(requestBody.getName());
            softly.assertThat(responseBody.getEmail()).isEqualTo(requestBody.getEmail());
            softly.assertThat(responseBody.getBody()).isEqualTo(requestBody.getBody());
        });
    }

    @ParameterizedTest
    @MethodSource("parametersForCreatingCommentsDataProvider")
    void updateCommentTest(String postId, String name, String email, String body) {
        var comments = getComments();
        var commentId = comments[new Random().nextInt(5)].getId();

        var requestBody = CreateCommentDataRequest.builder()
                                             .postId(postId)
                                             .name(name)
                                             .email(email)
                                             .body(body)
                                             .build();

        CommentData responseBody = new CommentAPIRequest(requestSpecification())
            .updateCommentById(requestBody, commentId)
            .then()
            .spec(responseSpecificationWithCode200())
            .extract()
            .as(CommentData.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(responseBody.getId()).isEqualTo(commentId);
            softly.assertThat(responseBody.getPostId()).isEqualTo(requestBody.getPostId());
            softly.assertThat(responseBody.getName()).isEqualTo(requestBody.getName());
            softly.assertThat(responseBody.getEmail()).isEqualTo(requestBody.getEmail());
            softly.assertThat(responseBody.getBody()).isEqualTo(requestBody.getBody());
        });
    }

    @ParameterizedTest
    @MethodSource("parametersForCreatingCommentsDataProvider")
    void updateNonexistentCommentTest(String postId, String name, String email, String body) {
        var commentId = new Random().nextInt(888888 + 111111);

        var requestBody = CreateCommentDataRequest.builder()
                                             .postId(postId)
                                             .name(name)
                                             .email(email)
                                             .body(body)
                                             .build();

        new CommentAPIRequest(requestSpecification())
            .updateCommentById(requestBody, commentId)
            .then()
            .spec(responseSpecificationWithCode404());
    }

    @ParameterizedTest
    @MethodSource("commentIdDataProvider")
    void deleteCommentTest(int id) {
        new CommentAPIRequest(requestSpecification())
            .deleteCommentById(id)
            .then()
            .spec(responseSpecificationWithCode204());
    }
}
