package com.demo.nopcommerce.resources.testdata;

import org.testng.annotations.DataProvider;


public class TestData {
    @DataProvider(name = "LoginData")
    public Object[][] getData() {
        return new Object[][]{
                {" dhanno@gmail.com", "123456"}
        };
    }

    @DataProvider(name = "InvalidData")
    public Object[][] getData1() {
        return new Object[][]{
                {"dhann@gmail.com", "12345"}
        };
    }

    @DataProvider(name = "Registration Data")
    public Object[][] Registration() {
        return new Object[][]{
                {"dhannoRun", "run", "4", "may", "1985", "dhanno limited", "dhannorun123@", "dhannorun123@"}

        };
    }

    @DataProvider(name = "invalid Registration Data")
    public Object[][] InvalidRegistration() {
        return new Object[][]{
                {"dhannoRun", "run", "4", "may", "1985", "dhanno limited", "dhannorun123@", "dhannorun123@"}
        };

    }

}
