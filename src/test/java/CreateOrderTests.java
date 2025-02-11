import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateOrderTests {

    CreateOrderTestsSteps createOrderTestsSteps = new CreateOrderTestsSteps();
    UserTestsSteps userTestsSteps = new UserTestsSteps();

    @Before
    public void createUser() {
        userTestsSteps.createUserIfNonExists();
    }

    @Test
    public void createOrderWithoutAuthorizationTest() {
        Response createOrder = createOrderTestsSteps.createOrder(CreateIngredientsTestData.validIngridientsList(createOrderTestsSteps.getIdIngredients()), "");
        createOrder.then().statusCode(200);
        assertTrue(createOrder.jsonPath().get("success"));
    }

    @Test
    public void createOrderWithAuthorizationTest() {
        Response createOrder = createOrderTestsSteps.createOrder(CreateIngredientsTestData.validIngridientsList(createOrderTestsSteps.getIdIngredients()), userTestsSteps.getTokenUser());
        createOrder.then().statusCode(200);
        assertEquals(CreateUserTestData.bodyStandartUser().getEmail(), createOrder.jsonPath().getString("order.owner.email"));
    }

    @Test
    public void createOrderWithoutIngredientsTest() {
        Response createOrder = createOrderTestsSteps.createOrder(CreateIngredientsTestData.emptyIngridientsList(),"");
        createOrder.then().statusCode(400);
    }

    @Test
    public void createOrderWithWrongIngredientsTest() {
        Response createOrder = createOrderTestsSteps.createOrder(CreateIngredientsTestData.unvalidIngridientsList(),"");
        createOrder.then().statusCode(500);
    }

    @After
    public void deleteUser() {
        userTestsSteps.deleteUserIfExists();
    }


}
