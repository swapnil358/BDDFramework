package stepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiSteps {

    private Response response;
    private RequestSpecification request;

    @Given("I perform a GET request to {string}")
    public void iPerformAGETRequestTo(String url) {
        RestAssured.baseURI = url;
        request = RestAssured.given();
        response = request.get();
    }

    @Then("I validate the response includes {string}, {string}, and all headers")
    public void iValidateTheResponseIncludesAndAllHeaders(String path, String ip) {
        Assert.assertNotNull("Path is missing in response", response.jsonPath().getString("path"));
        Assert.assertNotNull("IP is missing in response", response.jsonPath().getString("ip"));


        List<Header> headerList = response.getHeaders().asList();
        Map<String, String> headers = new HashMap<String, String>();


        for (io.restassured.http.Header header : headerList) {
            headers.put(header.getName(), header.getValue());
        }


        Assert.assertFalse("Headers are empty in response", headers.isEmpty());

    }

    @Given("I perform a POST request to {string} with the payload")
    public void iPerformAPOSTRequestToWithThePayload(String url, Map<String, String> data) {
        String payload = "{\n" +
                "  \"order_id\": \"" + data.get("order_id") + "\",\n" +
                "  \"customer\": {\n" +
                "    \"name\": \"" + data.get("customer_name") + "\",\n" +
                "    \"email\": \"" + data.get("email") + "\",\n" +
                "    \"phone\": \"" + data.get("phone") + "\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"" + data.get("address").split(",")[0] + "\",\n" +
                "      \"city\": \"" + data.get("address").split(",")[1].trim() + "\",\n" +
                "      \"state\": \"" + data.get("address").split(",")[2].trim() + "\",\n" +
                "      \"zipcode\": \"" + data.get("address").split(",")[3].trim() + "\",\n" +
                "      \"country\": \"" + data.get("address").split(",")[4].trim() + "\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"product_id\": \"A101\",\n" +
                "      \"name\": \"" + data.get("product").split(",")[0].trim() + "\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"price\": 79.99\n" +
                "    },\n" +
                "    {\n" +
                "      \"product_id\": \"B202\",\n" +
                "      \"name\": \"" + data.get("product").split(",")[1].trim() + "\",\n" +
                "      \"quantity\": 2,\n" +
                "      \"price\": 15.99\n" +
                "    }\n" +
                "  ],\n" +
                "  \"payment\": {\n" +
                "    \"method\": \"" + data.get("payment").split(",")[0].trim() + "\",\n" +
                "    \"transaction_id\": \"" + data.get("payment").split(",")[1].trim() + "\",\n" +
                "    \"amount\": " + data.get("payment").split(",")[2].trim() + ",\n" +
                "    \"currency\": \"" + data.get("payment").split(",")[3].trim() + "\"\n" +
                "  },\n" +
                "  \"shipping\": {\n" +
                "    \"method\": \"" + data.get("shipping").split(",")[0].trim() + "\",\n" +
                "    \"cost\": " + data.get("shipping").split(",")[1].trim() + ",\n" +
                "    \"estimated_delivery\": \"" + data.get("shipping").split(",")[2].trim() + "\"\n" +
                "  },\n" +
                "  \"order_status\": \"" + data.get("order_status") + "\",\n" +
                "  \"created_at\": \"" + data.get("created_at") + "\"\n" +
                "}";

        RestAssured.baseURI = url;
        request = RestAssured.given().header("Content-Type", "application/json").body(payload);
        response = request.post();
    }


    @Given("I perform a POST request to {string} with the payload {string}")
    public void iPerformAPOSTRequestToWithThePayload(String url,  String jsonPayloadFile) {
        try {
            // Read the JSON payload from the file
            String filePath = "src/test/resources/payloadJson/" + jsonPayloadFile;
            String jsonPayload = new String(Files.readAllBytes(Paths.get(filePath)));

            // Perform the POST request with Rest Assured
            response = RestAssured
                    .given()
                    .header("Content-Type", "application/json")
                    .body(jsonPayload)
                    .post(url);

        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Failed to read the JSON payload from the file: " + jsonPayloadFile);
        }

        System.out.println(response.prettyPrint());
    }


    @Then("I validate the accuracy of customer information, payment details, and product information")
    public void iValidateTheAccuracyOfCustomerInformationPaymentDetailsAndProductInformation() {

        Assert.assertEquals("Expected status code 200, but got: " + response.getStatusCode(), 200, response.getStatusCode());

        // Example of validating customer information (you can add more checks as needed)
        String responseBody = response.getBody().asString();
        Assert.assertTrue("Response body does not contain expected customer name", responseBody.contains("Jane Smith"));
        Assert.assertTrue("Response body does not contain expected email", responseBody.contains("janesmith@example.com"));
        Assert.assertTrue("Response body does not contain expected phone number", responseBody.contains("1-987-654-3210"));

        Assert.assertTrue("Response body does not contain expected payment method", responseBody.contains("credit_card"));
        Assert.assertTrue("Response body does not contain expected transaction ID", responseBody.contains("txn_67890"));
        Assert.assertTrue("Response body does not contain expected amount", responseBody.contains("111.97"));

        Assert.assertTrue("Response body does not contain expected product name", responseBody.contains("Wireless Headphones"));
        Assert.assertTrue("Response body does not contain expected product name", responseBody.contains("Smartphone Case"));
    }


    @Given("I perform a POST request to {string}")
    public void iPerformAPOSTRequestTo(String url) {
        // Initialize the URL for the POST request (this will be used later)
        RestAssured.baseURI = url;
    }

    @Given("I load the payload from {string} and update the following fields:")
    public void iLoadThePayloadFromAndUpdateTheFollowingFields(String jsonPayloadFile, DataTable dataTable) {
        try {
            // Read the JSON payload from the file
            String filePath = "src/test/resources/payloadJson/" + jsonPayloadFile;
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode jsonPayload = (ObjectNode) objectMapper.readTree(new File(filePath));

            // Update the JSON payload with values from the data table
            Map<String, String> updates = dataTable.asMap(String.class, String.class);
            for (Map.Entry<String, String> entry : updates.entrySet()) {
                String field = entry.getKey();
                String value = entry.getValue();
                jsonPayload.put(field, value); // Update the field with the new value
            }

            // Convert the updated JSON back to a string
            String updatedJsonPayload = objectMapper.writeValueAsString(jsonPayload);

            // Perform the POST request with Rest Assured
            response = RestAssured
                    .given()
                    .header("Content-Type", "application/json")
                    .body(updatedJsonPayload)
                    .post();
            System.out.println(response.prettyPrint());

        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Failed to read or update the JSON payload from the file: " + jsonPayloadFile);
        }
    }

}
