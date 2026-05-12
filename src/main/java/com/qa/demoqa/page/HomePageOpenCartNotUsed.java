package com.qa.demoqa.page;

import com.microsoft.playwright.Page;

public class HomePageOpenCartNotUsed {

    private Page page;

    private String logo = "div#logo";
    private String searchInput = "input[name='search']";
    private String searchButton = "button.btn.btn-light.btn-lgg";
    private String searchHeader = "div#content h1n";


    public HomePageOpenCartNotUsed(Page page) {
        this.page = page;
    }

    public boolean isLogoExist() {
        page.locator(logo).isVisible();
        return true;
    }

    public String getHomePageTitle() {
        return page.title();
    }

    public String getHomePageUrl() {
        return page.url();
    }

    public String doSearch(String productName) {
        page.locator(searchInput).fill(productName);
        page.locator(searchButton).click();
        return page.locator(searchHeader).textContent();
    }
}
