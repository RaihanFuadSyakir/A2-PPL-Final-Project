package a2.ppl.tugas.akhir.web.stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import a2.ppl.tugas.akhir.web.utils.ConfigReader;
import a2.ppl.tugas.akhir.web.utils.SeleniumHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.time.Duration;

public class CheckoutSteps {

    WebDriver driver;
    SeleniumHelper seleniumHelper;
    ConfigReader configReader;

    public CheckoutSteps() {
        configReader = new ConfigReader();
    }

    @Given("pengguna sudah login")
    public void login() {
        String url = "https://www.saucedemo.com";
        System.setProperty("webdriver.chrome.driver", configReader.getProperty("webdriver.chrome.driver"));
        driver = new ChromeDriver();
        seleniumHelper = new SeleniumHelper(driver);
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        assertEquals(seleniumHelper.isElementDisplayedById("login-button"), true);
        String username = "standard_user";
        String password = "secret_sauce";
        seleniumHelper.setInputByName("user-name", username);
        seleniumHelper.setInputByName("password", password);
        seleniumHelper.clickButtonById("login-button");
    }

    @And("Dan ada barang di dalam keranjang")
    public void barangDiDalamKeranjang() {
        addItem("add to cart", "Sauce Labs Backpack");
    }

    public void addItem(String buttonText, String productName) {
        productName = productName.toLowerCase();
        String combined = buttonText + " " + productName;
        combined = combined.replace(' ', '-');
        seleniumHelper.clickButtonByName(combined);
    }

    @When("pengguna menekan tombol Cart")
    public void penggunaMenekanTombolCart() {
        driver.findElement(By.id("shopping_cart_container")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_list")));
    }

    @And("pengguna menekan tombol Checkout pada halaman Cart")
    public void penggunaMenekanTombolCheckoutPadaHalamanCart() {
        driver.findElement(By.id("checkout")).click();
    }

    @Then("halaman Informasi Checkout ditampilkan")
    public void halamanInformasiCheckoutDitampilkan() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("checkout_info_container")));
    }

    @Given("halaman Checkout Information sudah tampil")
    public void halaman_checkout_information_sudah_tampil() {
        // Asumsi pengguna sudah berada di halaman Checkout Information
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("checkout_info_container"));
    }

    @When("pengguna memasukkan informasi pada field First name, Last name, dan Zip/Postal Code dengan valid")
    public void pengguna_memasukkan_informasi_yang_valid() {
        WebElement firstName = driver.findElement(By.id("first_name"));
        WebElement lastName = driver.findElement(By.id("last_name"));
        WebElement postalCode = driver.findElement(By.id("postal_code"));
        firstName.sendKeys("Lebron");
        lastName.sendKeys("James");
        postalCode.sendKeys("40121");
    }

    @When("pengguna menekan tombol Continue")
    public void pengguna_menekan_tombol_continue() {
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();
    }

    @Then("halaman Shipping Information ditampilkan")
    public void halaman_shipping_information_ditampilkan() {
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("checkout_summary_container"));
    }

    @When("pengguna menekan tombol Cancel")
    public void pengguna_menekan_tombol_cancel() {
        WebElement cancelButton = driver.findElement(By.id("cancel"));
        cancelButton.click();
    }

    @Then("halaman Cart ditampilkan")
    public void halaman_cart_ditampilkan() {
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("cart"));
    }
}