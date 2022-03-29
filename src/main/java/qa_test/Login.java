package qa_test;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;


public class Login extends TestBefore {
    private WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод логина")
    public void enterLogin(String login) {
        try {
            WebElement choicePost = (new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[text()='Почта']"))));
            choicePost.click();
        } catch (Exception ignored) {}

        WebElement logIn = driver.findElement(By.cssSelector("[data-t='field:input-login']"));
        logIn.click();
        logIn.sendKeys(login);
        logIn.sendKeys(Keys.ENTER);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        WebElement passwordLine = (new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-t='field:input-passwd']"))));;
        passwordLine.sendKeys(password);
        passwordLine.sendKeys(Keys.ENTER);
    }
}