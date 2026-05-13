package com.qa.demoqa.tests;

import com.qa.demoqa.base.BaseTest;
import com.qa.demoqa.constrains.AppConstants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void logoTest() {
        System.out.println("Home page logo test");
        boolean flag = homePage.isLogoExist();
        Assert.assertTrue(flag);
    }

    @Test
    public void bannerImageTest() throws InterruptedException {
        System.out.println("Home page banner image test");
        boolean flag = homePage.isBannerImageExist();
        Assert.assertTrue(flag);
    }

    @DataProvider(name = "sectionData")
    public Object[][] getSectionData() {
        return new Object[][] {
                {"Elements"},
                {"Forms"},
                {"Alerts, Frame & Windows"},
                {"Widgets"},
                {"Interactions"},
                {"Book Store Application"}
        };
    }

    @Test(dataProvider = "sectionData")
    public void allLinkTabExistsTest(final String sectionName) {
        System.out.println("Home page all link tab test");
        boolean flag = homePage.allLinkTabExists(sectionName);
        Assert.assertTrue(flag);
    }

    @Test
    public void titleTest() {
        Assert.assertEquals(homePage.getTitle(), AppConstants.HOME_PAGE_TITLE);
    }

}
