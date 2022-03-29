package qa_test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Listener.class)
public class Test4 extends TestBefore {
    @Test
    public void checkProduct() {
        driver.get("https://market.yandex.ru/product--15-6-noutbuk-honor-magicbook-15-2021bmh-wdq9hn-" +
                   "1920x1080-amd-ryzen-5-2-1-ggts-ram-8-gb-ssd-512-gb-win10-home/1699610203?cpa=1");
        FooterScope scope = new FooterScope(driver);
        scope.checkHasHistory();
        String name = "15.6\" Ноутбук HONOR MagicBook 15";
        scope.checkProductInSee(name);
    }
}
