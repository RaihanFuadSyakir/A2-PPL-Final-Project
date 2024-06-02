package a2.ppl.tugas.akhir.web.stepDefinitions;

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
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.time.Duration;

public class CartSteps {
    WebDriver driver;
    SeleniumHelper seleniumHelper;
    ConfigReader configReader;

    @Given("Pengguna berhasil melakukan login ke dalam aplikasi")
    public void penggunaBerhasilMelakukanLoginKeDalamAplikasi() {
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

    @And("Barang sudah ditambahkan ke dalam Cart")
    public void barangSudahDitambahkanKeDalamCart() {
        addItem("add to cart", "Sauce Labs Backpack");
    }

    public void addItem(String buttonText, String productName) {
        productName = productName.toLowerCase();
        String combined = buttonText + " " + productName;
        combined = combined.replace(' ', '-');
        seleniumHelper.clickButtonByName(combined);
    }

    @When("Pengguna klik gambar Cart pada halaman Dashboard untuk berpindah ke halaman Cart")
    public void penggunaKlikGambarCartPadaHalamanDashboardUntukBerpindahKeHalamanCart() {
        driver.findElement(By.id("cartIcon")).click();
        WebElement cart = seleniumHelper.getElementByClassName("shopping_cart_badge");
        cart.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_list")));
    }

    @Then("Sistem menampilkan daftar barang yang berisi:")
    public void sistemMenampilkanDaftarBarangYangBerisi(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> columns : rows) {
            String qty = columns.get(0);
            String description = columns.get(1);
            String name = columns.get(2);
            String hargaSatuan = columns.get(3);
            String removeButton = columns.get(4);

            WebElement cartItem = driver.findElement(By.id("cart_item"));
            assertEquals(qty, cartItem.findElement(By.className("cart_quantity")).getText());
            assertEquals(description, cartItem.findElement(By.className("inventory_item_desc")).getText());
            assertEquals(name, cartItem.findElement(By.className("inventory_item_name")).getText());
            assertEquals(hargaSatuan, cartItem.findElement(By.className("inventory_item_price")).getText());
            assertEquals(removeButton, cartItem.findElement(By.className("removeButton")).getText());
        }
    }

    @And("Sistem menampilkan button Continue Shopping")
    public void sistemMenampilkanButtonContinueShopping() {
        assertTrue(driver.findElement(By.id("continue-shopping")).isDisplayed());
    }

    @And("Sistem menampilkan button Checkout")
    public void sistemMenampilkanButtonCheckout() {
        assertTrue(driver.findElement(By.id("checkout")).isDisplayed());
    }

    @Then("Sistem menampilkan daftar Qty dan Description kosong")
    public void sistemMenampilkanDaftarQtyDanDescriptionKosong() {
        List<WebElement> cartItem = driver.findElements(By.className("cart_item"));
        assertTrue(cartItem.isEmpty());
    }

    @When("Pengguna klik button Continue Shopping")
    public void penggunaKlikButtonContinueShopping() {
        driver.findElement(By.id("continue-shopping")).click();
    }

    @Then("Sistem berpindah ke halaman Dashboard")
    public void sistemBerpindahKeHalamanDashboard() {
        String currentUrl = driver.getCurrentUrl();
        String expected = "https://www.saucedemo.com/inventory.html";
        assertEquals(expected, currentUrl);
    }

    @And("Sistem menampilkan daftar katalog produk")
    public void sistemMenampilkanDaftarKatalogProduk() {
        WebElement product = seleniumHelper.getElementByClassName("title");
        assertEquals("title", product.getText());
    }
}
