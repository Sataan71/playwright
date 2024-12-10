package de.bas.schulung.playwright;


import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright
public class VtsOnlineTest {

    public static final String SAMPLE_PAGE = "https://vts-upload.bundesamtsozialesicherung.de/integration/";
    private Page page;
    private Browser browser;
    private BrowserContext context;

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setSlowMo(1500);
        browser = playwright.chromium().launch(options);
        context = browser.newContext();
        page = context.newPage();
        page.navigate(SAMPLE_PAGE);
    }

    @AfterEach
    public void tearDown() {
        page.close();
    }

    @Test
    public void enterLoginPage() throws InterruptedException {
        page.locator("#username").fill("viorel.sfetea@bas.bund.de");
        page.locator("#password").fill("bON8iXebtAqVf2O2p4pF!");
        page.click("#kc-login");
        page.locator("//ul[@data-label='--Auswählen--']").click();
        page.locator("[id=\"form:klassifikationsschluessel_panel\"] li[data-item-value=\"42\"]").click();
    }
}
