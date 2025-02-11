import io.restassured.response.Response;

public class StellarburgersApi extends BaseHttpClient {

    private String pathCreateUser = "/api/auth/register";
    private String pathLogInUser = "/api/auth/login";
    private String pathDeleteLogInUser = "/api/auth/user";
    private String pathPatchUser = "api/auth/user";
    private String pathGetPathCreateOrder = "/api/orders";
    private String pathGetIngredients = "/api/ingredients";
    private String pathGetOrdersUser = "/api/orders";

    public Response createUser(Object object) {
        return doPostRequest(pathCreateUser, object);
    }

    public Response logInUser(Object object) {
        return doPostRequest(pathLogInUser, object);
    }

    public Response deleteUser(String token) {
        return doDeleteRequest(pathDeleteLogInUser, token);
    }

    public Response changUser(Object object, String token) {
        return doPatchRequest(pathPatchUser, object, token);
    }

    public Response createOrder(Object body, String token) {
        return doPostRequest(pathGetPathCreateOrder, body, token);
    }

    public Response getIngredients() {
        return doGetRequest(pathGetIngredients);
    }

    public Response getOrdersUser(String token) {
        return doGetRequest(pathGetOrdersUser, token);
    }
}
