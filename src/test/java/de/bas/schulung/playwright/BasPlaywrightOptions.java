package de.bas.schulung.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import com.microsoft.playwright.options.Proxy;

/**
 * siehe auch Doku https://playwright.dev/java/docs/junit
 */
public class BasPlaywrightOptions implements OptionsFactory {
    @Override
    public Options getOptions() {
        var newContextOptions = new Browser.NewContextOptions();

        Proxy proxy = new Proxy("http://10.12.184.56:8001/");
        proxy.setBypass("localhost,.bvaetw.de,.bvaint.de,.bva.de,.internal");
        newContextOptions.setProxy(proxy);

        var connectOptions = new BrowserType.ConnectOptions();
        connectOptions.setTimeout(10000);

        return new Options()
                .setHeadless(true)
                .setConnectOptions(connectOptions)
                .setContextOptions(newContextOptions);
    }
}
