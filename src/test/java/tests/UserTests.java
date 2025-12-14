package tests;

import api.UserApi;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTests {

    UserApi userApi = new UserApi();
    String username = "LisaTest";

    @Test(priority = 1)
    public void testCreateUser() {

        User user = new User();
        user.setId(1001);
        user.setUsername(username);
        user.setFirstName("Lisa");
        user.setLastName("Test");
        user.setEmail("lisa@test.com");
        user.setPassword("12345");
        user.setPhone("000111222");

        Response response = userApi.createUser(user);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testGetUserByUsername() {

        Response response = userApi.getUserByUsername(username);

        Assert.assertEquals(response.getStatusCode(), 200);

        User user = response.as(User.class);

        Assert.assertEquals(user.getUsername(), username);
        Assert.assertEquals(user.getFirstName(), "Lisa");
        Assert.assertEquals(user.getLastName(), "Test");
    }
    @Test(priority = 3)
    public void testUpdateUser() {

        User updatedUser = new User();
        updatedUser.setId(1001);
        updatedUser.setUsername(username);
        updatedUser.setFirstName("LisaUpdated");
        updatedUser.setLastName("TestUpdated");
        updatedUser.setEmail("lisa.updated@test.com");
        updatedUser.setPassword("54321");
        updatedUser.setPhone("999888777");

        Response response = userApi.updateUser(username, updatedUser);

        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test(priority = 4)
    public void testGetUserAfterUpdate() {

        Response response = userApi.getUserByUsername(username);
        Assert.assertEquals(response.getStatusCode(), 200);

        User user = response.as(User.class);

        Assert.assertEquals(user.getFirstName(), "LisaUpdated");
        Assert.assertEquals(user.getEmail(), "lisa.updated@test.com");
    }
    @Test(priority = 5)
    public void testDeleteUser() {

        Response response = userApi.deleteUser(username);

        Assert.assertEquals(response.getStatusCode(), 200);
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
