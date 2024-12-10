package de.bas.schulung.playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright(PlaywrightOptions.class)
public class VtsUploadTest {

    static final String KEYCLOAK_USER = System.getProperty("playwrightUser");
    static final String KEYCLOAK_PASSWORD = System.getProperty("playwrightPassword");

    @Test
    void fillMenu(Page page) throws InterruptedException {
        doLogin(page);
        chooseCheckboxOption(page, "form:klassifikationsschluessel", 20);
        chooseCheckboxMenu(page, "form:geltungsbereiche", "1", "2");
        Thread.sleep(50_000);
    }

    void doLogin(Page page) throws InterruptedException {
        page.navigate("https://vts-upload.bundesamtsozialesicherung.de/integration/");
        assertThat(page)
                .hasURL(Pattern.compile("https://[a-zA-Z.]*/auth/realms/VTS-TEST/.*$"));
        page.locator("#username").fill(KEYCLOAK_USER);
        page.locator("#password").fill(KEYCLOAK_PASSWORD);
        page.locator("#kc-login").click(new Locator.ClickOptions());
        assertThat(page)
                .hasURL("https://vts-upload.bundesamtsozialesicherung.de/integration/");

    }

    void chooseCheckboxMenu(Page page, String componentId, String... selectedOptions) {
        for (String selectedOption : selectedOptions) {
            page.locator("[id='%s'] ul.ui-selectcheckboxmenu-multiple-container".formatted(componentId)).click();
            page.locator("[id='%s_panel'] li[data-item-value='%s']".formatted(componentId, selectedOption)).click();
        }
    }

    public void chooseCheckboxOption(Page page, String componentId, int dataItemValue) {
        page.locator("[id='%s'] ul.ui-selectcheckboxmenu-multiple-container".formatted(componentId)).click();
        page.locator("[id='%s_panel'] li[data-item-value='%d']".formatted(componentId, dataItemValue)).click();
    }
}
