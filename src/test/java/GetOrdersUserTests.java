import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetOrdersUserTests {

    UserTestsSteps userTestsSteps = new UserTestsSteps();
    GetOrdersUserTestsSteps getOrdersUserTestsSteps = new GetOrdersUserTestsSteps();

    @Before
    public void createUser() {
        userTestsSteps.createUserIfNonExists();
    }

    @Test
    public void getOrdersUserWithoutAuthorizationTest() {
        Response getOrdersUser = getOrdersUserTestsSteps.getOrdersUser("");
        getOrdersUser.then().statusCode(401);
        assertEquals("You should be authorised", getOrdersUser.jsonPath().getString("message"));
    }

    @Test
    public void getOrdersUserWithAuthorizationTest() {
        Response getOrdersUser = getOrdersUserTestsSteps.getOrdersUser(userTestsSteps.getTokenUser());
        getOrdersUser.then().statusCode(200);
        assertTrue(getOrdersUser.jsonPath().get("success"));
    }

    @After
    public void deleteUser() {
        userTestsSteps.deleteUserIfExists();
    }
}
