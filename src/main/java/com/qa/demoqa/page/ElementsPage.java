package com.qa.demoqa.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ElementsPage {

    final private Page page;

    private final String elementsPage = "div:text('Elements')";
    private final String boxes = "span";

    public ElementsPage(Page page) {
        this.page = page;
    }

    public String getElementsPageTitle() {
        return page.title();
    }

    public String isElementsPage() {
        return page.locator(elementsPage).textContent();
    }

    public boolean isBoxExists(final String boxName) {
        return page.locator(boxes).filter(
                new Locator.FilterOptions().setHasText(boxName)).isVisible();
    }

    public TextBoxPage navigateTotextBoxPage() {
        page.locator(boxes).filter(
                new Locator.FilterOptions().setHasText("text-box")).click();
        return new TextBoxPage(page);
    }
}
