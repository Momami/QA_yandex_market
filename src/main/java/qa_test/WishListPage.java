package qa_test;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class WishListPage extends TestBefore {
    private WebDriver driver;
    private WebElement product;

    public WishListPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка добавления товара {0} в Избранное")
    public void checkProductInWishList(String name) {
        product = driver.findElement(
                By.xpath(".//article[@data-zone-name='snippet-card']//a[@title[contains(., '" + name + "')]]")
        );
        Assert.assertNotNull(product);
    }
}
