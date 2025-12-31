package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;

import static io.restassured.http.ContentType.JSON;

public class UserApi {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

        private RequestSpecification baseRequest() {
            return RestAssured
                    .given()
                    .contentType(JSON);
        }

        public Response createUser(User user) {
            return baseRequest()
                    .body(user)
                    .post(BASE_URL + "/user");
        }

        public Response login(String username, String password) {
            return baseRequest()
                    .queryParam("username", username)
                    .queryParam("password", password)
                    .get(BASE_URL + "/user/login");
        }

        public Response getUserByUsername(String username) {
            return baseRequest()
                    .pathParam("username", username)
                    .get(BASE_URL + "/user/{username}");
        }

        public Response updateUser(String username, User user) {
            return baseRequest()
                    .pathParam("username", username)
                    .body(user)
                    .put(BASE_URL + "/user/{username}");
        }

        public Response deleteUser(String username) {
            return baseRequest()
                    .pathParam("username", username)
                    .delete(BASE_URL + "/user/{username}");
        }

        public Response logout() {
            return baseRequest()
                    .get(BASE_URL + "/user/logout");
        }
    }

