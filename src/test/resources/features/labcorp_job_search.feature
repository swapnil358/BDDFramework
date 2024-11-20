Feature: Job Search on LabCorp Website

  @SmokeTest @UI
  Scenario Outline: Browse to Careers and Verify Job Listing
    Given I am on the LabCorp homepage
    When I navigate to the Careers page
    And I search for a job listing with title "<Role>"
    And I select the job with title "<Role>"
    And I verify job details
      | job id       | 2438897       |
      | job title    | AEM Developer |
      | job location | Bangalore      |
    And I verify job requirement
    |Design and develop high-quality AEM components, templates, and workflows based on business requirements and technical specifications.|
    |Participate in Agile development processes, including sprint planning, daily stand-ups, and retrospectives.                          |
    |Conduct thorough code reviews to ensure adherence to coding standards, best practices, and security guidelines.                      |
    And I click on Apply Job
    And Login to workday
      | username         | password     |
      | swap@yopmail.com | Qwerty@12345 |
    And I verify job title
      | job title | AEM Developer |
    And I navigate back to job page

    Examples:
      | Role |
      |AEM Developer|
