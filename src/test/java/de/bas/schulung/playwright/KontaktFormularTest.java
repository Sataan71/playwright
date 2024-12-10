package de.bas.schulung.playwright;


import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright
public class KontaktFormularTest {

    public static final String SAMPLE_PAGE = "https://autoprojekt.simplytest.de/sample-page/";
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
        page.navigate(SAMPLE_PAGE);
    }

    @AfterEach
    public void tearDown() {
        page.close();
    }

    @Test
    public void checkSubmitDeactivated() {
        assertThat(page).hasURL(SAMPLE_PAGE);
        Locator locatorInputName = page.locator("#name");
        Locator locatorEmail = page.locator("#mail");
        Locator submitButton = page.locator("#submit");

        Assertions.assertEquals(0, locatorInputName.inputValue().length());
        Assertions.assertEquals(0, locatorEmail.inputValue().length());
        assertThat(submitButton).isDisabled();
    }

    @Test
    public void fillFields() {
        assertThat(page).hasURL(SAMPLE_PAGE);
        page.locator("#name").fill("Uwe K.");
        page.locator("#mail").fill("ukirschenmann@gmx.de");
        page.locator("#betreff").fill("Keine Ahnung");
        page.locator("#nachricht").fill("Also hört mal her");
        page.locator("#aktuelles_werkzeug").selectOption("Cypress");
        page.locator("input[type=radio][value=Protractor]").check();
        page.locator("input#dsgvo_zustimmung").check();

        Locator submitButton = page.locator("#submit");
        assertThat(submitButton).isEnabled();
    }

    @Test
    public void alertDialog1() {
        assertThat(page).hasURL(SAMPLE_PAGE);
        page.onceDialog(alert -> {
            Assertions.assertTrue(alert.message().contains("Unfortunately"));
            alert.accept();
        });
        page.locator("#alert1").click();
    }

    @Test
    public void alertDialog2() {
        assertThat(page).hasURL(SAMPLE_PAGE);
        page.onceDialog(alert -> {
            Assertions.assertTrue(alert.message().contains("Further information"));
            alert.accept();
        });
        page.locator("#alert2").click();
    }

    @Test
    public void testGutesAngebot(){
        assertThat(page).hasURL(SAMPLE_PAGE);
        Page newPage = context.waitForPage(() -> page.click("#angebot"));
        newPage.waitForLoadState();
        assertThat(newPage).hasURL("https://autoprojekt.simplytest.de/produkt/beanie/");
        newPage.close();
    }
}
