package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;

import static io.restassured.http.ContentType.JSON;

public class UserApi {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    public Response createUser(User user) {
        return RestAssured
                .given()
                .contentType(JSON)
                .body(user)
                .post(BASE_URL + "/user");
    }

    public Response login(String username, String password) {
        return RestAssured
                .given()
                .get(BASE_URL + "/user/login?username=" + username + "&password=" + password);
    }
    public Response getUserByUsername(String username) {
        return RestAssured
                .given()
                .get(BASE_URL + "/user/" + username);
    }
    public Response updateUser(String username, User user) {
        return RestAssured
                .given()
                .contentType(JSON)
                .body(user)
                .put(BASE_URL + "/user/" + username);
    }
    public Response deleteUser(String username) {
        return RestAssured
                .given()
                .delete(BASE_URL + "/user/" + username);
    }
    public Response logout() {
        return RestAssured
                .given()
                .get(BASE_URL + "/user/logout");
    }

}
