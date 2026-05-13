package com.qa.demoqa.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {

    private Page page;

    private String logo = "img[src='/assets/Toolsqa-DZdwt2ul.jpg']";
    private String bannerImage = "img.banner-image";
    private String sections = "h5";

    public HomePage (Page page) {
        this.page = page;
    }

    public boolean isLogoExist() {
        return page.locator(logo).isVisible();
    }

    public boolean isBannerImageExist() throws InterruptedException {
        Locator banner = page.locator(bannerImage);
        banner.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        return banner.isVisible();
    }

    public String getTitle() {
        return page.title();
    }

    public boolean allLinkTabExists(final String sectionName) {
        return page.locator(sections).filter(
                new Locator.FilterOptions().setHasText(sectionName)).isVisible();
    }

    public ElementsPage navigateToElelmentsPage() {
        page.locator(sections).filter(
                new Locator.FilterOptions().setHasText("Elements")).click();
        return new ElementsPage(page);
    }
}
