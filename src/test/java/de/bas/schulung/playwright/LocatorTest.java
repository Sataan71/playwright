package de.bas.schulung.playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
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
                .containsText("I assure that this contact form has been sent through an automated testI assure that this contact form has been sent through an automated testI assure that this contact form has been sent through an automated test");
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
        // span.wpcf7-list-item:last > input:nth-child(1)
        // html body.page-template-default.page.page-id-2.wp-embed-responsive.theme-storefront.woocommerce-js.storefront-align-wide.right-sidebar.woocommerce-active div#page.hfeed.site div#content.site-content div.col-full div#primary.content-area main#main.site-main article#post-2.post-2.page.type-page.status-publish.hentry div.entry-content div#wpcf7-f57-p2-o1.wpcf7.js form.wpcf7-form.init p label span.wpcf7-form-control-wrap span#naechstes_werkzeug.wpcf7-form-control.wpcf7-radio span.wpcf7-list-item.last input
        // Locator locator = page.locator("#naechstes_werkzeug > *.last > input");
        Locator locator = page.locator("#naechstes_werkzeug > *.last-child > input");
        // Locator locator = page.locator("#naechstes_werkzeug input[value*='...']");
        assertThat(locator).hasValue("Something else...");
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
