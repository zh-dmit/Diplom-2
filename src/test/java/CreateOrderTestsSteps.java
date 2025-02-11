import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

public class CreateOrderTestsSteps {

    StellarburgersApi stellarburgersApi = new StellarburgersApi();

    @Step("Получить id ингридиентов")
    public List<String> getIdIngredients() {
        Response getIngredients = stellarburgersApi.getIngredients();
        JsonPath jsonPath = new JsonPath(getIngredients.body().asString());
        return jsonPath.getList("data._id");
    }

    @Step("Создать заказ с ингридиентами")
    public Response createOrder(Object ingredients, String token) {
        return stellarburgersApi.createOrder(ingredients, token);
    }
}
