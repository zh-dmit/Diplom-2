import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogInUserTests {

    private UserTestsSteps userTestsSteps = new UserTestsSteps();

    @Before
    public void createUser() {
        userTestsSteps.createUserIfNonExists();
    }

    @Test
    public void logInUserTest() {
        Response loginUserResponse = userTestsSteps.logInUser();

        loginUserResponse.then().statusCode(200);
        assertTrue(loginUserResponse.jsonPath().get("success"));
    }

    @Test
    public void logInUserWithWrongFieldTest() {
        Response loginUserResponse = userTestsSteps.logInUserWithWrongField();

        loginUserResponse.then().statusCode(401);
        assertEquals("email or password are incorrect", loginUserResponse.jsonPath().get("message"));
    }

    @After
    public void deleteUser() {
        userTestsSteps.deleteUserIfExists();
    }
}
