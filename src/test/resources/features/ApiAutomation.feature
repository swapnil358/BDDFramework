Feature: API Automation using REST Assured

  @API
  Scenario: Validate the response of a GET Request
    Given I perform a GET request to "https://echo.free.beeceptor.com/sample-request?author=beeceptor"
    Then I validate the response includes "path", "ip", and all headers

  @API
  Scenario Outline: Validate the response of a POST Request
#    Given I perform a POST request to "http://echo.free.beeceptor.com/sample-request?author=beeceptor" with the payload
#      | order_id      | 12345             |
#      | customer_name | Jane Smith        |
#      | email         | janesmith@example.com |
#      | phone         | 1-987-654-3210    |
#      | address       | 456 Oak Street, Metropolis, NY, 10001, USA |
#      | product       | Wireless Headphones, Smartphone Case       |
#      | payment       | credit_card, txn_67890, 111.97, USD        |
#      | shipping      | standard, 5.99, 2024-11-15                 |
#      | order_status  | processing         |
#      | created_at    | 2024-11-07T12:00:00Z |
    Given I perform a POST request to "http://echo.free.beeceptor.com/sample-request?author=beeceptor" with the payload "<jsonPayload>"
    Then I validate the accuracy of customer information, payment details, and product information
    Examples:
      | jsonPayload  |
      | payload.json |


  @API
  Scenario Outline: Perform a POST request with updated JSON payload fields
    Given I perform a POST request to "http://echo.free.beeceptor.com/sample-request?author=beeceptor"
    And I load the payload from "<jsonPayload>" and update the following fields:
      | field           | value              |
      | order_id        | 67890              |
      | customer_name   | John Doe           |
      | email           | johndoe@example.com|
      | phone           | 1-123-456-7890     |
      | order_status    | completed          |
    Then I validate the accuracy of customer information, payment details, and product information

    @payload
    Examples:
      | jsonPayload  |
      | payload.json |

    @payload1
    Examples:
      | jsonPayload   |
      | payload2.json |


