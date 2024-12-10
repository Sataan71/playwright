package de.beine.shop;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright
public class ShopTest {
    @Test
    public void test(Page page, BrowserContext browserContext) {
        page.navigate("https://autoprojekt.simplytest.de/");
        Locator locator = page.locator("h1");
        assertThat(locator).hasText("Shop");
        assertThat(locator).isVisible();

        Locator locator1 = page.locator("#site-header-cart span.count");
        assertThat(locator1).hasText("0 items");

        Locator locator2 = page.locator("a[data-product_id='22']");
        locator2.click();
        locator2.waitFor();

        page.navigate("https://autoprojekt.simplytest.de/cart");
        assertThat(locator1).hasText("1 item");

        Locator locator3 = page.locator("td[class='product-quantity'] input");
        assertThat(locator3).hasValue("1");
        locator3.fill("2");

        Locator locator4 = page.locator("button[name='update_cart']");
        locator4.waitFor();
        locator4.isEnabled();
        locator4.click();
        assertThat(locator1).hasText("2 items");




    }
}
