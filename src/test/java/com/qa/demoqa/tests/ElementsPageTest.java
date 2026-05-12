package com.qa.demoqa.tests;

import com.qa.demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ElementsPageTest extends BaseTest {

    @Test(priority = 1)
    public void navigateToElementsPageTest() {
        elementsPage = homePage.navigateToElelmentsPage();
        String elementsPageTitle = elementsPage.isElementsPage();
        Assert.assertEquals(elementsPageTitle, "Elements");
    }

    @DataProvider(name = "getBoxNames")
    public Object[][] getBoxNames() {
        return new Object[][] {
                {"Text Box"},
                {"Check Box"},
                {"Radio Button"},
                {"Web Tables"},
                {"Buttons"}
        };
    }

    @Test(dataProvider = "getBoxNames", priority = 2)
    public void boxExistsTest(String boxName) {
        boolean isExists = elementsPage.isBoxExists(boxName);
        assert isExists;
    }

}
