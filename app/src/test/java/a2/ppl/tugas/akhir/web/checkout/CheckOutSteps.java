package a2.ppl.tugas.akhir.web.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import a2.ppl.tugas.akhir.web.utils.ConfigReader;
import a2.ppl.tugas.akhir.web.utils.SeleniumHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;

public class CheckOutSteps {

    WebDriver driver;
    SeleniumHelper seleniumHelper;
    ConfigReader configReader;

    public CheckOutSteps() {
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

    @And("ada barang di dalam keranjang")
    public void barangDiDalamKeranjang() {
        seleniumHelper.clickButtonById("item_4_title_link");
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_info_container")));
        assertTrue(seleniumHelper.isElementDisplayedById("checkout_info_container"));
        driver.quit();
    }

    @Given("halaman Checkout Information sudah tampil")
    public void halaman_checkout_information_sudah_tampil() {
        // Asumsi pengguna sudah berada di halaman Checkout Information
        assertTrue(seleniumHelper.isElementDisplayedById("checkout_info_container"));
    }

    @When("pengguna memasukkan informasi pada field First name, Last name, dan Zip\\/Postal Code dengan valid")
    public void pengguna_memasukkan_informasi_yang_valid() {
        seleniumHelper.setInputByName("firstName", "Lebron");
        seleniumHelper.setInputByName("lastName", "James");
        seleniumHelper.setInputByName("postalCode", "40121");
    }

    @When("pengguna menekan tombol Continue")
    public void pengguna_menekan_tombol_continue() {
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();
    }

    @Then("halaman Shipping Information ditampilkan")
    public void halaman_shipping_information_ditampilkan() {
        assertTrue(seleniumHelper.isElementDisplayedById("checkout_summary_container"));
        driver.quit();
    }

    @When("pengguna menekan tombol Cancel")
    public void pengguna_menekan_tombol_cancel() {
        seleniumHelper.clickButtonById("cancel");
    }

    @Then("halaman Cart ditampilkan")
    public void halaman_cart_ditampilkan() {
        assertTrue(seleniumHelper.isElementDisplayedById("cart_contents_container"));
        driver.quit();
    }
}