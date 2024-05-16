package utils;

import pageObjects.PageFactory;

import java.io.IOException;

public class TestSetUp {
    public PageFactory pageFactory;
    public TestBase testBase;
    public Commons commons;

    public TestSetUp() throws IOException {
        testBase = new TestBase();
        pageFactory = new PageFactory(testBase.WebDriverManager());
        commons = new Commons(testBase.WebDriverManager());
    }
}
