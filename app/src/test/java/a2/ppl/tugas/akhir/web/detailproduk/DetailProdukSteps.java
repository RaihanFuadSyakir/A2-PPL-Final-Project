package a2.ppl.tugas.akhir.web.detailproduk;

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

public class DetailProdukSteps {
    WebDriver driver;
    SeleniumHelper seleniumHelper;
    ConfigReader configReader;

    public DetailProdukSteps() {
        configReader = new ConfigReader();
    }

    @Given("Pengguna berhasil login ke dalam aplikasi")
    public void penggunaBerhasilLoginKeDalamAplikasi() {
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

    @And("Pengguna berhasil mengakses halaman Dashboard aplikasi")
    public void penggunaBerhasilMengaksesHalamanDashboardAplikasi() {
        String currentUrl = driver.getCurrentUrl();
        String expected = "https://www.saucedemo.com/inventory.html";
        assertEquals(expected, currentUrl);
    }

    @When("Pengguna klik gambar Produk {string} pada halaman Dashboard")
    public void penggunaKlikGambarPadaHalamanDashboard(String productName) {
        driver.findElement(By.xpath("//img[@alt='" + productName + "']")).click();
    }

    @Then("Sistem menampilkan Gambar, Qty, Description, Nama, Harga Satuan, dan button \\(Add to cart atau Remove) dari barang pada halaman Detail Produk")
    public void sistemMenampilkanDetailProduk() {
        assertTrue(driver.findElement(By.className("inventory_details_img")).isDisplayed());
        assertTrue(driver.findElement(By.className("inventory_details_quantity")).isDisplayed());
        assertTrue(driver.findElement(By.className("inventory_details_desc")).isDisplayed());
        assertTrue(driver.findElement(By.className("inventory_details_name")).isDisplayed());
        assertTrue(driver.findElement(By.className("inventory_details_price")).isDisplayed());
        assertTrue(driver.findElement(By.className("btn_inventory")).isDisplayed());
    }

    @And("Pengguna berhasil mengakses halaman Detail Produk aplikasi")
    public void penggunaBerhasilMengaksesHalamanDetailProdukAplikasi() {
        seleniumHelper.clickButtonById("item_4_title_link");
        assertTrue(driver.findElement(By.className("inventory_details")).isDisplayed());
    }

    @When("Pengguna klik Back To Product pada halaman Detail Produk")
    public void penggunaKlikBackToProductPadaHalamanDetailProduk() {

        seleniumHelper.clickButtonById("back-to-products");
    }

    @Then("Sistem berpindah ke halaman Dashboard")
    public void sistemBerpindahKeHalamanDashboard() {
        String currentUrl = driver.getCurrentUrl();
        String expected = "https://www.saucedemo.com/inventory.html";
        assertEquals(expected, currentUrl);
    }

    @And("Sistem menampilkan daftar catalog produk")
    public void sistemMenampilkanDaftarCatalogProduk() {
        WebElement product = seleniumHelper.getElementByClassName("title");
        try {
            assertEquals("Products", product.getText());
        } finally {
            driver.quit();
        }
    }

    @When("Pengguna klik gambar Cart pada halaman Detail Produk")
    public void penggunaKlikGambarCartPadaHalamanDetailProduk() {
        seleniumHelper.clickButtonById("shopping_cart_container");
    }

    @Then("Sistem berpindah ke halaman Cart")
    public void sistemBerpindahKeHalamanCart() {
        String currentUrl = driver.getCurrentUrl();
        String expected = "https://www.saucedemo.com/cart.html";
        assertEquals(expected, currentUrl);
    }

    @And("Sistem menampilkan daftar barang yang telah ditambahkan jika ada atau menampilkan halaman kosong jika tidak terdapat barang")
    public void sistemMenampilkanDaftarBarangAtauHalamanKosong() {
        assertEquals(true, seleniumHelper.isElementDisplayedByClassName("cart_list"));
    }
}
