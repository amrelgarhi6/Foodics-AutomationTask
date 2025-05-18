@Regression   @Regression_API
Feature:  Validate CRU [Create - Read - Update] for Users endpoint


  @CreateUser_Valid_Request
  Scenario: Validate that the Post /users Endpoint create a new user when sending a valid request.
    Given the User invokes the Post-user API with valid successfully.
    Then API response status code should be SC_CREATED.
    And API response payload should contain a [Name - job - id - createdDate] objects

  @GetUser_Valid_Request
  Scenario: Validate that the Get /users with specific {ID} Endpoint get this user when sending a valid request.
    Given the User invokes the Get-user API with valid successfully.
    Then API response status code should be SC_OK.
    And API response payload should contain a [Data - Support] objects and its main data

  @UpdateUser_Valid_Request
  Scenario: Validate that the Put /users Endpoint create a new user when sending a valid request.
    Given the User invokes the Put-user API with valid successfully.
    Then API response status code should be SC_OK.
    And API response payload should be updated with new objects [Name - job - id - createdDate]

