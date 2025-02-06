import io.qameta.allure.Step;
import io.restassured.response.Response;

public class GetOrdersUserTestsSteps {

    StellarburgersApi stellarburgersApi = new StellarburgersApi();

    @Step("Получение заказов пользователя")
    public Response getOrdersUser(String token) {
        return stellarburgersApi.getOrdersUser(token);
    }
}
