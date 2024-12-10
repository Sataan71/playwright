package de.bas.schulung.playwright;


import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright
public class OnlineShopTest {

    public static final String SHOP_START = "https://autoprojekt.simplytest.de/";
    private Page page;
    private Browser browser;
    private BrowserContext context;

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setSlowMo(500);
        browser = playwright.chromium().launch(options);
        context = browser.newContext();
        page = context.newPage();
        page.navigate(SHOP_START);
    }

    @AfterEach
    public void tearDown() {
        page.close();
    }

    @Test
    public void doShopping() {
        assertThat(page).hasURL(SHOP_START);
        page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions());
        String ueberschrift = page.locator("header h1").textContent();
        Assertions.assertEquals("Shop", ueberschrift);
        String warenkorbleer = page.locator(".cart-contents .count").textContent();
        Assertions.assertEquals("0 items", warenkorbleer);
        page.getByLabel("Add “Album” to your cart").click();
        page.getByTitle("View cart").click();
        page.getByLabel("Album quantity").fill("2");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Update cart")).click();
        String wert = page.locator("[data-title='Total'] bdi").textContent();
        Assertions.assertTrue(wert.contains("30,00"));
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed to checkout ")).click();
        page.locator("#billing_first_name").fill("Uwe");
        page.locator("#billing_last_name").fill("Kirsche");
        page.locator("#billing_company").fill("Schwarzkopf");
        page.locator("#select2-billing_country-container").click();
    }
}
