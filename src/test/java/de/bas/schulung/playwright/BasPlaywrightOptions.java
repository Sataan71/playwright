package de.bas.schulung.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;

public class BasPlaywrightOptions implements OptionsFactory {
    @Override
    public Options getOptions() {
        var newContextOptions = new Browser.NewContextOptions();
        newContextOptions.setProxy("http://10.12.184.56:8001/");
        var connectOptions = new BrowserType.ConnectOptions();
        connectOptions.setTimeout(10000);

        return new Options()
                .setHeadless(true)
                .setConnectOptions(connectOptions)
                .setContextOptions(newContextOptions);
    }
}
