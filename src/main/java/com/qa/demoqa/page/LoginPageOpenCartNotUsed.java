package com.qa.demoqa.page;

import com.microsoft.playwright.Page;

public class LoginPageOpenCartNotUsed {

    private Page page;

    private String emailId = "input#input-email";
    private String password = "input#input-password";
    private String loginButton = "button.btn.btn-primary.btn-lg.hidden-xs";
    private String incorrectLoginErrorMessage = "div:has-text('No match for E-Mail and/or Password.')";

    public boolean isLoginPageTitleExist() {
        return page.title().contains("Account Login");
    }

    public void tryLogin(String email, String pwd) {
        page.locator(emailId).fill(email);
        page.locator(password).fill(pwd);
        page.locator(loginButton).click();
    }

    public boolean isIncorrectLoginErrorMessageExist() {
        return page.locator(incorrectLoginErrorMessage).isVisible();
    }

    public LoginPageOpenCartNotUsed(Page page) {
        this.page = page;
    }
}
