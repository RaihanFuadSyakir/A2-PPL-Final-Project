package a2.ppl.tugas.akhir.api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;
import com.fasterxml.jackson.databind.ObjectMapper;

import a2.ppl.tugas.model.User;

import java.util.Map;

public class ApiClient {
    private final String baseUrl;
    private final Map<String, String> headers;
    private RequestSpecification requestBuilder;

    public ApiClient(String baseUrl, Map<String, String> headers) {
        this.baseUrl = baseUrl;
        this.headers = headers;
        this.requestBuilder = configureGiven();
    }

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.headers = null;
    }

    private RequestSpecification configureGiven() {
        RequestSpecification requestSpecification = RestAssured.given()
                .contentType("application/json")
                .baseUri(this.baseUrl);
        if (headers != null) {
            requestSpecification.headers(headers);
        }
        return requestSpecification;
    }

    // Method to perform a POST request
    public Response post(String url, String body) {
        return this.requestBuilder
                .body(body)
                .post(url);
    }

    // Method to perform a GET request
    public Response get(String url) {
        return this.requestBuilder.get(url);
    }

    // Method to perform a DELETE request
    public Response delete(String url) {
        return this.requestBuilder.delete(url);
    }

    // Method to perform a PUT request
    public Response put(String url, String body) {
        return this.requestBuilder
                .body(body)
                .put(url);
    }

    // Method to convert a Person object to JSON string using ObjectMapper
    private String toJsonString(User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(user);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
            return null;
        }
    }

    public JsonPath createJsonStructure(User user) {
        String jsonString = toJsonString(user);
        return new JsonPath(jsonString);
    }

}
