@Workspace
Feature: Workspace
  
  @GetAllMyWorkspaces @Smoke
  Scenario Outline: Get All My Workspaces
    Given An account created in Clockify and x-api-key generated
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And I get the data from my Workspace
    
    Examples:
      | operation | entity    | jsonName         | statusCode |
      | GET       | WORKSPACE | getAllWorkspaces | 200        |