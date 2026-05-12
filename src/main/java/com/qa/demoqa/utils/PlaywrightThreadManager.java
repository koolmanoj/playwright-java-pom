package com.qa.demoqa.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public final class PlaywrightThreadManager {

    private void PlaywrightThreadManager() {}

    private static final ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> tlPage = new ThreadLocal<>();

    public static Playwright getTlPlaywright() {
        return tlPlaywright.get();
    }

    public static void setTlPlaywright(Playwright playwright) {
        tlPlaywright.set(playwright);
    }

    public static Browser getTlBrowser() {
        return tlBrowser.get();
    }

    public static void setTlBrowser(Browser browser) {
        tlBrowser.set(browser);
    }

    public static BrowserContext getTlBrowserContext() {
        return tlBrowserContext.get();
    }

    public static void setTlBrowserContext(BrowserContext browserContext) {
        tlBrowserContext.set(browserContext);
    }

    public static Page getTlPage() {
        return tlPage.get();
    }

    public static void setTlPage(Page page) {
        tlPage.set(page);
    }

    public static void unload() {

        if (getTlPage() != null) {
            getTlPage().close();
        }

        if (getTlBrowserContext() != null) {
            getTlBrowserContext().close();
        }

        if (getTlBrowser() != null) {
            getTlBrowser().close();
        }

        if (getTlPlaywright() != null) {
            getTlPlaywright().close();
        }

        tlPage.remove();
        tlBrowserContext.remove();
        tlBrowser.remove();
        tlPlaywright.remove();
    }

}
