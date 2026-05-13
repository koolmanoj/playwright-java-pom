package com.qa.demoqa.tests;

import com.microsoft.playwright.Page;
import com.qa.demoqa.factory.PlaywrightFactory;
import com.qa.demoqa.page.HomePageOpenCartNotUsed;
import com.qa.demoqa.page.LoginPageOpenCartNotUsed;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageOpenCartNotUsedTest {

    PlaywrightFactory pf;

    private Page page;
    private LoginPageOpenCartNotUsed loginPageOpenCartNotUsed;
    private HomePageOpenCartNotUsed homePageOpenCartNotUsed;

    @BeforeTest
    public void setUp() {
        System.out.println("Login page setup");
        pf = new PlaywrightFactory();
        page = pf.init("chromium", false, "https://demo.opencart.com/");
        loginPageOpenCartNotUsed = new LoginPageOpenCartNotUsed(page);
    }

    @AfterTest
    public void tearDown() {
        System.out.println("Login page tear down");
        pf.tearDown();
    }

    @Test
    public void loginPageTitleTest() {
        System.out.println("Login page title test");
        boolean flag = loginPageOpenCartNotUsed.isLoginPageTitleExist();
        assert flag;
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
                {"test1@test.com", "test1"},
                {"test1@test.com", "test2"},
                {"test1@test.com", "test3"}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(final String email, final String password) {
        System.out.println("Login page login test");
        loginPageOpenCartNotUsed.tryLogin(email, password);
        boolean flag = loginPageOpenCartNotUsed.isIncorrectLoginErrorMessageExist();
        assert flag;
    }
}
