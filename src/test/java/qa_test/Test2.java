package qa_test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({Listener.class})
public class Test2 extends TestBefore {
    @Test
    public void checkCameraTest() {
        StartPage page = new StartPage(driver);
        String category = "Электроника";
        String subCategory = "Экшн-камеры";
        String name = "Sony HDR-AZ1VW";
        String brand = "Sony";
        String tech = "Wi-Fi";
        page.findProducts(category, subCategory);

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.filterBrand(brand, tech);
        catalogPage.checkProduct(name);
        catalogPage.goToProductInfo();

        ProductPage productPage = new ProductPage(driver);
        productPage.addToWishlist();
        productPage.goToWishlist();

        WishListPage wishListPage = new WishListPage(driver);
        wishListPage.checkProductInWishList(name);

        FooterScope scope = new FooterScope(driver);
        scope.checkHasHistory();
        scope.checkProductInSee(name);
    }
}