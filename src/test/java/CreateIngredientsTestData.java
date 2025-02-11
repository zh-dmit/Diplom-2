import java.util.List;

public class CreateIngredientsTestData {

    public static Ingredients validIngridientsList(List<String> ingridientsList) {
        return new Ingredients(ingridientsList);
    }

    public static Ingredients unvalidIngridientsList() {
        List<String> ingridientsList = List.of("324dfsc32ds342");
        return new Ingredients(ingridientsList);
    }

    public static Ingredients emptyIngridientsList() {
        List<String> ingridientsList = null;
        return new Ingredients(ingridientsList);
    }
}
