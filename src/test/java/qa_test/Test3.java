package qa_test;

import org.testng.annotations.Listeners;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Listeners(Listener.class)
public class Test3 extends TestBefore {
    @DataProvider(name = "price")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{3000}, {5000}, {10000}};
    }

    @Test(dataProvider = "price")
    public void checkPriceTest(int price) {
        StartPage page = new StartPage(driver);
        String category = "Компьютеры";
        String subCategory = "Планшеты";
        page.findProducts(category, subCategory);

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.enterSecondPrice(price);
        catalogPage.showYet();
        catalogPage.checkEmptyListItem();
        catalogPage.checkPriceLessPriceTo(price);
    }
}