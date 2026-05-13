package com.qa.demoqa.factory;

import com.microsoft.playwright.*;
import com.qa.demoqa.utils.PlaywrightThreadManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

public class PlaywrightFactory {

    private Properties prop;

    public Page init(final String browserName, final boolean headless, final String url) {
        System.out.println("Browser name is: " + browserName);

        /*PlaywrightThreadManager.setTlPlaywright(Playwright.create());
        switch (browserName.toLowerCase()) {
            case "chromium" -> PlaywrightThreadManager.setTlBrowser(headlessFalse(PlaywrightThreadManager.getTlPlaywright().chromium()));
            case "firefox" -> PlaywrightThreadManager.setTlBrowser(headlessFalse(PlaywrightThreadManager.getTlPlaywright().firefox()));
            case "webkit" ->  PlaywrightThreadManager.setTlBrowser(headlessFalse(PlaywrightThreadManager.getTlPlaywright().webkit()));
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
        };

        PlaywrightThreadManager.setTlBrowserContext(PlaywrightThreadManager.getTlBrowser().newContext(
                new Browser.NewContextOptions()));

        PlaywrightThreadManager.setTlPage(PlaywrightThreadManager.getTlBrowserContext().newPage());
        PlaywrightThreadManager.getTlPage().navigate(url);*/

        initializePlaywright();
        initializeBrowser(browserName, headless);
        initializeContext();
        initializePage();
        navigateToUrl(url);

        return PlaywrightThreadManager.getTlPage();
    }

    private void navigateToUrl(String url) {
        PlaywrightThreadManager.getTlPage().navigate(url);
    }

    private void initializePage() {
        final Page page = PlaywrightThreadManager.getTlBrowserContext().newPage();
        PlaywrightThreadManager.setTlPage(page);
    }

    private void initializeContext() {
        final BrowserContext browserContext = PlaywrightThreadManager.getTlBrowser().newContext();

        PlaywrightThreadManager.setTlBrowserContext(browserContext);
    }

    private void initializeBrowser(final String browserName, final boolean headless) {
        final Browser browser = switch (browserName.toLowerCase()) {
            case "chromium" -> launchBrowser(PlaywrightThreadManager.getTlPlaywright().chromium(), headless);
            case "firefox" -> launchBrowser(PlaywrightThreadManager.getTlPlaywright().firefox(), headless);
            case "webkit" ->  launchBrowser(PlaywrightThreadManager.getTlPlaywright().webkit(), headless);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
        };

        PlaywrightThreadManager.setTlBrowser(browser);
    }

    private void initializePlaywright() {
        PlaywrightThreadManager.setTlPlaywright(Playwright.create());;
    }

    private Browser launchBrowser(BrowserType browserType, final boolean headless) {
        return browserType.launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(headless)
                        .setSlowMo(100)
                        .setArgs(Arrays.asList("--disable-blink-features=AutomationControlled"))
                        /*.setEnv(Map.of(
                "SELENIUM_REMOTE_URL",
                "http://localhost:4444/"
        ))*/);
    }

    private Browser launchBrowserOnGrid(BrowserType browserType) {
        return browserType.connectOverCDP("http://localhost:4444/wd/hub");
    }

    public void tearDown() {
       PlaywrightThreadManager.unload();
    }

    public Properties propInit () throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/cofig/config.properties");
        prop = new Properties();
        prop.load(fileInputStream);

        return prop;
    }
}
