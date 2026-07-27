import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerPriceParametrizedTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // bunPrice, fillingPrices, expectedTotalPrice
                {50.0F, List.of(20.0F), 120.0F},          // 50*2 + 20
                {50.0F, List.of(20.0F, 25.0F), 145.0F},  // 50*2 + 20 + 25
                {60.0F, List.of(10.0F, 10.0F, 5.0F), 145.0F}, // 60*2 + 10 + 10 + 5
                {40.0F, List.of(), 80.0F}                // 40*2
        });
    }

    private final float bunPrice;
    private final List<Float> fillingPrices;
    private final float expectedTotalPrice;

    public BurgerPriceParametrizedTest(float bunPrice, List<Float> fillingPrices, float expectedTotalPrice) {
        this.bunPrice = bunPrice;
        this.fillingPrices = fillingPrices;
        this.expectedTotalPrice = expectedTotalPrice;
    }

    @Mock
    private Bun bun;

    private Burger burger;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        burger = new Burger();
        burger.setBuns(bun);
    }

    @After
    public void tearDown() {
        bun = null;
        burger = null;
    }

    @Test
    public void calculatePriceWithDifferentIngredients() {
        for (float price : fillingPrices) {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            Mockito.when(ingredient.getPrice()).thenReturn(price);
            burger.addIngredient(ingredient);
        }

        assertEquals(
                "Цена не совпадает с ожидаемой",
                expectedTotalPrice,
                burger.getPrice(),
                0.01F
        );
    }
}