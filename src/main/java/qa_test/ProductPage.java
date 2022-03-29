package qa_test;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends TestBefore {
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Добавление товара в Избранное")
    public void addToWishlist() {
        WebElement toWishlistButton =
                (new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.presenceOfElementLocated(
                                By.cssSelector("[data-auto='wishlist-button']"))
                ));
        toWishlistButton.click();
    }

    @Step("Переход в Избранное")
    public void goToWishlist() {
        WebElement wishlistButton = driver.findElement(
                By.xpath(".//a[@href='/my/wishlist']")
        );
        wishlistButton.click();
    }
}
