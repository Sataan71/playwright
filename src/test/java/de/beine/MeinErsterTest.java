package de.beine;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MeinErsterTest {

    @Test
    void erstesSzenario() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();

        page.navigate("https://heise.de/");

        assertThat(page).hasURL("https://www.heise.de/");

        playwright.close();
    }
}
