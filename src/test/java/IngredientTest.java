import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {

    private IngredientType type;
    private String name;
    private float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Ингридиент {1} типа {0} за {2} денег" )
    public static Object[][] getParameters() {
        return new Object[][] {
                {IngredientType.FILLING, "hot sauce", 100.0F},
                {IngredientType.FILLING, "sour cream", 200.0F},
                {IngredientType.FILLING, "chili sauce", 300.0F},
                {IngredientType.SAUCE, "cutlet", 100.0F},
                {IngredientType.SAUCE, "dinosaur", 200.0F},
                {IngredientType.SAUCE, "sausage", 300.0F},
        };
    }

    @Test
    public void getPriceTest() {
        Ingredient ingredient = new Ingredient(type, name, price);
        float actualPrice = ingredient.getPrice();
        assertEquals("Ожидалось цена ингредиента: " + price + ", но была получено: " + actualPrice, price, actualPrice, 0);
    }

    @Test
    public void getNameTest() {
        Ingredient ingredient = new Ingredient(type, name, price);
        String actualName = ingredient.getName();
        assertEquals("Ожидалось имя ингредиента: " + name + ", но было получено: " + actualName, name, actualName);
    }

    @Test
    public void getIngredientTypeTest() {
        Ingredient ingredient = new Ingredient(type, name, price);
        IngredientType actualType = ingredient.getType();
        assertEquals("Ожидался ингредиент типа: " + type + ", но был получен: " + actualType, type, actualType);
    }

}