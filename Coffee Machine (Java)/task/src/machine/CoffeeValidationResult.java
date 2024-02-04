package machine;

public class CoffeeValidationResult {
    boolean isMade;

    String missingIngredient;

    public CoffeeValidationResult(boolean b, String ingredient) {
        this.isMade = b;
        this.missingIngredient = ingredient;
    }
}
