package a2.ppl.tugas.akhir.web.menu;

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

public class MenuStepDefinitions {
    WebDriver driver;
    SeleniumHelper seleniumHelper;
    ConfigReader configReader;

    public MenuStepDefinitions() {
        configReader = new ConfigReader();
    }

    @Given("Pengguna sudah login")
    public void penggunaSudahLogin() {
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

    @And("Pengguna berada pada halaman dashboard")
    public void penggunaBeradaPadaHalamanDashboard() {
        String currentUrl = driver.getCurrentUrl();
        String expected = "https://www.saucedemo.com/inventory.html";
        assertEquals(expected, currentUrl);
    }

    @When("Pengguna membuka menu")
    public void penggunaMembukaMenu() {
        seleniumHelper.clickButtonById("react-burger-menu-btn");

    }

    @And("Pengguna memilih {string}")
    public void penggunaMemilihMenu(String menuOption) {
        String id = "";
        switch (menuOption) {
            case "All Items":
                id = "inventory_sidebar_link";
                break;
            case "About":
                id = "about_sidebar_link";
                break;
            case "Logout":
                id = "logout_sidebar_link";
                break;
            default:
                break;
        }
        String currentUrl = driver.getCurrentUrl();
        String expected = "https://www.saucedemo.com/inventory.html";
        if (id == "inventory_sidebar_link" && currentUrl == expected) {
            driver.quit();
        } else {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
            seleniumHelper.clickButtonById(id);
        }
    }

    @Then("Sistem menampilkan halaman dashboard berisi katalog barang")
    public void sistemMenampilkanHalamanDashboardBerisiKatalogBarang() {
        WebElement product = seleniumHelper.getElementByClassName("title");
        assertEquals("products", product);
    }

    @And("Sistem menampilkan icon cart berjumlah {int}")
    public void sistemMenampilkanIconCartBerjumlah(int jumlah) {
        String cartBadgeText = driver.findElement(By.className("shopping_cart_link")).getText();
        String number = "";
        if (jumlah > 0) {
            number = String.valueOf(jumlah);
        }
        assertEquals(number, cartBadgeText);
    }

    @And("Pengguna berada pada halaman detail produk")
    public void penggunaBeradaPadaHalamanDetailProduk() {
        seleniumHelper.clickButtonById("item_4_title_link");
        assertTrue(seleniumHelper.getElementById("back-to-products").isDisplayed());
    }

    @Then("Sistem keluar dari aplikasi")
    public void sistemKeluarDariAplikasi() {

    }

    @Then("Sistem menampilkan halaman login")
    public void sistemMenampilkanHalamanLogin() {
        String currentUrl = driver.getCurrentUrl();
        String expected = "https://www.saucedemo.com/";
        assertEquals(expected, currentUrl);
        driver.quit();
    }

    @Then("Sistem menampilkan halaman pembuat website swag labs")
    public void sistemMenampilkanHalamanPembuatWebsiteSwagLabs() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String oldUrl = "https://www.saucedemo.com/inventory.html";
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(oldUrl)));
        driver.quit();
    }
}
