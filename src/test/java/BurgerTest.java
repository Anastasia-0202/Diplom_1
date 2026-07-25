import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    public Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient0;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Before
    public void makeBurger() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        when(bun.getName()).thenReturn("Название булочки");
        assertEquals("Установлена не верная булочка","Название булочки", burger.bun.getName());
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredient0);
        assertEquals("В бургер было добавлено " + burger.ingredients.size() + " ингредиентов, ожидалось добавление 1 ингредиента", 1, burger.ingredients.size());
        assertTrue("Ингредиент не был добавлен в бургер", burger.ingredients.contains(ingredient0));
    }

    @Test
    public void removeIngredientTest() {
        burger.ingredients.add(ingredient0);
        burger.ingredients.add(ingredient1);
        burger.ingredients.add(ingredient2);
        burger.removeIngredient(1);
        assertEquals("Из бургера не был удален ингредиент", 2, burger.ingredients.size());
        assertTrue("Из бургера был удален не верный ингредиент", burger.ingredients.contains(ingredient0) && burger.ingredients.contains(ingredient2) && !burger.ingredients.contains(ingredient1));
    }

    @Test
    public void moveIngredientTest() {
        burger.ingredients.add(ingredient0);
        burger.ingredients.add(ingredient1);
        burger.ingredients.add(ingredient2);
        burger.moveIngredient(1, 0);
        assertEquals("В бергере не верное число ингредиентов", 3, burger.ingredients.size());
        assertTrue("В бергере находится не верный ингредиент", burger.ingredients.contains(ingredient0) && burger.ingredients.contains(ingredient1) && burger.ingredients.contains(ingredient2));
        assertEquals("Ингредиент не был перемещен на новое место", burger.ingredients.get(0), ingredient1);
        assertEquals("Ингредиент не был перемещен со старого места", burger.ingredients.get(1), ingredient0);
    }

    @Test
    public void getPrice() {
        burger.bun = bun;
        burger.ingredients.add(ingredient0);
        burger.ingredients.add(ingredient1);
        burger.ingredients.add(ingredient2);

        when(bun.getPrice()).thenReturn(50.0F);
        when(ingredient0.getPrice()).thenReturn(20.0F);
        when(ingredient1.getPrice()).thenReturn(25.0F);
        when(ingredient2.getPrice()).thenReturn(5.0F);

        float expectedPrice = 150.0F;

        assertEquals("Ожидалась итоговая цена " + expectedPrice + ", но была получена " + burger.getPrice(), expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptTest() {
        burger.bun = bun;
        burger.ingredients.add(ingredient0);
        burger.ingredients.add(ingredient1);
        burger.ingredients.add(ingredient2);

        when(bun.getName()).thenReturn("bun");

        when(ingredient0.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2.getType()).thenReturn(IngredientType.SAUCE);

        when(ingredient0.getName()).thenReturn("ingredient0");
        when(ingredient1.getName()).thenReturn("ingredient1");
        when(ingredient2.getName()).thenReturn("ingredient2");

        when(bun.getPrice()).thenReturn(50.0F);
        when(ingredient0.getPrice()).thenReturn(20.0F);
        when(ingredient1.getPrice()).thenReturn(25.0F);
        when(ingredient2.getPrice()).thenReturn(5.0F);

        String expectedReceipt = "(==== bun ====)\r\n" +
                "= filling ingredient0 =\r\n" +
                "= filling ingredient1 =\r\n"    +
                "= sauce ingredient2 =\r\n" +
                "(==== bun ====)\r\n" +
                "\r\n" +
                "Price: 150,000000\r\n";

        assertEquals("Ожидался чек вида\n" + expectedReceipt + "\nБыл получен чек вида\n" + burger.getReceipt(), expectedReceipt, burger.getReceipt());
    }

}