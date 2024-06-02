package a2.ppl.tugas.akhir.api.stepsDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import a2.ppl.tugas.akhir.api.utils.ApiClient;
import a2.ppl.tugas.akhir.api.utils.CustomAssertionError;
import a2.ppl.tugas.akhir.api.utils.ErrorChecker;
import a2.ppl.tugas.akhir.api.utils.JsonPathHandler;
import a2.ppl.tugas.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUserSteps {
    private Map<String, String> headers = new HashMap<>();
    private String jsonBody = "{}";
    private Response response;
    private CustomAssertionError listError = new CustomAssertionError();

    @Given("Menambahkan app-id yang valid pada header request {string}")
    public void setHeader(String appId) {
        headers.put("app-id", appId);
    }

    @When("Menghapus header request {string}")
    public void deleteHeader(String key) {
        headers.remove(key);
    }

    @When("mengisi request body dengan json valid")
    public void setRequestBody(String body) {
        this.jsonBody = body;
    }

    @When("Mengirim request ke {string} dengan method POST")
    public void sendRequest(String url) {
        ApiClient client = new ApiClient(url, headers);
        this.response = client.post(url, jsonBody);
    }

    @Then("Menerima status code {int}")
    public void checkStatusCode(Integer expectedStatusCode) {
        try {
            assertEquals(expectedStatusCode, response.statusCode());
        } catch (Exception e) {
            listError.appendMessage(e.toString());
        }
    }

    @Then("Menerima response body json user yang telah dibuat")
    public void checkResponseBody(String body) {
        JsonPathHandler<User> jsonHandler = new JsonPathHandler<User>();
        User expectedUser = jsonHandler.fromJsonToObject(jsonBody, User.class);
        listError.appendMessages(ErrorChecker.checkResponse(expectedUser, response));
        if (listError.getMessage().length() > 0) {
            throw listError;
        }
    }

    @Then("Menerima response body json dengan key {string} value {string}")
    public void checkErrorBody(String key, String value) {
        JsonPath json = response.jsonPath();
        String actualValue = json.getString(key);
        assertEquals(actualValue, value);
    }
}
