package qa_test;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class FooterScope extends TestBefore {
    private WebDriver driver;
    private WebElement history;

    public FooterScope(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка наличия истории просмотров")
    public void checkHasHistory() {
        history = driver.findElement(
                By.cssSelector("[data-apiary-widget-id='/footer/history']")
        );
        Assert.assertNotNull(history);
    }

    @Step("Проверка добавления товара {0} в историю просмотров")
    public void checkProductInSee(String name) {
        WebElement product = history.findElement(By.xpath(".//div[text()[contains(., '" + name + "')]]"));
        Assert.assertNotNull(product);
    }
}
