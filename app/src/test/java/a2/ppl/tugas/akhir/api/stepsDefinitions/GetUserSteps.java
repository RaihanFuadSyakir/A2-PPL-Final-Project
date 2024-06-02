package a2.ppl.tugas.akhir.api.stepsDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import a2.ppl.tugas.akhir.api.utils.ApiClient;
import a2.ppl.tugas.akhir.api.utils.CustomAssertionError;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetUserSteps {
    private Map<String, String> headers = new HashMap<>();
    private Response response;
    private CustomAssertionError listError = new CustomAssertionError();

    public void sendError() {
        if (listError.getMessage().length() > 0) {
            throw listError;
        }
    }

    @Given("Menambahkan app-id yang tidak terdaftar pada header request {string}")
    public void setUnregisteredAppId(String appId) {
        headers.put("app-id", appId);
    }

    @Given("Menambahkan app-id yang terdaftar pada header request {string}")
    public void setValidAppId(String appId) {
        headers.put("app-id", appId);
    }

    @When("Kirim req GET ke endpoint {string}")
    public void sendGetRequest(String url) {
        ApiClient client = new ApiClient("", headers);
        Response res = client.get(url);
        this.response = res;
    }

    @Then("Menerima Status Code : {int} {string}")
    public void receiveStatusCode(Integer statusCode, String status) {
        try {
            assertEquals(statusCode, response.statusCode());
        } catch (AssertionError e) {
            listError.appendMessage(e.toString());
        }
    }

    @Then("Meneriman Respond Body : Respond JSON sesuai dengan ID {string}")
    public void receiveResponseBasedId(String expectedId) {
        JsonPath json = response.jsonPath();
        String error = json.getString("error");
        String id = json.getString("id");
        try {
            assertEquals(id, expectedId);
        } catch (AssertionError e) {
            listError.appendMessage(e.toString());
        } catch (Exception e) {
            listError.appendMessage(e.toString());
        }
        if (error != null) {
            listError.appendMessage(error);
        }
        this.sendError();
    }

    @Then("Meneriman Respond Body : {string}")
    public void receiveResponseBody(String value) {
        JsonPath json = response.jsonPath();
        String error = json.getString("error");
        try {
            assertEquals(value, error);
        } catch (AssertionError e) {
            listError.appendMessage(e.toString());
        }
        if (listError.getMessage().length() > 0) {
            throw listError;
        }
        this.sendError();
    }
}
