package de.beine;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright
public class MeinErsterTest {

    @Test
    void erstesSzenario() {
        try (Playwright playwright = Playwright.create()) {
            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
            options.setHeadless(false);
            options.setSlowMo(500);

            Browser browser = playwright.chromium().launch(options);
            Page page = browser.newPage();

            page.navigate("https://heise.de/");

            assertThat(page).hasURL("https://www.heise.de/");
        }
    }

    @Test
    void zweitesSzenario(Page page) {
        page.navigate("https://www.wikipedia.de/");
        page.getByLabel("Wikipedia durchsuchen").click();
        page.getByLabel("Wikipedia durchsuchen").fill("Hallo Welt!");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Suchen")).click();
        assertThat(page.locator("#firstHeading")).containsText("Hallo Welt!");
    }
}
