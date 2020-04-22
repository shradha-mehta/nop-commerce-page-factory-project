package com.demo.nopcommerce.testsuite;

import com.demo.nopcommerce.pages.HomePage;
import com.demo.nopcommerce.pages.LoginPage;
import com.demo.nopcommerce.resources.testdata.TestData;
import com.demo.nopcommerce.testbase.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



public class LoginTestWithDataProviderClass extends TestBase {
    HomePage homePage;
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(groups = {"sanity", "smoke", "regression"})
    public void setUp() {
        homePage = new HomePage();
        loginPage = new LoginPage();
    }

    @Test(groups = {"sanity", "regression"}, priority = 0)
    public void verifyUserShouldNavigateToLoginFeature() {
        //below methods calling from pages package
        homePage.clickOnLoginFeature();
        //compare expected with actual result
        softAssert.assertEquals("Welcome, Please Sign In!", loginPage.getWelcomeMessage());
        softAssert.assertAll();

    }
    @Test(groups = {"smoke","regression"}, dataProvider = "LoginData", dataProviderClass = TestData.class, priority = 1)
    public void verifyUserShouldLoginSuccessfully(String username, String password) {
        homePage.clickOnLoginFeature();
        loginPage.logToApp(username, password);
        softAssert.assertEquals("Welcome to our store", loginPage.welcomeToStore());
        softAssert.assertAll();
    }

    @Test(groups = {"regression"}, dataProvider = "InvalidData", dataProviderClass = TestData.class, priority = 2)
    public void verifyUserShouldNotLoginWithInvalidCredentials(String username, String password) {
        homePage.clickOnLoginFeature();
        loginPage.logToApp(username, password);
        softAssert.assertEquals("Welcome to our", loginPage.welcomeToStore());
        softAssert.assertAll();
    }

}