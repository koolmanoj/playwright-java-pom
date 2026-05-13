package com.qa.demoqa.base;

import com.microsoft.playwright.Page;
import com.qa.demoqa.factory.PlaywrightFactory;
import com.qa.demoqa.page.ElementsPage;
import com.qa.demoqa.page.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected PlaywrightFactory pf;
    protected Page page;
    protected HomePage homePage;
    protected ElementsPage elementsPage;
    Properties prop;

    @BeforeTest
    @Parameters({"browserName", "headless"})
    public void setUp(final String browserName, final boolean headless) throws IOException {
        System.out.println("Home page setup");
        pf = new PlaywrightFactory();
        prop = pf.propInit();
        page = pf.init(browserName, headless, prop.getProperty("URL"));
        homePage = new HomePage(page);
    }

    @AfterTest
    public void tearDown() {
        System.out.println("Home page tear down");
        pf.tearDown();
    }
}
