package de.bas.schulung.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@UsePlaywright(PlaywrightOptions.class)
public class OnlineShopTest {

    @Test
    void onlineshopTest(Page page) throws InterruptedException {
        page.navigate("https://autoprojekt.simplytest.de/");

        assertThat(page.locator(".woocommerce-products-header__title"))
                .containsText("Shop");
        String s = page.locator("#site-header-cart .count").textContent();
        assertTrue(s.startsWith("0 "));

        page.locator("[data-product_sku='woo-album']").click();

//        assertThat(page.locator("#site-header-cart .count")).containsText(Pattern.compile("1 items"));
//
//        page.locator("#site-header-cart a.cart-contents").click();
        Thread.sleep(10_000);
    }

    @Test
    void onlineshopTestbyTim(Page page) throws InterruptedException {
        page.navigate("https://autoprojekt.simplytest.de/");
        assertThat(page).hasURL("https://autoprojekt.simplytest.de/");

        assertThat(page.locator("h1")).hasText("Shop");
        assertTrue(page.locator("#site-header-cart .count").textContent().startsWith("0 "));

        page.locator(".products .product:first-of-type a.add_to_cart_button").click();

        page.waitForTimeout(3);

        page.locator(".products .product:first-of-type a.added_to_cart").click();
        page.locator(".shop_table input.qty").click();
        page.keyboard().press("ArrowUp");

        page.locator("button[name='update_cart']").click();
        page.locator(".entry-content .woocommerce-message").waitFor();
        assertThat(page.locator("td[data-title='Total']")).containsText("30");

        page.waitForTimeout(3);

        page.locator("a.checkout-button").click();

        page.locator("#billing_first_name").fill("Tim");
        page.locator("#billing_last_name").fill("Doe");
        page.locator("#select2-billing_country-container").click();
        page.locator("input.select2-search__field").fill("Germany");
        page.waitForTimeout(2);
        page.locator("ul.select2-results__options li:first-of-type").click();

        page.waitForTimeout(30);
    }
}
