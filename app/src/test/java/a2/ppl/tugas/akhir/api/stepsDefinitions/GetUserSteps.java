package a2.ppl.tugas.akhir.api.stepsDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import a2.ppl.tugas.akhir.api.utils.ApiClient;
import a2.ppl.tugas.akhir.api.utils.CustomAssertionError;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetUserSteps {
    private Map<String, String> headers = new HashMap<>();
    private String jsonBody = "{}";
    private Response response;
    private String baseUrl = "https://dummyapi.io/data/v1/";
    private CustomAssertionError listError = new CustomAssertionError();

    @Given("Menambahkan app-id yang tidak terdaftar pada header request {string}")
    void setUnregisteredAppId(String appId) {
        headers.put("app-id", appId);
    }

    @Given("Given Menambahkan app-id yang terdaftar pada header request {string}")
    void setValidAppId(String appId) {
        headers.put("app-id", appId);
    }

    @When("When Kirim req GET ke endpoint {string}")
    void sendGetRequest(String url) {
        ApiClient client = new ApiClient(url);
        Response res = client.get(url);
        this.response = res;
    }

    @Then("Then Menerima Status Code : {int}")
    void receiveStatusCode(Integer statusCode) {
        try {
            assertEquals(response.statusCode(), statusCode);
        } catch (AssertionError e) {
            listError.appendMessage(e.toString());
        }
    }

    @Then("And Meneriman Respond Body : Respond JSON sesuai dengan ID")
    void receiveResponseBasedId() {
        JsonPath json = response.jsonPath();
        String id = json.getString("id");
        try {

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Then("Meneriman Respond Body : {string}")
    void receiveResponseBody(String value) {
        JsonPath json = response.jsonPath();
        String error = json.getString("error");
        try {
            assertEquals(error, value);
        } catch (AssertionError e) {
            listError.appendMessage(e.toString());
        }
        if (listError.getMessage().length() > 0) {
            throw listError;
        }
    }
}
