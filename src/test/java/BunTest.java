import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    private String name;
    private float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "{0} за {1} денег" )
    public static Object[][] getParameters() {
        return new Object[][] {
                {"black bun", 100.0F},
                {"white bun", 200.0F},
                {"red bun", 300.0F}
        };
    }

    @Test
    public void getNameTest() {
        Bun bun = new Bun(name, price);
        String actualName = bun.getName();
        assertEquals("Ожидалось имя булочки: " + name + ", но было получено: " + actualName, name, actualName);
    }

    @Test
    public void getPriceTest() {
        Bun bun = new Bun(name, price);
        float actualPrice = bun.getPrice();
        assertEquals("Ожидалось цена булочки: " + price + ", но было получено: " + actualPrice, price, actualPrice, 0);
    }

}