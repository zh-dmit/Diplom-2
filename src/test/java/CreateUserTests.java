import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateUserTests {

    private UserTestsSteps userTestsSteps = new UserTestsSteps();

    @Before
    public void deleteUser() {
        userTestsSteps.deleteUserIfExists();
    }

    @Test
    public void createUserTest() {
        Response createUserResponse = userTestsSteps.createUser();

        createUserResponse.then().statusCode(200);
        assertTrue(createUserResponse.jsonPath().get("success"));
    }

    @Test
    public void createUserIfExistsTest() {
        userTestsSteps.createUser().then().statusCode(200);
        Response createUserResponse = userTestsSteps.createUser();

        createUserResponse.then().statusCode(403);
        assertEquals("User already exists", createUserResponse.jsonPath().get("message"));
    }

    @Test
    public void createUserWithEmptyFieldTest() {
        Response createUserResponse = userTestsSteps.createUserWithEmptyField();

        createUserResponse.then().statusCode(403);
        assertEquals("Email, password and name are required fields", createUserResponse.jsonPath().get("message"));
    }
}
