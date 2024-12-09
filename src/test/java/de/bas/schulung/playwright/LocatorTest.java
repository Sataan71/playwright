package de.bas.schulung.playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright
public class LocatorTest {

    // 1. Locator
    @Test
    void submitButton(Page page) {
        page.navigate("https://autoprojekt.simplytest.de/sample-page/");
        assertThat(page.locator("#submit"))
                .isDisabled();
    }

    // 2. DSGVO – Beschreibungstext
    @Test
    void dsgvoText(Page page) {
        page.navigate("https://autoprojekt.simplytest.de/sample-page/");
        assertThat(page.locator("#dsgvo_zustimmung + span"))
                .containsText("I assure that this contact ");
    }

    // 3. Dop Down Current Automation Framework – Option „Cypress“
    @Test
    void optionCypress(Page page) {
        page.navigate("https://autoprojekt.simplytest.de/sample-page/");
        assertThat(page.locator("#aktuelles_werkzeug > option[value=Cypress]"))
                .containsText("Cypress");
    }


    // 4. Your Next Framework Radiofeld – Option „Something else…“
    @Test
    void optionSomethingElse(Page page) {
        page.navigate("https://autoprojekt.simplytest.de/sample-page/");
        Locator locator = page.locator(" #naechstes_werkzeug input[value^='Some']");
        Assertions.assertTrue(locator.inputValue().contains("Some"));
    }

    // 5. Link zu testautomation.org
    @Test
    void linkTestautomation(Page page) {
        page.navigate("https://autoprojekt.simplytest.de/sample-page/");
        // .entry-content > p:nth-child(2) > a:nth-child(1)
        Locator locator = page.locator(".entry-content a[href$=\"testautomatisierung.org\"]");
        String html = locator.innerHTML();
    }
}
