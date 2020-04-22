package com.demo.nopcommerce.testsuite;

import com.demo.nopcommerce.excelutility.ExcelUtility;
import com.demo.nopcommerce.pages.HomePage;
import com.demo.nopcommerce.pages.LoginPage;
import com.demo.nopcommerce.testbase.TestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class LoginTestWithExcelFile extends TestBase {

    public static final String File_path = System.getProperty("user.dir") + "/src/test/java/com/demo/nopcommerce/resources/testdata/Checker.xlsx";

    HomePage homePage;
    LoginPage loginPage;
    SoftAssert softAssert;

    @BeforeMethod(groups = {"sanity", "smoke", "regression"})
    public void setUp() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        softAssert = new SoftAssert();
    }


    @BeforeClass(groups = {"sanity", "smoke", "regression"})
    public void setExcel() {
        try {
            ExcelUtility.setExcelFile(File_path, "Log");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @DataProvider(name = "Login Test")
    public Object[][] getData() {
        Object[][] data = ExcelUtility.getTestData("Excel");
        return data;
    }

    @Test(groups = {"sanity","regression"}, priority = 0)
    public void verifyUserShouldNavigateToLoginFeature() {
        //below methods calling from pages package
        homePage.clickOnLoginFeature();
        //compare expected with actual result
        softAssert.assertEquals("Welcome, Please Sign In!",loginPage.getWelcomeMessage());
        softAssert.assertAll();

    }
    @Test(groups = {"smoke","regression"}, dataProvider = "Login Test", priority = 1)
    public void verifyUserShouldLoginSuccessfully(String username, String password) {
        homePage.clickOnLoginFeature();
        loginPage.logToApp(username, password);
        softAssert.assertEquals("Welcome to our store", loginPage.welcomeToStore());
        softAssert.assertAll();
    }

    @Test(groups = {"regression"}, dataProvider = "Login Test", priority = 2)
    public void verifyUserShouldNotLoginSuccessfully(String username, String password) {
        homePage.clickOnLoginFeature();
        loginPage.logToApp(username, password);
        softAssert.assertEquals("Welcome to our", loginPage.welcomeToStore());
        softAssert.assertAll();


    }
}
