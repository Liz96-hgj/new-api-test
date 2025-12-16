package tests;

import api.UserApi;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    private UserApi userApi;
    private String username;
    private int userId = 1001;

    @BeforeClass
    public void setup() {
        userApi = new UserApi();
        username = "LisaTest" + System.currentTimeMillis(); // унікальний username
    }

    @Test(priority = 1)
    public void testCreateUser() {
        User user = User.builder()
                .id(userId)
                .username(username)
                .firstName("Lisa")
                .lastName("Test")
                .email("lisa@test.com")
                .password("1234")
                .phone("999888777")
                .build();

        Response response = userApi.createUser(user);
        Assert.assertEquals(response.getStatusCode(), 200, "User creation failed");
    }

    @Test(priority = 2, dependsOnMethods = {"testCreateUser"})
    public void testGetUserByUsername() {
        Response response = userApi.getUserByUsername(username);
        Assert.assertEquals(response.getStatusCode(), 200, "User not found");

        User user = response.as(User.class);
        Assert.assertEquals(user.getUsername(), username);
        Assert.assertEquals(user.getFirstName(), "Lisa");
        Assert.assertEquals(user.getLastName(), "Test");
    }

    @Test(priority = 3, dependsOnMethods = {"testGetUserByUsername"})
    public void testUpdateUser() {
        User updatedUser = User.builder()
                .id(userId)
                .username(username)
                .firstName("LisaUpdated")
                .lastName("TestUpdated")
                .email("lisa.updated@test.com")
                .password("54321")
                .phone("999888777")
                .build();

        Response response = userApi.updateUser(username, updatedUser);
        Assert.assertEquals(response.getStatusCode(), 200, "User update failed");
    }

    @Test(priority = 4, dependsOnMethods = {"testUpdateUser"})
    public void testGetUserAfterUpdate() {
        Response response = userApi.getUserByUsername(username);
        Assert.assertEquals(response.getStatusCode(), 200, "User not found after update");

        User user = response.as(User.class);
        Assert.assertEquals(user.getFirstName(), "LisaUpdated");
        Assert.assertEquals(user.getLastName(), "TestUpdated");
        Assert.assertEquals(user.getEmail(), "lisa.updated@test.com");
    }

    @Test(priority = 5, dependsOnMethods = {"testGetUserAfterUpdate"})
    public void testDeleteUser() {
        Response response = userApi.deleteUser(username);
        Assert.assertEquals(response.getStatusCode(), 200, "User deletion failed");

        Response getResponse = userApi.getUserByUsername(username);
        Assert.assertEquals(getResponse.getStatusCode(), 404, "Deleted user still exists");
    }
    @Test(priority = 6)
    public void testGetDeletedUser() {

        Response response = userApi.getUserByUsername(username);

        Assert.assertEquals(response.getStatusCode(), 404);
    }
    @Test(priority = 7)
    public void testLogin() {

        Response response = userApi.login(username, "54321");

        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test(priority = 8)
    public void testLogout() {

        Response response = userApi.logout();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
