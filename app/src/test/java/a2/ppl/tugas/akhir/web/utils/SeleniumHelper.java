package a2.ppl.tugas.akhir.web.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class SeleniumHelper {
    private WebDriver driver;

    public SeleniumHelper(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElementById(String id) {
        return driver.findElement(By.id(id));
    }

    public WebElement getElementByName(String name) {
        return driver.findElement(By.name(name));
    }

    public WebElement getElementByClassName(String className) {
        return driver.findElement(By.className(className));
    }

    public void setInputByName(String elementName, String value) {
        WebElement element = getElementByName(elementName);
        element.clear();
        element.sendKeys(value);
    }

    public void clickButtonById(String id) {
        WebElement button = getElementById(id);
        button.click();
    }

    public void clickButtonByName(String name) {
        WebElement button = getElementByName(name);
        button.click();
    }

    public void clickElementById(String id) {
        WebElement button = getElementById(id);
        button.click();
    }

    public void clickElementByClassName(String className) {
        WebElement element = getElementByClassName(className);
        element.click();
    }

    public boolean isElementDisplayedById(String id) {
        try {
            WebElement element = getElementById(id);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementDisplayedByClassName(String className) {
        try {
            WebElement element = getElementByClassName(className);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementDisplayedByName(String name) {
        try {
            WebElement element = getElementByName(name);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}