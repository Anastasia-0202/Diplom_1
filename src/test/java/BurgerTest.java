import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

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
        assertEquals("Установлена не верная булочка", "Название булочки", burger.bun.getName());
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredient0);
        assertEquals(1, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(ingredient0));
    }

    @Test
    public void removeIngredientTest() {
        burger.ingredients.add(ingredient0);
        burger.ingredients.add(ingredient1);
        burger.ingredients.add(ingredient2);

        burger.removeIngredient(1);

        assertEquals(2, burger.ingredients.size());
        assertTrue(
                burger.ingredients.contains(ingredient0) &&
                        burger.ingredients.contains(ingredient2) &&
                        !burger.ingredients.contains(ingredient1)
        );
    }

    @Test
    public void moveIngredientTest() {
        burger.ingredients.add(ingredient0);
        burger.ingredients.add(ingredient1);
        burger.ingredients.add(ingredient2);

        burger.moveIngredient(1, 0);

        assertEquals(3, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
        assertEquals(ingredient0, burger.ingredients.get(1));
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
                "= filling ingredient1 =\r\n" +
                "= sauce ingredient2 =\r\n" +
                "(==== bun ====)\r\n\r\nPrice: 150,000000\r\n";

        assertEquals("Ожидался чек вида", expectedReceipt, burger.getReceipt());
    }
}