package a2.ppl.tugas.akhir.web.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import a2.ppl.tugas.akhir.web.utils.ConfigReader;
import a2.ppl.tugas.akhir.web.utils.SeleniumHelper;

public class LogoutSteps {
    WebDriver driver;
    SeleniumHelper seleniumHelper;
    ConfigReader configReader;

    public LogoutSteps() {
        configReader = new ConfigReader();
    }

    @Given("Pengguna sudah berhasil mengakses website Swaglabs pada browser")
    public void access_url() {
        String url = "https://www.saucedemo.com";
        System.setProperty("webdriver.chrome.driver", configReader.getProperty("webdriver.chrome.driver"));
        driver = new ChromeDriver();
        seleniumHelper = new SeleniumHelper(driver);
        driver.get(url);
    }

    @Given("Pengguna berhasil melakukan login dengan username {string} dan password {string}")
    public void the_user_is_logged_in_with_username_and_password(String username, String password) {

        // Perform login if needed
        if (!seleniumHelper.isElementDisplayedById("login-button")) {
            // Already logged in, no need to perform login again
            return;
        }

        // Perform login
        seleniumHelper.setInputByName("user-name", username);
        seleniumHelper.setInputByName("password", password);
        seleniumHelper.clickButtonById("login-button");
    }

    @Given("Pengguna berada pada halaman dashboard")
    public void checkIsStillInLoginPage() {
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, currentUrl);
    }

    @When("Pengguna menekan tombol logout")
    public void clickLogoutButton() {
        seleniumHelper.clickElementById("react-burger-menu-btn");
        seleniumHelper.clickElementById("logout_sidebar_link");

    }

    @When("click element {string}")
    public void click_element(String elementId) {
        seleniumHelper.clickElementById(elementId);
    }

    @When("click element with id {string}")
    public void click_element_with_id(String elementId) {
        seleniumHelper.clickElementById(elementId);
    }

    @When("logout via url {string}")
    public void access_logout_link(String url) {
        driver.get(url);
    }

    @Then("Logout berhasil, sistem menampilkan halaman login")
    public void check_if_login_button_is_displayed() {
        String elementId = "login-button";
        try {
            assertTrue(seleniumHelper.isElementDisplayedById(elementId));
        } finally {
            driver.quit();
        }
    }
}
