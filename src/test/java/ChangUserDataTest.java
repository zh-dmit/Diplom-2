import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChangUserDataTest {

    private UserTestsSteps userTestsSteps = new UserTestsSteps();

    @Before
    public void createUser() {
        userTestsSteps.createUserIfNonExists();
    }

    @Test
    public void changUserEmailWithAuthorizationTest() {
        Response changUserEmail = userTestsSteps.changUserEmail(userTestsSteps.getTokenUser());

        changUserEmail.then().statusCode(200);
        assertEquals(CreateUserTestData.bodyUserWithDifferentEmail().getEmail(), changUserEmail.jsonPath().getString("user.email"));

        String token = userTestsSteps.getTokenUser(CreateUserTestData.bodyUserWithDifferentEmail());
        userTestsSteps.deleteUser(token);
    }

    @Test
    public void changUserPasswordWithAuthorizationTest() {
        Response changUserPassword = userTestsSteps.changUserPassword(userTestsSteps.getTokenUser());

        changUserPassword.then().statusCode(200);
        assertTrue(changUserPassword.jsonPath().get("success"));

        String token = userTestsSteps.getTokenUser(CreateUserTestData.bodyUserWithDifferentPassword());
        userTestsSteps.deleteUser(token);
    }

    @Test
    public void changUserNameWithAuthorizationTest() {
        Response changUserName = userTestsSteps.changUserName(userTestsSteps.getTokenUser());

        changUserName.then().statusCode(200);
        assertEquals(CreateUserTestData.bodyUserWithDifferentName().getName(), changUserName.jsonPath().getString("user.name"));

        String token = userTestsSteps.getTokenUser(CreateUserTestData.bodyUserWithDifferentName());
        userTestsSteps.deleteUser(token);
    }

    @Test
    public void changUserEmailWithoutAuthorizationTest() {
        Response changUserEmail = userTestsSteps.changUserEmail("");

        changUserEmail.then().statusCode(401);
        assertEquals("You should be authorised", changUserEmail.jsonPath().getString("message"));
    }

    @Test
    public void changUserNameWithoutAuthorizationTest() {
        Response changUserName = userTestsSteps.changUserName("");

        changUserName.then().statusCode(401);
        assertEquals("You should be authorised", changUserName.jsonPath().getString("message"));
    }

    @Test
    public void changUserPasswordWithoutAuthorizationTest() {
        Response changUserPassword = userTestsSteps.changUserPassword("");

        changUserPassword.then().statusCode(401);
        assertEquals("You should be authorised", changUserPassword.jsonPath().getString("message"));
    }

}
