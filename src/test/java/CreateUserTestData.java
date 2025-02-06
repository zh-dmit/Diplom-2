public class CreateUserTestData {

    public static User bodyStandartUser() {
        return new User("testdmit@test.com", "12345", "testdmituser");
    }

    public static User bodyUserWithEmptyField() {
        return new User("", "12345", "testdmituser");
    }

    public static User bodyUserWithWrongField() {
        return new User("wrongLogin", "12345", "testdmituser");
    }

    public static User bodyUserWithDifferentPassword() {
        return new User("testdmit@test.com", "54321", "testdmituser");
    }

    public static User bodyUserWithDifferentEmail() {
        return new User("testdmitdifferentemail@test.com", "12345", "testdmituser");
    }

    public static User bodyUserWithDifferentName() {
        return new User("testdmit@test.com", "12345", "testdmituserdifferentname");
    }
}
