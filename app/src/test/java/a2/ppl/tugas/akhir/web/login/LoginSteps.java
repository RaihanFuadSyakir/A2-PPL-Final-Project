package a2.ppl.tugas.akhir.web.login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import a2.ppl.tugas.akhir.web.utils.ConfigReader;
import a2.ppl.tugas.akhir.web.utils.SeleniumHelper;
import io.cucumber.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

public class LoginSteps {
    WebDriver driver;
    SeleniumHelper seleniumHelper;
    ConfigReader configReader;

    public LoginSteps() {
        configReader = new ConfigReader();
    }

    @Given("Pengguna berhasil mengakses website Swaglabs pada browser")
    public void access_url() {
        String url = "https://www.saucedemo.com";
        System.setProperty("webdriver.chrome.driver", configReader.getProperty("webdriver.chrome.driver"));
        driver = new ChromeDriver();
        seleniumHelper = new SeleniumHelper(driver);
        driver.get(url);
    }

    @Given("Pengguna berada pada halaman login")
    public void checkIsCurrentLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        assertEquals(seleniumHelper.isElementDisplayedById("login-button"), true);
    }

    @Given("Pengguna menginput username {string} dan password {string}")
    public void setUsernameAndPassword(String username, String password) {
        seleniumHelper.setInputByName("user-name", username);
        seleniumHelper.setInputByName("password", password);
    }

    @Given("Pengguna menginput username {string}")
    public void set_username(String username) {
        seleniumHelper.setInputByName("user-name", username);
    }

    @Given("Pengguna menginput password {string}")
    public void set_password(String password) {
        seleniumHelper.setInputByName("password", password);
    }

    @When("Pengguna menekan tombol login")
    public void press_login_button() {
        seleniumHelper.clickButtonById("login-button");
    }

    @Then("Pengguna tetap berada pada halaman login")
    public void checkIsStillInLoginPage() {
        seleniumHelper.isElementDisplayedById("login-button");
    }

    @Then("check url {string}")
    public void check_url(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, currentUrl);
        driver.quit();
    }

    @Then("Login berhasil dan sistem menampilkan halaman dashboard yang berisi daftar katalog produk")
    public void pageShowDashboard() {
        try {
            String expectedUrl = "https://www.saucedemo.com/inventory.html";
            String currentUrl = driver.getCurrentUrl();
            assertEquals(expectedUrl, currentUrl);
        } finally {
            driver.quit();
        }
    }

    @Then("Pengguna mendapatkan pesan error {string}")
    public void cekPesanError(String expectedMessage) {
        try {
            String actualMessage = seleniumHelper.getElementByClassName("error-message-container").getText();
            assertEquals(expectedMessage, actualMessage);
        } finally {
            driver.quit();
        }
    }

    @Then("Login gagal, current screen tetap berada pada halaman login, dan sistem menampilkan pesan error {string}")
    public void check_error_message(String expectedMessage) {
        try {
            String actualMessage = seleniumHelper.getElementByClassName("error-message-container").getText();
            assertEquals(expectedMessage, actualMessage);
        } finally {
            driver.quit();
        }
    }
}