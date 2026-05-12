package com.qa.demoqa.tests;

import com.microsoft.playwright.Page;
import com.qa.demoqa.factory.PlaywrightFactory;
import com.qa.demoqa.page.HomePageOpenCartNotUsed;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTestOpenCartNotUsed {

    private PlaywrightFactory pf;
    Page page;

    HomePageOpenCartNotUsed homePageOpenCartNotUsed;

    @BeforeTest
    public void setUp() {
        System.out.println("Home page setup");
        pf = new PlaywrightFactory();
        page = pf.init("chromium", "https://demo.opencart.com/");
        homePageOpenCartNotUsed = new HomePageOpenCartNotUsed(page);
    }

    @AfterTest
    public void tearDown() {
        System.out.println("Home page tear down");
        pf.tearDown();
    }

    @Test
    public void logoTest() {
        System.out.println("Home page logo test");
        boolean flag = homePageOpenCartNotUsed.isLogoExist();
        Assert.assertTrue(flag);
    }

    @Test
    public void getHomePageTitleTest() {
        System.out.println("Home page title test");
        String title = homePageOpenCartNotUsed.getHomePageTitle();
        Assert.assertEquals(title, "OpenCart - Demo");
    }

    @DataProvider(name = "searchData")
    public Object[][] getSearchData() {
        return new Object[][] {
                {"MacBook"},
                {"iMac"},
                {"Apple"}
        };
    }

    @Test(dataProvider = "searchData")
    public void searchTest(final String productName) {
        System.out.println("Home page search test");
        String header = homePageOpenCartNotUsed.doSearch(productName);
        Assert.assertTrue(header.contains(productName));
    }

}
