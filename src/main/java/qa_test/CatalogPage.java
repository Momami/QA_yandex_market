package qa_test;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CatalogPage extends TestBefore {
    private WebDriver driver;
    private List<WebElement> elements = null;
    private WebElement product;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement fieldPriceToExtract(){
        String xpath = ".//*[@id='glpriceto']|.//div[@data-auto='filter-range-glprice']/div/span[2]//input";
        return (new WebDriverWait(driver, Duration.ofSeconds(5)))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        
    }

    private WebElement searchResultExtract(){
        String xpath = ".//*[@data-node-name='snippet-card'][1]//a[@data-node-name='title']/span";
        return (new WebDriverWait(driver, Duration.ofSeconds(5)))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    private void checkTab(String titleTab){
        while (true) {
            try {
                WebElement elementTab = searchResultExtract();
                String newTitle = elementTab.getText();
                if (!titleTab.equals(newTitle)) return;
            } catch (StaleElementReferenceException ignored){}
        }
    }

    @Step("Ввод правой границы ценового диапазона: {0}")
    public void enterSecondPrice(int price) {
        WebElement fieldPriceTo = fieldPriceToExtract();

        WebElement elementTabStart = searchResultExtract();
        String titleTabStart = elementTabStart.getText();

        // Вводим цену во второе поле
        fieldPriceTo.click();
        String priceStr = Integer.toString(price);
        fieldPriceTo.sendKeys(priceStr);

        checkTab(titleTabStart);
    }

    @Step("Нажатие на кнопку \"Показать еще\"")
    public void showYet() {
        while (true) {
            try {
                String xpath = ".//button[text()='Показать ещё']|" +
                        ".//*[@data-zone-name='SearchPager']//*[text()='Показать ещё']";

                WebElement showNewElement =
                        (new WebDriverWait(driver, Duration.ofSeconds(5)))
                                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

                showNewElement.click();
            } catch (Exception e) {
                break;
            }
        }
    }

    @Step("Проверка того, что список элементов не пуст")
    public void checkEmptyListItem() {
        elements = driver.findElements(By.cssSelector("[data-zone-name='snippet-card']"));
        Assert.assertNotNull(elements);
        Assert.assertTrue(elements.size() != 0);
    }

    @Step("Проверка ценового диапазона: все товары ценой до {0}")
    public void checkPriceLessPriceTo(int priceTo) {
        boolean flag = true;
        for (WebElement element : elements) {
            try {
                WebElement priceZone = element.findElement(By.cssSelector("[data-zone-name='price']"));
                String priceContent = priceZone.findElement(By.xpath(".//a//span/span")).getText();
                int price = Integer.parseInt(priceContent.replace(" ", ""));

                if (price > priceTo) {
                    flag = false;
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(flag);
    }

    @Step("Установка фильтра по производителю \"{0}\"")
    public void filterBrand(String brand, String tech) {
        WebElement brandsAll = (new WebDriverWait(driver, Duration.ofSeconds(5)))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//button[text()='Показать всё']")));
        brandsAll.click();

        WebElement fieldSearch = (new WebDriverWait(driver, Duration.ofSeconds(5)))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name='Поле поиска']")));
        fieldSearch.sendKeys(brand);
        fieldSearch.sendKeys(Keys.ENTER);

        (new WebDriverWait(driver, Duration.ofSeconds(5)))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[text()='" + brand + "']")))
                .click();
        (new WebDriverWait(driver, Duration.ofSeconds(5)))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[text()='" + tech + "']"))).click();

        WebElement searchResult = driver.findElement(By.cssSelector("[data-zone-name='SearchResults']"));
        (new WebDriverWait(driver, Duration.ofSeconds(5)))
                .until(ExpectedConditions.visibilityOf(searchResult));
    }

    @Step("Проверка наличия продукта {0} на странице")
    public void checkProduct(String name) {
        product = driver.findElement(
                By.xpath(".//article[@data-zone-name='snippet-card']//a[@title[contains(., '" + name + "')]]")
        );
        Assert.assertNotNull(product);
    }

    @Step("Переход на страницу с подробной информацией о продукте")
    public void goToProductInfo() {
        product.click();
        ArrayList<String> tabs2 = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
    }
}