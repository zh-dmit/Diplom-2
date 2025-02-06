import io.qameta.allure.Step;
import io.restassured.response.Response;

public class UserTestsSteps {

    StellarburgersApi stellarburgersApi = new StellarburgersApi();

    @Step("Создать стандартного пользователя")
    public Response createUser() {
        return stellarburgersApi.createUser(CreateUserTestData.bodyStandartUser());
    }

    @Step("Получить токен пользователя")
    public String getTokenUser() {
        return stellarburgersApi.logInUser(CreateUserTestData.bodyStandartUser()).jsonPath().getString("accessToken");
    }

    @Step("Получить токен не стандартного пользователя")
    public String getTokenUser(User user) {
        return stellarburgersApi.logInUser(user).jsonPath().getString("accessToken");
    }

    @Step("Удалить пользователя")
    public void deleteUser() {
        Response deleteUserResponse = stellarburgersApi.deleteUser(getTokenUser());
        deleteUserResponse.then().statusCode(202);
    }

    @Step("Удалить не стандартного пользователя")
    public void deleteUser(String token) {
        Response deleteUserResponse = stellarburgersApi.deleteUser(token);
        deleteUserResponse.then().statusCode(202);
    }

    @Step("Проверить существует ли пользователь")
    public boolean checkIfUserExists() {
        return !(getTokenUser() == null);
    }

    @Step("Удалить пользователя, если он существует")
    public void deleteUserIfExists() {
        if (checkIfUserExists()) {
            deleteUser();
        }
    }

    @Step("Создать пользователя, если он не существует")
    public void createUserIfNonExists() {
        if (!checkIfUserExists()) {
            createUser();
        }
    }

    @Step("Создать пользователя с пустым логином")
    public Response createUserWithEmptyField() {
        return stellarburgersApi.createUser(CreateUserTestData.bodyUserWithEmptyField());
    }

    @Step("Залогинить стандартного пользователя")
    public Response logInUser() {
        return stellarburgersApi.logInUser(CreateUserTestData.bodyStandartUser());
    }

    @Step("Залогинить не стандартного пользователя")
    public Response logInUser(User user) {
        return stellarburgersApi.logInUser(user);
    }

    @Step("Залогинить пользователя с неверным логином")
    public Response logInUserWithWrongField() {
        return stellarburgersApi.logInUser(CreateUserTestData.bodyUserWithWrongField());
    }

    @Step("Изменить пароль пользователя")
    public Response changUserPassword(String token) {
        return stellarburgersApi.changUser(CreateUserTestData.bodyUserWithDifferentPassword(), token);
    }

    @Step("Изменить email пользователя")
    public Response changUserEmail(String token) {
        return stellarburgersApi.changUser(CreateUserTestData.bodyUserWithDifferentEmail(), token);
    }

    @Step("Изменить имя пользователя")
    public Response changUserName(String token) {
        return stellarburgersApi.changUser(CreateUserTestData.bodyUserWithDifferentName(), token);
    }
}
