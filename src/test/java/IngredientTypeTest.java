import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    public static final String SAUCE = "SAUCE";
    public static final String FILLING = "FILLING";

    @Test
    public void sauceTest() {
        assertEquals("Ожидался тип ингредиента " + SAUCE + ", но был получен " + IngredientType.SAUCE.toString(), SAUCE, IngredientType.SAUCE.toString());
    }

    @Test
    public void fillingTest() {
        assertEquals("Ожидался тип ингредиента " + FILLING + ", но был получен " + IngredientType.FILLING.toString(), FILLING, IngredientType.FILLING.toString());
    }

}