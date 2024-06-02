package a2.ppl.tugas.akhir.api.utils;

import io.restassured.path.json.JsonPath;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPathHandler<T> {

    public JsonPathHandler() {
        // Constructor with no arguments
    }

    // Method to s erialize an object to JsonPath
    public JsonPath toJsonPath(T object) {
        String jsonString = toJsonString(object);
        return new JsonPath(jsonString);
    }

    // Method to deseriaze

    public T fromJsonToObject(String json, Class<T> objectType) {
        JsonPath jsonPath = new JsonPath(json);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonPath.prettify(), objectType);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
            return null;
        }
    }

    // Method to convert an object to JSON string using ObjectMapper
    private String toJsonString(T object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
            return null;
        }
    }
}