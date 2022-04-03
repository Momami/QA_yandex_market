package qa_test;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;

public class StartPage extends TestBefore {
    private WebElement userButtonLog;
    private WebElement menuUser;
    private WebDriver driver;

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    private void findButtonAccount() {
        userButtonLog = driver.findElement(By.cssSelector("[data-zone-name='headerLoginButton']"));
    }

    @Step("Нажатие на кнопку \"Войти\"")
    public void clickButtonAccount() {
        findButtonAccount();
        userButtonLog.click();
    }

    private WebElement findLogoAccount() {
        return driver.findElement(By.cssSelector("[data-apiary-widget-id='/header/nav']"));
    }

    @Step("Поиск меню пользователя")
    public void findMenuUser() {
        WebElement userLogo = findLogoAccount();
        userLogo.click();
        WebElement profileMenu = driver.findElement(By.cssSelector("[data-zone-name='profileMenu']"));
        menuUser = profileMenu.findElement(By.cssSelector("[data-autotest-id='profile-menu-user']"));
    }

    @Step("Проверка имени пользователя")
    public void checkNameAccount(String name) {
        WebElement nameUser = menuUser.findElement(By.xpath("div[1]/div[2]/div[1]"));
        Assert.assertEquals(nameUser.getAttribute("textContent"), name);
    }

    @Step("Проверка почты пользователя")
    public void checkUserMenuEmail(String email) {
        WebElement userMenuEmail = menuUser.findElement(By.xpath("div[1]/div[2]/div[2]"));
        Assert.assertEquals(userMenuEmail.getAttribute("textContent"), email);
    }

    @Step("Поиск подкатегории {1} в категории {0}")
    public void findProducts(String category, String subCategory) {
        WebElement catalogPopupButton = driver.findElement(By.id("catalogPopupButton"));
        catalogPopupButton.click();

        WebElement categoriesLink = (new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[alt='" + category + "']"))));
        (new Actions(driver)).moveToElement(categoriesLink).build().perform();

        WebElement subCategoriesLink = (new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//a[text()='" + subCategory + "']"))));

        subCategoriesLink.click();
    }
}